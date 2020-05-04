package model;

public enum Media {
	
	CD(cod: 0, name: "CD"), DVD(cod: 1, name "DVD"), BLURAY(cod: 2, name: "Blueray");
	
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
