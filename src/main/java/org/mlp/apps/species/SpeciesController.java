package org.mlp.apps.species;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeciesController {
	
	@Autowired
	private SpeciesRepository speciesRepository;
	
	@GetMapping("/public/species")
	public List<Species> getAllSpecies(@RequestParam(required = false, 
			value = "page") Integer page, @RequestParam(required=false, 
			value="limite") Integer limite) {
		if(page != null && limite != null && page>= 1 && limite > 0) {
			Pageable pageable = PageRequest.of(page - 1, limite);
			return speciesRepository.findAll(pageable).getContent();
		}
		return speciesRepository.findAll();
	}
	
	@GetMapping("/public/specie/{id}")
	public Optional<Species> getOne(@PathVariable Integer id) {
		return speciesRepository.findById(id);
	}
}
