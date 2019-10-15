package xin.lz1998.wcads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xin.lz1998.wcads.entity.WcaPerson;
import xin.lz1998.wcads.repository.WcaPersonRepository;
import xin.lz1998.wcads.service.WcaPersonService;

import java.util.List;

@Service
public class WcaPersonServiceImpl implements WcaPersonService {
    @Autowired
    private WcaPersonRepository wcaPersonRepository;


    @Override
    public WcaPerson findPersonById(String id) {
        return wcaPersonRepository.findWcaPersonById(id);
    }


    // 关键词出现在名字或ID中
    @Override
    public Page searchPeople(List<String> keywords, Pageable pageable) {
        return wcaPersonRepository.searchPeople(keywords, pageable);
    }
}
