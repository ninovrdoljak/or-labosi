package hr.fer.or.lab3.RestAPI.dto;

public class WikiDTO {
	
	private String wiki;
	private Object links;
	public WikiDTO(String wiki, Object links) {
		super();
		this.wiki = wiki;
		this.links = links;
	}
	public String getWiki() {
		return wiki;
	}
	public void setWiki(String wiki) {
		this.wiki = wiki;
	}
	public Object getLinks() {
		return links;
	}
	public void setLinks(Object links) {
		this.links = links;
	}

}
