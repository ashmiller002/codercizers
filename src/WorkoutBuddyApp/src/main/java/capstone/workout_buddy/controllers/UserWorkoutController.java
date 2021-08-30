package capstone.workout_buddy.controllers;

import capstone.workout_buddy.domain.Result;
import capstone.workout_buddy.domain.UserWorkoutService;
import capstone.workout_buddy.models.UserWorkout;
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
    public ResponseEntity<List<UserWorkout>> findWorkoutsByUserId(@PathVariable int userId){
        List<UserWorkout> userWorkouts = service.findWorkoutsByUserId(userId);
        if(userWorkouts == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userWorkouts);
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
