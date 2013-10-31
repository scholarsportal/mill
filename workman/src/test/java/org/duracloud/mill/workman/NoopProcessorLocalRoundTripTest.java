/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.mill.workman;

import java.io.File;

import org.duracloud.mill.config.ConfigurationManager;
import org.duracloud.mill.domain.NoopTask;
import org.duracloud.mill.domain.Task;
import org.duracloud.mill.queue.TaskQueue;
import org.duracloud.mill.queue.local.LocalTaskQueue;
import org.duracloud.mill.workman.spring.AppConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class tests a round trip for processing Noop Tasks, from task creation
 * to task processing.
 * 
 * @author Daniel Bernstein Date: Oct 24, 2013
 */
public class NoopProcessorLocalRoundTripTest {
    private static LocalTaskQueue QUEUE = new LocalTaskQueue();
    private ApplicationContext context;

    @Configuration
    @ComponentScan(basePackages={"org.duracloud.mill"})
    public static class TestAppConfig extends AppConfig {

        @Bean
        @Override
        public TaskQueue taskQueue(ConfigurationManager configurationManager) {
            return QUEUE;
        }
    }
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        File testProperties = new File("src/test/resources/workman-test.properties");
        Assert.assertTrue(testProperties.exists());
        System.setProperty(ConfigurationManager.DURACLOUD_MILL_CONFIG_FILE_KEY, testProperties.getAbsolutePath());
        context = new AnnotationConfigApplicationContext(TestAppConfig.class);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        context = null;
    }

    @Test
    public void test() {
        int count = 10;
        for(int i = 0; i < count; i++){
            NoopTask noopTask = new NoopTask();
            Task task = noopTask.writeTask();
            task.setVisibilityTimeout(600);
            QUEUE.put(task);
        }

        
        sleep(5000);
        
        Assert.assertEquals(0, this.QUEUE.getInprocessCount());
        Assert.assertEquals(count, this.QUEUE.getCompletedCount());
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
