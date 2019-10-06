package xin.lz1998.wcads.service;

import xin.lz1998.wcads.entity.WcaPerson;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;


public interface WcaPersonService {
    void importData() ;
    WcaPerson findPersonById(String id);
    List<WcaPerson> findPeopleByNameContaining(String name);
    List<WcaPerson> searchPeople(List<String> keywords);
}
