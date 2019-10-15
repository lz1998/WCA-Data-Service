package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.entity.WcaRankSingle;
import xin.lz1998.wcads.repository.WcaRankSingleRepository;
import xin.lz1998.wcads.service.WcaRankSingleService;

@Service
public class WcaRankSingleServiceImpl implements WcaRankSingleService {
    @Autowired
    private WcaRankSingleRepository wcaRankSingleRepository;


    @Override
    public Page findBestResultsByPersonId(String personId, Pageable pageable) {
        return wcaRankSingleRepository.findWcaRankSinglesByPersonId(personId, pageable);
    }

    @Override
    public WcaRankSingle findBestResultsByPersonIdAndEventId(String personId, String eventId) {
        return wcaRankSingleRepository.findWcaRankSingleByPersonIdAndEventId(personId, eventId);
    }
}
