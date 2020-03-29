import React from "react";
import logo from "./COVID-19_World_Map.svg";
import "./App.css";
import useFetchCustomers from "./hooks/useFetchCustomers";

const App = () => {
  const [customers, useCustomers] = useFetchCustomers();

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>Update Cool frontend for reduce covid spreading.</p>
      </header>
    </div>
  );
};

export default App;
