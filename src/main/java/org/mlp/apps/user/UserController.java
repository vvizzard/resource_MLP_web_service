package org.mlp.apps.user;

import java.util.List;
import org.mlp.apps.usertype.UserType;
import org.mlp.apps.usertype.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@GetMapping("/user/users")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User getOne(@PathVariable Integer id) {
		return userRepository.findById(id).get();
	}
	
	@GetMapping("/public/experts")
	public List<User> getAllExperts() {
		UserType exp = new UserType();
		exp.setId(101); //101 is the id of expert
		List<User> valiny = userTypeRepository.findUser(exp);
		for (User u : valiny) {
			if (u.getPhotoProfil()==null || u.getPhotoProfil().isEmpty()) {
				continue;
			}
			u.setPhotoProfil("https://www.lemursportal.org/forum/resources" 
					+ u.getPhotoProfil());
		}
		return valiny;
	}
	
//	@PostMapping("/authenticate")
//	public HashMap<String, String> authenticate(@RequestParam(
//			value = "refresh", required = false) String refresh, 
//			@RequestParam String email, @RequestParam String password) {
//		
//		//Authenticate server side
//		CloseableHttpClient client = HttpClients.createDefault();
//	    HttpPost httpPost = new HttpPost("https://www.lemursportal.org/api-test/oauth/token");
//	 
//	    String json = "{"id":1,"name":"John"}";
//	    StringEntity entity = new StringEntity(json);
//	    httpPost.setEntity(entity);
//	    httpPost.setHeader("Accept", "application/json");
//	    httpPost.setHeader("Content-type", "application/json");
//	 
//	    CloseableHttpResponse response = client.execute(httpPost);
//	    assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
//	    client.close();
//		
//	}
	
}
