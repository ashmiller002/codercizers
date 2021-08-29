package capstone.workout_buddy.data.mappers;

import capstone.workout_buddy.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setDateBirth(resultSet.getDate("date_birth").toLocalDate());
        user.setEmail(resultSet.getString("email"));
        user.setProgramId(resultSet.getInt("program_id"));
        user.setLoginId(resultSet.getString("login_id"));


        return user;
    }
}
