package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
	public Restaurant() {
		System.out.println("Restaurant() call.");
	}
	//root-context.xml에 context(namespace)(네임스페이스는 라이브러리에 등록되어있어야함(web.xml)) 등록해주고 해당 클래스에 @Conponent, @Control(컨트롤러) 표현해줘야함
	//set 메소드 호출과 동일 @Setter
	@Setter(onMethod_ = @Autowired)//스프링에서 인스턴스를 주입시키겠다는 의미
	private Chef chef; // @Autowired: 스프링에서 관리하는 객체를 주입. get/set
}
