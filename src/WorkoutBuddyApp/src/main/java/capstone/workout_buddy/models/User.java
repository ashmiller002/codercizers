package capstone.workout_buddy.models;

import org.apache.tomcat.jni.Local;

import java.sql.Date;
import java.time.LocalDate;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String loginId;
    private int programId;

    public User(){};

    public User(int userId, String firstName, String lastName, LocalDate dob, String email, String loginId, int programId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
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
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgramId(int program) {
        this.program = program;
    }

}
