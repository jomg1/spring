package org.zerock.domain;

import lombok.Data;
//get,set method 호출하기 위한 Data 어노테이션
@Data
public class SampleDTO {
	private String name;
	private int age;
}
