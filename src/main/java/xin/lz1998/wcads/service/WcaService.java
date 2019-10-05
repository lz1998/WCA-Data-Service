package xin.lz1998.wcads.service;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface WcaService {
    public void downloadWcaData();
    public void extractWcaData();
    public void importWcaData();
    public void updateWcaData();
}
