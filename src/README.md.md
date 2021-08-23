# Service Rules
* Keep track of the last 6 days of working out to suggest what to do for the 7th
* Use an array to narrow down list of possible categories based off of service rules then randomly choose a category. Then within that category randomly choose a workout.


### Programs
* moderate cardio 
    * exercise ~3 times a week. alternate rest day with exercise day
    * 3 cardio
* high cardio
    * 4 cardio a week
    * 50% chance mobility or strength
* moderate strength
    * alternate rest day with excercise day
    * one upper body
    * one lower body
    * one 50% chance upper or lower body
* high strength
    * two upper body
    * two lower body
    * 50% chance mobility or cardio
    * No upper body two days in a row
    * No lower body two days in a row
* moderate mobility
    * alternate rest day with exercise day
    * 3 mobility
* high mobility
    * 4 mobility a week
    * 50% chance cardio or strength


## Workouts
* external upper body
* external lower body
* external cardio
* external mobility
* Running
* Cycling
* Dance cardio
* Yoga
* Stretching
* rest day

## Goals
* cardio
* strength
* mobility

## Categories
* upper body
* lower body
* mobility
* cardio
* rest day

## Admin 
* Do we need an admin? Admin can enable and disable workouts and create a workout (add image url. could be something from the web). If no image, add our stock image. Run by Brendan and Irina

## Technical Requirements
* Manage 4-7 database tables (entities) that are independent concepts. A simple bridge table doesn't count.
* MySQL for data management
* Spring Boot, MVC (@RestController), JdbcTemplate, Testing
* An HTML and CSS UI that's built with React
* Sensible layering and pattern choices
* At least one UI secured by role
* A full test suite that covers the domain and data layers

## Steps
* General - 9 hours
    * Create Maven Project IntelliJ - 5 minutes
    * Add dependencies to POM - 30 minutes
        * See lessons and field agent
    * Create packages - 5 minutes
        * data, domain, controller
    * Create React App - Melissa - 15 minutes
    * Build SQL Schema - 4 hours
    * Build Test SQL - 4 hours

* Models - 4 hours
    * User
    * Workout
    * Category - Enum
    * Goal - Enum
    * Activity Level - Enum
    * Program (has goal and activity level)
    * UserWorkout
* Data - 21 hours
    * Get rid of activity_level and goal from database schema - 15 minutes
    * WorkoutRepository - Interface - 15 minutes
    * WorkoutJDBCRepository - 2 hours
        * get workout by category(Category category)
        * if admin: edit workout, disable workout, enable workout. maybe add?
    * WorkoutJDBCRepository Testing - 4 hours
    * ProgramRepository - Interface - 15 minutes
    * ProgramJDBCRepository - 1 hour
        * get all programs
    * ProgramJDBCRepository testing - 1 hour
    * UserWorkoutRepository - Interface - 15 minutes
    * UserWorkoutJDBCRepository - 2 hours
        * add user workout,
        * find workouts by user
    * UserWorkoutJDBCRepository testing - 4 hours
    * UserRepository - Interface - 15 minutes
    * UserJDBCRepository - 2 hours
        * add a user
        * edit a user
     UserJDBCRepository testing - 4 hours

* Domain - 24 hours
    * UserWorkout Service - 4 hours
        * enforce program rules
    * UserWorkout Service Testing - 4 hours
    * Program Service - 1 hours
        * decide which program based on goals and activity level
    * Program Service Testing - 2 hours
    * UserService - 2 hours
        * enforce not null fields and reasonable values
        * talk to user API?
    * UserService Testing - 2 hours
    * Workout Service - 4 hours
        * if admin: edit workout, disable workout, enable workout. maybe add?
        * get workout by category
    * Workout Service Testing - 4 hours
    * Result - 2 hours

* Controllers/HTTP requests to validate - 8 hours
    * UserWorkout Controller - 2 hour
        * get workouts by user
    * User Controller - 2 hours
        * get user information
    * Workout Controller - 2 hour
        * get workouts by category
    * Response Entity - 2 hours

* React - 48 hours
    * Components
        * NavBar - 4 hours
        * Login - 4 hours
        * Register - 4 hours
        * WorkoutCard - 4 hours
        * Workout History - 4 hours
        * Workout Catalogue - 4 hours
        * Custom Workout - 4 hours
        * Main - redirect to login if not logged in - 4 hours
        * Current Workout - 4 hours
        * Account - 4 hours
    * Adding Material Design - 4 hours
    * Making Responsive - 8 hours

* With extra time, make android app

Total Time:  118 hours



## HTTP Endpoints
* GET workouts by user
GET  http://localhost8080/workouts/{loginId} - loginId comes from User API
    { workouts: [
        "workoutId": 01,
        "workoutName": "Push Ups" ,
        "imageUrl": "path/directory/hereitis",
        "date":"yyyy-mm-dd"
        "categoryName":"Upper Body Strength"
    ]}
* GET workouts by category
GET http://localhost8080/workouts/{category}
response:
    { workouts: [
        "workoutId": 01,
        "workoutName": "Push Ups" ,
        "imageUrl": "path/directory/hereitis"
    ]}
* POST user - need to look into more with "register" and user api
* PUT user - change account info - need to be logged in
    PUT http://localhost8080/workouts/{loginId} - loginId comes from UserApi
* GET suggested workout
    GET http://localhost8080/workouts/suggestedworkout/{loginId}
* POST workout to workout history
    POST http://localhost8080/workouts/{userId}
    {
        workoutId: 01
    }








