package xin.lz1998.wcads.repository.impl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.repository.Top10RankRepository;

import java.util.List;

import static xin.lz1998.wcads.entity.QWcaPerson.wcaPerson;
import static xin.lz1998.wcads.entity.QWcaRankAverage.wcaRankAverage;
import static xin.lz1998.wcads.entity.QWcaRankSingle.wcaRankSingle;

@Repository
public class Top10RankRepositoryImpl implements Top10RankRepository {

    private static final int TOP_NUMBER = 10;

    private JPAQueryFactory queryFactory;

    public Top10RankRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10Rank(String event, String region, String type, String gender) {
        EntityPathBase rankTable = "sin".equals(type) ? wcaRankSingle : wcaRankAverage;
        NumberPath<Integer> rankTableBestField = "sin".equals(type) ? wcaRankSingle.best : wcaRankAverage.best;
        StringPath rankTablePersonIdField = "sin".equals(type) ? wcaRankSingle.personId : wcaRankAverage.personId;
        StringPath rankTableEventIdField = "sin".equals(type) ? wcaRankSingle.eventId : wcaRankAverage.eventId;
        NumberPath<Integer> rankTableCountryRankField = "sin".equals(type) ? wcaRankSingle.countryRank : wcaRankAverage.countryRank;
        return queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        rankTableBestField))
                .from(rankTable)
                .innerJoin(wcaPerson)
                .on(rankTablePersonIdField.eq(wcaPerson.id))
                .where(rankTableEventIdField.eq(event)
                        .and(wcaPerson.countryId.eq(region))
                        .and(wcaPerson.gender.eq(gender)))
                .orderBy(rankTableCountryRankField.asc())
                .limit(TOP_NUMBER)
                .fetch();
    }
}
