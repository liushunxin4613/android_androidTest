package entity;

public class Find {
	private int index;
	private String name;
	private String id;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Find(int index, String name, String id) {
		this.index = index;
		this.name = name;
		this.id = id;
	}
	public Find() {
	}
}
