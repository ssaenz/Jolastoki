package eu.lapecera.jolastoki.domain;

public class Score {
	
	// Score table name
    public static final String TABLE_NAME = "score";
 
    // Score Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_AREA = 	"area";
    public static final String KEY_NAME = "name";
    public static final String KEY_SCORE = "score";
	
	private long id;
	private String area;
	private String level;
	private String name;
	private int score;
	private int position;
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public GameArea getArea() {
		return GameArea.valueOf(this.area);
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public GameLevel getLevel() {
		return GameLevel.valueOf(this.level);
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
}
