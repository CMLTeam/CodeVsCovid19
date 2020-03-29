import { Customer } from "../dto";
import { useEffect, useState } from "react";
import axios from "axios";
import { BACKEND_API } from "../index";
import {sleep} from "./utils";

const getDummyData = async (): Promise<Customer[]> => {
  await sleep(2);
  return [
    {
      id: 546,
      phoneNumber: "555-55-55",
      documentId: "DI-25645",
      name: "John Dou Micklovich",
      illnessRate: 254,
      address: "Third planet from Sun",
      status: "analysis",
      pictureUrl: "url",
      closeCommunicationWith: [547],
    },
    {
      id: 547,
      phoneNumber: "555-55-55",
      documentId: "DI-25646",
      name: "Johna Dou Silvestrovna",
      illnessRate: 356,
      address: "Third planet from Sun",
      status: "analysis",
      pictureUrl: "url",
      closeCommunicationWith: [546],
    },
    {
      id: 85,
      phoneNumber: "555-35-55",
      documentId: "DI-65486",
      name: "Another Name",
      illnessRate: 1000,
      address: "Third planet from Sun",
      status: "analysis",
      pictureUrl: "url",
      closeCommunicationWith: [],
    },
  ];
};

const useFetchCustomers = (): [Customer[], () => void] => {
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [updateCustomers, setUpdate] = useState({});

  useEffect(() => {
    (async () => {
      // const data = (await axios.get(`${BACKEND_API}/doctors/42/customers}`)).data;
      const data = (await getDummyData());
      setCustomers(data);
    })();
  }, [updateCustomers]);

  return [
    customers,
    () => {
      console.log("Fetch");
      setUpdate({});
    },
  ];
};

export default useFetchCustomers;
