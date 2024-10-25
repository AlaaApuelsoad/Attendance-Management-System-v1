package com.demo.Attendance.jwtSecurity;

import com.demo.Attendance.dto.LoginDto.LoginDto;
import com.demo.Attendance.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static String SECRET_KEY;
    private final UserRepository userRepository;

    public JwtService(com.demo.Attendance.repository.UserRepository userRepository) {
        this.userRepository = userRepository;
        SECRET_KEY = generateSecretKey();
    }

    //Function to generate token
    public String generateSecretKey() {

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating SecretKey", e);
        }
    }

    public String generateToken(LoginDto loginDto) {

        Map<String,Object> claims = new HashMap<>();
        String roleValue = userRepository.findByUserName(loginDto.getUsername()).getRole().getRoleName();
        System.out.println("Role: "+roleValue);
        claims.put("role", roleValue);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(loginDto.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))
                .signWith(getKey(),SignatureAlgorithm.HS256).compact();
    }

    private SecretKey getKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    // extract username from jwt token
    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // extract role from jwt token
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
