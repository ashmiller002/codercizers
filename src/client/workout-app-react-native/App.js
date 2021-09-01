import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import { NativeRouter, Switch, Route, Link, useHistory } from 'react-router-native';
import Home from './components/Home';
import LoginGood from './components/LoginGood';


export default function App() {

  return (
    <View style={styles.container}>
      <NativeRouter>
        <Switch>
          <Route exact path="/login" component={LoginGood}>
          </Route>
          <Route exact path="/getsuggestedworkout">

          </Route>
          <Route exact path="/" component={Home} />
        </Switch>
      </NativeRouter>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',

  },
});
