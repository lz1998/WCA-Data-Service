package xin.lz1998.wcads.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static boolean downloading=false;// TODO 有没有更好的写法
    private static Logger logger= LoggerFactory.getLogger(DownloadUtil.class);
    public static void download(final String url, final String destFileName, final OnDownloadListener listener) {
        if(downloading){
            logger.info("download ignore");
            return;
        }
        downloading=true;// TODO 下载开始，改变状态，下载失败或成功改为false
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

 
        //异步请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
                downloading=false;
                listener.onDownloadFailed(e);
            }
 
            @Override
            public void onResponse(Call call, Response response) {
 
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;

                File file = new File(destFileName);

                ResponseBody responseBody = response.body();
                try {

                    is = responseBody.byteStream();
                    long total = responseBody.contentLength();
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
                    downloading=false;
                    listener.onDownloadSuccess(file);
//                   TODO  A connection to https://www.worldcubeassociation.org/ was leaked. Did you forget to close a response body? To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);

                } catch (Exception e) {
                    downloading=false;
                    listener.onDownloadFailed(e);
                }finally {
                    downloading=false;
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                        if(responseBody!=null){
                            responseBody.close();
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
