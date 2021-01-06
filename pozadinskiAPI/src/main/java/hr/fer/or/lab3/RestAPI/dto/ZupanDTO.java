package hr.fer.or.lab3.RestAPI.dto;

public class ZupanDTO {

	private String zupan;
	private Object links;
	public ZupanDTO(String zupan, Object links) {
		super();
		this.zupan = zupan;
		this.links = links;
	}
	public String getZupan() {
		return zupan;
	}
	public void setZupan(String zupan) {
		this.zupan = zupan;
	}
	public Object getLinks() {
		return links;
	}
	public void setLinks(Object links) {
		this.links = links;
	}
}
