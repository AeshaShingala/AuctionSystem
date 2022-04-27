package com.simformsolutions.auction.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.simformsolutions.auction.service.CustomUserDetailsService;

//@Component
public class AuctionUtility {

//	@Autowired
//	private JwtUtil jwtUtil;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;

	public static String saveImage(String folderLocation, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		Path path = Paths.get(folderLocation, fileName);
		try {
			Files.write(path, file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		return fileName;
	}

	public static Cookie cookieMaker(String email, String password, AuthenticationManager authenticationManager,
			JwtUtil jwtUtil, CustomUserDetailsService service,HttpServletRequest httpServletRequest) {
		Cookie cookie = null;
		try {
			UserDetails userDetails = service.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					email,password,userDetails.getAuthorities());
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (Exception ex) {
//			System.out.println(ex);
			return cookie;
		}
		cookie = new Cookie("token", jwtUtil.generateToken(email));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*10);
		return cookie;
	}
	
}
