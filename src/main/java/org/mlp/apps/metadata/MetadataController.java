package org.mlp.apps.metadata;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;

import javax.servlet.ServletContext;

import org.mlp.apps.photo.Photo;
import org.mlp.apps.photo.PhotoRepository;
import org.mlp.apps.photo.PhotoService;
import org.mlp.apps.user.User;
import org.mlp.apps.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.mlp.apps.association.AssociationMetadataTaxonomi;
import org.mlp.apps.association.AssociationMetadataTopic;

@RestController
@CrossOrigin(origins = "*")
public class MetadataController {
	
	@Autowired
    ServletContext context;
	
	@Autowired
	private MetadataRepository metadataRepository;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private UserRepository userRepository;

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
	public Boolean addPhoto(@RequestParam("token") String token, 
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
			photo.setIdUser(Integer.getInteger(token.substring(token.indexOf(",.5")+1)));
			
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
	
	@PostMapping("/user/document")
	public Boolean addDoc(@RequestParam("idUser") Integer idUser, 
			@RequestParam("date") String date, @RequestParam("coverage") String coverage, 
			@RequestParam("title") String title, @RequestParam("creator") String creator, 
			@RequestParam("rights") String rights, @RequestParam("type") String type, 
			@RequestParam(name = "url", required = false) String url, 
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
			metadata.setType(type);
			
			metadata.setDate(date);
			metadata.setYear(getYear(date));
			metadata.setCoverage(coverage);
			metadata.setTitle(title);
			metadata.setCreator(creator);
			metadata.setRights(rights);
			
			if (null != file && !file.isEmpty()) {
	            String additionalName = "";
	            additionalName += Calendar.getInstance().getTime().getTime();
	            String filename = file.getOriginalFilename().replaceAll("\\s+", "");
	            filename = Normalizer.normalize(filename, Normalizer.Form.NFD);
	            filename = filename.replaceAll("[^\\p{ASCII}]", "");
	            String path = context.getRealPath("/") + File.separator + "resources"
	                    + File.separator + "upload" + File.separator + additionalName
	                    + filename;
	            // Add the url path 
	            metadata.setUrl("https://www.lemursporal.org/forum/" + "resources" + "/"
	                    + "upload" + "/" + additionalName + filename);
	            if (!Files.exists(Paths.get(context.getRealPath("/"), File.separator,
	                    "resources", File.separator, "upload"), LinkOption.NOFOLLOW_LINKS)) {
	                try {
	                    Files.createDirectories(Paths.get(context.getRealPath("/"),
	                            File.separator, "resources", File.separator,
	                            "upload"));
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
	                stream.write(bytes);
	                stream.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	                return false;
	            }
	        } else {
	            System.out.println("filefile empty");
	        }
	        metadata.setIdUtilisateur(idUser);
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
	
	@PostMapping(value = "/secured/document/{filter}")
    public Boolean submit(@RequestParam("idu") String token, 
    		@RequestParam(name = "bibliographicResource", required = false) String bibliographicResource,
            @RequestParam(name = "url", required = false) String url,
            @RequestParam("date") String date,
            @RequestParam(name = "idThematique", required = false) String idThematique,
            @RequestParam(name = "species", required = false) String species,
            @RequestParam(name = "id", required = false) Integer id, 
            @RequestParam("coverage") String coverage,
            @RequestParam(name = "description", required = false) String description, 
            @RequestParam(name = "language", required = false) String language,
            @RequestParam(name = "relation", required = false) String relation, 
            @RequestParam(name = "source", required = false) String source,
            @RequestParam(name = "subject", required =false) String subject, 
            @RequestParam("title") String title,
            @RequestParam(name = "format", required =false) String format, 
            @RequestParam(name = "fileFormat", required = false) String fileFormat,
            @RequestParam(name = "identifier", required = false) String identifier, 
//            @RequestParam("type") String type,
            @RequestParam(name = "contributor", required = false) String contributor, 
            @RequestParam("creator") String creator,
            @RequestParam(name = "publisher", required = false) String publisher, 
            @RequestParam("rights") String rights,
            @RequestParam(name = "year", required = false) String year, 
            @RequestParam(name = "file", required = false) MultipartFile file,
            @PathVariable String filter) {
		String type = "";
        if (filter == "document" ) {
            if (idThematique == null || idThematique.isEmpty()) {
            	return false;
            } else {
            	type = "4";
            }
        } else if (filter == "photo") {
        	type = "1";
        } else if (filter == "video") {
        	type = "2";
        } else if (filter == "audio") {
        	type = "3";
        }
        if (idThematique == null || idThematique.isEmpty()) {
        	idThematique = "797298";
        }
        Metadata post = new Metadata();
        if(bibliographicResource != null) post.setBibliographicResource(bibliographicResource);
        post.setDate(date);
        post.setCoverage(coverage);
        if(description != null) post.setDescription(description);
        if(language != null) post.setLanguage(language);
        if(relation != null) post.setRelation(relation);
        if(source != null) post.setSource(source);
        if(subject != null) post.setSubject(subject);
        post.setTitle(title);
        if(format != null) post.setFormat(format);
        if(fileFormat != null) post.setFileFormat(fileFormat);
        if(identifier != null) post.setIdentifier(identifier);
        if(contributor != null) post.setContributor(contributor);
        post.setCreator(creator);
        if(publisher != null) post.setPublisher(publisher);
        post.setRights(rights);
        if(year != null) post.setYear(year);
        post.setType(type);
        if(url != null) post.setUrl(url);

        String[] idsThematique = null;
        try {
            idsThematique = idThematique.split(",");
        } catch (PatternSyntaxException nse) {
            idsThematique = new String[]{idThematique};
        }
        for (String s : idsThematique) {
            AssociationMetadataTopic amt = new AssociationMetadataTopic();
            amt.setId2(Integer.parseInt(s));
            post.addListeAssociationMetadataTopic(amt);
        }
        String[] listSpecies = null;
        try {
            listSpecies = species.split(",");
        } catch (PatternSyntaxException nse) {
            listSpecies = new String[]{species};
        } catch (NullPointerException npe) {
            // If null, we don't care, we catch it later;
        }
        for (String s : listSpecies) {
            AssociationMetadataTaxonomi amt = new AssociationMetadataTaxonomi();
            try {
                amt.setId2(Integer.parseInt(s));
            } catch (Exception e) {
                continue;
            }
            post.addListeAssociationMetadataTaxonomi(amt);
        }
        Optional<User> currentUser = userRepository.findById(Integer.getInteger(token.substring(token.indexOf(",.5")+1)));
        System.out.println("mail");
        Date now = Calendar.getInstance().getTime();

        //handle file upload        
        if (null != file && !file.isEmpty()) {
            String additionalName = "";
            additionalName += Calendar.getInstance().getTime().getTime();
            String filename = file.getOriginalFilename().replaceAll("\\s+", "");
            filename = Normalizer.normalize(filename, Normalizer.Form.NFD);
            filename = filename.replaceAll("[^\\p{ASCII}]", "");
            String path = context.getRealPath("/") + File.separator + "resources"
                    + File.separator + "upload" + File.separator + additionalName
                    + filename;
            // Add the url path 
            post.setUrl("https://www.lemursporal.org/forum/" + "resources" + "/"
                    + "upload" + "/" + additionalName + filename);
            if (!Files.exists(Paths.get(context.getRealPath("/"), File.separator,
                    "resources", File.separator, "upload"), LinkOption.NOFOLLOW_LINKS)) {
                try {
                    Files.createDirectories(Paths.get(context.getRealPath("/"),
                            File.separator, "resources", File.separator,
                            "upload"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                return false;
            }
        } else {
        }
        if (id == null) {
            // Save the document and the metadata in BDD
            post.setIdUtilisateur(currentUser.get().getId());
            metadataRepository.save(post);
        } else {
            post.setId(id);
            post.setIdUtilisateur(currentUser.get().getId());
            metadataRepository.save(post);
        }
        return true;
    }
}
