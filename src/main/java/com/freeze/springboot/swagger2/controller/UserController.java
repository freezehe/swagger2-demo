package com.freeze.springboot.swagger2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freeze.springboot.swagger2.entity.User;

/**
 * <p>
 * Title: UserController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author he_jiebing@jiuyv.com
 * @date 2019年7月10日 下午3:39:36
 */
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/user")
public class UserController {

	@PostMapping("/")
	@ApiOperation("添加用户的接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
			@ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true) })
	public void addUser(String username,
			@RequestParam(required = true) String address) {
		return;
	}

	@GetMapping("/")
	@ApiOperation("根据id查询用户的接口")
	@ApiImplicitParam(name = "id", value = "用户id", defaultValue = "99", required = true)
	public User getUserById(@PathVariable Integer id) {
		User user = new User();
		user.setId(id);
		return user;
	}

	@PutMapping("/{id}")
	@ApiOperation("根据id更新用户的接口")
	public User updateUserById(@RequestBody User user) {
		return user;
	}

}
