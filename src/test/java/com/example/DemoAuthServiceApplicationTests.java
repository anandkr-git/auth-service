package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.intraedge.auth.AuthServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthServiceApplication.class)
@WebAppConfiguration
public class DemoAuthServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
