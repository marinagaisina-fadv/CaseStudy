package com.marinagaisina.casestudy;

import com.marinagaisina.casestudy.controller.CaseStudyController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
class CasestudyApplicationTests {

    @Autowired
    CaseStudyController caseStudyController;

    @Test
    void contextLoads() {
        Assertions.assertThat(caseStudyController).isNotNull();
    }

}
