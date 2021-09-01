
import React from 'react';
import { StyleSheet, Text, View, SafeAreaView, Button } from 'react-native';

function SuggestedWorkout({history}) {
    return(
        <View>
            <Text>Suggested Workout</Text>
        <Button title="Home" onPress={() => history.push("/")} />
        </View>
        
    )
} 

export default SuggestedWorkout;