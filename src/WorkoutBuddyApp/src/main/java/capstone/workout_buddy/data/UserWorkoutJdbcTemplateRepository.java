package capstone.workout_buddy.data;

import capstone.workout_buddy.data.mappers.WorkoutMapper;
import capstone.workout_buddy.models.UserWorkout;
import capstone.workout_buddy.models.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
            ps.setInt(2, userWorkout.getUserWorkoutId());
            ps.setDate(3, userWorkout.getWorkoutDate());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        userWorkout.setUserWorkoutId(keyHolder.getKey().intValue());
        return userWorkout;
    }

    @Override
    public List<Workout> findWorkoutsByUserId(int userId){

        final String sql = "select user_id, user_workout_id, activity_id, workout_date, workout_id," +
                " workout_name, image_url, workout_status " +
                " from user_workout uw" +
                " inner join workout w on uw.workout_id = w workout_id;";


        return jdbcTemplate.query(sql, new WorkoutMapper(), userId);

    }

}
