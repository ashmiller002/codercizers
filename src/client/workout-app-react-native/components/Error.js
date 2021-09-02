import React from 'react';
import { View, Text } from 'react-native';

function Error({ errorMessages }) {


    if (errorMessages !== undefined) {
        return (
            <View className="materialert danger">
                {
                    errorMessages.map((m) => {
                        return (<View key={errorMessages.indexOf(m)}><Text>{m}</Text></View>)
                    })
                }

            </View>
        );
    } else {
        return <View></View>;
    }
}

export default Error;