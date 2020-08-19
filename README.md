# MeetingApp

Serivce is built on Docker, Spring-boot using Java 8 version and can be run on Docker container.

The Meeting-Service app, supports rest api like

1. OfficeRegister Service and the Meeting rooms( Provided the Sample request in resource folder)
    Service End point: /addInventory
    Sample request: Refere resource Folder json file name :/resource/json.request/OfficeAddInventory.json
    
2. EmployeesRegister Service  and build the empty Calendar Intially.(Provided the sample request in resource folder)
   Service End point: /registerEmployee
   Sample request: Refere resource Folder json file name :/resource/json.request/EmployeeRegister.json
   
3. Schedule Meeting service to schedule the meeting by blocking the Calendar checking the availability of Employee/User for the given employees.
   Service end point :/scheduleMeeting
   
   Sample request: Refere resource Folder json file name :/resource/json.request/ScheduleMeetingRequest.json
   
4. Fetch the all the Meeting details based on the Employee email Id
   Service end point: /searchMeetings
   Method : POST
   Sample Request:
     
   {"emailIds":["xyz@abc.com"]}
   
   
   In Scope:
   
   Maintaning all the inserted data in HashMaps and Injected the beans from SpringBootApplication class file.
   For every start of application the run API's sequentially 
   1. Emplyee Registration / Office Room Inventory Registration
   2. Schedule Meeting and Block the Room
   3. Fetch the Meeting for a given or list of EmailsIds
   
   
   PostMan Collection link:
   
   https://www.getpostman.com/collections/29161d2140e8ae1c4c69


