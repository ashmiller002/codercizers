package capstone.workout_buddy.data;

import capstone.workout_buddy.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{
    @Override
    public User findByUserId() {
        return null;
    }

    @Override
    public User findByLoginId() {
        return null;
    }

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }
    //private final JdbcTemplate jdbcTemplate;
}
