package xin.lz1998.wcads.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.controller.dto.PersonResultDTO;
import xin.lz1998.wcads.domain.ResultType;
import xin.lz1998.wcads.repository.GroupResultRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static xin.lz1998.wcads.entity.QWcaCompetition.wcaCompetition;
import static xin.lz1998.wcads.entity.QWcaResult.wcaResult;

@Repository
public class GroupResultRepositoryImpl implements GroupResultRepository {

    public static final int INVALID_RESULT = 0;

    private JPAQueryFactory queryFactory;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public GroupResultRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<PersonResultDTO> findAllEventResultsByWcaIdAndDurationAndResultType(String wcaId, Date startTime, Date endTime, ResultType resultType) {
        NumberPath<Long> resultColumn = resultType.equals(ResultType.SINGLE) ? wcaResult.best : wcaResult.average;
        return queryFactory.select(
                Projections.constructor(
                        PersonResultDTO.class,
                        wcaResult.eventId,
                        resultColumn.min()))
                .from(wcaResult)
                .innerJoin(wcaCompetition)
                .on(wcaResult.competitionId.eq(wcaCompetition.id))
                .where(resultColumn.gt(INVALID_RESULT)
                        .and(wcaResult.personId.eq(wcaId))
                        .and(buildDateExpression(startTime, endTime)))
                .groupBy(wcaResult.eventId)
                .fetch();
    }

    @NotNull
    private BooleanExpression buildDateExpression(Date startTime, Date endTime) {
        Integer startNum = Integer.valueOf(sdf.format(startTime));
        Integer endNum = Integer.valueOf(sdf.format(endTime));
        NumberExpression yearNumber = wcaCompetition.year.multiply(10000);
        NumberExpression monthNumber = wcaCompetition.month.multiply(100);
        NumberExpression dayNumber = wcaCompetition.day;
        NumberExpression dateNumber = yearNumber.add(monthNumber).add(dayNumber);
        return dateNumber.gt(startNum).and(dateNumber.lt(endNum));
    }
}
