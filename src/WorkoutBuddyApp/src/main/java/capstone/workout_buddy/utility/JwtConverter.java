package capstone.workout_buddy.utility;

import capstone.workout_buddy.models.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
public class JwtConverter {
    private final String secretKey = "de8a26d0-f6e8-4470-91d0-ba7a44391281";
    SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HS256.getJcaName());

    private final String ISSUER = "dev10-users-api";
    private final int EXPIRATION_MINUTES = 15;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    public AppUser getUserFromToken(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(secretKeySpec)
                    .build()
                    .parseClaimsJws(token);

            String username = jws.getBody().getSubject();

            Claims claims = jws.getBody();
            String id = (String)claims.get("id");
            String rolesString = (String)claims.get("roles");
            String[] roles = rolesString.split(",");

            return new AppUser(id, username, null, false, roles);

        } catch (JwtException e) {
            System.out.println(e);
        }

        return null;
    }
}