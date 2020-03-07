package xin.lz1998.wcads.repository.impl.rank;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.domain.ResultType;

import static xin.lz1998.wcads.entity.QWcaRankSingle.wcaRankSingle;

@Repository
public class Top10RankSingleResultRepositoryImpl extends BaseTop10RankRepository {

    public Top10RankSingleResultRepositoryImpl(JPAQueryFactory queryFactory) {
        super(queryFactory);
    }

    @NotNull
    @Override
    protected EntityPathBase getRankTableName() {
        return wcaRankSingle;
    }

    @Override
    protected StringPath getEventIdColumnPath() {
        return wcaRankSingle.eventId;
    }

    @Override
    protected StringPath getPersonIdColumnPath() {
        return wcaRankSingle.personId;
    }

    @Override
    protected NumberPath<Integer> getBestResultColumnPath() {
        return wcaRankSingle.best;
    }

    @Override
    protected NumberPath<Integer> getWorldRankColumnPath() {
        return wcaRankSingle.worldRank;
    }

    @Override
    protected NumberPath<Integer> getContinentRankColumnPath() {
        return wcaRankSingle.continentRank;
    }

    @Override
    protected NumberPath<Integer> getCountryRankColumnPath() {
        return wcaRankSingle.countryRank;
    }

    @Override
    public boolean matchResultType(ResultType type) {
        return type.equals(ResultType.SINGLE);
    }
}
