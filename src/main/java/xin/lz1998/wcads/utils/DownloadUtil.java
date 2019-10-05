package xin.lz1998.wcads.utils;

import okhttp3.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

// https://blog.csdn.net/qq_17058993/article/details/80505085
public class DownloadUtil {
    // TODO 不确定能不能保证下载成功
    /**
     * @param url          下载连接
     * @param destFileName 下载文件名称
     * @param listener     下载监听
     */
    public static void download(final String url, final String destFileName, final OnDownloadListener listener) {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();


        OkHttpClient client = new OkHttpClient();
 
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        //异步请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
                listener.onDownloadFailed(e);
            }
 
            @Override
            public void onResponse(Call call, Response response) throws IOException {
 
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;

                File file = new File(destFileName);
 
                try {
 
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //下载中更新进度条
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    //下载完成
                    listener.onDownloadSuccess(file);
                } catch (Exception e) {
                    listener.onDownloadFailed(e);
                }finally {
 
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
 
                    }
 
                }
 
 
            }
        });
    }
 
 
    public interface OnDownloadListener{
 
        /**
         * 下载成功之后的文件
         */
        void onDownloadSuccess(File file) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, FileNotFoundException;
 
        /**
         * 下载进度
         */
        void onDownloading(int progress);
 
        /**
         * 下载异常信息
         */
 
        void onDownloadFailed(Exception e);
    }
}
