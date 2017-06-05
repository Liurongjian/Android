package com.arms.rong.routermoduleb.pojo;

import java.io.Serializable;

/**
 * Created by rong on 2017/5/22.
 */

public class User implements Serializable{

	private String name;

	public User(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
