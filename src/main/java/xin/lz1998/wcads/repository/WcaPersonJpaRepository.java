package xin.lz1998.wcads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xin.lz1998.wcads.entity.WcaPerson;


public interface WcaPersonJpaRepository extends JpaRepository<WcaPerson, String>, QuerydslPredicateExecutor<WcaPerson> {
    WcaPerson findWcaPersonById(String id);
}

