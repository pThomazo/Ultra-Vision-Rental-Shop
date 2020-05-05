package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Rent {
	
	//declaring in the class the variables
	private Integer id;
	private int userId;
	private Date dateRent;
	private Date dateReturn;
	private Set<Title> titles = new HashSet<>();
	
	//constructor
	public Rent(){
		
	}
	//constructor
	public Rent (Integer id, Date dateRent, Date dateReturn, Set<Title> titles) {
		this.id = id;
		this.setDateRent(dateRent);
		this.setDateReturn(dateReturn);
		this.setTitles(titles);
	}
	
	//creating get's and setter's
	
	public Integer getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDateRent() {
		return dateRent;
	}

	public void setDateRent(Date dateRent) {
		this.dateRent = dateRent;
	}

	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}

	public Set<Title> getTitles() {
		return titles;
	}

	public void setTitles(Set<Title> titles) {
		this.titles = titles;
	}
	
	@Override
	public String toString() {
		StringBuilder sbTS = new StringBuilder();
		titles.forEach((t) -> { sbTS.append(t.getName()).append(",");
		
		});
		return sbTS.substring(0, sbTS.length()-1);
	}

}
