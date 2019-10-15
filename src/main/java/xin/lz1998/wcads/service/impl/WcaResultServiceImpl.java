package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.repository.WcaResultRepository;
import xin.lz1998.wcads.service.WcaResultService;

@Service
public class WcaResultServiceImpl implements WcaResultService {
    @Autowired
    WcaResultRepository wcaResultRepository;


    public Page findResultsByPersonIdAndEventId(String personId, String eventId, Pageable pageable) {
        return wcaResultRepository.findWcaResultsByPersonIdAndEventId(personId, eventId, pageable);
    }

    @Override
    public Page findWcaResultsByPersonId(String personId, Pageable pageable) {
        return wcaResultRepository.findWcaResultsByPersonId(personId,pageable);
    }
}
