package capstone.workout_buddy.data.mappers;

import capstone.workout_buddy.models.UserWorkout;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserWorkoutMapper implements RowMapper<UserWorkout> {

    @Override
    public UserWorkout mapRow(ResultSet resultSet, int i) throws SQLException{
        UserWorkout userWorkout = new UserWorkout();


//        userWorkout.getWorkout().setCategoryId(resultSet.getInt("category_id"));
//        userWorkout.getWorkout().setWorkoutName(resultSet.getString("workout_name"));
//        userWorkout.getWorkout().setWorkoutId(resultSet.getInt("workout_id"));
//        userWorkout.getWorkout().setImageUrl(resultSet.getString("image_url"));
//        userWorkout.getWorkout().setWorkoutStatus(resultSet.getString("workout_status"));
        userWorkout.setWorkoutDate(resultSet.getDate("workout_date").toLocalDate());
        userWorkout.setUserId(resultSet.getInt("user_id"));
        userWorkout.setUserWorkoutId(resultSet.getInt("user_workout_id"));


        WorkoutMapper workoutMapper = new WorkoutMapper();
        userWorkout.setWorkout(workoutMapper.mapRow(resultSet, i));

        return userWorkout;

    }
}
