package model;

public enum Plan {
	
	
	
	MusicLovers( 0, "Music Lovers"), VideoLovers( 1,  "Video Lovers"), TVLovers( 2,  "TV Lovers"), Premium( 3, "Preminum");
	
	public int cod;
	public String name;
	
	private Plan (int cod, String name) {
		this.cod = cod;
		this.name = name;
	}
}
