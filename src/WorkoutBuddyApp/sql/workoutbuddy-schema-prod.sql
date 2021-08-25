drop database if exists workout_buddy;
create database workout_buddy;
use workout_buddy;

-- create tables and relationships
create table category (
	category_id int primary key auto_increment,
    category_name varchar(100) not null
);

create table workout (
	workout_id int primary key auto_increment,
    workout_name varchar(100) not null,
    category_id int not null,
    workout_status varchar(20) not null,
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
    user_status varchar(20) not null,
    login_id varchar(50) not null,
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
 
 insert into category (category_id, category_name)
	values (1, 'Upper Body Strength'),
			(2, 'Lower Body Strength'),
		(3, 'Cardio'),
		(4, 'Mobility'),
        (5, 'Rest Day');
        
insert into goal (goal_id, goal_name)
	values (1, 'Strength'),
		(2, 'Mobility'),
        (3, 'Weight Loss');
        
insert into workout (workout_id, workout_name, category_id, workout_status)
	values (1, 'Upper Body 1', 1, 'enable'),
			(2, 'Upper Body 2', 1, 'enable'),
            (3, 'Upper Body External', 1, 'enable'),
            (4, 'Lower Body 1', 2, 'enable'),
            (5, 'Lower Body 2', 2, 'enable'),
            (6, 'Lower Body External', 2, 'enable'),
            (7, 'Running', 3, 'enable'),
            (8, 'Dance Cardio', 3, 'enable'),
            (9, 'Cardio External', 3, 'enable'),
            (10, 'Stretching', 4, 'enable'),
            (11, 'Yoga', 4, 'enable'),
            (12, 'Stretching External', 4, 'enable'),
            (13, 'Rest Day', 5, 'enable');
            
	insert into activity_level (activity_level_id, activity_level_name)
		values (1, 'infrequent'),
			(2, 'frequent');
	
    insert into program (program_id, activity_level_id, goal_id)
		values (1, 1, 1),
			(2, 1, 2),
            (3, 1, 3),
            (4, 2, 1),
            (5, 2, 2),
            (6, 2, 3);
    
    