package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.lz1998.wcads.Config;
import xin.lz1998.wcads.entity.WcaResult;
import xin.lz1998.wcads.repository.WcaResultRepository;
import xin.lz1998.wcads.service.WcaResultService;
import xin.lz1998.wcads.utils.DataImportUtil;

import java.util.List;

@Service
public class WcaResultServiceImpl implements WcaResultService {
    @Autowired
    WcaResultRepository wcaResultRepository;

    private static final String FILENAME = "WCA_export_Results.tsv";

    @Transactional
    @Override
    public void importData() {
        String filepath = Config.getWcaExtractPath() + FILENAME;
        DataImportUtil.importData(filepath, wcaResultRepository, WcaResult.class);
    }

    @Override
    public List<WcaResult> findResultsByPersonIdAndEventId(String personId, String eventId) {
        return wcaResultRepository.findWcaResultsByPersonIdAndEventId(personId,eventId);
    }
}
