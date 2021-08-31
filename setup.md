# Workout Buddy - The Codercizers

## User API
* Open the user API server app in intelliJ located here: codercizers\src\user-api\server\
* The user API runs on port 5000
* Configure environmental variables - database name: dev_10_users
    * DB_URL=jdbc:mysql://localhost:3306/dev_10_users
    * JWT_SECRET_KEY=de8a26d0-f6e8-4470-91d0-ba7a44391281
* Run the SQL Scripts located here: codercizers\src\user-api\database
    * First run a_ddl
    * Second run b_dml


## Workout API
* Open the workout API app in intelliJ located here: codercizers\src\WorkoutBuddyApp\
* The workout API runs on port 8080
* Configure environmental variables - database name: workout_buddy
    * DB_URL=jdbc:mysql://localhost:3306/workout_buddy;
* Run the SQL Script located here: codercizers\src\WorkoutBuddyApp\sql
    * Run both scripts, order does not matter

## React
* Open the react app here: codercizers\src\client\workout-app-react\
* Open a terminal and type "npm install" to install necessary dependencies
* Type "npm start" to run the app
* Type ctrl + c to stop running the app, then type Y when prompted
* The react app runs on port 3000
