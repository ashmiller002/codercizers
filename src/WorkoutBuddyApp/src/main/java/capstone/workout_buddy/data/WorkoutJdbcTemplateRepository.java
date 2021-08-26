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

        final String sql = "select category_id, workout_name, workout_id, image_url, workout_status from workout where category_id = ?;";

        return jdbcTemplate.query(sql, new WorkoutMapper(), categoryId);


    }

    @Override
    public Workout add(Workout workout){

        final String sql = "insert into workout (workout_name, category_id, workout_status) " +
                "values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, workout.getWorkoutName());
            ps.setInt(2, workout.getCategoryId());
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
                + "category_id = ?, "
                + "workout_name = ?, "
                + "workout_status = ?, "
                + "image_url = ? "
                + "where workout_id = ?;";

        return jdbcTemplate.update(sql,
                workout.getCategoryId(),
                workout.getWorkoutName(),
                workout.getWorkoutStatus(),
                workout.getImageUrl(),
                workout.getWorkoutId()) > 0;
    }

}
