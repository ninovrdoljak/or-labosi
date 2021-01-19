package hr.fer.or.lab3.RestAPI.dto;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldId;

public class ZupanijaDTO {
	
	@JsonldId
	private Long id;
	
	
	private String naziv;
	
	
	private String _context;
	
	
    public String get_context() {
		return _context;
	}

	public void set_context(String _context) {
		this._context = _context;
	}
	
	private String _type;
	
	private String slika;

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public void setSjediste(Object sjediste) {
		this.sjediste = sjediste;
	}

	public void setZupan(Object zupan) {
		this.zupan = zupan;
	}

	private Object sjediste;
    
    private int stanovnistvo;
    
    private int brojgradova;
    
    private int brojopcina;
    
    private int brojnaselja;
    
    private int povrsina;
    
    private Object zupan;
    
    private String wikipoveznica;
    
    private int gdppercapita;
    
    private Object links;
    
    
	public ZupanijaDTO(Long id, String naziv, String sjediste, int stanovnistvo, int brojgradova, int brojopcina,
			int brojnaselja, int povrsina, String zupan, String wikipoveznica, int gdppercapita, Object links) {
		super();
		this.id = id;
		this._type = "State";
		this.naziv = naziv;
		this.sjediste = new PomocniZupan(false, sjediste);
		this.stanovnistvo = stanovnistvo;
		this.brojgradova = brojgradova;
		this.brojopcina = brojopcina;
		this.brojnaselja = brojnaselja;
		this.povrsina = povrsina;
		this.zupan = new PomocniZupan(true, zupan);
		this.wikipoveznica = wikipoveznica;
		this.gdppercapita = gdppercapita;
		this.links = links;
		this._context = "https://schema.org/";
		this.slika = "http://localhost:8080/api/v1/zupanije/id/"+id+"/picture";
	}
	
	public ZupanijaDTO() {
		
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Object getSjediste() {
		return sjediste;
	}

	public void setSjediste(String sjediste) {
		this.sjediste = sjediste;
	}

	public int getStanovnistvo() {
		return stanovnistvo;
	}

	public void setStanovnistvo(int stanovnistvo) {
		this.stanovnistvo = stanovnistvo;
	}

	public int getBrojgradova() {
		return brojgradova;
	}

	public void setBrojgradova(int brojgradova) {
		this.brojgradova = brojgradova;
	}

	public int getBrojopcina() {
		return brojopcina;
	}

	public void setBrojopcina(int brojopcina) {
		this.brojopcina = brojopcina;
	}

	public int getBrojnaselja() {
		return brojnaselja;
	}

	public void setBrojnaselja(int brojnaselja) {
		this.brojnaselja = brojnaselja;
	}

	public int getPovrsina() {
		return povrsina;
	}

	public void setPovrsina(int povrsina) {
		this.povrsina = povrsina;
	}

	public Object getZupan() {
		return zupan;
	}

	public void setZupan(String zupan) {
		this.zupan = zupan;
	}

	public String getWikipoveznica() {
		return wikipoveznica;
	}

	public void setWikipoveznica(String wikipoveznica) {
		this.wikipoveznica = wikipoveznica;
	}

	public int getGdppercapita() {
		return gdppercapita;
	}

	public void setGdppercapita(int gdppercapita) {
		this.gdppercapita = gdppercapita;
	}

	public Object getLinks() {
		return links;
	}

	public void setLinks(Object links) {
		this.links = links;
	}
    
    

}
