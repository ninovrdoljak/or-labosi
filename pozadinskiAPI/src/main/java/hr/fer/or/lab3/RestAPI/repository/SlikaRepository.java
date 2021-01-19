package hr.fer.or.lab3.RestAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import hr.fer.or.lab3.RestAPI.models.Slika;



public interface SlikaRepository extends JpaRepository<Slika, Long>{
	
	Optional<Slika> findByNaziv(String naziv);

}
