package xin.lz1998.wcads.domain;

import org.junit.Test;
import xin.lz1998.wcads.exception.UnknownEventException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EventTest {

    @Test
    public void shouldThrowExceptionWhenEventIsNull() {
        assertThatThrownBy(() -> Event.from(null))
                .isInstanceOf(UnknownEventException.class)
                .hasMessage("unknown event");
    }

    @Test
    public void shouldThrowExceptionWhenEventIsUnknown() {
        assertThatThrownBy(() -> Event.from("111"))
                .isInstanceOf(UnknownEventException.class)
                .hasMessage("unknown event: 111");
    }
}
