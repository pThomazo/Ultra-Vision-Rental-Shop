package model;

public enum Plan {
	
	
	
	MusicLovers(cod: 0, name: "Music Lovers"), VideoLovers(cod: 1, name: "Video Lovers"), TVLovers(cod: 2, name: "TV Lovers"), Premium(cod: 3, name: "Preminum");
	
	public int cod;
	public String name;
	
	private Plan (int cod, String name) {
		this.cod = cod;
		this.name = name;
	}
}
