package xin.lz1998.wcads.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtil {
    private static int PAGE_SIZE_LIMIT=1000;
    public static Pageable getPageable(Integer pageNum,Integer pageSize){
        if(pageNum==null){
            pageNum=0;
        }
        if(pageSize==null){
            pageSize=10;
        }
        if(pageSize>1000){
            pageSize=PAGE_SIZE_LIMIT;
        }
        return PageRequest.of(pageNum,pageSize);
    }
}
