package com.mljr.heil;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@EnableTransactionManagement(mode = AdviceMode.PROXY)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles("test")
@ContextConfiguration(locations = {"classpath:spring.xml"})
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {
    protected Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected String MISS_GEN_KEY = "缺少配置[useGeneratedKeys=\"true\" keyProperty=\"id\"]";
}
