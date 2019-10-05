package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.lz1998.wcads.Config;
import xin.lz1998.wcads.entity.WcaRankSingle;
import xin.lz1998.wcads.repository.WcaRankSingleRepository;
import xin.lz1998.wcads.service.WcaRankSingleService;
import xin.lz1998.wcads.utils.DataImportUtil;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@Service
public class WcaRankSingleServiceImpl implements WcaRankSingleService {
    @Autowired
    private WcaRankSingleRepository wcaRankSingleRepository;
    private static final String FILENAME="WCA_export_RanksSingle.tsv";
    @Transactional
    @Override
    public void importData() {
        String filepath= Config.getWcaExtractPath()+FILENAME;
        DataImportUtil.importData(filepath,wcaRankSingleRepository,WcaRankSingle.class);
    }

    @Override
    public List<WcaRankSingle> findWcaRankSinglesByPersonId(String personId) {
        return wcaRankSingleRepository.findWcaRankSinglesByPersonId(personId);
    }

    @Override
    public WcaRankSingle findWcaRankSingleByPersonIdAndEventId(String personId, String eventId) {
        return wcaRankSingleRepository.findWcaRankSingleByPersonIdAndEventId(personId,eventId);
    }
}
