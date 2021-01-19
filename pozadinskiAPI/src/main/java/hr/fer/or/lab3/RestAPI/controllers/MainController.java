package hr.fer.or.lab3.RestAPI.controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import hr.fer.or.lab3.RestAPI.dto.GradDTO;
import hr.fer.or.lab3.RestAPI.dto.LinksDTO;
import hr.fer.or.lab3.RestAPI.dto.MessageResponse;
import hr.fer.or.lab3.RestAPI.dto.SjedisteDTO;
import hr.fer.or.lab3.RestAPI.dto.SlikaPom;
import hr.fer.or.lab3.RestAPI.dto.WikiDTO;
import hr.fer.or.lab3.RestAPI.dto.ZupanDTO;
import hr.fer.or.lab3.RestAPI.dto.ZupanijaDTO;
import hr.fer.or.lab3.RestAPI.dto.ZupanijaReqDTO;
import hr.fer.or.lab3.RestAPI.models.Grad;
import hr.fer.or.lab3.RestAPI.models.Slika;
import hr.fer.or.lab3.RestAPI.models.Zupanija;
import hr.fer.or.lab3.RestAPI.repository.GradRepository;
import hr.fer.or.lab3.RestAPI.repository.SlikaRepository;
import hr.fer.or.lab3.RestAPI.repository.ZupanijaRepository;
import ioinformarics.oss.jackson.module.jsonld.JsonldModule;
import ioinformarics.oss.jackson.module.jsonld.JsonldResource;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/zupanije")
public class MainController {
	
	@Autowired
	ZupanijaRepository zupanijaRepository;
	
	@Autowired
	GradRepository gradRepository;
	
	@Autowired
	SlikaRepository slikaRepository;
	
	@GetMapping("/sve")
	//@GetMapping(path = "/sve", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> dobijSveZupanije() {
		
		List<ZupanijaDTO> rez = new ArrayList<>();
		
		
		zupanijaRepository.findAll().forEach(zup -> {
			List<LinksDTO> lista = new ArrayList<>();
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/sve/gradovi","Svi gradovi","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId(),"Self","GET"));
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zup.getId()+"/picture","Slika","GET"));
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
			lista.add(new LinksDTO("http://localhost:8080/api/v1/zupanije/id/"+zupanija.getId()+"/picture","Slika","GET"));
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
	
	@GetMapping(
			  value = "/id/{id}/picture",
			  produces = MediaType.IMAGE_PNG_VALUE
			)
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
		
		Path root = Paths.get("slike");
		Resource resurs = null;
		
		Optional<Zupanija> z = zupanijaRepository.findById(id);
    	if (z.isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	Zupanija zup = z.get();
    	
    	try {   	    	   
 	    	Optional<Slika> slika = slikaRepository.findByNaziv(zup.getWikipoveznica());
 	    	if (slika.isEmpty()) {
 	    		//ako slika ne postoji
 	    		System.out.println("nema ju u cache-u");
 	    		saveajSliku(zup);
 	    		Date dt = new Date();
 	    		Calendar c = Calendar.getInstance(); 
 	    		c.setTime(dt); 
 	    		//c.add(Calendar.DATE, 1);
 	    		c.add(Calendar.SECOND, 20);
 	    		dt = c.getTime();
 				slikaRepository.save(new Slika(zup.getWikipoveznica(),dt));
 	    	} else {
 	    		Date now = new Date();
 	    		Calendar c = Calendar.getInstance(); 
 	    		c.setTime(now);
 	    		now = c.getTime();
 	    		//System.out.println(slika.get().getDate().toString());
 	    		//System.out.println(now.toString());
 	    		//ako slika postoji i isteklo joj je vrijeme cache-a
 	    		if (slika.get().getDate().before(now)) {
 	    			//ponovno učitaj file
 	    			System.out.println("vrijeme joj je isteklo, ponovno ju uzimam");
 	    			slikaRepository.delete(slika.get());
 	    			Files.delete(Paths.get("slike/"+zup.getWikipoveznica()+".png"));
 	    			saveajSliku(zup);
 	    			Date dt = new Date();
 	 	    		c.setTime(dt); 
 	 	    		//c.add(Calendar.DATE, 1);
 	 	    		c.add(Calendar.SECOND, 20);
 	 	    		dt = c.getTime();
 	    			slikaRepository.save(new Slika(zup.getWikipoveznica(), dt));
 	    		} else {
 	    			System.out.println("slika postoji i nije joj isteklo vrijeme cache-a");
 	    		}
 	    	}
 	    	Path file = root.resolve(zup.getWikipoveznica()+".png");
 	    	Resource resource = new UrlResource(file.toUri());
             if (resource.exists()) {
                 resurs = resource;
             } else {
                 throw new RuntimeException("Could not read the file!");
             }
 	    } catch (Exception e) {  
 	        e.printStackTrace();  
 	    }
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentDisposition(ContentDisposition.builder("attachment").filename( zup.getWikipoveznica()+".png").build());
    	headers.setContentType(MediaType.IMAGE_PNG);
    	return new ResponseEntity<>(resurs, headers, HttpStatus.OK);
    	/*
	    return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zup.getWikipoveznica()+".png" + "\"").body(resurs);
                */
    }
	
	private void saveajSliku(Zupanija zup) throws Exception {
			Path root = Paths.get("slike");
			URL jsonUrl = new URL("https://en.wikipedia.org/api/rest_v1/page/summary/"+zup.getWikipoveznica());
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			Map<String, Object> jsonMap = mapper.readValue(jsonUrl, new TypeReference<Map<String,Object>>(){});
			Object novi = jsonMap.get("thumbnail");
			String adresa = novi.toString();
			adresa = adresa.substring(adresa.indexOf("=")+1);
			adresa = adresa.split("\\s+")[0];
			adresa = adresa.substring(0, adresa.length()-1);
			//System.out.println(adresa);
			// sada spremi
			URL url = new URL(adresa);
			InputStream in = new BufferedInputStream(url.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
			   out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			String ime = root.toString()+"/"+zup.getWikipoveznica()+".png";
			//System.out.println(ime);
			FileOutputStream fos = new FileOutputStream(ime);
			fos.write(response);
			fos.close();
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