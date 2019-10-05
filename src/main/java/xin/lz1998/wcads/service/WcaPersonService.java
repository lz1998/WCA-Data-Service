package xin.lz1998.wcads.service;

import xin.lz1998.wcads.entity.WcaPerson;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;


public interface WcaPersonService {
    public void importData() ;
    public WcaPerson findPersonById(String id);
    public List<WcaPerson> findPeopleByNameContaining(String name);
    public List<WcaPerson> searchPeople(List<String> keywords);
}
