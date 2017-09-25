package com.tw.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Orders extends AbstractEntity<Long>{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderid")
	private long orderId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemid", nullable = false)
	private Items purchasedItem;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "delivery_address")
	private String deliveryAddress;
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ordered_datetime", nullable = false)
	private Date orderedDateTime;
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="buyerid", unique= true, nullable=true, insertable=true, updatable=true)
	private Buyer purchasedUser;
	@Column(name = "total_amount")
	private int totalAmount;
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public Items getPurchasedItem() {
		return purchasedItem;
	}
	public void setPurchasedItem(Items purchasedItem) {
		this.purchasedItem = purchasedItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public Date getOrderedDateTime() {
		return orderedDateTime;
	}
	public void setOrderedDateTime(Date orderedDateTime) {
		this.orderedDateTime = orderedDateTime;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Buyer getPurchasedUser() {
		return purchasedUser;
	}
	public void setPurchasedUser(Buyer purchasedUser) {
		this.purchasedUser = purchasedUser;
	}
	
	
}
