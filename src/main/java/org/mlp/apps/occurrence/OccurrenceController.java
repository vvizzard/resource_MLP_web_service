package org.mlp.apps.occurrence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class OccurrenceController {
	
	@Autowired
	private OccurrenceRepository speciesRepository;
	
	@GetMapping("/public/observations")
	public List<Occurrence> getAllSpecies(@RequestParam(required = false, 
			value = "page") Integer page, @RequestParam(required=false, 
			value="limite") Integer limite) {
		Occurrence oc = new Occurrence();
		oc.setPublique(Boolean.TRUE);
		if(page != null && limite != null && page>= 1 && limite > 0) {
			Pageable pageable = PageRequest.of(page - 1, limite);
			return speciesRepository.findAll(Example.of(oc), pageable).getContent();
		}
		return speciesRepository.findAll(Example.of(oc));
	}
	
	@GetMapping("/public/observation/{id}")
	public Occurrence getOne(@PathVariable Integer id) {
		Occurrence temp = new Occurrence();
		temp.setId(id);
		temp.setPublique(true);
		List<Occurrence> val =  speciesRepository.findAll(Example.of(temp));
		if (!val.isEmpty()) {
			return val.get(0);
		} else return null;
	}
}
