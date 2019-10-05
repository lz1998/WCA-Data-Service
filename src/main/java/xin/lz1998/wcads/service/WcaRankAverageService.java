package xin.lz1998.wcads.service;

import xin.lz1998.wcads.entity.WcaRankAverage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface WcaRankAverageService {
    public void importData();

    public List<WcaRankAverage> findWcaRankAveragesByPersonId(String personId);
    public WcaRankAverage findWcaRankAverageByPersonIdAndEventId(String personId,String eventId);
}
