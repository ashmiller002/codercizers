import { BrowserRouter as Router, Switch, Route, useHistory, Redirect } from 'react-router-dom';
import UserNavBar from './components/UserNavBar.js';
import AdminNavBar from './components/AdminNavBar.js';
import WorkoutCatalogue from './components/WorkoutCatalogue.js';
import './App.css';

function App() {
  return (
    <div>

      <Router>
        <Switch>
          {/* Do these routes if user role = user */}

          <Route path="/workouts/workouthistory/">

          </Route>
          <Route path="/workoutcatalogue/">
            <WorkoutCatalogue />
          </Route>
          <Route path="/account/">

          </Route>
        </Switch>
      </Router>

    </div>
  );
}

export default App;
