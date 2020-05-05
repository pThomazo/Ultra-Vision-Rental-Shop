package model;

public class Title {
	
	//declaring variables in the class
	private Integer id;
	private String name;
	private int yearReleased;
	private Media midia;
	private Titletype type;
	private String genre;
	
	//constructor
	public Title() {
		
	}

	//getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}

	public Media getMidia() {
		return midia;
	}

	public void setMidia(Media midia) {
		this.midia = midia;
	}

	public Titletype getType() {
		return type;
	}

	public void setType(Titletype type) {
		this.type = type;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
