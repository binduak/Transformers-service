package com.tw.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({	
		@NamedQuery(name = "getUserLoginInfo", query = "from Users u where u.username = :username and u.password = :password"),
})

@Entity
@Table(name = "users")
public class Users extends AbstractEntity<Long>{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	private long userId;
	@Column(name = "name")
	private String name;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "username", unique = true)
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "address")
	private String address;
	@Column(name = "mobile_number")
	private String mobileNumber;
	@OneToOne(mappedBy = "buyerUserInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Buyer buyerInfo;
	
	@OneToOne(mappedBy = "sellerUserInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Seller sellerUserInfo;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Buyer getBuyerInfo() {
		return buyerInfo;
	}
	public void setBuyerInfo(Buyer buyerInfo) {
		this.buyerInfo = buyerInfo;
	}
	public Seller getSellerUserInfo() {
		return sellerUserInfo;
	}
	public void setSellerUserInfo(Seller sellerUserInfo) {
		this.sellerUserInfo = sellerUserInfo;
	}
	
	
	
}
