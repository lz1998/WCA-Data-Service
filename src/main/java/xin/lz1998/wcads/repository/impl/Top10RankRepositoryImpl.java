package xin.lz1998.wcads.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.Region;
import xin.lz1998.wcads.repository.Top10RankRepository;

import java.util.List;

import static xin.lz1998.wcads.domain.Gender.ALL;
import static xin.lz1998.wcads.entity.QContinent.continent;
import static xin.lz1998.wcads.entity.QCountry.country;
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
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankForCountryAndSingleResult(Event event, String country, Gender gender) {
        return queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        wcaRankSingle.best))
                .from(wcaRankSingle)
                .innerJoin(wcaPerson)
                .on(wcaRankSingle.personId.eq(wcaPerson.id))
                .where(buildWhereExpression(event.getBriefName(), country, gender, wcaRankSingle.eventId, wcaRankSingle.countryRank))
                .orderBy(wcaRankSingle.countryRank.asc())
                .limit(TOP_NUMBER)
                .fetch();
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankForCountryAndAverageResult(Event event, String country, Gender gender) {
        return queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        wcaRankAverage.best))
                .from(wcaRankAverage)
                .innerJoin(wcaPerson)
                .on(wcaRankAverage.personId.eq(wcaPerson.id))
                .where(buildWhereExpression(event.getBriefName(), country, gender, wcaRankAverage.eventId, wcaRankAverage.countryRank))
                .orderBy(wcaRankAverage.countryRank.asc())
                .limit(TOP_NUMBER)
                .fetch();
    }

    private BooleanBuilder buildWhereExpression(String event, String country, Gender gender, StringPath eventId,
                                                NumberPath<Integer> rankTableCountryRankField) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(eventId.eq(event))
                .and(wcaPerson.countryId.eq(country))
                // TODO: 2020/3/6 check the meaning of subid = 2
                .and(wcaPerson.subId.ne(2))
                // remove dirty data with rank 0
                .and(rankTableCountryRankField.ne(0));
        if (!ALL.equals(gender)) {
            booleanBuilder.and(wcaPerson.gender.eq(gender.getBriefName()));
        }
        return booleanBuilder;
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankSingleResultForWholeWorld(Event event, Gender gender) {
        return queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        wcaRankSingle.best))
                .from(wcaRankSingle)
                .innerJoin(wcaPerson)
                .on(wcaRankSingle.personId.eq(wcaPerson.id))
                .where(buildWhereExpression(event.getBriefName(), gender, wcaRankSingle.eventId, wcaRankSingle.worldRank))
                .orderBy(wcaRankSingle.worldRank.asc())
                .limit(TOP_NUMBER)
                .fetch();
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankAverageResultForWholeWorld(Event event, Gender gender) {
        return queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        wcaRankAverage.best))
                .from(wcaRankAverage)
                .innerJoin(wcaPerson)
                .on(wcaRankAverage.personId.eq(wcaPerson.id))
                .where(buildWhereExpression(event.getBriefName(), gender, wcaRankAverage.eventId, wcaRankAverage.worldRank))
                .orderBy(wcaRankAverage.worldRank.asc())
                .limit(TOP_NUMBER)
                .fetch();
    }

    private Predicate buildWhereExpression(String event, Gender gender, StringPath eventId, NumberPath<Integer> worldRank) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(eventId.eq(event))
                .and(wcaPerson.subId.ne(2))
                .and(worldRank.ne(0));
        if (!ALL.equals(gender)) {
            booleanBuilder.and(wcaPerson.gender.eq(gender.getBriefName()));
        }
        return booleanBuilder;
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankSingleResultForContinent(Event event, Region continentName, Gender gender) {
        return queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        wcaRankSingle.best))
                .from(wcaRankSingle)
                .innerJoin(wcaPerson)
                .on(wcaRankSingle.personId.eq(wcaPerson.id))
                .innerJoin(country)
                .on(wcaPerson.countryId.eq(country.id))
                .innerJoin(continent)
                .on(country.continentId.eq(continent.id))
                .where(buildWhereExpression(event.getBriefName(), continentName, gender, wcaRankSingle.eventId, wcaRankSingle.continentRank))
                .orderBy(wcaRankSingle.continentRank.asc())
                .limit(TOP_NUMBER)
                .fetch();
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankAverageResultForContinent(Event event, Region continentName, Gender gender) {
        return queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        wcaRankAverage.best))
                .from(wcaRankAverage)
                .innerJoin(wcaPerson)
                .on(wcaRankAverage.personId.eq(wcaPerson.id))
                .innerJoin(country)
                .on(wcaPerson.countryId.eq(country.id))
                .innerJoin(continent)
                .on(country.continentId.eq(continent.id))
                .where(buildWhereExpression(event.getBriefName(), continentName, gender, wcaRankAverage.eventId, wcaRankAverage.continentRank))
                .orderBy(wcaRankAverage.continentRank.asc())
                .limit(TOP_NUMBER)
                .fetch();
    }

    private BooleanBuilder buildWhereExpression(String event, Region continentName, Gender gender, StringPath eventId,
                                                NumberPath<Integer> continentRank) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(eventId.eq(event))
                .and(continent.recordName.equalsIgnoreCase(continentName.getBriefName()))
                .and(wcaPerson.subId.ne(2))
                .and(continentRank.ne(0));
        if (!ALL.equals(gender)) {
            booleanBuilder.and(wcaPerson.gender.eq(gender.getBriefName()));
        }
        return booleanBuilder;
    }
}
