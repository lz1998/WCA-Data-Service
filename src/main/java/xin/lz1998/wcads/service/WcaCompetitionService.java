package xin.lz1998.wcads.service;

import xin.lz1998.wcads.entity.WcaCompetition;

public interface WcaCompetitionService {
    void importData() ;
    WcaCompetition findWcaCompetitionById(String id);
}
