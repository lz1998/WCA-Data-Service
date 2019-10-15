package xin.lz1998.wcads.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.Config;
import xin.lz1998.wcads.entity.*;
import xin.lz1998.wcads.repository.*;
import xin.lz1998.wcads.service.*;
import xin.lz1998.wcads.utils.DataImportUtil;
import xin.lz1998.wcads.utils.DownloadUtil;
import xin.lz1998.wcads.utils.UnZipUtil;

import java.io.File;

@Service
public class WcaServiceImpl implements WcaService {
    @Autowired
    WcaPersonRepository wcaPersonRepository;

    @Autowired
    WcaCompetitionRepository wcaCompetitionRepository;

    @Autowired
    WcaRankAverageRepository wcaRankAverageRepository;

    @Autowired
    WcaRankSingleRepository wcaRankSingleRepository;

    @Autowired
    WcaResultRepository wcaResultRepository;
    @Autowired
    DataImportUtil dataImportUtil;

    private static final String PERSONS_FILE = "WCA_export_Persons.tsv";
    private static final String COMPETITIONS_FILE = "WCA_export_Competitions.tsv";
    private static final String RANKS_AVERAGE_FILE = "WCA_export_RanksAverage.tsv";
    private static final String RANKS_SINGLE_FILE = "WCA_export_RanksSingle.tsv";
    private static final String RESULTS_FILE = "WCA_export_Results.tsv";

    private Logger logger =LoggerFactory.getLogger(WcaServiceImpl.class);

    @Override
    public void updateData(){
        DownloadUtil.download(Config.getWcaExportUrl(), Config.getWcaExportZip(), new DownloadUtil.OnDownloadListener() {
            private int percent=-1;
            @Override
            public void onDownloadSuccess(File file) {
                extractData();
                importData();
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
    public void downloadData(){
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
    public void extractData(){
        UnZipUtil.unzip(Config.getWcaExportZip(),Config.getWcaExtractPath());
    }

    @Override
    public void importData() {
        importPersons();
        importCompetitions();
        importRanksAverage();
        importRanksSingle();
//        importResults();
        // TODO 这里可以导入其他数据，但是不常用，为了避免占内存就没写
    }

    @Override
    public void importPersons() {
        String filepath = Config.getWcaExtractPath() + PERSONS_FILE;
        dataImportUtil.importData(filepath, wcaPersonRepository, WcaPerson.class);
    }

    @Override
    public void importCompetitions() {
        String filepath = Config.getWcaExtractPath() + COMPETITIONS_FILE;
        dataImportUtil.importData(filepath, wcaCompetitionRepository, WcaCompetition.class);
    }

    @Override
    public void importRanksAverage() {
        String filepath= Config.getWcaExtractPath()+RANKS_AVERAGE_FILE;
        dataImportUtil.importData(filepath, wcaRankAverageRepository, WcaRankAverage.class);
    }

    @Override
    public void importRanksSingle() {
        String filepath= Config.getWcaExtractPath()+RANKS_SINGLE_FILE;
        dataImportUtil.importData(filepath, wcaRankSingleRepository, WcaRankSingle.class);
    }

    @Override
    public void importResults() {
        String filepath = Config.getWcaExtractPath() + RESULTS_FILE;
        dataImportUtil.importData(filepath, wcaResultRepository, WcaResult.class);
    }
}
