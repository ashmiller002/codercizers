package capstone.workout_buddy.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

public class UserJdbcTemplateRepository implements UserRepository{
  
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public User findByUserId(int userId) {
        final String sql = "select user_id, first_name, last_name, date_birth, email, program_id, " +
                "login_id from `user` where user_id = ?;";

        User user = jdbcTemplate.query(sql, new UserMapper(), userId).stream()
                .findFirst().orElse(null);

        return user;
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

