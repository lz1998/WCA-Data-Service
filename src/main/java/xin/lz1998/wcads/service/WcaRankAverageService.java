package xin.lz1998.wcads.service;

import xin.lz1998.wcads.entity.WcaRankAverage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface WcaRankAverageService {
    void importData();

    List<WcaRankAverage> findBestResultsByPersonId(String personId);
    WcaRankAverage findBestResultByPersonIdAndEventId(String personId, String eventId);
}
