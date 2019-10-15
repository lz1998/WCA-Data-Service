package xin.lz1998.wcads.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xin.lz1998.wcads.entity.WcaRankAverage;

public interface WcaRankAverageService {
    Page findBestResultsByPersonId(String personId, Pageable pageable);

    WcaRankAverage findBestResultByPersonIdAndEventId(String personId, String eventId);
}
