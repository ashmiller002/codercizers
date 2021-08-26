package capstone.workout_buddy.controllers;


import capstone.workout_buddy.domain.UserService;
import capstone.workout_buddy.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
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
}
