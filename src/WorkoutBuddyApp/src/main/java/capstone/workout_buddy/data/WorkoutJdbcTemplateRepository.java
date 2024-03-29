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
    public List<Workout> findAll(){
        final String sql = "select workout_id, workout_name, category_id, image_url, workout_status " +
                "from workout limit 1000;";
        return jdbcTemplate.query(sql, new WorkoutMapper());
    }

    @Override
    public Workout findById(int workoutId){
        final String sql = "select category_id, workout_status, workout_id, workout_name, image_url "
                + "from workout "
                + "where workout_id = ?;";

        return jdbcTemplate.query(sql, new WorkoutMapper(), workoutId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<Workout> findByCategory(int categoryId){

        final String sql = "select category_id, workout_status, workout_id, workout_name, image_url" +
                " from workout "
                + "where category_id = ? " +
                "and workout_status = 'enable';";

        return jdbcTemplate.query(sql, new WorkoutMapper(), categoryId);
    }

    @Override
    public Workout add(Workout workout){

        final String sql = "insert into workout (workout_name, category_id, workout_status, image_url) " +
                "values (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, workout.getWorkoutName());
            ps.setInt(2, workout.getCategoryId());
            ps.setString(3, workout.getWorkoutStatus());
            ps.setString(4, workout.getImageUrl());
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
                + "category_id = ?, "
                + "workout_status = ?, "
                + "image_url = ? "
                + "where workout_id = ?;";

        return jdbcTemplate.update(sql,
                workout.getWorkoutName(),
                workout.getCategoryId(),
                workout.getWorkoutStatus(),
                workout.getImageUrl(),
                workout.getWorkoutId()) > 0;
    }

}
