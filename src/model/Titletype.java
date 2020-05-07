package model;

public enum Titletype {

	Music(0, "Music"), LiveConcert(1, "Live concert"), Movie(2, "Movie"), BoxSet(3, "Box set");

	public int cod;
	public String name;

	private Titletype(int cod, String name) {
		this.cod = cod;
		this.name = name;
	}

	public static Titletype fromCode(int code) {
		switch (code) {
		case 0:
			return Music;
		case 1:
			return Titletype.LiveConcert;
		case 2:
			return Movie;
		case 3:
			return BoxSet;
		default:
			throw new IllegalArgumentException();
		}
	}
}
