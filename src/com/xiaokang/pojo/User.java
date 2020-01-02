package com.xiaokang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @Description: 用户实体类
 * @author 小康
 * @version V1.0.0 2019年11月23日 下午1:07:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer id;
	private String username;
	private String password;

	public User(String userName, String password) {
		this.username = userName;
		this.password = password;
	}

}
