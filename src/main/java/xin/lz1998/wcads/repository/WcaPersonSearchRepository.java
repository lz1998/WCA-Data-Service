package xin.lz1998.wcads.repository;

import xin.lz1998.wcads.entity.WcaPerson;

import java.util.List;

public interface WcaPersonSearchRepository {
    // 关键词出现在名字或ID中
    List<WcaPerson> searchPeople(List<String> keywords);
}
