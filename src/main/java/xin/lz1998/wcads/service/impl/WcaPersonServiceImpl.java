package xin.lz1998.wcads.service.impl;

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
    // TODO 分页，对结果包装，status,msg,rst_size,total_size,cur_page,total_page
    @Autowired
    private WcaPersonRepository wcaPersonRepository;

    private static final String FILENAME = "WCA_export_Persons.tsv";

    @Transactional
    @Override
    public void importData() {
        String filepath = Config.getWcaExtractPath() + FILENAME;
        DataImportUtil.importData(filepath, wcaPersonRepository, WcaPerson.class);

    }

    @Override
    public WcaPerson findPersonById(String id) {
        return wcaPersonRepository.findWcaPersonById(id);
    }


    // 关键词出现在名字或ID中
    @Override
    public List<WcaPerson> searchPeople(List<String> keywords) {
        // TODO 暂时只返回100个，以后做分页
        return wcaPersonRepository.searchPeople(keywords,100);
    }
}
