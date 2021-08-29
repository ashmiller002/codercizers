package capstone.workout_buddy.controllers;

import capstone.workout_buddy.domain.Result;
import capstone.workout_buddy.domain.UserWorkoutService;
import capstone.workout_buddy.domain.WorkoutService;
import capstone.workout_buddy.models.UserWorkout;
import capstone.workout_buddy.models.Workout;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/userWorkout")
public class UserWorkoutController {
    private final UserWorkoutService service;

    public UserWorkoutController(UserWorkoutService service){
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Workout>> findWorkoutsByUserId(@PathVariable int userId){
        List<Workout> workouts = service.findWorkoutsByUserId(userId);
        if(workouts == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(workouts);
    }

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody UserWorkout userWorkout){
        Result<UserWorkout> result = service.add(userWorkout);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
}
