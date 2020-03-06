package xin.lz1998.wcads.domain.converter;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import xin.lz1998.wcads.domain.ResultType;

public class ResultTypeConverter implements Converter<String, ResultType> {
    @Override
    public ResultType convert(@NotNull String source) {
        return ResultType.from(source);
    }
}
