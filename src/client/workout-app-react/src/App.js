import { BrowserRouter as Router, Switch, Route, useHistory, Redirect } from 'react-router-dom';
import UserNavBar from './components/UserNavBar.js';
import AdminNavBar from './components/AdminNavBar.js';
import WorkoutCatalogue from './components/WorkoutCatalogue.js';
import WorkoutHistory from './components/WorkoutHistory.js';
import AddExternalWorkout from './components/AddExternalWorkout.js';
import './App.css';
import Account from './components/Account.js';
import AddWorkout from './components/AddWorkout.js';
import EditWorkout from './components/EditWorkout.js';
import Home from './components/Home.js';

function App() {
  return (
    <div>

      <Router>
        <Switch>
          {/* Do these routes if user role = user */}

          <Route path="/workouthistory">
            <UserNavBar />
            <WorkoutHistory />
          </Route>
          <Route path="/workoutcatalogue">
            <UserNavBar />
            <WorkoutCatalogue />
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
            <AddWorkout />
          </Route>
          <Route path="/editworkout:workoutid">
            <EditWorkout />
          </Route>
          {/* Do these routes below with both roles. if not logged in, redirect to login */}
          <Route exact path="/">
            {/* if user: */}
            <UserNavBar/>
            {/* if admin: */}
            <AdminNavBar/>
            <Home />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
