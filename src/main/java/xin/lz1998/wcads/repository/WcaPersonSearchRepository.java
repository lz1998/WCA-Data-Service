package xin.lz1998.wcads.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xin.lz1998.wcads.entity.WcaPerson;

import java.util.List;

public interface WcaPersonSearchRepository {
    // 关键词出现在名字或ID中
    Page<WcaPerson> searchPeople(List<String> keywords, Pageable pageable);
}
