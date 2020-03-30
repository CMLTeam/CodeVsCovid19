import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';


let BACKEND;
if (window.location.hostname === "localhost" || window.location.hostname === "127.0.0.1") {
  BACKEND = "http://127.0.0.1:8099";
} else {
  BACKEND = "http://cmlteam.com:8099";
}

export const BACKEND_API = BACKEND;

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
