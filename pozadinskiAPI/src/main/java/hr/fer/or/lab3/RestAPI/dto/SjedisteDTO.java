package hr.fer.or.lab3.RestAPI.dto;

public class SjedisteDTO {
	
	private String sjediste;
	private Object links;
	public SjedisteDTO(String sjediste, Object links) {
		super();
		this.sjediste = sjediste;
		this.links = links;
	}
	public String getSjediste() {
		return sjediste;
	}
	public void setSjediste(String sjediste) {
		this.sjediste = sjediste;
	}
	public Object getLinks() {
		return links;
	}
	public void setLinks(Object links) {
		this.links = links;
	}
	
	

}
