package com.tw.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "buyer")
public class Buyer extends AbstractEntity<Long>{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "buyerid")
	private long buyerId;
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="userid", unique= true, nullable=true, insertable=true, updatable=true)
	private Users buyerUserInfo;
	@Column(name = "gender")
	private String gender;
	@Temporal(TemporalType.DATE)
    @Column(name = "dob", nullable = false)
	private Date dob;
	
	public long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}
	
	public Users getBuyerUserInfo() {
		return buyerUserInfo;
	}
	public void setBuyerUserInfo(Users buyerUserInfo) {
		this.buyerUserInfo = buyerUserInfo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
}
