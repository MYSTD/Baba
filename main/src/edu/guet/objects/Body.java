package edu.guet.objects;

public class Body {
	private String name;
	private String pic; //暂且这样定义，2d引擎的使用我还不会
	public String getName() {
		return this.name;
	}
	public String getPic() {

		return this.pic;
	}
	Body(String name, String pic) {
		this.name = name;
		this.pic = pic;
	}
}
