package xin.lz1998.wcads.repository;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.domain.ResultType;

import static xin.lz1998.wcads.entity.QWcaRankAverage.wcaRankAverage;

@Repository
public class Top10RankAverageResultRepositoryImpl extends BaseTop10RankRepository {

    public Top10RankAverageResultRepositoryImpl(JPAQueryFactory queryFactory) {
        super(queryFactory);
    }

    @NotNull
    @Override
    protected EntityPathBase getRankTableName() {
        return wcaRankAverage;
    }

    @Override
    protected StringPath getEventIdColumnPath() {
        return wcaRankAverage.eventId;
    }

    @Override
    protected StringPath getPersonIdColumnPath() {
        return wcaRankAverage.personId;
    }

    @Override
    protected NumberPath<Integer> getBestResultColumnPath() {
        return wcaRankAverage.best;
    }

    @Override
    protected NumberPath<Integer> getWorldRankColumnPath() {
        return wcaRankAverage.worldRank;
    }

    @Override
    protected NumberPath<Integer> getContinentRankColumnPath() {
        return wcaRankAverage.continentRank;
    }

    @Override
    protected NumberPath<Integer> getCountryRankColumnPath() {
        return wcaRankAverage.countryRank;
    }

    @Override
    public boolean matchResultType(ResultType type) {
        return type.equals(ResultType.AVERAGE);
    }

}
