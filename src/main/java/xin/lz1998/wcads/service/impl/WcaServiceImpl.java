package xin.lz1998.wcads.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.lz1998.wcads.Config;
import xin.lz1998.wcads.service.*;
import xin.lz1998.wcads.utils.DownloadUtil;
import xin.lz1998.wcads.utils.UnZipUtil;

import java.io.File;

@Service
public class WcaServiceImpl implements WcaService {

    @Autowired
    private WcaPersonService wcaPersonService;
    @Autowired
    private WcaRankAverageService wcaRankAverageService;
    @Autowired
    private WcaRankSingleService wcaRankSingleService;
    @Autowired
    private WcaCompetitionService wcaCompetitionService;
    @Autowired
    private WcaResultService wcaResultService;
    private Logger logger =LoggerFactory.getLogger(WcaServiceImpl.class);

    @Transactional
    @Override
    public void importWcaData() {
        wcaPersonService.importData();
        wcaCompetitionService.importData();
        wcaRankAverageService.importData();
        wcaRankSingleService.importData();
        // TODO 这里可以导入其他数据，但是不常用，为了避免占内存就没写
//        wcaResultService.importData();
    }

    @Override
    public void downloadWcaData(){
        DownloadUtil.download(Config.getWcaExportUrl(), Config.getWcaExportZip(), new DownloadUtil.OnDownloadListener() {
            private int percent=-1;
            @Override
            public void onDownloadSuccess(File file) {
                System.out.println(file.toString());
            }

            @Override
            public void onDownloading(int progress) {
                if(progress!=percent){
                    percent=progress;
                    logger.info("{}% downloaded",percent);
                }

            }

            @Override
            public void onDownloadFailed(Exception e) {
                System.out.println(e.toString());
            }
        });
    }
    @Override
    public void extractWcaData(){
        UnZipUtil.unzip(Config.getWcaExportZip(),Config.getWcaExtractPath());
    }
    @Override
    public void updateWcaData(){
        DownloadUtil.download(Config.getWcaExportUrl(), Config.getWcaExportZip(), new DownloadUtil.OnDownloadListener() {
            private int percent=-1;
            @Override
            public void onDownloadSuccess(File file) {
                extractWcaData();
                importWcaData();
            }

            @Override
            public void onDownloading(int progress) {
                if(progress!=percent){
                    percent=progress;
                    logger.info("{}% downloaded",percent);
                }

            }

            @Override
            public void onDownloadFailed(Exception e) {
                System.out.println(e.toString());
            }
        });

    }

}
