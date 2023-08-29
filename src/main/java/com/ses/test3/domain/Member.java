package com.ses.test3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	private String memberid;
	private String memberpw;
	private String membername;
	private String inputdate;
}
