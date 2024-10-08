# Train Ticket Management

## Overview

The Train Ticket Booking System is a Spring Boot RESTful service for managing train tickets from Location A to Location B. It supports seat booking, viewing, modification, and cancellation of tickets.

## How to start the Applications

1. This application requires ```Java 17```
2. run ``` ./gradlew clean build```
3. to start the app  ``` ./gradlew bootRun```


#### ** This application makes use of In-Memory Database H2, so every time the server restarts the data will be purged

## To visualize DB

1. go to ```http://localhost:8080/h2-console```
2. use JDBC url as ```jdbc:h2:mem:ticket-management```
3. userName ```vikrant```
4. password ```pratap```

### Book Ticket If User Exists  
    curl --location 'http://localhost:8080/api/v1/book-ticket' \--header 'Content-Type: application/json' \
    --data-raw '{
    "userId":"5f47c687-d3f8-4a5e-9fc3-5a4ad8b6b7a1",
    "from":"London",
    "to":"France",
    "ticketPrice":"20",
    "userInfo":{
    "userId":"5f47c687-d3f8-4a5e-9fc3-5a4ad8b6b7a1",
    "firstName":"John",
    "lastName":"Doe",  
    "emailId":"john.doe@example.com"
    }
    }'


    Response:
    {
    "data": {
        "userId": "5f47c687-d3f8-4a5e-9fc3-5a4ad8b6b7a1",
        "from": "London",
        "to": "France",
        "pricePaid": 20,
        "ticketId": "0fe0c173-e827-4a42-8877-7197ec5e7dd7",
        "section": "A",
        "seatNumber": 1
    },
    "status": "OK"
    } 

### Book Ticket for New User
     curl --location 'http://localhost:8080/api/v1/book-ticket' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "from":"America",
    "to":"India",
    "ticketPrice":"98",
    "userInfo":{
    "firstName":"Jhonny",
    "lastName":"Rhoades",
    "emailId":"john.doe5@example.com"
    }
    }'
    
    Response:

    {
    "data": {
        "userId": "6756f1b1-c6c2-49d4-af43-8f1ac229d236",
        "from": "America",
        "to": "India",
        "pricePaid": 98,
        "ticketId": "d02e8098-94a2-4e90-a2a4-161330b3456d",
        "section": "A",
        "seatNumber": 3
    },
    "status": "OK"
    }

### Fetch Tickets of Section

     curl --location 'http://localhost:8080/api/v1/fetch/B'
     
     Response:
 
     {
    "data": {
        "seats": [
            {
                "seatId": 4,
                "section": "B",
                "seatNumber": 1,
                "isOccupied": false
            },
            {
                "seatId": 5,
                "section": "B",
                "seatNumber": 2,
                "isOccupied": false
            },
            {
                "seatId": 6,
                "section": "B",
                "seatNumber": 3,
                "isOccupied": false
            },
            {
                "seatId": 10,
                "section": "B",
                "seatNumber": 1,
                "isOccupied": false
            }
        ]
    },
    "status": "OK"
    }


### Fetch Users Ticket Receipt

     curl --location 'http://localhost:8080/api/v1/ticket/5f47c687-d3f8-4a5e-9fc3-5a4ad8b6b7a1'

     Response:
    
     {
    "data": {
        "userId": "5f47c687-d3f8-4a5e-9fc3-5a4ad8b6b7a1",
        "ticketInfos": [
            {
                "ticketId": "0fe0c173-e827-4a42-8877-7197ec5e7dd7",
                "section": "A",
                "seatNumber": 1,
                "paidAmount": 20
            }
        ]
    },
    "status": "OK"
    }

### Update Ticket (Booked)

     curl --location --request PUT 'http://localhost:8080/api/v1/update-ticket' \
     --header 'Content-Type: application/json' \
     --data '{
    "ticketId":"d02e8098-94a2-4e90-a2a4-161330b3456d",
     "userId":"6756f1b1-c6c2-49d4-af43-8f1ac229d236",
    "newSection":"B",
    "newSeatNumber":"3",
    "isEnabled":"true"
    }'

    Response:
    
    {
    "data": {
        "userId": "6756f1b1-c6c2-49d4-af43-8f1ac229d236",
        "ticketId": "d02e8098-94a2-4e90-a2a4-161330b3456d",
        "isEnabled": true,
        "newSection": "B",
        "newSeatNumber": 3
    },
    "status": "OK"
    }

### Kick User From Train
   
    curl --location --request DELETE 'http://localhost:8080/api/v1/ticket/6756f1b1-c6c2-49d4-af43-8f1ac229d236'

    Response:

    {
    "data": true,
    "message":"Deleted Successfully",
    "status": "OK"
    }

#### To keep testing easy ,load the database with these data manually 

#### Data loaded manually for users table 

    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('5f47c687-d3f8-4a5e-9fc3-5a4ad8b6b7a1', 'John', 'Doe', 'john.doe@example.com');
    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('32db5931-775b-4d4b-8ba0-162da9340f82', 'Jane', 'Smith', 'jane.smith@example.com');
    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('926fbaf6-7e42-4c9b-9253-3a0b67d80557', 'Robert', 'Brown', 'robert.brown@example.com');
    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('a94e7f55-2094-4a6a-80ed-8b3c0d2f4342', 'Emily', 'Davis', 'emily.davis@example.com');
    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('2b33c90a-80d1-4bb0-8b07-cd6a5d2cdbd6', 'Michael', 'Johnson', 'michael.johnson@example.com');
    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('36f4f9b9-59ff-4688-b09e-cd7e992065ec', 'Laura', 'Wilson', 'laura.wilson@example.com');
    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('1b5f9db2-0407-4b85-85d3-8c05bfc034b7', 'David', 'Lee', 'david.lee@example.com');
    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('9fdf1d44-8f7b-4294-9d9a-97fa80d9fd39', 'Sarah', 'Martin', 'sarah.martin@example.com');
    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('b3a8f9c2-c869-4aef-9908-10976a56fbe1', 'James', 'Moore', 'james.moore@example.com');
    INSERT INTO users (id, first_name, last_name, email_id) VALUES ('cc4b1d6b-e3d7-46af-8d42-1d3a14567498', 'Olivia', 'Taylor', 'olivia.taylor@example.com');

#### Data loaded manually for seats table

    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (1, 'A', 1, false);
    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (2, 'A', 2, false);
    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (3, 'A', 3, false);
    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (4, 'B', 1, false);
    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (5, 'B', 2, false);
    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (6, 'B', 3, false);
    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (7, 'A', 1, false);
    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (8, 'A', 2, false);
    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (9, 'A', 3, false);
    INSERT INTO seats (id, seat_section, seat_number, is_occupied) VALUES (10, 'B', 1, false);

