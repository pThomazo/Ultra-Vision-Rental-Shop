package model;

public enum Media {
	
	CD( 0,"CD"), DVD( 1, "DVD"), BLURAY( 2,"Blueray");
	
	int cod;
	String name;
	
	private Media(int cod, String name) {
		this.cod = cod;
		this.name = name;
	}
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static Media fromCode(int code) {
		switch (code) {
		
		case 0: return Media.CD;
		case 1: return Media.DVD;
		case 2: return Media.BLURAY;
		
		default: throw new IllegalArgumentException();
		}
	}

}
