package com.simformsolutions.auction.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.simformsolutions.auction.service.CustomUserDetailsService;

public class AuctionUtility {

//	@Autowired
//	private JwtUtil jwtUtil;

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

	public Cookie cookieMaker(String email, String password, AuthenticationManager authenticationManager,
			JwtUtil jwtUtil, CustomUserDetailsService service,HttpServletRequest httpServletRequest) {
		Cookie cookie = null;
		try {
			UserDetails userDetails = service.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					email,password,userDetails.getAuthorities());
			System.out.println(userDetails.getAuthorities());
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			usernamePasswordAuthenticationToken
			.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		} catch (Exception ex) {
			System.out.println(ex);
			return cookie;
		}
		cookie = new Cookie("token", jwtUtil.generateToken(email));
		cookie.setMaxAge(60);
		return cookie;
	}
}
