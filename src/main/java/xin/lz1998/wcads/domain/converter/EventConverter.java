package xin.lz1998.wcads.domain.converter;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import xin.lz1998.wcads.domain.Event;

public class EventConverter implements Converter<String, Event> {
    @Override
    public Event convert(@NotNull String source) {
        return Event.from(source);
    }
}
