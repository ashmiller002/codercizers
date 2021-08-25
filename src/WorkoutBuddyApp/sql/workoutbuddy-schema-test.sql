drop database if exists workout_buddy_test;
create database workout_buddy_test;
use workout_buddy_test;

-- create tables and relationships
create table category (
	category_id int primary key auto_increment,
    category_name varchar(100) not null
);

create table workout (
	workout_id int primary key auto_increment,
    workout_name varchar(100) not null,
    category_id int not null,
    image_url varchar(10000),
    constraint fk_workout_category_id
		foreign key (category_id)
        references category(category_id)
 );
 
 create table activity_level (
	activity_level_id int primary key auto_increment,
    activity_level_name varchar(100) not null
 );
 
 create table goal (
	goal_id int primary key auto_increment,
    goal_name varchar(100) not null
 );
 
 create table program (
	program_id int primary key auto_increment,
    activity_level_id int not null,
    goal_id int not null,
    constraint fk_program_activity_level_id
		foreign key (activity_level_id)
        references activity_level(activity_level_id),
	constraint fk_program_goal_id
		foreign key (goal_id)
        references goal(goal_id)
 );
 
 create table `user` (
	user_id int primary key auto_increment,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    date_birth date not null,
    email varchar(100) unique,
    program_id int not null,
    login_id int not null,
    constraint fk_user_program_id
		foreign key (program_id)
        references program(program_id)
 );
 
 create table user_workout (
	user_workout_id int primary key auto_increment,
    user_id int not null,
    workout_id int not null,
    workout_date date not null,
    constraint fk_user_workout_user_id
		foreign key (user_id)
        references `user`(user_id),
	constraint fk_user_workout_workout_id
		foreign key (workout_id)
        references workout(workout_id)
 );