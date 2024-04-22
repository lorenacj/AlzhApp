package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Carer;
import com.example.demo.model.CarerModel;
import com.example.demo.service.CarerService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;


@RestController
public class CarerController {
	@Autowired
	@Qualifier("carerService")
	private CarerService carerService;

	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
	    Carer carer = carerService.findByPID(username);
	    if (carer != null && carerService.checkPassword(pwd, carer.getPassword())) {
	        String token = getJWTToken(carer.getUsername(), carer.getRole());
	        carer.setToken(token);
	        carer.setPassword(null);
	        return ResponseEntity.ok(carer);
	    } else {
	    	//especificar si es una u otra
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID passport or password is incorrect");
	    }
	}

	@PostMapping("/register")
	public Carer registerCarer(@RequestBody CarerModel carer) {
		return carerService.register(carer);
	}

	
	private String getJWTToken(String username, String role) {
		String SECRET_KEY = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600_000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes()).compact();

		return "Bearer " + token;
	}
	
	public Claims parseToken(String token) {
		final String SECRET_KEY = "mySecretKey";
		token = token.replace("Bearer ", "");
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();

			// String role = claims.get("role", String.class);

			return claims;
		} catch (ExpiredJwtException e) {
			// Manejar la excepción de token expirado
			e.printStackTrace();
		} catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
			// Manejar otras excepciones de token no válido
			e.printStackTrace();
		}
		return null;
	}
	
}
