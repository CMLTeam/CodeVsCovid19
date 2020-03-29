
### Endpoints for menu

GET /targets 

backend assume that user always the same, auth not required.

--> url params
```     
    locationLatitude: integer // optional(default:customer's address from db)
    locationLongitude: integer // optional(default:customer's address from db)
    type: string // optional(default:"shop") // enum: ["shop", "pharmacy", ...]
```
<-- json
```
[
    {
        id: integer, // slots could be fetched by this id as targetId
        type: string // enum: ["shop", "pharmacy", ...]
        name: string,
        distance: integer, // distance in meters from you,
        maxPeopleCapacity: integer, // how many people curr. target could initially handle
        address: string,
        latitude: float,
        longitude: float,
        workingTime: "string", // when target(e.g. shop) is working
        pictureUrl: string, // to put it in <img> tag
        slotsPreviews: [
            {
                startDate: datetime, // ISO8601, to sort by,
                endDate: datetime, // ISO8601, end date to maybe sort by
                freeCapacity: integer // how many people may join slot
            }, 
            {
            ...
            },
            ...   
        ]
    },
    ...
]
```

GET /targets/slots/

get slots by target id

--> url params
```   
    targetId: integer
```
<-- json
```
[
    target: {
        id: integer, 
        type: string // enum: ["shop", "pharmacy", ...]
        name: string,
        distance: integer, // distance in meters from you,
        maxPeopleCapacity: integer,
        address: string,
        latitude: float,
        longitude: float,
        workingTime: "string", // when target(e.g. shop) is working
        pictureUrl: string, // to put it in <img> tag
    },
    slots: [
        {
        asString: string, //representation like string: "08.00 - 10.30 AM"
        startDate: datetime, // ISO8601, start date to sort by 
        endDate: datetime, // ISO8601, end date to maybe sort by
        freeCapacity: integer // how many people may join slot  
        },
        ...
    ]
]
```

## Personal customer's endpoints

#### Customer is gonna be hardcoded, therefore customer_id in requests not required

GET /me

<-- json
```
    {
        id: interger, // customer id
        phoneNumber: string, // phoneNumber,
        documentId: string,//some sort of ID for government, acquired via BankID (potentially)
        illnessRate: integer, // from 0 to 1000 
        status: string, // enum: [normal, required_doctor_visit, civid19_positive]
        address: string, // just to print
        pictureUrl: string, // to put it in <img> tag
        closeСommunicationWith: interger[] // ids of customers with whom customer relate
}
```

GET /slots/

Get customer's booked slots 

<-- json
```
[
    {
        targetName: string,
        asString: string, //representation like string: "08.00 - 10.30 AM"
        startDate: datetime, // ISO8601, start date to sort by 
        endDate: datetime, // ISO8601, end date to maybe sort by
        freeCapacity: integer // how many people may join slot  
    }
]
```

POST /bookings

post new booking (customer is gonna be hardcoded)

--> url param
```
    slotId: integer, // slot id 
```
<-- ```200 OK```

## Endpoints for guard

GET /customers/{customer_id}/validate/{target_id}

for security guard on target's entrance

<--
```
   [
       {
           asString: string, //representation like string: "08.00 - 10.30 AM"
           startDate: datetime, // ISO8601, start date to sort by 
           endDate: datetime, // ISO8601, end date to maybe sort by
           freeCapacity: integer // how many people may join slot  
       }
   ]
```

POST /took-slot

Web-Hook to inform backend that customer used current slot

--> json
```
{
    customerId : integer,
    targetId : integer
}
```
<-- ``` 200 ```

## Endpoints for doctor

GET /doctor/{doctor_id}/customers

Getting customers.
Authorization for doctor is not required, we assume here is only one doctor

--> url param
```
    id: interger // customer id.
```
<-- json
```
{
    id: interger, // customer id
    phoneNumber: string, // phoneNumber,
    documentId: string,//some sort of ID for government, acquired via BankID (potentially)
    illnessRate: integer, // from 0 to 1000 
    status: string, // enum: [normal, required_doctor_visit, civid19_positive]
    address: string, // just to print
    pictureUrl: string, // to put it in <img> tag
    closeCommunicationWith: interger[] // ids of customers with whom customer relate
}
```

GET /customers/{customer_id}/related

get customers with whom given customer in close relationship (basically - family)

<-- json
```
    [
      {     
           id: interger, // customer id
           phoneNumber: string, // phoneNumber,
           documentId: string,//some sort of ID for government, acquired via BankID (potentially)
           illnessRate: integer, // from 0 to 1000 
           status: string, // enum: [normal, required_doctor_visit, civid19_positive]
           address: string, // just to print
           pictureUrl: string, // to put it in <img> tag,
      },
      {
        ...
      }, 
      ...
    ]
```

PUT /customers/{customer_id}/status

when status changed from anything to 'normal', illnessRate resets to default

--> json
```
{
    status: string, // enum: [normal, required_doctor_visit, civid19_positive]
}
```
<--



