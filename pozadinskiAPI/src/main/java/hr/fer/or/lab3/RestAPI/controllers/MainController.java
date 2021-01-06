package hr.fer.or.lab3.RestAPI.controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.or.lab3.RestAPI.dto.GradDTO;
import hr.fer.or.lab3.RestAPI.dto.LinksDTO;
import hr.fer.or.lab3.RestAPI.dto.MessageResponse;
import hr.fer.or.lab3.RestAPI.dto.SjedisteDTO;
import hr.fer.or.lab3.RestAPI.dto.WikiDTO;
import hr.fer.or.lab3.RestAPI.dto.ZupanDTO;
import hr.fer.or.lab3.RestAPI.dto.ZupanijaDTO;
import hr.fer.or.lab3.RestAPI.dto.ZupanijaReqDTO;
import hr.fer.or.lab3.RestAPI.models.Grad;
import hr.fer.or.lab3.RestAPI.models.Zupanija;
import hr.fer.or.lab3.RestAPI.repository.GradRepository;
import hr.fer.or.lab3.RestAPI.repository.ZupanijaRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/zupanije")
public class MainController {
	
	@Autowired
	ZupanijaRepository zupanijaRepository;
	
	@Autowired
	GradRepository gradRepository;
	
	@GetMapping("/sve")
    public ResponseEntity<?> dobijSveZupanije() {
		
		List<ZupanijaDTO> rez = new ArrayList<>();
		
		zupanijaRepository.findAll().forEach(zup -> {
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/sve/gradovi","Svi gradovi","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId(),"Self","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/naziv/"+zup.getNaziv(),"Self, by name","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId()+"/gradovi","Svi gradovi u ovoj županiji","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId(),"Ažuriranje ove županije","POST"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId(),"Brisanje ove županije","DELETE"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/","Dodaj novu županiju","PUT"));
			if (zup.getBrojgradova()!=0) {
				gradRepository.findAll().forEach(grad -> {
					if (grad.getNaziv().equals(zup.getNaziv())) {
						lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId() +"/"+grad.getId(),
								"Grad u ovoj županiji: "+grad.getNazivgrada(),"GET"));
					}
				});
			}
			rez.add(new ZupanijaDTO(zup.getId(), zup.getNaziv(), zup.getSjediste(), 
									zup.getStanovnistvo(), zup.getBrojgradova(), 
									zup.getBrojopcina(), zup.getBrojnaselja(), 
									zup.getPovrsina(), zup.getZupan(), 
									zup.getWikipoveznica(), zup.getGdppercapita(), lista));
		});
		return ResponseEntity.ok(new MessageResponse("OK", "Dohvacene sve županije.", rez));
	}
	
	@GetMapping("/sve/gradovi")
    public ResponseEntity<?> dobijSveGradove() {
		
		List<GradDTO> rez = new ArrayList<>();
		
		gradRepository.findAll().forEach(gr -> {
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/sve","Sve županije","GET"));
			
			Long idZup;
			if (zupanijaRepository.findByNaziv(gr.getNaziv()).isPresent()) {
				idZup = zupanijaRepository.findByNaziv(gr.getNaziv()).get().getId();
				lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+idZup,"Županija ovog grada","GET"));
				lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+idZup+"/"+gr.getId(),"Self","GET"));
			}
			
			rez.add(new GradDTO(gr.getId(), gr.getNazivgrada(),gr.getNaziv(), gr.getStanovnistvograd(), lista));
		});
		
		return ResponseEntity.ok(new MessageResponse("OK", "Dohvaceni svi gradovi.", rez));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> dobijZupanijuPoNazivu(@PathVariable Long id) {
		
		Zupanija zupanija;
		ZupanijaDTO z = null;
		try {
			zupanija = zupanijaRepository.findById(id).get();
			
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/sve","Sve županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Self","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/naziv/"+zupanija.getNaziv(),"Self, by name","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/gradovi","Svi gradovi u ovoj županiji","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Ažuriranje ove županije","POST"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Brisanje ove županije","DELETE"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/","Dodaj novu županiju","PUT"));
			if (zupanija.getBrojgradova()!=0) {
				gradRepository.findAll().forEach(grad -> {
					if (grad.getNaziv().equals(zupanija.getNaziv())) {
						lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId() +"/"+grad.getId(),
								"Grad u ovoj županiji: "+grad.getNazivgrada(),"GET"));
					}
				});
			}
			
			z = new ZupanijaDTO(zupanija.getId(), zupanija.getNaziv(), zupanija.getSjediste(), 
					zupanija.getStanovnistvo(), zupanija.getBrojgradova(), 
					zupanija.getBrojopcina(), zupanija.getBrojnaselja(), 
					zupanija.getPovrsina(), zupanija.getZupan(), 
					zupanija.getWikipoveznica(), zupanija.getGdppercapita(), lista);
			
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new MessageResponse("OK", "Dohvacena zupanija sa ID-jem: "+ id, z));
	}
	
	@GetMapping("/id/{id}/gradovi")
	public ResponseEntity<?> dobijGradoveZaZupaniju(@PathVariable Long id) {
		
		Zupanija zupanija;
		List<GradDTO> rez=new ArrayList<>();
		try {
			zupanija = zupanijaRepository.findById(id).get();
			
			
			gradRepository.findAll().forEach(grad -> {
				if (grad.getNaziv().equals(zupanija.getNaziv())) {
					List<LinksDTO> lista = new ArrayList<>();
					lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id,"Županija ovog grada","GET"));
					lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/"+grad.getId(),"Self","GET"));
					rez.add(new GradDTO(grad.getId(), grad.getNazivgrada(), grad.getNaziv(),grad.getStanovnistvograd(), lista));
				}
			});
			
			
			
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(new MessageResponse("OK", "Dohvaceni gradovi "+zupanija.getNaziv()+ " županije.", rez));
	}
	
	@GetMapping("/id/{id}/{idGrada}")
	public ResponseEntity<?> dobijSjedisteZaZupaniju(@PathVariable Long id,@PathVariable Long idGrada) {
		
		Zupanija zupanija;
		Grad grad;
		
		GradDTO rez = null;
		try {
			zupanija = zupanijaRepository.findById(id).get();
			grad = gradRepository.findById(idGrada).get();
			List<LinksDTO> lista = new ArrayList<>();
			if (!grad.getNaziv().equals(zupanija.getNaziv())) return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id,"Županija ovog grada","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/"+idGrada,"Self","GET"));
			
			rez = new GradDTO(idGrada, grad.getNazivgrada(), grad.getNaziv(), grad.getStanovnistvograd(), lista);
			
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(new MessageResponse("OK", "Dohvaceni grad "+zupanija.getNaziv()+ " županije.", rez));
	}
	
	@GetMapping("/id/{id}/sjediste")
	public ResponseEntity<?> dobijSjedisteZaZupaniju(@PathVariable Long id) {
		SjedisteDTO rez = null;
		Zupanija zupanija;
		try {
			zupanija = zupanijaRepository.findById(id).get();
			if (zupanija.getSjediste() == null) return ResponseEntity.badRequest().body(new MessageResponse("Not found","Ta zupanija nema sjediste!",null));
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/sjediste","Županija ovog sjedista","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/sjediste","Self","GET"));
			rez = new SjedisteDTO(zupanija.getSjediste(), lista);
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(new MessageResponse("OK", "Dohvaceno sjedište "+zupanija.getNaziv()+ " županije.", rez));
	}
	
	@GetMapping("/id/{id}/zupan")
	public ResponseEntity<?> dobijZupanZaZupaniju(@PathVariable Long id) {
		ZupanDTO rez = null;
		Zupanija zupanija;
		try {
			zupanija = zupanijaRepository.findById(id).get();
			if (zupanija.getZupan() == null) return ResponseEntity.badRequest().body(new MessageResponse("Not found","Ta zupanija nema župana!",null));
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/zupan","Županija ovog župana","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/zupan","Self","GET"));
			rez = new ZupanDTO(zupanija.getZupan(), lista);
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(new MessageResponse("OK", "Dohvaceno župana "+zupanija.getNaziv()+ " županije.", rez));
	}
	
	@GetMapping("/id/{id}/wiki")
	public ResponseEntity<?> dobijWikiZaZupaniju(@PathVariable Long id) {
		WikiDTO rez = null;
		Zupanija zupanija;
		try {
			zupanija = zupanijaRepository.findById(id).get();
			if (zupanija.getWikipoveznica() == null) return ResponseEntity.badRequest().body(new MessageResponse("Not found","Ta zupanija nema wiki poveznicu!",null));
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/wiki","Županija ovog župana","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+id+"/wiki","Self","GET"));
			rez = new WikiDTO(zupanija.getWikipoveznica(), lista);
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(new MessageResponse("OK", "Dohvaceno wiki poveznicu "+zupanija.getNaziv()+ " županije.", rez));
	}
	
	@GetMapping("/naziv/{naziv}")
	public ResponseEntity<?> dobijZupanijuPoNazivu(@PathVariable String naziv) {
		
		ZupanijaDTO z = null;
		Zupanija zupanija;
		try {
			zupanija =  zupanijaRepository.findByNaziv(naziv).get();

			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/sve","Sve županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Self","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/naziv/"+zupanija.getNaziv(),"Self, by name","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/gradovi","Svi gradovi u ovoj županiji","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Ažuriranje ove županije","POST"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Brisanje ove županije","DELETE"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/","Dodaj novu županiju","PUT"));
			if (zupanija.getBrojgradova()!=0) {
				gradRepository.findAll().forEach(grad -> {
					if (grad.getNaziv().equals(zupanija.getNaziv())) {
						lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId() +"/"+grad.getId(),
								"Grad u ovoj županiji: "+grad.getNazivgrada(),"GET"));
					}
				});
			}
			
			z = new ZupanijaDTO(zupanija.getId(), zupanija.getNaziv(), zupanija.getSjediste(), 
					zupanija.getStanovnistvo(), zupanija.getBrojgradova(), 
					zupanija.getBrojopcina(), zupanija.getBrojnaselja(), 
					zupanija.getPovrsina(), zupanija.getZupan(), 
					zupanija.getWikipoveznica(), zupanija.getGdppercapita(), lista);
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new MessageResponse("OK", "Dohvacena zupanija sa nazivom: "+ naziv, z));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> izbrisiZupanijuPoIDu(@PathVariable Long id) {
		ZupanijaDTO z = null;
		Zupanija zupanija;
		try {
			zupanija = zupanijaRepository.findById(id).get();
			
			
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/sve","Sve županije","GET"));
			
			z = new ZupanijaDTO(zupanija.getId(), zupanija.getNaziv(), zupanija.getSjediste(), 
					zupanija.getStanovnistvo(), zupanija.getBrojgradova(), 
					zupanija.getBrojopcina(), zupanija.getBrojnaselja(), 
					zupanija.getPovrsina(), zupanija.getZupan(), 
					zupanija.getWikipoveznica(), zupanija.getGdppercapita(), lista);
			
			
			zupanijaRepository.delete(zupanija);
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new MessageResponse("OK", "Izbrisana zupanija sa id-jem: "+ id, z));
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<?> updateajZupaniju(@PathVariable Long id, @RequestBody ZupanijaReqDTO zupanijaDTO) {
		Zupanija zupanija;
		ZupanijaDTO z = null;
		try {
			zupanija = zupanijaRepository.findById(id).get();
			zupanija.setBrojgradova(zupanijaDTO.getBrojgradova());
			zupanija.setBrojnaselja(zupanijaDTO.getBrojnaselja());
			zupanija.setBrojopcina(zupanijaDTO.getBrojopcina());
			zupanija.setGdppercapita(zupanijaDTO.getGdppercapita());
			zupanija.setNaziv(zupanijaDTO.getNaziv());
			zupanija.setPovrsina(zupanijaDTO.getPovrsina());
			zupanija.setSjediste(zupanijaDTO.getSjediste());
			zupanija.setStanovnistvo(zupanijaDTO.getStanovnistvo());
			zupanija.setWikipoveznica(zupanijaDTO.getWikipoveznica());
			zupanija.setZupan(zupanijaDTO.getZupan());
			zupanijaRepository.save(zupanija);
			
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/sve","Sve županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Self","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/naziv/"+zupanija.getNaziv(),"Self, by name","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/gradovi","Svi gradovi u ovoj županiji","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Ažuriranje ove županije","POST"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Brisanje ove županije","DELETE"));
			
			
			z = new ZupanijaDTO(zupanija.getId(), zupanija.getNaziv(), zupanija.getSjediste(), 
					zupanija.getStanovnistvo(), zupanija.getBrojgradova(), 
					zupanija.getBrojopcina(), zupanija.getBrojnaselja(), 
					zupanija.getPovrsina(), zupanija.getZupan(), 
					zupanija.getWikipoveznica(), zupanija.getGdppercapita(), lista);
			
			
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Takav ID ne postoji!",null), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new MessageResponse("OK", "Ažurirana zupanija sa id-jem: "+ id, z));
	}
	
	@PostMapping("/")
	public ResponseEntity<?> dodajZupaniju(@RequestBody ZupanijaReqDTO zupanijaDTO) {
		Zupanija zupanija;
		ZupanijaDTO z = null;
		try {
			zupanija = new Zupanija(zupanijaDTO.getNaziv(), zupanijaDTO.getSjediste(), 
									zupanijaDTO.getStanovnistvo(), zupanijaDTO.getBrojgradova(), 
									zupanijaDTO.getBrojopcina(), zupanijaDTO.getBrojnaselja(), 
									zupanijaDTO.getPovrsina(), zupanijaDTO.getZupan(), 
									zupanijaDTO.getWikipoveznica(), zupanijaDTO.getGdppercapita());
			zupanijaRepository.save(zupanija);
			
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/sve","Sve županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Self","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/naziv/"+zupanija.getNaziv(),"Self, by name","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/gradovi","Svi gradovi u ovoj županiji","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/sjediste","Sjediste ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/zupan","Župan ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/wiki","Dio wiki poveznice za stranicu ove županije","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId(),"Brisanje ove županije","DELETE"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/","Dodaj novu županiju","PUT"));
			
			z = new ZupanijaDTO(zupanija.getId(), zupanija.getNaziv(), zupanija.getSjediste(), 
					zupanija.getStanovnistvo(), zupanija.getBrojgradova(), 
					zupanija.getBrojopcina(), zupanija.getBrojnaselja(), 
					zupanija.getPovrsina(), zupanija.getZupan(), 
					zupanija.getWikipoveznica(), zupanija.getGdppercapita(), lista);
			
		} catch(Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Not found","Krivo predani argumenti!",null), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new MessageResponse("OK", "Ubačena županija sa id-jem: "+ zupanija.getId(), z));
	}
}