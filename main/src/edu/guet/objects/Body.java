package edu.guet.objects;

public class Body {
	private String name;
	private String pic; //�����������壬2d�����ʹ���һ�����
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
