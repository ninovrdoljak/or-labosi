package hr.fer.or.lab3.RestAPI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.or.lab3.RestAPI.models.Zupanija;
import hr.fer.or.lab3.RestAPI.repository.ZupanijaRepository;


@Service
public class StaticHelper {
	
	@Autowired
	ZupanijaRepository zupanijaRepository;
	
	public List<Zupanija> dobijZupanije(String atribut, String pretraga) {
		List<Zupanija> rez = new ArrayList<>();
		if (atribut.equals("naziv") || atribut.equals("sjediste") || atribut.equals("all")|| atribut.equals("zupan") || atribut.equals("wikipoveznica")) {
			zupanijaRepository.findAll().forEach(z -> {
				switch (atribut) {
				case "naziv":
					if (z.getNaziv().equals(pretraga)) {
						rez.add(z);
					}
					break;
				case "sjediste":
					if (z.getSjediste().equals(pretraga)) {
						rez.add(z);
					}
					break;
				case "zupan":
					if (z.getZupan().equals(pretraga)) {
						rez.add(z);
					}
					break;
				case "wikipoveznica":
					if (z.getWikipoveznica().equals(pretraga)) {
						rez.add(z);
					}
					break;
				case "all":
					try {
						int pretragaInt = Integer.parseInt(pretraga);
						if (z.getBrojgradova() == pretragaInt ||
							z.getBrojnaselja() == pretragaInt ||
							z.getPovrsina() == pretragaInt ||
							z.getBrojopcina() == pretragaInt ||
							z.getStanovnistvo() == pretragaInt ||
							z.getGdppercapita() == pretragaInt ) {
							rez.add(z);
						}
					} catch (Exception e) {
						if (z.getNaziv().equals(pretraga) ||
							z.getSjediste().equals(pretraga) ||
							z.getZupan().equals(pretraga) ||
							z.getWikipoveznica().equals(pretraga)) {
							rez.add(z);
						}	
					}	
					break;				
				}
			});
		} else {
			int pretragaInt = Integer.parseInt(pretraga);
			zupanijaRepository.findAll().forEach(z -> {
				switch (atribut) {
				case "gdppercapita":
					if (z.getGdppercapita() == pretragaInt) {
						rez.add(z);
					}
					break;
				case "povrsina":
					if (z.getPovrsina() == pretragaInt) {
						rez.add(z);
					}
					break;
				case "brojnaselja":
					if (z.getBrojnaselja() == pretragaInt) {
						rez.add(z);
					}
					break;
				case "brojgradova":
					if (z.getBrojgradova() == pretragaInt) {
						rez.add(z);
					}
					break;
				case "stanovnistvo":
					if (z.getStanovnistvo() == pretragaInt) {
						rez.add(z);
					}
					break;
				case "brojopcina":
					if (z.getBrojopcina() == pretragaInt) {
						rez.add(z);
					}
					break;
				}
			});
		}
		
		return rez;
		
	}

}
