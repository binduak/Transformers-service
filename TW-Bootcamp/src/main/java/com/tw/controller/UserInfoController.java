package com.tw.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.tw.entity.Buyer;
import com.tw.entity.Seller;
import com.tw.entity.Users;
import com.tw.exception.BaseException;
import com.tw.request.BuyerRequest;
import com.tw.request.LoginRequest;
import com.tw.request.SellerRequest;
import com.tw.response.BaseResponse;
import com.tw.response.UserInfoResponse;
import com.tw.service.IUserService;
import com.tw.utility.ApplicationUtility;

@RequestMapping("user")
@Controller
public class UserInfoController {

	public static final Logger log = Logger.getLogger(UserInfoController.class.getName());
	
	@Autowired
	private IUserService userService;
	

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<UserInfoResponse>> validateLogin(@RequestBody LoginRequest login) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "validateLogin");
		BaseResponse<UserInfoResponse> returnSucessResponse = new BaseResponse<>();
		try {
			Users userInfo = userService.getUserLoginInfo(login.getUsername(), login.getPassword());
			if (userInfo !=null) {
				returnSucessResponse.setData(new UserInfoResponse(userInfo.getName(),userInfo.getEmailId(),userInfo.getUsername(),userInfo.getAddress(),userInfo.getMobileNumber()));
				returnSucessResponse.setSucessResponse();
			}else{
				returnSucessResponse.setData(null);
				returnSucessResponse.setFailureResponse();
			}
		}catch (BaseException baseExceptoin) {
			returnSucessResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnSucessResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnSucessResponse.setFailureResponse();
		}
		log.debug(ApplicationUtility.ENTER_METHOD  + "validateLogin");
		return new ResponseEntity<>(returnSucessResponse, HttpStatus.OK);
	}


	@GetMapping("logout/{username}")
		public ResponseEntity<BaseResponse<String>> logoutUser(@PathVariable("username") String username) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "logoutUser");
			BaseResponse<String> returnSucessResponse = new BaseResponse<>();
			if (username.equalsIgnoreCase("123")) {
				returnSucessResponse.setData("User Logged Out Sucessfully");
				returnSucessResponse.setSucessResponse();
			}else{
				returnSucessResponse.setData("Invalid Username to Logout");
				returnSucessResponse.setFailureResponse();
			}
			log.debug(ApplicationUtility.ENTER_METHOD  + "logoutUser");
			return new ResponseEntity<>(returnSucessResponse, HttpStatus.OK);
		}
	
	
	@RequestMapping(value = "registerBuyer", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<Boolean>> registerBuyer(@RequestBody BuyerRequest registerBuyer) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "registerBuyer");
		BaseResponse<Boolean> returnSucessResponse = new BaseResponse<>();
		try {
			Users toSaveUser = new Users();
			toSaveUser.setAddress(registerBuyer.getAddress());
			toSaveUser.setEmailId(registerBuyer.getEmailId());
			toSaveUser.setMobileNumber(registerBuyer.getMobileNumber());
			toSaveUser.setName(registerBuyer.getName());
			toSaveUser.setPassword(registerBuyer.getPassword());
			toSaveUser.setUsername(registerBuyer.getUsername());
			toSaveUser.setAddress(registerBuyer.getAddress());
			Buyer toSaveBuyer = new Buyer();
			toSaveBuyer.setUserInfo(toSaveUser);
			toSaveBuyer.setUserInfo(toSaveUser);
			toSaveBuyer.setDob(registerBuyer.getDateOfBirth());
			toSaveBuyer.setGender(registerBuyer.getGender());
			returnSucessResponse.setData(userService.registerBuyer(toSaveBuyer));
			returnSucessResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnSucessResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnSucessResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnSucessResponse.setFailureResponse();
		}
		
		log.debug(ApplicationUtility.EXIT_METHOD  + "registerBuyer");
		return new ResponseEntity<>(returnSucessResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "registerSeller", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<Boolean>> registerBuyer(@RequestBody SellerRequest registerSeller) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "registerSeller");
		BaseResponse<Boolean> returnSucessResponse = new BaseResponse<>();
		try {
			Users toSaveUser = new Users();
			toSaveUser.setAddress(registerSeller.getAddress());
			toSaveUser.setEmailId(registerSeller.getEmailId());
			toSaveUser.setMobileNumber(registerSeller.getMobileNumber());
			toSaveUser.setName(registerSeller.getName());
			toSaveUser.setPassword(registerSeller.getPassword());
			toSaveUser.setUsername(registerSeller.getUsername());
			toSaveUser.setAddress(registerSeller.getAddress());
			Seller toSaveSeller = new Seller();
			toSaveSeller.setUserInfo(toSaveUser);
			toSaveSeller.setExperienceYears(registerSeller.getExperienceYears());
			toSaveSeller.setExperienceMonths(registerSeller.getExperienceMonths());
			toSaveSeller.setPanCardNo(registerSeller.getPanCardNo());
			returnSucessResponse.setData(userService.registerSeller(toSaveSeller));
			returnSucessResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnSucessResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnSucessResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnSucessResponse.setFailureResponse();
		}
		
		log.debug(ApplicationUtility.EXIT_METHOD  + "registerSeller");
		return new ResponseEntity<>(returnSucessResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "isUsernameOrEmailPresent", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<Boolean>> isUsernameOrEmailPresent( @RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "emailId", required = false) String emailId) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "isUserPresent");
		BaseResponse<Boolean> returnSucessResponse = new BaseResponse<>();
		try {
			Users toValidateUser = new Users();
			toValidateUser.setUsername(username);
			toValidateUser.setEmailId(emailId);
			returnSucessResponse.setData(userService.isUsernameOrEmailPresent(toValidateUser));
			returnSucessResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnSucessResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnSucessResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnSucessResponse.setFailureResponse();
		}
		
		log.debug(ApplicationUtility.EXIT_METHOD  + "isUserPresent");
		return new ResponseEntity<>(returnSucessResponse, HttpStatus.OK);
	}
}
