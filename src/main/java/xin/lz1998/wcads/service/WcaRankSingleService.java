package xin.lz1998.wcads.service;

import xin.lz1998.wcads.entity.WcaRankSingle;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface WcaRankSingleService {
    public void importData();

    public List<WcaRankSingle> findBestResultsByPersonId(String personId);
    public WcaRankSingle findBestResultsByPersonIdAndEventId(String personId, String eventId);
}