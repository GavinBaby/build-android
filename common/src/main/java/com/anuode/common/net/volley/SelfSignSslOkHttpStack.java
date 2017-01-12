package com.anuode.common.net.volley;

import android.content.Context;

import com.android.volley.toolbox.HurlStack;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.Hashtable;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

@SuppressWarnings("WeakerAccess")
public class SelfSignSslOkHttpStack extends HurlStack {

    private OkHttpClient mOkHttpClient;

    private static Map<String, SSLSocketFactory> mSocketFactoryMap = new Hashtable<>();

    private static Context mContext;

    public SelfSignSslOkHttpStack() {
        this(new OkHttpClient());
    }

    @SuppressWarnings("WeakerAccess")
    public SelfSignSslOkHttpStack(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static void addCert(int resId, String password, String host) {
        SSLSocketFactory sslSocketFactory = createSSLSocketFactory(mContext, resId, password);
        if (sslSocketFactory == null) {
            return;
        }
        mSocketFactoryMap.put(host, sslSocketFactory);
    }

    private static SSLSocketFactory createSSLSocketFactory(Context context, int resId, String password) {
        try {
            KeyStore trusted = KeyStore.getInstance("BKS");
            InputStream in = context.getResources().openRawResource(resId);
            trusted.load(in, password.toCharArray());
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trusted);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        if ("https".equals(url.getProtocol()) && mSocketFactoryMap.containsKey(url.getHost())) {
            HttpsURLConnection connection = (HttpsURLConnection) new OkUrlFactory(mOkHttpClient).open(url);
            connection.setSSLSocketFactory(mSocketFactoryMap.get(url.getHost()));
            return connection;
        } else {
            return  new OkUrlFactory(mOkHttpClient).open(url);
        }
    }
}