package xin.lz1998.wcads.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import xin.lz1998.wcads.entity.WcaCompetition;

import java.util.List;

public class ResultWrapperUtils {
    public static String resultWrapper(Object data){
        JSONObject result=new JSONObject();
        result.put("retcode",0);
        result.put("msg","ok");
        result.put("data",data);
        return JSON.toJSONString(result);
    }

    public static String pageResultWrapper(Page data){
        JSONObject result=new JSONObject();
        result.put("data",data.getContent());
        result.put("retcode",0);
        result.put("msg","ok");

        result.put("totalElements",data.getTotalElements());
        result.put("totalPages",data.getTotalPages());
        result.put("pageNumberOfElements",data.getNumberOfElements());
        result.put("pageSize",data.getSize());
        result.put("pageNum",data.getNumber());
        result.put("pageFirst",data.isFirst());
        result.put("pageLast",data.isLast());
        result.put("pageEmpty",data.isEmpty());
        return JSON.toJSONString(result);
    }
}
