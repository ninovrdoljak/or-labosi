package hr.fer.or.lab3.RestAPI.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "zupanija", uniqueConstraints = {
        @UniqueConstraint(columnNames = "naziv")
})
public class Zupanija {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String naziv;
    private String sjediste;
    
    private int stanovnistvo;
    
    private int brojgradova;
    
    private int brojopcina;
    
    private int brojnaselja;
    
    private int povrsina;
    
    private String zupan;
    
    private String wikipoveznica;
    
    private int gdppercapita;
    /*
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "zupanija_id")
    private Set<Grad> gradovi = new HashSet<>();
     */
    
    public Zupanija() {
    	
    }
	public Zupanija(String naziv, String sjediste, int stanovnistvo, int brojgradova,
			int brojopcina, int brojnaselja, int povrsina, String zupan, String wikipoveznica, int gdppercapita) {
		super();
		this.naziv = naziv;
		this.sjediste = sjediste;
		
		this.stanovnistvo = stanovnistvo;
		this.brojgradova = brojgradova;
		this.brojopcina = brojopcina;
		this.brojnaselja = brojnaselja;
		this.povrsina = povrsina;
		this.zupan = zupan;
		this.wikipoveznica = wikipoveznica;
		this.gdppercapita = gdppercapita;
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

	public String getSjediste() {
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

	public String getZupan() {
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

}
