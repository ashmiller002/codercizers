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
    public Workout findById(@PathVariable int workoutId){
        return service.findById(workoutId);
    }

    @GetMapping("/{categoryId}")
    public List<Workout> findByCategory(@PathVariable int categoryId){
        return service.findByCategory(categoryId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Workout workout){
        Result<Workout> result = service.add(workout);
        if(result.isSuccess()){
            return  new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED)
        }
        return ErrorResponse.build(result);
    }
}
