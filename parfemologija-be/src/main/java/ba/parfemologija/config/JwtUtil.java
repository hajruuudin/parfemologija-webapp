package ba.parfemologija.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("fd8a71b6fc1e14f91011b19631c4ce6b91ddd95f52ca85d0324b7e433da65398129d93bd0458a8a1ed0abbbb11ed1bd763cde6253259e039096bbac3d1d9fee529c573bb9cea4d796571dc799e61235eeb11c33d2870d371beab186b7e26ca51de6a4a9b7e97a583b751406e2d7dfee4c0946f98f061e7003326565c081d823f9dbc2584a071f09ff2b7bcc3708c845e834752d7d6fc1a939aaabd65d1678536ccefec7bad5629ecd7708d73587cf4eb6a6af296a80c8b1f76378765189445f91adfeec694b0a823d2379a1a2f816d1100655540e49d6d01f8e7da60098a658fde860a4ffd62638af836ff57c6a2949b6104fa300113dc7c16f5ffa2d0317240")
    private String secret;

    @Value("3600000")
    private long jwtExpirationMs;

    public String extractUsername(String token) { // Renamed from extractEmail
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // Using username as subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Renamed from email
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String getUsernameFromJwtToken(String token) { // Renamed from getEmailFromJwtToken
        return extractUsername(token);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("JWT Validation Error: " + e.getMessage());
            return false;
        }
    }
}