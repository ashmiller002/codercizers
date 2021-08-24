import { BrowserRouter as Router, Switch, Route, useHistory, Redirect } from 'react-router-dom';
import UserNavBar from './components/UserNavBar.js';
import './App.css';

function App() {
  return (<>
    <div>

      <Router>
        <Switch>
          <Route exact path="/">
            <UserNavBar />
          </Route>
        </Switch>
      </Router>

    </div></>
  );
}

export default App;
