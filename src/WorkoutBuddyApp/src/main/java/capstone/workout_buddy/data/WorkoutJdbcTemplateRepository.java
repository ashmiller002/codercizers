package capstone.workout_buddy.data;


import capstone.workout_buddy.data.mappers.WorkoutMapper;
import capstone.workout_buddy.models.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WorkoutJdbcTemplateRepository implements WorkoutRepository {

    private final JdbcTemplate jdbcTemplate;

    public WorkoutJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Workout> findByCategory(int categoryId){

        final String sql = "select workout_id, workout_name, image_url" +
                " from workout "
                + "where category_id = ?;";

        List<Workout> workouts = jdbcTemplate.query(sql, new WorkoutMapper());
        return workouts;

    }

    @Override
    public Workout add(Workout workout){

        final String sql = "insert into workout (workout_name, category_id, workout_status) " +
                "values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, workout.getCategoryId());
            ps.setString(2, workout.getWorkoutName());
            ps.setString(3, workout.getWorkoutStatus());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        workout.setWorkoutId(keyHolder.getKey().intValue());
        return workout;

    }

    @Override
    public boolean update(Workout workout){

        final String sql = "update workout set "
                + "workout_name = ?, "
                + "image_url = ?, "
                + "where workout_id = ?;";

        return jdbcTemplate.update(sql,
                workout.getWorkoutName(),
                workout.getCategoryId(),
                workout.getImageUrl(),
                workout.getWorkoutId()) > 0;
    }

}
