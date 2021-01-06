package hr.fer.or.lab3.RestAPI.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grad")
public class Grad {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nazivgrada;
	private String naziv;
	private int stanovnistvograd;

	public Grad(String nazivgrada, String naziv, int stanovnistvograd) {
		super();
		this.nazivgrada = nazivgrada;
		this.naziv = naziv;
		this.stanovnistvograd = stanovnistvograd;
	}
	
	public Grad() {
		
	}
	
	
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNazivgrada() {
		return nazivgrada;
	}

	public void setNazivgrada(String nazivgrada) {
		this.nazivgrada = nazivgrada;
	}

	public int getStanovnistvograd() {
		return stanovnistvograd;
	}

	public void setStanovnistvograd(int stanovnistvograd) {
		this.stanovnistvograd = stanovnistvograd;
	}
	
	

}
