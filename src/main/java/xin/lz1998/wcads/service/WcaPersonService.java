package xin.lz1998.wcads.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xin.lz1998.wcads.entity.WcaPerson;

import java.util.List;


public interface WcaPersonService {
    WcaPerson findPersonById(String id);

    Page searchPeople(List<String> keywords, Pageable pageable);
}
