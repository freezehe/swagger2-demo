package com.freeze.springboot.swagger2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * Title: User
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author he_jiebing@jiuyv.com
 * @date 2019年7月10日 下午3:38:29
 */
@ApiModel
public class User {

	@ApiModelProperty(value = "用户id")
	private Integer id;
	@ApiModelProperty(value = "用户名")
	private String username;
	@ApiModelProperty(value = "用户地址")
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
