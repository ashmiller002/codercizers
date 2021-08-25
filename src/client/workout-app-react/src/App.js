import { BrowserRouter as Router, Switch, Route, useHistory, Redirect } from 'react-router-dom';
import { useState, useEffect } from 'react';
import jwtDecode from 'jwt-decode';
import UserNavBar from './components/UserNavBar.js';
import AdminNavBar from './components/AdminNavBar.js';
import WorkoutCatalog from './components/WorkoutCatalog.js';
import WorkoutHistory from './components/WorkoutHistory.js';
import AddExternalWorkout from './components/AddExternalWorkout.js';
import './App.css';
import Account from './components/Account.js';
import AddWorkout from './components/AddWorkout.js';
import EditWorkout from './components/EditWorkout.js';
import Home from './components/Home.js';
import AdminWorkoutCatalog from './components/AdminWorkoutCatalog.js';
import Login from './components/Login.js';
import LoginContext from './contexts/LoginContext.js';
import Register from './components/Register.js';
import FullUserContext from './contexts/FullUserContext.js';

function App() {

  const [user, setUser] = useState(null);
  const [initialized, setInitialized] = useState(false);
  const history = useHistory();
  const fullUser = {
    loginId: null,
    userId: 0,
    firstName: null,
    lastName: null,
    dateBirth: null,
    email: null,
    goal: null, // strength = 1, mobility = 2, weight loss = 3 
    activityLevel: null, // frequent = 2 or infrequent = 1 send just number
  }

  // on login get user info and setFullUser

  const onAuthenticated = (token) => {
    const payload = jwtDecode(token);
    setUser([payload.sub, payload.roles]);
    localStorage.setItem('jwt_token', token);
  }

  useEffect(() => {
    const token = localStorage.getItem('jwt_token');
    if (token) {
      onAuthenticated(token);
    }

    setInitialized(true);
  }, [history])

  function logout() {
    setUser(null);
    localStorage.removeItem('jwt_token');
  }

  const auth = {
    user,
    onAuthenticated,
    logout
  }

  if (!initialized) {
    return null;
  }

  return (
    <div class="App">
      <LoginContext.Provider value={auth}>
        <FullUserContext.Provider value={fullUser}>
          <Router>
            <Switch>
              {/* Do these routes if user role = user */}
              <Route path="/workouthistory">
                {auth.user !== null && auth.user[1] === "USER"
                  ? <div>
                    <UserNavBar />
                    <WorkoutHistory />
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
              <Route path="/addexternalworkout">
                {auth.user !== null && auth.user[1] === "USER"
                  ? <div>
                    <UserNavBar />
                    <AddExternalWorkout />
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
              {/* do these route if user role = admin */}
              <Route path="/addworkout">
                {auth.user !== null && auth.user[1] === "ADMIN"
                  ? <div>
                    <AdminNavBar />
                    <AddWorkout />
                  </div>
                  : <Redirect to="/login" />
                }
              </Route>
              <Route path="/editworkout:workoutid">
                {auth.user !== null && auth.user[1] === "ADMIN"
                  ? <div>
                    <AdminNavBar />
                    <EditWorkout />
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
                  <UserNavBar />
                }

                {/* if admin: */}
                {auth.user !== null && auth.user[1] === "ADMIN" &&
                  <AdminNavBar />
                }

                <Home />
              </Route>
            </Switch>
          </Router>
        </FullUserContext.Provider>
      </LoginContext.Provider>
    </div>
  );
}

export default App;
