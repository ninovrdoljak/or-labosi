package hr.fer.or.lab3.RestAPI;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hr.fer.or.lab3.RestAPI.models.Grad;
import hr.fer.or.lab3.RestAPI.models.Zupanija;
import hr.fer.or.lab3.RestAPI.repository.GradRepository;
import hr.fer.or.lab3.RestAPI.repository.ZupanijaRepository;

@SpringBootApplication
public class RestApiApplication {
	
	@Autowired
	GradRepository gradRepository;
	
	@Autowired
	ZupanijaRepository zupanijaRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	
	    	gradRepository.deleteAll();
	    	
	    	gradRepository.save(new Grad("Orahovica", "Virovitičko-podravska županija", 5304));
	    	gradRepository.save(new Grad("Slatina", "Virovitičko-podravska županija", 13686));
	    	gradRepository.save(new Grad("Virovitica", "Virovitičko-podravska županija", 21291));
	    	gradRepository.save(new Grad("Ivanić Grad", "Zagrebačka županija", 14548));
	    	gradRepository.save(new Grad("Samobor", "Zagrebačka županija", 37728));
	    	gradRepository.save(new Grad("Velika Gorica", "Zagrebačka županija", 63514));
	    	gradRepository.save(new Grad("Dugo Selo", "Zagrebačka županija", 17466));
	    	gradRepository.save(new Grad("Jastrebarsko", "Zagrebačka županija", 16689));
	    	gradRepository.save(new Grad("Sveta Nedelja", "Zagrebačka županija", 18059));
	    	gradRepository.save(new Grad("Sveti Ivan Zelina", "Zagrebačka županija", 15959));
	    	gradRepository.save(new Grad("Vrbovec", "Zagrebačka županija", 14797));
	    	gradRepository.save(new Grad("Zaprešić", "Zagrebačka županija", 25223));
	    	gradRepository.save(new Grad("Karlovac", "Karlovačka županija", 55705));
	    	gradRepository.save(new Grad("Duga resa", "Karlovačka županija", 10583));
	    	gradRepository.save(new Grad("Ogulin", "Karlovačka županija", 13915));
	    	gradRepository.save(new Grad("Slunj", "Karlovačka županija", 5076));
	    	gradRepository.save(new Grad("Ozalj", "Karlovačka županija", 7932));
	    	gradRepository.save(new Grad("Đurđevac", "Koprivničko-križevačka županija", 8862));
	    	gradRepository.save(new Grad("Koprivnica", "Koprivničko-križevačka županija", 30854));
	    	gradRepository.save(new Grad("Križevci", "Koprivničko-križevačka županija", 21122));
	    	gradRepository.save(new Grad("Nova Gradiška", "Brodsko-posavska županija", 14229));
	    	gradRepository.save(new Grad("Slavonski Brod", "Brodsko-posavska županija", 59141));
	    	gradRepository.save(new Grad("Čakovec", "Međimurska županija", 27104));
	    	gradRepository.save(new Grad("Prelog", "Međimurska županija", 7871));
	    	gradRepository.save(new Grad("Mursko Središće", "Međimurska županija", 6307));
	    	gradRepository.save(new Grad("Dubrovnik", "Dubrovačko-neretvanska županija", 42615));
	    	gradRepository.save(new Grad("Korčula", "Dubrovačko-neretvanska županija", 5663));
	    	gradRepository.save(new Grad("Metković", "Dubrovačko-neretvanska županija", 16788));
	    	gradRepository.save(new Grad("Opuzen", "Dubrovačko-neretvanska županija", 3254));
	    	gradRepository.save(new Grad("Ploče", "Dubrovačko-neretvanska županija", 9415));
	    	gradRepository.save(new Grad("Ilok", "Vukovarsko-srijemska županija", 6767));
	    	gradRepository.save(new Grad("Otok", "Vukovarsko-srijemska županija", 7755));
	    	gradRepository.save(new Grad("Vinkovci", "Vukovarsko-srijemska županija", 32029));
	    	gradRepository.save(new Grad("Županja", "Vukovarsko-srijemska županija", 12090));
	    	gradRepository.save(new Grad("Vukovar", "Vukovarsko-srijemska županija", 27683));
	    	gradRepository.save(new Grad("Gospić", "Ličko-senjska županija", 12980));
	    	gradRepository.save(new Grad("Novalja", "Ličko-senjska županija", 3335));
	    	gradRepository.save(new Grad("Otočac", "Ličko-senjska županija", 4240));
	    	gradRepository.save(new Grad("Senj", "Ličko-senjska županija", 8132));
	    	gradRepository.save(new Grad("Kutjevo", "Požeško-slavonska županija", 6247));
	    	gradRepository.save(new Grad("Lipik", "Požeško-slavonska županija", 6170));
	    	gradRepository.save(new Grad("Pakrac", "Požeško-slavonska županija", 8460));
	    	gradRepository.save(new Grad("Požega", "Požeško-slavonska županija", 26248));
	    	gradRepository.save(new Grad("Pleternica", "Požeško-slavonska županija", 11323));
	    	

	    	zupanijaRepository.deleteAll();
	    	zupanijaRepository.save(new Zupanija("Virovitičko-podravska županija", "Virovitica", 84836, 3, 13, 188, 2024, "Igor Andrović", "Virovitičko-podravska_županija", 7641));
	    	zupanijaRepository.save(new Zupanija("Zagrebačka županija", "Grad Zagreb", 317606, 9, 25, 694, 3060, "Stjepan Kožić", "Zagrebačka_županija", 10358));
	    	zupanijaRepository.save(new Zupanija("Karlovačka županija", "Karlovac", 128899, 5, 17, 649, 3622, "Damir Jelić", "Karlovačka_županija", 10158));
	    	zupanijaRepository.save(new Zupanija("Koprivničko-križevačka županija", "Koprivnica", 115584, 3, 22, 264, 1748, "Darko Koren", "Koprivničko-križevačka_županija", 10583));
	    	zupanijaRepository.save(new Zupanija("Brodsko-posavska županija", "Slavonski Brod", 158575, 2, 26, 185, 2030, "Danijel Marušić", "Brodsko-posavska_županija", 7746));
	    	zupanijaRepository.save(new Zupanija("Međimurska županija", "Čakovec", 113804, 3, 22, 131, 729, "Matija Posavec", "Međimurska_županija", 10847));
	    	zupanijaRepository.save(new Zupanija("Dubrovačko-neretvanska županija", "Dubrovnik", 122568, 5, 17, 230, 1781, "Nikola Dobroslavić", "Dubrovačko-neretvanska_županija", 12415));
	    	zupanijaRepository.save(new Zupanija("Vukovarsko-srijemska županija", "Vukovar", 179521, 5, 26, 85, 2454, "Božo Galić", "Vukovarsko-srijemska_županija", 8003));
	    	zupanijaRepository.save(new Zupanija("Ličko-senjska županija", "Gospić", 50927, 4, 8, 255, 5353, "Darko Milinović", "Ličko-senjska_županija", 9831));
	    	zupanijaRepository.save(new Zupanija("Požeško-slavonska županija", "Požega", 78034, 5, 5, 277, 1823, "Alojz Tomašević", "Požeško-slavonska_županija", 7874));
	    	
	      };
	   }
}
