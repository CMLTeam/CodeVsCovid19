import React from "react";
import "./App.css";
import useFetchCustomers from "./hooks/useFetchCustomers";
import { CustomersTable } from "./CustomersTable";

const App = () => {
  const [customers, useCustomers] = useFetchCustomers();

  return (
    <div className="App">
      <CustomersTable customers={customers} />
    </div>
  );
};

export default App;
