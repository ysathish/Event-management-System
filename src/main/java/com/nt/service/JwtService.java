package com.nt.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private String secretKey;
	public JwtService()
	{
		this.secretKey=generateSecretKey();
	}
	public String generateSecretKey()
	{
		try {
			KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
			SecretKey secreKey=keyGen.generateKey();
			System.out.println("secretKey:"+secreKey.toString());
			return Base64.getEncoder().encodeToString(secreKey.getEncoded());
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException("Error generatin key ",e);
		}
	}

	public String generateToken(String username) {
		// TODO Auto-generated method stub
		Map<String,Object> claims=new HashMap<>();
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	public Key getKey()
	{
		byte[] keyBytes=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token,Claims::getSubject);
	}
	private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		// TODO Auto-generated method stub
		return claimResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		final String userName=extractUserName(token);
		
		return (userName.equals(userDetails.getUsername())&&! isTokenExpired(token));
	}
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token,Claims::getExpiration);
	}

}
