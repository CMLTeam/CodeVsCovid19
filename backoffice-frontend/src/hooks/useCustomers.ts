import { Customer } from "../dto";
import { useEffect, useState } from "react";
import axios from "axios";
import { BACKEND_API } from "../index";
import { sleep } from "./utils";

const getDummyData = async (): Promise<Customer[]> => {
  await sleep(1);
  return [
    {
      id: 546,
      phoneNumber: "555-55-55",
      documentId: "DI-25645",
      name: "John Dou Micklovich",
      illnessRate: 0,
      address: "Third planet from Sun",
      status: "negative",
      pictureUrl: "/546_profile_photo.jpg",
      closeCommunicationWith: [547],
    },
    {
      id: 547,
      phoneNumber: "555-55-55",
      documentId: "DI-25646",
      name: "Johna Dou Silvestrovna",
      illnessRate: 356,
      address: "Third planet from Sun",
      status: "suspected",
      closeCommunicationWith: [546],
    },
    {
      id: 85,
      phoneNumber: "555-35-55",
      documentId: "DI-65486",
      name: "Another Name",
      illnessRate: 1000,
      address: "Second planet from Sun",
      status: "positive",
      closeCommunicationWith: [],
    },
  ];
};

const useCustomers = (): [Customer[], () => void] => {
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [updateCustomers, setUpdate] = useState({});

  useEffect(() => {
    (async () => {
      const { data } = await axios.get(`${BACKEND_API}/doctors/2001/customers`);
      // const data = await getDummyData();
      console.log("Customers updated", data);
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

export default useCustomers;
