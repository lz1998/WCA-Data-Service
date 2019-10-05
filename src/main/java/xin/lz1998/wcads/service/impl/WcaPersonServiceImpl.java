package xin.lz1998.wcads.service.impl;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.lz1998.wcads.Config;
import xin.lz1998.wcads.entity.WcaPerson;
import xin.lz1998.wcads.repository.WcaPersonRepository;
import xin.lz1998.wcads.service.WcaPersonService;
import xin.lz1998.wcads.utils.DataImportUtil;

import java.util.List;

@Service
public class WcaPersonServiceImpl implements WcaPersonService {
    // TODO 分页
    // TODO 根据多个关键字查找
    @Autowired
    private WcaPersonRepository wcaPersonRepository;

    private static final String FILENAME="WCA_export_Persons.tsv";
    @Transactional
    @Override
    public void importData(){
        String filepath= Config.getWcaExtractPath() +FILENAME;
        DataImportUtil.importData(filepath,wcaPersonRepository,WcaPerson.class);

    }

    @Override
    public WcaPerson findWcaPersonById(String id) {
        return wcaPersonRepository.findWcaPersonById(id);
    }


    @Override
    public List<WcaPerson> findWcaPeopleByNameContaining(String name) {
        return wcaPersonRepository.findWcaPeopleByNameContaining(name);
    }
}
