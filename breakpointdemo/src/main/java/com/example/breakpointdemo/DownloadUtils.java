package com.example.breakpointdemo;

import android.content.Context;
import android.os.AsyncTask;

import com.example.common.DLog;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public final class DownloadUtils {
    private static final String RUNNING = "running";
    private static final String PAUSE = "pause";
    private static final String CANCEL = "cancel";
    private static final String COMPLETE = "complete";

    private static String status;

    public static void startDownload(Context context) {
        download(context);
    }

    public static void pauseDownload() {
        status = PAUSE;
    }

    public static void cancelDownload() {
        status = CANCEL;
    }

    private static void download(final Context context) {
        status = RUNNING;
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                HttpURLConnection httpURLConnection = null;
                RandomAccessFile randomAccessFile = null;
                try {
                    URL url = new URL("https://imtt.dd.qq.com/16891/apk/5353A974225B25C17F58C2DA4D6D25AC.apk?fsname=cn.etouch.ecalendar_7.9.1_807.apk&csr=1bbd");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setRequestMethod("GET");


                    File file = new File(context.getCacheDir() + File.separator + "test.apk");
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    long curLength = file.length();


                    randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(curLength);


                    int contentLength = 0;
                    if (curLength == 0) {
                        contentLength = httpURLConnection.getContentLength();
                    } else {
                        httpURLConnection.setRequestProperty("Range","bytes="+curLength+"-");
                    }
                    DLog.d("contentLength:" + contentLength);
                    InputStream is = httpURLConnection.getInputStream();
                    byte[] buffer = new byte[8192];
                    int len = -1;
                    long finishLen = curLength;
                    while ((len = is.read(buffer)) != -1) {
                        randomAccessFile.write(buffer);
                        finishLen += len;
                        DLog.d("finishLen:" + finishLen);
                        if (PAUSE.equals(status)) {
                            return null;
                        }

                        if (CANCEL.equals(status)) {
                            file.delete();
                            return null;
                        }
                    }
                } catch (Exception e) {
                    DLog.d("e:" + e.getMessage());
                }


                return null;
            }
        }.execute();
    }
}
