import { Link } from 'react-router-native';
import React from 'react';
import { StyleSheet, Text, View, SafeAreaView, Button } from 'react-native';
import WorkoutCard from './WorkoutCard';

function SuggestedWorkout({history}) {
    return(
        <View>
            <Text>Suggested Workout</Text>
            <WorkoutCard />
        <Button title="Home" onPress={() => history.push("/")} />
        </View>
        
    )
} 

export default SuggestedWorkout;