package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.lz1998.wcads.Config;
import xin.lz1998.wcads.entity.WcaCompetition;
import xin.lz1998.wcads.repository.WcaCompetitionRepository;
import xin.lz1998.wcads.service.WcaCompetitionService;
import xin.lz1998.wcads.utils.DataImportUtil;

@Service
public class WcaCompetitionServiceImpl implements WcaCompetitionService {
    @Autowired
    WcaCompetitionRepository wcaCompetitionRepository;

    private static final String FILENAME = "WCA_export_Competitions.tsv";

    @Transactional
    @Override
    public void importData() {
        String filepath = Config.getWcaExtractPath() + FILENAME;
        DataImportUtil.importData(filepath, wcaCompetitionRepository, WcaCompetition.class);
    }
}
