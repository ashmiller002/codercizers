package capstone.workout_buddy.utility;

import capstone.workout_buddy.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter extends HttpFilter {
    @Autowired
    private JwtConverter jwtConverter;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");

        AppUser appUser = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            appUser = jwtConverter.getUserFromToken(token);
        }

        if (appUser == null) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }

        if (request.getServletPath().contains("admin") && !appUser.getRoles().contains("ADMIN")) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }

        super.doFilter(request, response, chain);
    }
}
