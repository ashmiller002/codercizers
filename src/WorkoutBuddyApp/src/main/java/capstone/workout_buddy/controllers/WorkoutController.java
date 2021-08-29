package capstone.workout_buddy.controllers;

import capstone.workout_buddy.domain.Result;
import capstone.workout_buddy.domain.WorkoutService;
import capstone.workout_buddy.models.Workout;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/workout")
public class WorkoutController {

    private final WorkoutService service;

    public WorkoutController(WorkoutService service){
        this.service = service;
    }

    @GetMapping
    public List<Workout> findAll(){
        return service.findAll();
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<Workout> findById(@PathVariable int workoutId){
        Workout workout = service.findById(workoutId);
        if(workout == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(workout);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Workout>> findByCategory(@PathVariable int categoryId){
        List<Workout> workouts = service.findByCategory(categoryId);
        if(workouts == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(workouts);
    }

    @PostMapping("/admin")
    public ResponseEntity<Object> add(@RequestBody Workout workout){
        Result<Workout> result = service.add(workout);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/admin/{workoutId}")
    public ResponseEntity<Object> update(@PathVariable int workoutId, @RequestBody Workout workout){
        if(workoutId != workout.getWorkoutId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Workout> result = service.update(workout);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
