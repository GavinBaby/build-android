package com.anuode.common.net.thrift;
import com.anuode.common.lang.Reflector;
import org.apache.thrift.async.AsyncMethodCallback;

public abstract class TAsyncMethodResult<TResult> implements AsyncMethodCallback {

    @Override
    public void onComplete(Object t) {
        try {
            @SuppressWarnings("unchecked")
            TResult result = (TResult) Reflector.invokeMethod(t.getClass(), t, "getResult");
            onResult(null, result);
        } catch (Exception e) {
            onResult(e, null);
        }
    }
    @Override
    public void onError(Exception e) {
        onResult(e, null);
    }

    public void onResult(Exception e, TResult result) {  }
}
