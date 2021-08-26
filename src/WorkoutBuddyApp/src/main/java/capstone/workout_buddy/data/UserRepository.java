package capstone.workout_buddy.data;


import capstone.workout_buddy.models.User;

public interface UserRepository {

    User findByUserId(int userId);

    User findByLoginId();

    User add(User user);

    boolean update(User user);


}
