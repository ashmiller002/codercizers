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

function App() {

  const [user, setUser] = useState(null);
  const [initialized, setInitialized] = useState(false);
  const history = useHistory();

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
                ? <UserNavBar />
                : <WorkoutCatalog />
              }
            </Route>
            <Route path="/addexternalworkout">
              {auth.user !== null && auth.user[1] === "USER"
                ? <UserNavBar />
                : <AddExternalWorkout />
              }
            </Route>
            <Route path="/account">
              {auth.user !== null && auth.user[1] === "USER"
                ? <UserNavBar />
                : <Account />
              }
            </Route>
            {/* do these route if user role = admin */}
            <Route path="/addworkout">
              {auth.user !== null && auth.user[1] === "ADMIN"
                ? <AdminNavBar />
                : <AddWorkout />
              }
            </Route>
            <Route path="/editworkout:workoutid">
              {auth.user !== null && auth.user[1] === "ADMIN"
                ? <AdminNavBar />
                : <EditWorkout />
              }
            </Route>
            <Route path="/adminworkoutcatalog">
              {auth.user !== null && auth.user[1] === "ADMIN"
                ? <AdminNavBar />
                : <AdminWorkoutCatalog />
              }
            </Route>
            {/* Do these routes below with both roles. if not logged in, redirect to login */}
            <Route path="/login">
              <Login />
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
      </LoginContext.Provider>
    </div>
  );
}

export default App;
