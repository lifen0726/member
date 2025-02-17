//package com.library.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.IdClass;
//import jakarta.persistence.Table;
//import jakarta.persistence.Column;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.JoinColumn;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "Borrowing_Records")
//@IdClass(BorrowingRecordId.class)
//public class BorrowingRecord {
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "userId")
//    private Users user;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "inventory_id", referencedColumnName = "inventoryId")
//    private Inventory inventory;
//
//    @Id
//    private LocalDateTime borrowingTime;
//
//    @Column(nullable = true)
//    private LocalDateTime returnTime;
//
//    // Getters and Setters
//	public Users getUser() {
//		return user;
//	}
//
//	public void setUser(Users user) {
//		this.user = user;
//	}
//
//	public Inventory getInventory() {
//		return inventory;
//	}
//
//	public void setInventory(Inventory inventory) {
//		this.inventory = inventory;
//	}
//
//	public LocalDateTime getBorrowingTime() {
//		return borrowingTime;
//	}
//
//	public void setBorrowingTime(LocalDateTime borrowingTime) {
//		this.borrowingTime = borrowingTime;
//	}
//
//	public LocalDateTime getReturnTime() {
//		return returnTime;
//	}
//
//	public void setReturnTime(LocalDateTime returnTime) {
//		this.returnTime = returnTime;
//	}
//
//	@Override
//	public String toString() {
//		return "BorrowingRecord [user=" + user + ", inventory=" + inventory + ", borrowingTime=" + borrowingTime
//				+ ", returnTime=" + returnTime + "]";
//	}
//
//	public BorrowingRecord() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//    
//}

