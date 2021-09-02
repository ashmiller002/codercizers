import React from 'react';
import { Text, Button, View } from 'react-native';


function WorkoutCard( {workout} ) {





    return (
    <View>
        <div className="row">
            <div className="col s12 m6">
                <div className="card">
                    <div className="card-image">
                        <Image src="assets/icon.png"/>
                        <span className="card-title">workout.workoutName</span>
                        <a class="btn-floating halfway-fab waves-effect waves-light red"><i class="material-icons">add</i></a>                      
                    </div>
                </div>
            </div>
        </div>
    </View>);
}

export default WorkoutCard;