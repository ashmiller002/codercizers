package capstone.workout_buddy.utility;

import capstone.workout_buddy.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringJwtToAppUserConverter implements Converter<String, AppUser> {
    @Autowired
    private JwtConverter jwtConverter;

    @Override
    public AppUser convert(String s) {
        AppUser appUser = null;

        if (s != null && s.startsWith("Bearer ")) {
            String token = s.substring(7);

            appUser = jwtConverter.getUserFromToken(token);
        }

        return appUser;
    }
}

