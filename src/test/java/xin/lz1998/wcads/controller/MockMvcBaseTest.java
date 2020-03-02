package xin.lz1998.wcads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@EnableSpringDataWebSupport
public class MockMvcBaseTest {
    @Autowired
    WebApplicationContext wac;
}
