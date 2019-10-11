package xin.lz1998.wcads.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.entity.QWcaPerson;
import xin.lz1998.wcads.entity.WcaPerson;
import xin.lz1998.wcads.repository.WcaPersonSearchRepository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public class WcaPersonSearchRepositoryImpl implements WcaPersonSearchRepository {
    @Autowired
    JPAQueryFactory queryFactory;
    @Override
    public List<WcaPerson> searchPeople(List<String> keywords,int limit) {
        // 关键词出现在名字或ID中
        QWcaPerson wcaPerson=QWcaPerson.wcaPerson;
        BooleanExpression booleanExpression=null;
        for(String keyword :keywords){
            if(booleanExpression==null){
                booleanExpression=wcaPerson.name.contains(keyword).or(wcaPerson.id.contains(keyword));
            }else{
                booleanExpression=booleanExpression.and(
                        wcaPerson.name.contains(keyword).or(wcaPerson.id.contains(keyword))
                );
            }
        }
        List<WcaPerson> personList =  queryFactory.selectFrom(wcaPerson)
                .where(booleanExpression)
                .limit(limit)
                .fetch();
        return personList;
    }
}
