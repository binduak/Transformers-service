package com.tw.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({	
	@NamedQuery(name = "validateSellerByUsernameAndId", query = "from Seller s where s.sellerId= :validateSellerId and s.sellerUserInfo.username=:validateUsername"),
})


@Entity
@Table(name = "seller")
public class Seller extends AbstractEntity<Long>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sellerid")
	private long sellerId;
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="userid", unique= true, nullable=true, insertable=true, updatable=true)
	private Users sellerUserInfo;
	@Column(name = "experience_in_years")
	private int experienceYears;
	@Column(name = "experience_in_months")
	private int experienceMonths;
	@Column(name = "pan_card_no")
	private String panCardNo;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOwner")
	private Set<Items> sellerItemInfo = new HashSet<Items>(0);
	
	
	public Users getSellerUserInfo() {
		return sellerUserInfo;
	}
	public void setSellerUserInfo(Users sellerUserInfo) {
		this.sellerUserInfo = sellerUserInfo;
	}
	public int getExperienceYears() {
		return experienceYears;
	}
	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}
	public int getExperienceMonths() {
		return experienceMonths;
	}
	public void setExperienceMonths(int experienceMonths) {
		this.experienceMonths = experienceMonths;
	}
	public String getPanCardNo() {
		return panCardNo;
	}
	public void setPanCardNo(String panCardNo) {
		this.panCardNo = panCardNo;
	}
	public long getSellerId() {
		return sellerId;
	}
	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
	public Set<Items> getSellerItemInfo() {
		return sellerItemInfo;
	}
	public void setSellerItemInfo(Set<Items> sellerItemInfo) {
		this.sellerItemInfo = sellerItemInfo;
	}

	
	
}
