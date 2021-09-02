import { BrowserRouter as Router, Switch, Route, useHistory, Redirect } from 'react-router-dom';
import { useState, useEffect } from 'react';
import UserNavBar from './components/UserNavBar.js';
import AdminNavBar from './components/AdminNavBar.js';
import WorkoutCatalog from './components/WorkoutCatalog.js';
import WorkoutHistory from './components/WorkoutHistory.js';
import './App.css';
import Account from './components/Account.js';
import AddEditWorkout from './components/AddEditWorkout.js';
import Home from './components/Home.js';
import AdminWorkoutCatalog from './components/AdminWorkoutCatalog.js';
import Login from './components/Login.js';
import LoginContext from './contexts/LoginContext.js';
import Register from './components/Register.js';
import EditAccount from './components/EditAccount.js';
import CurrentWorkout from './components/CurrentWorkout.js';
import { refresh } from './services/auth.js';
import { getUserWithLoginId } from './services/user.js';
import AdminHome from './components/AdminHome.js';
import NotFound from './components/NotFound.js';


const wait = 1000 * 60 * 7;

function App() {

  const [user, setUser] = useState(null);
  const [initialized, setInitialized] = useState(false);
  const history = useHistory();

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

  function onAuthenticated(token) {
    const payload = parseToken(token);
    setUser([payload.sub, payload.roles, payload.id]);
    localStorage.setItem('jwt_token', token);
    if (payload.roles === "USER") {
      setUserInformation(token);
    }
    setTimeout(refreshToken, wait);

  }

  useEffect(() => {
    const token = localStorage.getItem('jwt_token');
    if (token) {
      onAuthenticated(token);
    }
    setInitialized(true);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [history])


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


  function logout() {
    setUser(null);
    localStorage.removeItem('jwt_token');
    setFullUser(blankUser);
  }

  const refreshToken = () => {
    refresh()
      .then(token => {
        localStorage.setItem('jwt_token', token);
        setTimeout(refreshToken, wait);
      })
      .catch(console.error);
  };

  const auth = {
    user,
    onAuthenticated,
    setFullUserInformation,
    logout,
    fullUser
  }

  if (!initialized) {
    return null;
  }


  return (
    <div className="App">
      <LoginContext.Provider value={auth}>
        <Router>
          <Switch>
            {/* Do these routes if user role = user */}
            <Route path="/workouthistory">
              {auth.user !== null && auth.user[1] === "USER"
                ? <div>
                  <UserNavBar />
                  <WorkoutHistory userId={auth.fullUser.userId} />
                </div>
                : <Redirect to="/login" />
              }

            </Route>
            <Route path="/currentworkout/:workoutid">
              {auth.user !== null && auth.user[1] === "USER"
                ? <div>
                  <UserNavBar />
                  <CurrentWorkout />
                </div>
                : <Redirect to="/login" />
              }
            </Route>
            <Route path="/workoutcatalog">
              {auth.user !== null && auth.user[1] === "USER"
                ? <div>
                  <UserNavBar />
                  <WorkoutCatalog />
                </div>
                : <Redirect to="/login" />
              }
            </Route>
            <Route path="/account">
              {auth.user !== null && auth.user[1] === "USER"
                ? <div>
                  <UserNavBar />
                  <Account />
                </div>
                : <Redirect to="/login" />
              }
            </Route>
            <Route path="/editaccount/:loginId">
              {auth.user !== null && auth.user[1] === "USER"
                ? <div>
                  <UserNavBar />
                  <EditAccount />
                </div>
                : <Redirect to="/login" />
              }
            </Route>
            {/* do these route if user role = admin */}
            <Route path="/addworkout">
              {auth.user !== null && auth.user[1] === "ADMIN"
                ? <div>
                  <AdminNavBar />
                  <AddEditWorkout />
                </div>
                : <Redirect to="/login" />
              }
            </Route>
            <Route path="/editworkout/:id">
              {auth.user !== null && auth.user[1] === "ADMIN"
                ? <div>
                  <AdminNavBar />
                  <AddEditWorkout />
                </div>
                : <Redirect to="/login" />
              }
            </Route>
            <Route path="/adminworkoutcatalog">
              {auth.user !== null && auth.user[1] === "ADMIN"
                ? <div>
                  <AdminNavBar />
                  <AdminWorkoutCatalog />
                </div>
                : <Redirect to="/login" />
              }
            </Route>
            {/* Do these routes below with both roles. if not logged in, redirect to login */}
            <Route path="/login">
              <Login />
            </Route>
            <Route path="/register">
              <Register />
            </Route>
            <Route exact path="/">
              {auth.user === null &&
                <Redirect to="/login" />}
              {auth.user !== null && auth.user[1] === "USER" &&
                <div>
                  <UserNavBar />
                  <Home userId={auth.fullUser.userId} />
                </div>
              }

              {/* if admin: */}
              {auth.user !== null && auth.user[1] === "ADMIN" &&
                <div>
                  <AdminNavBar />
                  <AdminHome />
                </div>
              }
            </Route>
            <Route>
              {auth.user !== null && auth.user[1] === "USER" &&
                <UserNavBar />
              }

              {/* if admin: */}
              {auth.user !== null && auth.user[1] === "ADMIN" &&
                <AdminNavBar />
              }
              <NotFound />
            </Route>
          </Switch>
        </Router>
      </LoginContext.Provider>
    </div>
  );
}

export default App;
