package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//경로를 더 추가하고싶으면 {}, {} 배열 형식으로 추가 할 수 있다. 그게 아니라면("경로주소")
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class SampleTests {
	//Restaurant 객체가 주입이 되는지를 확인해서 객체정보를 출력
	
	@Setter(onMethod_ = @Autowired)
	private Restaurant restaurant; // = new Restaurant();
	
	@Test
	public void testExist() {
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("-----------");
		log.info(restaurant.getChef()); // restaurant 호출 시 chef 인스턴스 할당
	}
	
	
}
