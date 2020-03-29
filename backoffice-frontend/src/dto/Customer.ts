import {Status} from "./Status";

export interface Customer {
    id: number, // customer id
    phoneNumber: string, // phoneNumber,
    documentId: string,//some sort of ID for government, acquired via BankID (potentially)
    name: string, // first last names
    illnessRate: number, // from 0 to 1000
    status: Status, // enum: [normal, required_doctor_visit, civid19_positive]
    address: string, // just to print
    pictureUrl: string, // to put it in <img> tag
    closeCommunicationWith: number[] // ids of customers with whom customer relate
}