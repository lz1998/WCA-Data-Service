package xin.lz1998.wcads.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.repository.Top10RankRepository;

import java.util.List;

import static xin.lz1998.wcads.entity.QWcaPerson.wcaPerson;
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
        return queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        wcaRankSingle.best))
                .from(wcaRankSingle)
                .innerJoin(wcaPerson)
                .on(wcaRankSingle.personId.eq(wcaPerson.id))
                .where(wcaRankSingle.eventId.eq(event)
                        .and(wcaPerson.countryId.eq(region))
                        .and(wcaPerson.gender.eq(gender)))
                .orderBy(wcaRankSingle.countryRank.asc())
                .limit(TOP_NUMBER)
                .fetch();
    }
}
