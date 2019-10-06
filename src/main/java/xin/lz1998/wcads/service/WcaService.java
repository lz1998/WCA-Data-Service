package xin.lz1998.wcads.service;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface WcaService {
    void downloadWcaData();
    void extractWcaData();
    void importWcaData();
    void updateWcaData();
}
