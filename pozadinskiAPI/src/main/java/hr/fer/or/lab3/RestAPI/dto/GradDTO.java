package hr.fer.or.lab3.RestAPI.dto;

public class GradDTO {
	
	private Long id;
	private String nazivgrada;
	private String naziv;
	private int stanovnistvograd;
	private Object links;
	
	public GradDTO(Long id, String nazivgrada, String naziv, int stanovnistvograd, Object links) {
		super();
		this.id = id;
		this.nazivgrada = nazivgrada;
		this.naziv = naziv;
		this.stanovnistvograd = stanovnistvograd;
		this.links = links;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNazivgrada() {
		return nazivgrada;
	}
	public void setNazivgrada(String nazivgrada) {
		this.nazivgrada = nazivgrada;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getStanovnistvograd() {
		return stanovnistvograd;
	}
	public void setStanovnistvograd(int stanovnistvograd) {
		this.stanovnistvograd = stanovnistvograd;
	}
	public Object getLinks() {
		return links;
	}
	public void setLinks(Object links) {
		this.links = links;
	}
	
}
