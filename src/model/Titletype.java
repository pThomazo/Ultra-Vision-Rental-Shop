package model;

public enum Titletype {
	

	Music ( 0, "Music"), LiveConcert(1, "Live concert"), Movie(2, "Movie"), BoxSet(3, "Box set");

	//declaring the variables
	public int cod;
	public String name;
	
	// Constructor
	private Titletype(int cod, String name) {
		this.cod = cod;
		this.name = name;
	}
	
	//giving the options for the customer
	public static Titletype fromCode(int code) {
		
		//using the switch-case for choosing the options
		switch (code) {
		case 0: return Music;
		case 1: return Titletype.LiveConcert;
		case 2: return Movie;
		case 3: return BoxSet;
		//case of none of the options throws the IllegalArgument
		default: throw new IllegalArgumentException();
		
		
		}
		
	}


	
}
