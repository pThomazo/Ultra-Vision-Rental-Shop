package model;

public enum Titletype {
	

	Music (cod: 0, name: "Music"), LiveConcert(cod: 1, name:  "Live concert"), Movie(cod: 2, name: "Movie"), BoxSet(cod: 3, name: "Box set");

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
