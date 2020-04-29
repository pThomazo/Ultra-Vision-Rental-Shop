package model;

public class Customer {
	
	private int id;
	private String name;
	private MembershipCard memberCard;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public MembershipCard getMemberCard() {
		return memberCard;
	}
	
	public void setMemberCard(MembershipCard memberCard) {
		this.memberCard = memberCard;
	}
	
	

}
