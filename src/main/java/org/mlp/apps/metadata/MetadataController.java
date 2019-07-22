package org.mlp.apps.metadata;

import java.util.List;
import java.util.Optional;

import org.mlp.apps.photo.Photo;
import org.mlp.apps.photo.PhotoRepository;
import org.mlp.apps.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MetadataController {
	
	@Autowired
	private MetadataRepository metadataRepository;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private PhotoRepository photoRepository;

	@GetMapping("/public/metadatas")
	public List<Metadata> getAllMetadatas(@RequestParam(required = false, 
			value = "page") Integer page, @RequestParam(required=false, 
			value="limite") Integer limite) {
		if(page != null && limite != null && page>= 1 && limite > 0) {
			Pageable pageable = PageRequest.of(page - 1, limite);
			return metadataRepository.findAll(pageable).getContent();
		}
		return metadataRepository.findAll();
	}
	
	@GetMapping("/public/metadata/{id}")
	public Optional<Metadata> findById(@PathVariable Integer id) {
		return metadataRepository.findById(id);
	}
	
	@GetMapping("/public/{filter}")
	public List<Metadata> findById(@PathVariable String filter) {
		if (filter.compareTo("photos")==0) {
			filter = "1";
		} else if (filter.compareTo("videos")==0) {
			filter = "2";
		} else if (filter.compareTo("audios")==0) {
			filter = "3";
		} else if (filter.compareTo("documents")==0) {
			filter = "4";
		}
		return metadataRepository.findByType(filter);
	}
	
	@PostMapping("/user/photo")
	public Boolean addPhoto(@RequestParam("idUser") Integer idUser, 
			@RequestParam("date") String date, @RequestParam("coverage") String coverage, 
			@RequestParam("title") String title, @RequestParam("creator") String creator, 
			@RequestParam("rights") String rights,  
            @RequestParam(name = "file", required = false) MultipartFile file) {
		
		Boolean response = false;
		
		try {
			Metadata metadata = new Metadata();
			metadata.setBibliographicResource("");
			metadata.setDescription("");
			metadata.setLanguage("");
			metadata.setRelation("");
			metadata.setSource("");
			metadata.setSubject("");
			metadata.setFormat("");
			metadata.setFileFormat("");
			metadata.setIdentifier("");
			metadata.setContributor("");
			metadata.setPublisher("");
			metadata.setType("1");
			
			metadata.setDate(date);
			metadata.setYear(getYear(date));
			metadata.setCoverage(coverage);
			metadata.setTitle(title);
			metadata.setCreator(creator);
			metadata.setRights(rights);
			
			Photo photo = new Photo();
			photo.setIdUser(idUser);
			
			photoService.preparePhoto(photo, file.getBytes());
			
			metadata = metadataRepository.save(metadata);
			photo.setIdMetadata(metadata.getId());
			
			photoRepository.save(photo);
			
			response = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return response;
		}
		
		return response;
	}
	
	private String getYear(String date) {
		String[] splited = date.split("-|.|-|_");
		for (String s : splited) {
			if (s.length()==4) return s;
		}
		return "";
	}
}
