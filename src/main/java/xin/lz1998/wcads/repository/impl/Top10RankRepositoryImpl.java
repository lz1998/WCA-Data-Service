package xin.lz1998.wcads.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.repository.Top10RankRepository;

import java.util.List;

import static xin.lz1998.wcads.domain.Gender.ALL;
import static xin.lz1998.wcads.domain.ResultType.SINGLE;
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
    public List<Top10ResultDTO.Top10ItemDTO> findTop10Rank(Event event, String region, ResultType type, Gender gender) {
        EntityPathBase rankTable = SINGLE.equals(type) ? wcaRankSingle : wcaRankAverage;
        NumberPath<Integer> rankTableBestField = SINGLE.equals(type) ? wcaRankSingle.best : wcaRankAverage.best;
        StringPath rankTablePersonIdField = SINGLE.equals(type) ? wcaRankSingle.personId : wcaRankAverage.personId;
        StringPath rankTableEventIdField = SINGLE.equals(type) ? wcaRankSingle.eventId : wcaRankAverage.eventId;
        NumberPath<Integer> rankTableCountryRankField = SINGLE.equals(type) ? wcaRankSingle.countryRank : wcaRankAverage.countryRank;
        return queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        rankTableBestField))
                .from(rankTable)
                .innerJoin(wcaPerson)
                .on(rankTablePersonIdField.eq(wcaPerson.id))
                .where(buildWhereExpression(event.getBriefName(), region, gender, rankTableEventIdField, rankTableCountryRankField))
                .orderBy(rankTableCountryRankField.asc())
                .limit(TOP_NUMBER)
                .fetch();
    }

    private BooleanBuilder buildWhereExpression(String event, String region, Gender gender, StringPath eventId,
                                                NumberPath<Integer> rankTableCountryRankField) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(eventId.eq(event))
                .and(wcaPerson.countryId.eq(region))
                // TODO: 2020/3/6 check the meaning of subid = 2
                .and(wcaPerson.subId.ne(2))
                // remove dirty data with rank 0
                .and(rankTableCountryRankField.ne(0));
        if (!ALL.equals(gender)) {
            booleanBuilder.and(wcaPerson.gender.eq(gender.getBriefName()));
        }
        return booleanBuilder;
    }
}
