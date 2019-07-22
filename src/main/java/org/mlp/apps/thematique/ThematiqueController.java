package org.mlp.apps.thematique;

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
public class ThematiqueController {
	
	@Autowired
	private ThematiqueRepository thematiqueRepository;

	@GetMapping("/public/thematiques")
	public List<Thematique> getAllThematiques(@RequestParam(required = false, 
			value = "page") Integer page, @RequestParam(required=false, 
			value="limite") Integer limite) {
		if(page != null && limite != null && page>= 1 && limite > 0) {
			Pageable pageable = PageRequest.of(page - 1, limite);
			return thematiqueRepository.findAll(pageable).getContent();
		}
		return thematiqueRepository.findAll();
	}
	
	@GetMapping("/public/thematique/{id}")
	public Optional<Thematique> findById(@PathVariable Integer id) {
		return thematiqueRepository.findById(id);
	}
}
