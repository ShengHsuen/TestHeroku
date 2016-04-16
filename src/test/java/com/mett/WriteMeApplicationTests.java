package com.mett;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mett.writeMe.config.WriteMeApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WriteMeApplication.class)
@WebAppConfiguration
public class WriteMeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
