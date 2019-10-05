package xin.lz1998.wcads;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
    private static String WCA_EXPORT_URL;
    private static String PATH;
    private static String WCA_EXPORT_ZIP;
    private static String WCA_EXTRACT_PATH;

    @Value("${wca.export-url}")
    public void setWcaExportUrl(String wcaExportUrl) {
        WCA_EXPORT_URL = wcaExportUrl;
    }

    @Value("${wca.path}")
    public void setPATH(String path) {
        Config.PATH = path;
    }

    @Value("${wca.export-zip}")
    public void setWcaExportZip(String wcaExportZip) {
        WCA_EXPORT_ZIP = wcaExportZip;
    }

    @Value("${wca.extract-path}")
    public void setWcaExtractPath(String wcaExportExtractPath) {
        WCA_EXTRACT_PATH = wcaExportExtractPath;
    }

    public static String getWcaExportUrl() {
        return WCA_EXPORT_URL;
    }

    public static String getPATH() {
        return PATH;
    }

    public static String getWcaExportZip() {
        return PATH + WCA_EXPORT_ZIP;
    }

    public static String getWcaExtractPath() {
        return PATH + WCA_EXTRACT_PATH;
    }
}
