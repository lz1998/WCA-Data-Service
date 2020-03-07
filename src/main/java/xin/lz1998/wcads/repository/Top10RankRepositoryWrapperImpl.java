package xin.lz1998.wcads.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import xin.lz1998.wcads.controller.dto.Top10ResultDTO;
import xin.lz1998.wcads.domain.Event;
import xin.lz1998.wcads.domain.Gender;
import xin.lz1998.wcads.domain.ResultType;

import java.util.List;

@Primary
@Repository
public class Top10RankRepositoryWrapperImpl implements Top10RankRepository {
    private List<BaseTop10RankRepository> top10RankRepositories;

    public Top10RankRepositoryWrapperImpl(List<BaseTop10RankRepository> top10RankRepositories) {
        this.top10RankRepositories = top10RankRepositories;
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankForCountry(Event event, String region, ResultType type, Gender gender) {
        return getRepository(type).findTop10RankForCountry(event, region, type, gender);
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankForWholeWorld(Event event, ResultType type, Gender gender) {
        return getRepository(type).findTop10RankForWholeWorld(event, type, gender);
    }

    @Override
    public List<Top10ResultDTO.Top10ItemDTO> findTop10RankForContinent(Event event, String region, ResultType type, Gender gender) {
        return getRepository(type).findTop10RankForContinent(event, region, type, gender);
    }

    private Top10RankRepository getRepository(ResultType type) {
        return top10RankRepositories.stream().filter(repository -> repository.matchResultType(type)).findFirst().orElseThrow(RuntimeException::new);
    }
}
