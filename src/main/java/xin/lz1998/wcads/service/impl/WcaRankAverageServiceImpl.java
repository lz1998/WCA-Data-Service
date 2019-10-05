package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.lz1998.wcads.Config;
import xin.lz1998.wcads.entity.WcaRankAverage;
import xin.lz1998.wcads.repository.WcaRankAverageRepository;
import xin.lz1998.wcads.service.WcaRankAverageService;
import xin.lz1998.wcads.utils.DataImportUtil;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@Service
public class WcaRankAverageServiceImpl implements WcaRankAverageService {
    @Autowired
    private WcaRankAverageRepository wcaRankAverageRepository;
    private static final String FILENAME="WCA_export_RanksAverage.tsv";
    @Transactional
    @Override
    public void importData() {
        String filepath= Config.getWcaExtractPath()+FILENAME;
        DataImportUtil.importData(filepath,wcaRankAverageRepository,WcaRankAverage.class);
    }

    @Override
    public List<WcaRankAverage> findWcaRankAveragesByPersonId(String personId) {
        return wcaRankAverageRepository.findWcaRankAveragesByPersonId(personId);
    }

    @Override
    public WcaRankAverage findWcaRankAverageByPersonIdAndEventId(String personId, String eventId) {
        return wcaRankAverageRepository.findWcaRankAverageByPersonIdAndEventId(personId,eventId);
    }
}
