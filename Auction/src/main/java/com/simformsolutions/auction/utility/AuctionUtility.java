package com.simformsolutions.auction.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public class AuctionUtility {
	
//	@Autowired
//	private JwtUtil jwtUtil;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	public static String saveImage(String folderLocation ,MultipartFile file) {
		String fileName = file.getOriginalFilename();
		Path path = Paths.get(folderLocation, fileName);
		try {
			Files.write(path, file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		return fileName;
	}
	public Cookie cookieMaker(String email,String password, AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
		Cookie cookie = null;
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, password));
		} catch (Exception ex) {
			System.out.println("OK"+ ex);
			return cookie;
		}
		cookie = new Cookie("token",jwtUtil.generateToken(email));
		cookie.setMaxAge(60 * 60 * 10);
		return cookie;
	}
}
