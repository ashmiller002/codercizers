package capstone.workout_buddy.data;

import capstone.workout_buddy.data.mappers.UserMapper;
import capstone.workout_buddy.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll(){
        final String sql = "select user_id, first_name, last_name, date_birth, email, program_id, login_id from `user`";

        return jdbcTemplate.query(sql, new UserMapper());
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
    public User findByLoginId(String loginId) {
        final String sql = "select user_id, first_name, last_name, date_birth, email, program_id, " +
                "login_id from `user` where login_id = ?;";

        User user = jdbcTemplate.query(sql, new UserMapper(), loginId).stream()
                .findFirst().orElse(null);

        //add the goalId and activityLevelId

        return user;
    }

    @Override
    public User add(User user) {
        final String sql = "insert into `user` (first_name, last_name, date_birth, email, program_id, login_id) " +
                "values (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, Date.valueOf(user.getDateBirth()));
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getProgram());
            ps.setString(6, user.getLoginId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public boolean update(User user) {

        final String sql = "update `user` set "
                + "first_name = ?, "
                + "last_name = ?, "
                + "date_birth = ?, "
                + "email = ?, "
                + "program_id = ?, "
                + "login_id = ? " +
                "where user_id = ?;";

        return jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getDateBirth(),
                user.getEmail(),
                user.getProgram(),
                user.getLoginId(),
                user.getUserId()) > 0;
    }



}


