package xin.lz1998.wcads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xin.lz1998.wcads.entity.WcaPerson;

import java.util.List;

public interface WcaPersonRepository extends JpaRepository<WcaPerson,Integer>,WcaPersonSearchRepository {
    WcaPerson findWcaPersonById(String id);
    List<WcaPerson> findWcaPeopleByNameContaining(String name);

}

