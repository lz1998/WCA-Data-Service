package xin.lz1998.wcads.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;


public class UnZipUtil {
    private static boolean unzipping=false;
    public static void unzip(String filename, String outpath){
        if(unzipping){
            return;
        }
        unzipping=true;
        try {
            ZipFile zipFile = new ZipFile(filename);
            zipFile.extractAll(outpath);
            unzipping=false;
        } catch (ZipException e) {
            e.printStackTrace();
            unzipping=false;
        }
    }
}
