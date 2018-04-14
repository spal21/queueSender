package com.cs.queue.component.sender;
import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.queue.component.sender.Sender;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueSenderApplicationTest {

  private static ApplicationContext applicationContext;

  @Autowired
  void setContext(ApplicationContext applicationContext) {
    QueueSenderApplicationTest.applicationContext = applicationContext;
  }

  @AfterClass
  public static void afterClass() {
  ((ConfigurableApplicationContext) applicationContext).close();
  }

  @ClassRule
  public static EmbeddedActiveMQBroker broker = new EmbeddedActiveMQBroker();

  @Autowired
  private Sender sender;


  @Test
  public void testSend() throws Exception {
    for (int i = 1; i <= 100; i++) {
    	sender.send("test.q", "Test Message "+i);
	}
  }
}