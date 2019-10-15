package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.entity.WcaRankAverage;
import xin.lz1998.wcads.repository.WcaRankAverageRepository;
import xin.lz1998.wcads.service.WcaRankAverageService;

@Service
public class WcaRankAverageServiceImpl implements WcaRankAverageService {
    @Autowired
    private WcaRankAverageRepository wcaRankAverageRepository;


    @Override
    public Page findBestResultsByPersonId(String personId, Pageable pageable) {
        return wcaRankAverageRepository.findWcaRankAveragesByPersonId(personId, pageable);
    }

    @Override
    public WcaRankAverage findBestResultByPersonIdAndEventId(String personId, String eventId) {
        return wcaRankAverageRepository.findWcaRankAverageByPersonIdAndEventId(personId, eventId);
    }
}
