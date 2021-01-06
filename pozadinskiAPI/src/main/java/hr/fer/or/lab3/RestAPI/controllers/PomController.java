package hr.fer.or.lab3.RestAPI.controllers;


import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import hr.fer.or.lab3.RestAPI.StaticHelper;
import hr.fer.or.lab3.RestAPI.models.Zupanija;
import hr.fer.or.lab3.RestAPI.repository.GradRepository;
import hr.fer.or.lab3.RestAPI.repository.ZupanijaRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v0/zupanije")
public class PomController {
	
	@Autowired
	GradRepository gradRepository;
	
	@Autowired
	ZupanijaRepository zupanijaRepository;
	
	@Autowired
	StaticHelper staticHelper;
	
	@GetMapping("/sve/{atribut}/{pretraga}")
	public ResponseEntity<?> dobijCustom(@PathVariable String atribut,@PathVariable String pretraga) {
		List<Zupanija> rez = staticHelper.dobijZupanije(atribut, pretraga);
		return ResponseEntity.ok(rez);
	}
	
	@GetMapping("/files/{atribut}/{pretraga}/json")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String atribut,@PathVariable String pretraga) {
		Path root = Paths.get("uploads");
        
        List<Zupanija> rez = staticHelper.dobijZupanije(atribut, pretraga);
		
		ObjectMapper mapper = new ObjectMapper();

	    try {  
	    	Path file = root.resolve("zup.json");
	        // Writing to a file
	    	PrintWriter pw = new PrintWriter(file.toFile());
	    	pw.close();
	        mapper.writeValue(file.toFile(), rez );

	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  

		
		Resource resurs;
        
        try {
            Path file = root.resolve("zup.json");
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                resurs = resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "zup.json" + "\"").body(resurs);
    }
	
	@GetMapping("/files/{atribut}/{pretraga}/csv")
    @ResponseBody
    public ResponseEntity<Resource> getFile2(@PathVariable String atribut,@PathVariable String pretraga) {
		Path root = Paths.get("uploads");
        
		List<Zupanija> rez = staticHelper.dobijZupanije(atribut, pretraga);
		
	    try {  
	    	Path file = root.resolve("zup.csv");
	        // Writing to a file
	    	PrintWriter pw = new PrintWriter(file.toFile());
	    	pw.write("id,naziv,brojgradova,brojnaselja,brojopcina,gdppercapita,povrsina,sjediste,stanovnistvo,wikipoveznica,zupan\n");
	    	rez.forEach(z -> {
	    		String s = "";
	    		s =z.getId()+"," + z.getNaziv()+"," + z.getBrojgradova()+"," +z.getBrojnaselja()+"," + z.getBrojopcina()+"," 
	    		+ z.getGdppercapita()+"," + z.getPovrsina()+"," + z.getSjediste()+"," +z.getStanovnistvo()+"," 
	    		+z.getWikipoveznica()+"," +z.getZupan()+"\n";
	    		pw.write(s);
	    	});
	    	pw.close();
	        
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  

		
		Resource resurs;
        
        try {
            Path file = root.resolve("zup.csv");
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                resurs = resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "zup.csv" + "\"").body(resurs);
    }
	
	@GetMapping("/sve")
    public ResponseEntity<?> dobijSve() {
		return ResponseEntity.ok(zupanijaRepository.findAll());
	}
	
}
