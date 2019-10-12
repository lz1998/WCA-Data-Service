package xin.lz1998.wcads.service;

import java.util.List;
import java.util.Map;

public interface XRankService {
    // 可以统计某魔方社团的排名
    Map<String, Object> getRank(List<String> wcaIds);
}
