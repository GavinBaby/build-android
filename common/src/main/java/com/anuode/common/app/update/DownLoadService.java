package com.anuode.common.app.update;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownLoadService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public DownLoadService() {
        super("com.txjc.app.util.DownLoadService");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        try {
            String url = intent.getStringExtra("path");
            downLoad(url, "txjc.apk", Environment.getExternalStorageDirectory().getPath(), new DownLoadListener() {
                @Override
                public void onStart() {
                    Intent mIntent = new Intent("com.txjc.app.download");
                    mIntent.putExtra("start", "start");
                    sendBroadcast(mIntent);
                }

                @Override
                public void onSeekChange(int seek) {
                    Intent mIntent = new Intent("com.txjc.app.download");
                    mIntent.putExtra("process", seek);
                    sendBroadcast(mIntent);
                }

                @Override
                public void onComplete() {
                    Intent mIntent = new Intent("com.txjc.app.download");
                    mIntent.putExtra("complete", "complete");
                    sendBroadcast(mIntent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downLoad(String url, String fileName, String path, DownLoadListener listener) throws Exception {
        URLConnection conn = new URL(url).openConnection();
        int length = 15388109;
        InputStream in = conn.getInputStream();
        File mFile = new File(path, fileName);
        if (!mFile.exists()) {
            boolean success = mFile.createNewFile();
            if (!success) {
                return;
            }
        }
        FileOutputStream out = new FileOutputStream(mFile);

        byte[] bytes = new byte[4096];

        if (listener != null) {
            listener.onStart();
        }
        int len;
        int seek = 0;
        while ((len = in.read(bytes)) > 0) {
            out.write(bytes, 0, len);
            if (listener != null && seek == 0) {
                listener.onSeekChange(0);
            }
            if (listener != null) {
                seek += len;
                double currentSeek = (double) seek / length * 100;
                listener.onSeekChange((int) currentSeek);
            }
        }
        if (listener != null) {
            listener.onComplete();
        }
        out.close();
        in.close();
    }
}
