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
    setUser({
      username: payload.sub,
      role: payload.roles
    });
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
    <div>
      <LoginContext.Provider value={auth}>
      <Router>
        <Switch>
          {/* Do these routes if user role = user */}
          <Route path="/workouthistory">
            <UserNavBar />
            <WorkoutHistory />
          </Route>
          <Route path="/workoutcatalog">
            <UserNavBar />
            <WorkoutCatalog />
          </Route>
          <Route path="/addexternalworkout">
            <UserNavBar />
            <AddExternalWorkout />
          </Route>
          <Route path="/account">
            <UserNavBar />
            <Account />
          </Route>
          {/* do these route if user role = admin */}
          <Route path="/addworkout">
            <AdminNavBar />
            <AddWorkout />
          </Route>
          <Route path="/editworkout:workoutid">
            <AdminNavBar />
            <EditWorkout />
          </Route>
          <Route path="/adminworkoutcatalog">
            <AdminNavBar />
            <AdminWorkoutCatalog />
          </Route>
          {/* Do these routes below with both roles. if not logged in, redirect to login */}
          <Route path="/login">
            <Login />
          </Route>
          <Route exact path="/">
            {/* if user: */}
            <UserNavBar/>
            {/* if admin: */}
            <AdminNavBar/>
            <Home />
          </Route>
        </Switch>
      </Router>
      </LoginContext.Provider>
    </div>
  );
}

export default App;
