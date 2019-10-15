package xin.lz1998.wcads.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xin.lz1998.wcads.entity.WcaRankSingle;

public interface WcaRankSingleService {
    Page findBestResultsByPersonId(String personId, Pageable pageable);

    WcaRankSingle findBestResultsByPersonIdAndEventId(String personId, String eventId);
}
