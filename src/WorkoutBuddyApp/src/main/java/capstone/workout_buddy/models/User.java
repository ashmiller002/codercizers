package capstone.workout_buddy.models;

import java.time.LocalDate;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String email;
    private String loginId;
    private int programId;
    private int goalId;
    private int activityLevelId;

    public User(){};

    public User(int userId, String firstName, String lastName, LocalDate dob, String email, String loginId, int programId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dob;
        this.email = email;
        this.loginId = loginId;
        this.programId = programId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public int getProgram() {
        return programId;
    }

    public void setProgramId(int program) {
        this.programId = program;
    }

}
