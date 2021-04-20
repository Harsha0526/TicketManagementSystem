
# Ticket management System

I have have considered Ticket managment system as not a standalone system, by that I mean different products like growfin, fyle can integrate with it as a Third Party Application.
For our example we will consider Growfin

1] The org which wants to use Ticketing system will first have to singup for that we hwill have to use use 

````
EndPoint --> "/auth/org/signup"  "POST"
Payload --> {
    "name": "Growfin",
    "domain": "growfin.com"
}
authorization --> Not Required 
````

2] Once orgs sigup using the above endpoint orgs will get a "Secret Key" 

```
Example --> 
Secret: "JuVGkloGLLMX5WBkZFoE0qKQcwLPfqLwsRGtkLtWu3n8Zxh690"
```

Using this scret now orgs can create "Administrators" in the Tickting managment System.
For creating Administrators we can use following end point using Secret Key.

```
EndPoint --> "/auth/admin/signup" "POST"
Payload --> 
{
    "firstName": "Kartikey",
    "lastName": "Rajvaidya",
    "email": "kartikey.raj@fyle.in",
    "password": "123122123",
    "secret": "JuVGkloGLLMX5WBkZFoE0qKQcwLPfqLwsRGtkLtWu3n8Zxh690"
}
authorization --> Screct Key required
```


3] Once admin singup using the above Endpoint, they can signin using the below Endpoint --> 
```
EndPoint --> "/auth/admin/signin" "POST"
Payload --> 
{
    "email": "kartikey.raj@fyle.in",
    "password": "123122123"
}

authorization --> Not Required 
```
User will receive a JWT Bearer token after signin 

```
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXJ0aWtleS5yYWpAZnlsZS5pbiIsImV4cCI6MTYxODkwNzQ3OSwiaWF0IjoxNjE4ODcxNDc5fQ.JyC0hOwAATnKm4dqKB8-m-19XV7a6lB4VMGUj9T8SSM"
}
```



4] Tickets In Tickting managment System will be created by the users through the Ticket managment system's Client In our Case Growfin, so user detail and authorization of the user will be taken care by Growfin, tickets will be generated from Growfin application using the "secret" using following EndPoint -->

```
EndPoint --> "/tickets" "POST"
{
    "title": "title",
    "description": "This is  341234",
    "addedBy": "kartikeyrajvaidya@gmail.com",
    "secret": "JuVGkloGLLMX5WBkZFoE0qKQcwLPfqLwsRGtkLtWu3n8Zxh690"
}
authorization --> Screct Key required
````

Using the above EndPoint ticket can be created and the ticket will be assigned to the respective org Available Admin.


5] Now Admins Can take actions On the Ticket Using the Following End Points.

```
EndPoint --> "/tickets/<ticket_id>/change_status"  POST

Payload --> 
{
    "status": "CLOSED"
}
Authorization --> JWT token required
````

Purpose --> For changing the Status of the Ticket 
Remark --> For every Ticket which is moveed to "RESPONDED" we create a Job which will run after 30 days and will close the ticket.



6] Admin Can use the Following end point to Respond to a ticket 

```
EndPoint --> "/tickets/<ticket_id>/respond" "POST"
Payload --> 
{
    "message": "Testing ticket Response"
}
Authorization --> JWT token required
````
Remark -->  Mail will be send to the Ticket creator once admin respond to the ticket.



7] For Getting a Ticket Related details Admin can use following Endpoint

```
EndPoint --> "/tickets/<ticket_id>" "GET"

Authorization --> JWT token required

```


8] For Getting a Tickets filleterd by added by, status and admin Admin can use following API 

```
EndPoint --> "http://localhost:8080/tickets?adminEmail=kartikey@gmail.com&status=OPEN&customerEmail=kartikey@gmail.com"

Authorization --> JWT token required
```



9] For Updating a Ticket Admin can use following End Point 

```
EndPoint --> "/tickets/<ticket_id>" "POST"

Payload --> 
{
    "description": "Testing", 
    "title": "Testing title",
    "adminId": "026d0473-a02f-4ec1-9bd0-237437140f6d"
    "status": "CLOSED",
    "orgId": "65dc5ea3-4490-4300-984d-46009df43ab6"
}

Authorization --> JWT token required
````

Remark --> All the Actions like Scheduling Job setting Closed_at will happen normally by this API call aswell.


```
I have Used JobRunR for scheduling jobs you ca always got to
The smallest heading URL --> `localhost:8000` in the browser for checking the dashboard.

```


Thanks
