package com.tw.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public ResponseEntity<BaseResponse<String>> validateLogin(@RequestBody LoginRequest login) {
		
		BaseResponse<String> returnSucessResponse = new BaseResponse<String>();
		if (userService.validateUserLogin(login.getUsername(), login.getPassword())) {
			returnSucessResponse.setData("User Logged In Sucessfully");
			returnSucessResponse.setSucessResponse();
		}else{
			returnSucessResponse.setData("Invalid Username Or Password");
			returnSucessResponse.setFailureResponse();
		}
		return new ResponseEntity<BaseResponse<String>>(returnSucessResponse, HttpStatus.OK);
	}
	 
	 
	 @GetMapping("logout/{username}")
		public ResponseEntity<BaseResponse<String>> logoutUser(@PathVariable("username") String username) {
			
			BaseResponse<String> returnSucessResponse = new BaseResponse<String>();
			if (username.equalsIgnoreCase("123")) {
				returnSucessResponse.setData("User Logged Out Sucessfully");
				returnSucessResponse.setSucessResponse();
			}else{
				returnSucessResponse.setData("Invalid Username to Logout");
				returnSucessResponse.setFailureResponse();
			}
			return new ResponseEntity<BaseResponse<String>>(returnSucessResponse, HttpStatus.OK);
		}
}
