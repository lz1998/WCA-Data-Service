package xin.lz1998.wcads.repository.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.entity.QWcaPerson;
import xin.lz1998.wcads.entity.WcaPerson;
import xin.lz1998.wcads.repository.WcaPersonJpaRepository;
import xin.lz1998.wcads.repository.WcaPersonSearchRepository;

import java.util.List;

@Primary
@Repository
public class WcaPersonSearchRepositoryImpl implements WcaPersonSearchRepository {
    @Autowired
    JPAQueryFactory queryFactory;
    @Autowired
    WcaPersonJpaRepository wcaPersonJpaRepository;

    @Override
    public Page<WcaPerson> searchPeople(List<String> keywords, Pageable pageable) {
        // 关键词出现在名字或ID中
        QWcaPerson wcaPerson = QWcaPerson.wcaPerson;
        Predicate pre = wcaPerson.id.isNotNull();
        for (String keyword : keywords) {
            pre = ExpressionUtils.and(pre, wcaPerson.id.contains(keyword).or(wcaPerson.name.contains(keyword)));
        }
        Page<WcaPerson> result = wcaPersonJpaRepository.findAll(pre, pageable);
        return result;
    }
}
