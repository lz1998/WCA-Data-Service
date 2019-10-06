package xin.lz1998.wcads.service;

import xin.lz1998.wcads.entity.WcaResult;

import java.util.List;

public interface WcaResultService {
    void importData() ;
    List<WcaResult> findResultsByPersonIdAndEventId(String personId, String eventId);
}
