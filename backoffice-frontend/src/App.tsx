import React from "react";
import "./App.css";
import logo from "./COVID-19_World_Map.svg";
import useFetchCustomers from "./hooks/useFetchCustomers";
import { CustomersTable } from "./CustomersTable";
import {Header} from "./Header";

const App = () => {
  const [customers, useCustomers] = useFetchCustomers();

  return (
    <div className="App">
      <Header/>
      <CustomersTable customers={customers} />
    </div>
  );
};

export default App;
