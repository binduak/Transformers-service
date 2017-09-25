package com.tw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Items extends AbstractEntity<Long>{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "itemid")
	private long itemId;
	@Column(name = "image_path")
	private String imagePath;
	@Column(name = "item_name")
	private String itemName;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private float price;
	@Column(name = "quantity")
	private int quantity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryid", nullable = false)
	private Category itemCategoryType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sellerid", nullable = false)
	private Seller itemOwner;
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Category getItemCategoryType() {
		return itemCategoryType;
	}
	public void setItemCategoryType(Category itemCategoryType) {
		this.itemCategoryType = itemCategoryType;
	}
	public Seller getItemOwner() {
		return itemOwner;
	}
	public void setItemOwner(Seller itemOwner) {
		this.itemOwner = itemOwner;
	}
	
	
}
