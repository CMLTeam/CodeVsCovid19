import React from "react";
import "./App.css";
import { CustomersTable } from "./CustomersTable";
import { Header } from "./Header";

const App = () => {
  return (
    <div className="App">
      <Header />
      <CustomersTable />
    </div>
  );
};

export default App;
