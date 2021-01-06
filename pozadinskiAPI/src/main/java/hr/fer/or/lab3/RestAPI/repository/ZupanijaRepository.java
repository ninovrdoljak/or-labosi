package hr.fer.or.lab3.RestAPI.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.or.lab3.RestAPI.models.Zupanija;



public interface ZupanijaRepository extends JpaRepository<Zupanija, Long>{

	
	List<Zupanija> findAll();

	Optional<Zupanija> findByNaziv(String naziv);
}
