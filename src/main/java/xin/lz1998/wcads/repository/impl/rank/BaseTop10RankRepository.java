package xin.lz1998.wcads.repository.impl.rank;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jetbrains.annotations.NotNull;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.repository.Top10RankRepository;

import java.util.List;
import java.util.stream.Collectors;

import static xin.lz1998.wcads.domain.Gender.ALL;
import static xin.lz1998.wcads.entity.QContinent.continent;
import static xin.lz1998.wcads.entity.QCountry.country;
import static xin.lz1998.wcads.entity.QWcaPerson.wcaPerson;

public abstract class BaseTop10RankRepository implements Top10RankRepository {

    /**
     * query top 20 results in case of there are multiple results same with 10th result.
     * the results after 20th will be lose, though there are results same with 10th result after 20th.
     * it's hard to use integrity query dsl because query-dsl and JPQL does not support LIMIT and OFFSET in subquery.
     */
    private static final int PRE_QUERY_NUMBER = 20;

    private static final int TOP_NUMBER = 10;

    private static final int STANDARD_PERSON_SUB_ID = 1;

    private JPAQueryFactory queryFactory;

    public BaseTop10RankRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankForWholeWorld(Event event, ResultType type, Gender gender) {
        List<Top10ResultDTO.Top10ItemDTO> topResults = queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        getBestResultColumnPath()))
                .from(getRankTableName())
                .innerJoin(wcaPerson)
                .on(getPersonIdColumnPath().eq(wcaPerson.id))
                .where(buildWorldWhereExpression(event, gender))
                .orderBy(getWorldRankColumnPath().asc())
                .limit(PRE_QUERY_NUMBER)
                .fetch();
        return getFullTop10Result(topResults);
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankForContinent(Event event, String continentName, ResultType type, Gender gender) {
        List<Top10ResultDTO.Top10ItemDTO> topResults = queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        getBestResultColumnPath()))
                .from(getRankTableName())
                .innerJoin(wcaPerson)
                .on(getPersonIdColumnPath().eq(wcaPerson.id))
                .innerJoin(country)
                .on(wcaPerson.countryId.eq(country.id))
                .innerJoin(continent)
                .on(country.continentId.eq(continent.id))
                .where(buildContinentWhereExpression(event, continentName, gender))
                .orderBy(getContinentRankColumnPath().asc())
                .limit(PRE_QUERY_NUMBER)
                .fetch();
        return getFullTop10Result(topResults);
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankForCountry(Event event, String country, ResultType type, Gender gender) {
        List<Top10ResultDTO.Top10ItemDTO> topResults = queryFactory.select(
                Projections.constructor(
                        Top10ResultDTO.Top10ItemDTO.class,
                        wcaPerson.name,
                        getBestResultColumnPath()))
                .from(getRankTableName())
                .innerJoin(wcaPerson)
                .on(getPersonIdColumnPath().eq(wcaPerson.id))
                .where(buildCountryWhereExpression(event, country, gender))
                .orderBy(getCountryRankColumnPath().asc())
                .limit(PRE_QUERY_NUMBER)
                .fetch();
        return getFullTop10Result(topResults);
    }

    @NotNull
    private List<Top10ResultDTO.Top10ItemDTO> getFullTop10Result(List<Top10ResultDTO.Top10ItemDTO> topResults) {
        return topResults.stream()
                .filter(top10Item -> top10Item.getBestResult() <= topResults.get(TOP_NUMBER - 1).getBestResult())
                .collect(Collectors.toList());
    }

    private Predicate buildWorldWhereExpression(Event event, Gender gender) {
        BooleanBuilder booleanBuilder = buildBaseWhereExpression(event, gender);
        booleanBuilder.and(getWorldRankColumnPath().ne(0));
        return booleanBuilder;
    }

    private BooleanBuilder buildContinentWhereExpression(Event event, String continentName, Gender gender) {
        BooleanBuilder booleanBuilder = buildBaseWhereExpression(event, gender);
        booleanBuilder.and(continent.recordName.equalsIgnoreCase(continentName))
                .and(getContinentRankColumnPath().ne(0));
        return booleanBuilder;
    }

    private BooleanBuilder buildCountryWhereExpression(Event event, String country, Gender gender) {
        BooleanBuilder booleanBuilder = buildBaseWhereExpression(event, gender);
        booleanBuilder.and(wcaPerson.countryId.eq(country))
                .and(getCountryRankColumnPath().ne(0));
        return booleanBuilder;
    }

    @NotNull
    private BooleanBuilder buildBaseWhereExpression(Event event, Gender gender) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(getEventIdColumnPath().eq(event.getBriefName()))
                .and(wcaPerson.subId.eq(STANDARD_PERSON_SUB_ID));
        if (!ALL.equals(gender)) {
            booleanBuilder.and(wcaPerson.gender.eq(gender.getBriefName()));
        }
        return booleanBuilder;
    }

    @NotNull
    protected abstract EntityPathBase getRankTableName();

    protected abstract StringPath getEventIdColumnPath();

    protected abstract StringPath getPersonIdColumnPath();

    protected abstract NumberPath<Integer> getBestResultColumnPath();

    protected abstract NumberPath<Integer> getWorldRankColumnPath();

    protected abstract NumberPath<Integer> getContinentRankColumnPath();

    protected abstract NumberPath<Integer> getCountryRankColumnPath();

    public abstract boolean matchResultType(ResultType type);
}
