package com.mljr.heil;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@EnableTransactionManagement(mode = AdviceMode.PROXY)
@RunWith(SpringRunner.class)
@Transactional
@Rollback
@ActiveProfiles("dev-zy")
@SpringBootTest(classes = AbstractTest.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ComponentScan(basePackages = "com.mljr")
public class AbstractTest {
    protected Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected String MISS_GEN_KEY = "缺少配置[useGeneratedKeys=\"true\" keyProperty=\"id\"]";
}
