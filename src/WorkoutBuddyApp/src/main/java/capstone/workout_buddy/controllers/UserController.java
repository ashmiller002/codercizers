package capstone.workout_buddy.controllers;


import capstone.workout_buddy.domain.Result;
import capstone.workout_buddy.domain.UserService;
import capstone.workout_buddy.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;


    public UserController(UserService service) {this.service = service;}

    @GetMapping("/userid/{userId}")
    public ResponseEntity<User> findByUserId(@PathVariable int userId){
        User user = service.findByUserId(userId);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{loginId}")
    public ResponseEntity<User> findByLoginId(@PathVariable String loginId){
        User user = service.findByLoginId(loginId);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> add(@RequestBody User user){
        Result<User> result = service.add(user);

        if (result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{loginId}")
    public ResponseEntity<Object> update(@PathVariable String loginId, @RequestBody User user){
        if (!loginId.equals(user.getLoginId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<User> result = service.update(user);
        if (result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }
}
