import React from 'react';
import { useState, useEffect } from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { NativeRouter, Switch, Route, Link, useHistory } from 'react-router-native';
import Home from './components/Home';
import LoginGood from './components/LoginGood';
import SuggestedWorkout from './components/SuggestedWorkout';
import LoginContext from './contexts/LoginContext';


export default function App() {
  const [user, setUser] = useState(null);
  const [initialized, setInitialized] = useState(false);

  const blankUser = {
    loginId: null,
    userId: 0,
    firstName: null,
    lastName: null,
    dateBirth: null,
    email: null,
    goal: null, // strength = 1, mobility = 2, weight loss = 3 
    activityLevel: null, // frequent = 2 or infrequent = 1 send just number
  }


  const [fullUser, setFullUser] = useState(blankUser);
  // on login get user info and setFullUser
  const parseToken = (token) => {
    const tokenTokens = token.split('.');
    try {
      const jwtBody = tokenTokens[1];
      return JSON.parse(atob(jwtBody));
    } catch (err) {
      console.log(err)
      console.log("Failed to parse token.")
    }
  }

  async function onAuthenticated(token) {
    const payload = parseToken(token);
    setUser([payload.sub, payload.roles, payload.id]);
    try {
      await AsyncStorage.setItem('jwt_token', token);
    }
    catch (e) {
      console.log(e)
    }

    if (payload.roles === "USER") {
      setUserInformation(token);
    }
    setTimeout(refreshToken, wait);

  }

  useEffect(() => {
    getToken();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  async function getToken() {
    try {
      const token = await AsyncStorage.getItem('jwt_token');
      if (token) {
        onAuthenticated(token);
      }
      setInitialized(true);
    }
    catch (e) {
      console.log(e);
    }
  }

  function setFullUserInformation(userInfo) {
    const { loginId, userId, firstName, lastName, dateBirth, email, goalId, activityLevelId } = userInfo;
    let nextFullUser = blankUser;
    nextFullUser.loginId = loginId
    nextFullUser.userId = userId;
    nextFullUser.firstName = firstName;
    nextFullUser.lastName = lastName;
    nextFullUser.dateBirth = dateBirth;
    nextFullUser.email = email;
    nextFullUser.goalId = goalId;
    nextFullUser.activityLevelId = activityLevelId;
    setFullUser(nextFullUser);

  }

  // to keep user information with page refresh
  function setUserInformation(token) {
    const payload = parseToken(token);
    const id = payload.id;
    getUserWithLoginId(id, token)
      .then((userInfo) => {
        setFullUser(userInfo);
      }
      )
      .catch((err) => {
        console.log(err);
        auth.logout();
        //setErrors(err);

      }
      )
  }


  async function logout() {
    setUser(null);
    try {
      await AsyncStorage.removeItem('jwt_token');
    }
    catch (e) {
      console.log(e);
    }
    setFullUser(blankUser);
  }

  // const refreshToken = async () => {
  //    refresh()
  //     .then(token => {
  //       try {
  //         await AsyncStorage.setItem('jwt_token', token);
  //         setTimeout(refreshToken, wait);
  //       }
  //       catch(e) {
  //         console.log(e);
  //       }

  //     })
  //     .catch(console.error);
  //};

  const auth = {
    user,
    onAuthenticated,
    setFullUserInformation,
    logout,
    fullUser
  }

  return (
    <View style={styles.container}>
      <LoginContext.Provider value={auth}>
        <NativeRouter>
          <Switch>
            <Route exact path="/login" component={LoginGood}>
            </Route>
            <Route exact path="/getsuggestedworkout" component={SuggestedWorkout} />
            <Route exact path="/" component={Home} />
          </Switch>
        </NativeRouter>
      </LoginContext.Provider>
    </View>
  );

  return (
    <View style={styles.container}>
      <Text>Hello</Text>
    </View>
  )
}



const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',

  },
});
