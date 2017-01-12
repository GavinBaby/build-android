package com.anuode.common.net.thrift;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.anuode.common.lang.Reflector;
import com.anuode.common.net.volley.SelfSignSslOkHttpStack;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClient;
import org.apache.thrift.async.TAsyncClientFactory;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.async.TAsyncMethodCall;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Map;

public class TAsyncHttpClientManager extends TAsyncClientManager {

    private final TAsyncHttpTransport mTransport;

    private static Context mContext;
    private static final Map<String, TAsyncClient> mClientsMap = new HashMap<>();
    private static final Map<String, Map<String, String>> mClientsHeaders = new HashMap<>();

    public static void init(Context context) {
        mContext = context;
    }

    public static <T1 extends TAsyncClientFactory, T2 extends TAsyncClient> T2 createClient(String url, Class<T1> clazzClientFactory) {
        try {
            TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
            final TAsyncHttpTransport transport = new TAsyncHttpTransport(url + "?p=1", protocolFactory, mContext);
            TAsyncHttpClientManager clientManager = new TAsyncHttpClientManager(transport);
            TAsyncClientFactory clientFactory = clazzClientFactory.getConstructor(TAsyncClientManager.class, TProtocolFactory.class).newInstance(clientManager, protocolFactory);
            @SuppressWarnings("unchecked")
            T2 client = (T2)clientFactory.getAsyncClient(transport);
            transport.setClient(client);
            String clientName = client.getClass().toString();
            mClientsMap.put(clientName, client);
            Map<String, String> headers = new HashMap<>();
            mClientsHeaders.put(clientName, headers);
            return client;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends TAsyncClient> T getClient(Class<T> clazz) {
        try {
            String clientName = clazz.toString();
            return (T) mClientsMap.get(clientName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getClientHeaders(TAsyncClient client) {
        try {
            String clientName = client.getClass().toString();
            return mClientsHeaders.get(clientName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public TAsyncHttpClientManager(TAsyncHttpTransport transport) throws IOException {
        mTransport = transport;
    }

    @Override
    public void call(TAsyncMethodCall method) throws TException {
        mTransport.flush(method);
    }

    public static class TAsyncHttpTransport extends TNonblockingTransport {
        public String mUri = null;
        private final TProtocolFactory mProtocolFactory;
        private final ByteArrayOutputStream mRequestBuffer = new ByteArrayOutputStream();
        private final Context mContext;
        private RequestQueue mRequestQueue = null;
        private TAsyncClient mClient = null;

        public TAsyncHttpTransport(String uri, TProtocolFactory protocolFactory, Context context) {
            this.mUri = uri;
            mProtocolFactory = protocolFactory;
            mContext = context;
            mRequestQueue = Volley.newRequestQueue(context, new SelfSignSslOkHttpStack());
        }

        public void setClient(TAsyncClient client) {
            this.mClient = client;
        }

        @Override
        public boolean startConnect() throws IOException {
            return true;
        }

        @Override
        public boolean finishConnect() throws IOException {
            return true;
        }

        @Override
        public SelectionKey registerSelector(Selector selector, int i) throws IOException {
            return null;
        }

        @Override
        public int read(ByteBuffer byteBuffer) throws IOException {
            return 0;
        }

        @Override
        public int write(ByteBuffer byteBuffer) throws IOException {
            return 0;
        }

        @Override
        public boolean isOpen() {
            return true;
        }

        @Override
        public void open() throws TTransportException {
        }

        @Override
        public void close() {
        }

        @Override
        public int read(byte[] buf, int off, int len) throws TTransportException {
            return 0;
        }

        @Override
        public void write(byte[] buf, int off, int len) throws TTransportException {
            this.mRequestBuffer.write(buf, off, len);
        }

        public void flush(final TAsyncMethodCall method) {
            mRequestBuffer.reset();
            TProtocol protocol = mProtocolFactory.getProtocol(this);
            Reflector.invokeMethod(TAsyncMethodCall.class, method, "write_args", new Class[]{TProtocol.class}, new Object[]{protocol});
            Reflector.setFieldValue(TAsyncMethodCall.class, method, "state", TAsyncMethodCall.State.RESPONSE_READ);
            final AsyncMethodCallback callback = (AsyncMethodCallback)Reflector.getFieldValue(TAsyncMethodCall.class, method, "callback");

            BytesRequest request = new BytesRequest(
                    Request.Method.POST
                    , mUri
                    , mRequestBuffer.toByteArray(), response -> {
                Reflector.setFieldValue(TAsyncMethodCall.class, method, "frameBuffer", ByteBuffer.wrap(response));
                try {
                    assert callback != null;
                    //noinspection unchecked
                    callback.onComplete(method);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            },
                    error -> {
                        assert callback != null;
                        callback.onError(error);
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = TAsyncHttpClientManager.getClientHeaders(TAsyncHttpTransport.this.mClient);
                    return headers;
                }
            };
            Volley.newRequestQueue(mContext, null);
            mRequestQueue.add(request);
        }
    }

    public static class BytesRequest extends Request<byte[]> {
        private final byte[] mRequestData;
        private final Response.Listener<byte[]> mListener;

        public BytesRequest(int method, String uri, byte[] requestData, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
            super(method, uri, errorListener);
            mRequestData = requestData;
            mListener = listener;
        }

        @Override
        public String getBodyContentType() {
            return "";
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            return mRequestData;
        }

        @Override
        protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
            return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
        }

        @Override
        protected void deliverResponse(byte[] response) {
            mListener.onResponse(response);
        }
    }
}