package capstone.workout_buddy.controllers;

import capstone.workout_buddy.domain.Result;
import capstone.workout_buddy.domain.SuggestedWorkoutService;
import capstone.workout_buddy.models.Workout;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/suggested")
public class SuggestedWorkoutController {

    private final SuggestedWorkoutService service;

    public SuggestedWorkoutController(SuggestedWorkoutService service) {this.service = service;}

    @GetMapping("/{userId}")
    public ResponseEntity<Object> suggestedWorkout(@PathVariable int userId){
        Result<Workout> workoutResult = service.suggestWorkout(userId);
        if (workoutResult.isSuccess()){
            return new ResponseEntity<>(workoutResult.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(workoutResult);
    }
}
