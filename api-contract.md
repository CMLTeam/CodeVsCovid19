

## Test data

To see detailed information, call correspondent API endpoints.

#### Customers:
1001 - John Doe, 1002 - Jennifer Doe (both related to each other)

#### Doctors: 
2001 - watching for 1001 John Doe

#### Slots: 
3001, 3002, 3003, 3004, 3005, 3006
    
#### Targets: 
4001 (slots: 3001 3002 3003) ,

4002 (slots: 3004 3005 3006)
    
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
        slots: [
            {
                slotId: integer,
                asString: string,
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

GET /targets/{target_id}/slots

get slots by target id. Returned slots are grouped by target id.

<-- json
```
[
    {
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
        slots: [
            {
            slotId: integer,
            asString: string, //representation like string: "08.00 - 10.30 AM"
            startDate: datetime, // ISO8601, start date to sort by 
            endDate: datetime, // ISO8601, end date to maybe sort by
            freeCapacity: integer // how many people may join slot  
            },
            ...
        ]
    },
    ...
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
        name: string, // first last names
        illnessRate: integer, // from 0 to 1000 
        status: string, // enum: [normal, analysis, ill]
        address: string, // just to print
        pictureUrl: string, // to put it in <img> tag
        closeСommunicationWith: interger[] // ids of customers with whom customer relate
}
```

GET /slots/

Get customer's booked slots 

<--- json

```
[
    {
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
        slots: [
            {
            slotId: integer,
            asString: string, //representation like string: "08.00 - 10.30 AM"
            startDate: datetime, // ISO8601, start date to sort by 
            endDate: datetime, // ISO8601, end date to maybe sort by
            freeCapacity: integer // how many people may join slot  
            },
            ...
        ]
    },
    ...
]
```


POST /bookings

post new booking (customer is gonna be hardcoded)

--> url param
```
    customerId: integer //it can be also called by guard, but if null use hardcoded customer id?
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
        slotId: integer,
        asString: string, //representation like string: "08.00 - 10.30 AM"
        startDate: datetime, // ISO8601, start date to sort by 
        endDate: datetime, // ISO8601, end date to maybe sort by
        freeCapacity: integer // how many people may join slot  
        },
        ...
    ]
```

POST /took-slot

Web-Hook to inform backend that customer used current slot

--> json
```
{
    customerId : integer,
    slotId: integer,
}
```
<-- ``` 200 ```

## Endpoints for doctor

GET /doctors/{doctor_id}

<--
{
    id: integer
    name: string
}

GET /doctors/{doctor_id}/customers

Getting customers.
Authorization for doctor is not required, we assume here is only one doctor

<-- json
```
[
    {
        id: interger, // customer id
        phoneNumber: string, // phoneNumber,
        documentId: string,//some sort of ID for government, acquired via BankID (potentially)
        name: string, // first last names
        illnessRate: integer, // from 0 to 1000 
        status: string, // enum: [normal, analysis, ill]
        address: string, // just to print
        pictureUrl: string, // to put it in <img> tag
        closeCommunicationWith: interger[] // ids of customers with whom customer relate
    },
    ...
]
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
           name: string, // first and last name
           illnessRate: integer, // from 0 to 1000 
           status: string, // enum: [normal, analysis, ill]
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
    status: string, // enum: [normal, analysis, ill]
}
```
<--



