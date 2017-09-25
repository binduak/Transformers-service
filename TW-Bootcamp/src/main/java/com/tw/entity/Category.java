package com.tw.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends AbstractEntity<Long>{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categoryid")
	private int categoryId;
	@Column(name = "category_name")
	private String categoryName;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOwner")
	private List<Items> cateoryItemList = new ArrayList<Items>(0);
	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Items> getCateoryItemList() {
		return cateoryItemList;
	}
	public void setCateoryItemList(List<Items> cateoryItemList) {
		this.cateoryItemList = cateoryItemList;
	}
	
	
}
