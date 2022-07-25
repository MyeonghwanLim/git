package model;

public class CategoryVO {
	private String name;
	private int num; // 카테고리 pk
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "CategoryVO [name=" + name + "]";
	}
	
}
