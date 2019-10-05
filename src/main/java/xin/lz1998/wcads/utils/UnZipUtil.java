package xin.lz1998.wcads.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;


public class UnZipUtil {
    public static void unzip(String filename, String outpath){
        try {
            ZipFile zipFile = new ZipFile(filename);
            zipFile.extractAll(outpath);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
