package xin.lz1998.wcads.domain.converter;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import xin.lz1998.wcads.domain.Gender;

public class GenderConverter implements Converter<String, Gender> {
    @Override
    public Gender convert(@NotNull String source) {
        return Gender.from(source);
    }
}
