package com.tw.controller;

import com.tw.response.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tw.request.LoginRequest;
import com.tw.response.BaseResponse;
import com.tw.service.impl.IUserService;

//@RequestMapping("user")
@Controller
public class UserInfoController {

	public static final Logger log = Logger.getLogger(UserInfoController.class.getName());
	
	@Autowired
	private IUserService userService;
	

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<UserInfo>> validateLogin(@RequestBody LoginRequest login) {

		BaseResponse<UserInfo> returnSucessResponse = new BaseResponse<>();
		UserInfo userInfo = userService.getUserLoginInfo(login.getUsername(), login.getPassword());
		if (userInfo !=null) {
			returnSucessResponse.setData(userInfo);
			returnSucessResponse.setSucessResponse();
		}else{
			returnSucessResponse.setData(null);
			returnSucessResponse.setFailureResponse();
		}
		return new ResponseEntity<>(returnSucessResponse, HttpStatus.OK);
	}


	@GetMapping("logout/{username}")
		public ResponseEntity<BaseResponse<String>> logoutUser(@PathVariable("username") String username) {
			
			BaseResponse<String> returnSucessResponse = new BaseResponse<>();
			if (username.equalsIgnoreCase("123")) {
				returnSucessResponse.setData("User Logged Out Sucessfully");
				returnSucessResponse.setSucessResponse();
			}else{
				returnSucessResponse.setData("Invalid Username to Logout");
				returnSucessResponse.setFailureResponse();
			}
			return new ResponseEntity<>(returnSucessResponse, HttpStatus.OK);
		}
}
