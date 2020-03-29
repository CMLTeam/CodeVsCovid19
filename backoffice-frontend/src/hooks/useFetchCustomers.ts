import {Customer} from "../dto";
import {useState} from "react";


const useFetchCustomers = (): [Customer[], () => void] => {

    const [customers, setCustomers] = useState<Customer[]>([]);


    return [[], () => (console.log("Fetch"))]
};

export default useFetchCustomers;