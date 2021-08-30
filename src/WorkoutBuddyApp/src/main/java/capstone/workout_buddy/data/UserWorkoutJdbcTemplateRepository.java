package capstone.workout_buddy.data;

import capstone.workout_buddy.data.mappers.UserWorkoutMapper;
import capstone.workout_buddy.data.mappers.WorkoutMapper;
import capstone.workout_buddy.models.UserWorkout;
import capstone.workout_buddy.models.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;
import java.util.List;

@Repository
public class UserWorkoutJdbcTemplateRepository implements UserWorkoutRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserWorkoutJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

     //for findWorkOutsById pass the user specific workouts with ALL workout details for each individual workout

    //find workouts by user

    @Override
    public UserWorkout add(UserWorkout userWorkout){

        final String sql = "insert into user_workout " +
                "(user_id, workout_id, workout_date) values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userWorkout.getUserId());
            ps.setInt(2, userWorkout.getWorkout().getWorkoutId());
            ps.setDate(3, Date.valueOf(userWorkout.getWorkoutDate()));
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        userWorkout.setUserWorkoutId(keyHolder.getKey().intValue());
        return userWorkout;
    }

    @Override
    public List<UserWorkout> findWorkoutsByUserId(int userId){

        final String sql = "select uw.user_id, uw.user_workout_id, uw.workout_date, uw.workout_id, w.workout_name, w.image_url, w.workout_status, w.category_id " +
                "        from user_workout AS uw " +
                "        inner join workout AS w on uw.workout_id = w.workout_id " +
                "        where user_id = ?;";

        return jdbcTemplate.query(sql, new UserWorkoutMapper(), userId);

    }

}
