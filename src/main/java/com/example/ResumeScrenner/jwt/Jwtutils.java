package com.example.ResumeScrenner.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class Jwtutils {
    private final String key="bXl0b2tlbmFuc2h1bGpha2htb2xhZ2VuZXJhdGVtZWFqd3R0b2tlbmtleW9mMjU2Yml0c21vcmVtb3JlbW9yZQ==";
    public String generateToken( String username) {
       Map<String,Object > claims= new HashMap<>();
       return createToken(username,claims);
           }
       
           private String createToken(String username, Map<String,Object> claims) {
              return Jwts.builder()
                        .setClaims(claims)
                        .setSubject(username)
                          .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis()+ 1000L * 60 * 60 * 24 * 365))
                            .signWith(generateKey(), SignatureAlgorithm.HS256).compact();
           }

        private Key generateKey() {
            byte [] keybytes=Decoders.BASE64.decode(key);
            return Keys.hmacShaKeyFor(keybytes);
        }

        public String extractUsernameByToken(String token) {
          return extractClaim(token,Claims::getSubject);
        }

        public Date extractExpiration(String token){
          return extractClaim(token, Claims :: getExpiration);
        }

        public boolean isTokenExpired(String token){
          return extractExpiration(token).before(new Date());
        }
        
        public <T> T extractClaim(String token,Function<Claims,T>claimsResolver){
          final Claims claims=extractAllClaim(token);
          return claimsResolver.apply(claims);
        }
          
       private Claims extractAllClaim(String token) {
             return Jwts.parserBuilder()
                         .setSigningKey(generateKey())
                          .build()
                           .parseClaimsJws(token)
                            .getBody();       
       }
       public Boolean isValidToken(String token,UserDetails userinfo){
          final String userName = extractUsernameByToken(token);
          return (userinfo.getUsername().equals(userName) && !isTokenExpired( token));
                 }
           
    
}
