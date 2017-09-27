package com.tw.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(origins = "*")
@RequestMapping("user")
@Controller
public class UserInfoController {

	public static final Logger log = Logger.getLogger(UserInfoController.class.getName());
	
	@Autowired
	private IUserService userService;
	

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<UserInfoResponse>> validateLogin(@RequestBody LoginRequest login) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "validateLogin");
		BaseResponse<UserInfoResponse> returnResponse = new BaseResponse<>();
		try {
			Users userInfo = userService.getUserLoginInfo(login.getUsername(), login.getPassword());
			if (userInfo !=null) {
				boolean isBuyer = false;
				if (userInfo.getBuyerInfo()!= null && userInfo.getBuyerInfo().getBuyerId() >0) isBuyer =true;
				returnResponse.setData(new UserInfoResponse(userInfo.getName(),userInfo.getEmailId(),userInfo.getUsername(),userInfo.getAddress(),userInfo.getMobileNumber(),isBuyer));
				returnResponse.setSucessResponse();
			}else{
				returnResponse.setData(null);
				returnResponse.setFailureResponse();
			}
		}catch (BaseException baseExceptoin) {
			returnResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnResponse.setFailureResponse();
		}
		log.debug(ApplicationUtility.ENTER_METHOD  + "validateLogin");
		return new ResponseEntity<>(returnResponse, HttpStatus.OK);
	}


	@GetMapping("logout/{username}")
		public ResponseEntity<BaseResponse<String>> logoutUser(@PathVariable("username") String username) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "logoutUser");
			BaseResponse<String> returnResponse = new BaseResponse<>();
			if (username.equalsIgnoreCase("123")) {
				returnResponse.setData("User Logged Out Sucessfully");
				returnResponse.setSucessResponse();
			}else{
				returnResponse.setData("Invalid Username to Logout");
				returnResponse.setFailureResponse();
			}
			log.debug(ApplicationUtility.ENTER_METHOD  + "logoutUser");
			return new ResponseEntity<>(returnResponse, HttpStatus.OK);
		}
	
	
	@RequestMapping(value = "registerBuyer", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<Boolean>> registerBuyer(@RequestBody BuyerRequest registerBuyer) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "registerBuyer");
		BaseResponse<Boolean> returnResponse = new BaseResponse<>();
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
			toSaveBuyer.setBuyerUserInfo(toSaveUser);
			toSaveBuyer.setDob(ApplicationUtility.convertDOBToDate(registerBuyer.getDateOfBirth()));
			toSaveBuyer.setGender(registerBuyer.getGender());
			returnResponse.setData(userService.registerBuyer(toSaveBuyer));
			returnResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnResponse.setFailureResponse();
		}
		
		log.debug(ApplicationUtility.EXIT_METHOD  + "registerBuyer");
		return new ResponseEntity<>(returnResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "registerSeller", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<Boolean>> registerBuyer(@RequestBody SellerRequest registerSeller) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "registerSeller");
		BaseResponse<Boolean> returnResponse = new BaseResponse<>();
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
			toSaveSeller.setSellerUserInfo(toSaveUser);
			toSaveSeller.setExperienceYears(registerSeller.getExperienceYears());
			toSaveSeller.setExperienceMonths(registerSeller.getExperienceMonths());
			toSaveSeller.setPanCardNo(registerSeller.getPanCardNo());
			returnResponse.setData(userService.registerSeller(toSaveSeller));
			returnResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnResponse.setFailureResponse();
		}
		
		log.debug(ApplicationUtility.EXIT_METHOD  + "registerSeller");
		return new ResponseEntity<>(returnResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "isUsernameOrEmailPresent", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<Boolean>> isUsernameOrEmailPresent( @RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "emailId", required = false) String emailId) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "isUserPresent");
		BaseResponse<Boolean> returnResponse = new BaseResponse<>();
		try {
			Users toValidateUser = new Users();
			toValidateUser.setUsername(username);
			toValidateUser.setEmailId(emailId);
			returnResponse.setData(userService.isUsernameOrEmailPresent(toValidateUser));
			returnResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnResponse.setFailureResponse();
		}
		
		log.debug(ApplicationUtility.EXIT_METHOD  + "isUserPresent");
		return new ResponseEntity<>(returnResponse, HttpStatus.OK);
	}
}
