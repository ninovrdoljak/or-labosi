package hr.fer.or.lab3.RestAPI.dto;

public class LinksDTO {
	
	private String href;
	private String rel;
	private String type;
	public LinksDTO(String href, String rel, String type) {
		super();
		this.href = href;
		this.rel = rel;
		this.type = type;
	}

	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
