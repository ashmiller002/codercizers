import React from 'react';
import { View, Button } from "react-native";

function Home({history}) {
    return(
        <View>
            <Button title="Login" onPress={() => history.push("/login")} />
            <Button title="Get Suggested Workout" onPress={() => history.push("/getsuggestedworkout")} />
        </View>

    )
}

export default Home;