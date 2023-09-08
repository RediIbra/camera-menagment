package com.npa.getway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

@SpringBootTest
class GetwayApplicationTests {

    @Autowired
    ConversionService conversionService;

    @Test
    void contextLoads() {
        assert conversionService.convert("25", Integer.class) == 25;
    }

}
