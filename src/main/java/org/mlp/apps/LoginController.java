package org.mlp.apps;

import java.util.Optional;

import org.mlp.apps.user.User;
import org.mlp.apps.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 
 */
@RestController
@CrossOrigin(origins = "*")
public class LoginController {

//    @Autowired(required = true)
//    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired(required = true)
    UserRepository userRepository;

    @PostMapping("/login")
	public String login(@RequestParam("login") String login, @RequestParam("password") String pw) {
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		Optional<User> u = userRepository.findByEmail(login);
		if(u.isPresent() && bCryptPasswordEncoder.matches(pw, u.get().getPassword())) {
			return bCryptPasswordEncoder.encode("itoDiaJustePourEncoderFOtsiny") + ",.5" + u.get().getId().toString();
		}
		return null;
	}
}
