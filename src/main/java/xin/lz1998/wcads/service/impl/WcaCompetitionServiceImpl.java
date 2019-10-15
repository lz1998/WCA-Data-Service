package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.entity.WcaCompetition;
import xin.lz1998.wcads.repository.WcaCompetitionRepository;
import xin.lz1998.wcads.service.WcaCompetitionService;

@Service
public class WcaCompetitionServiceImpl implements WcaCompetitionService {
    @Autowired
    WcaCompetitionRepository wcaCompetitionRepository;


    @Override
    public WcaCompetition findCompetitionById(String id) {
        return wcaCompetitionRepository.findWcaCompetitionById(id);
    }
}
