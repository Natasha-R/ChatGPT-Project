# Praktikum Softwaretechnik 2 (ST2) im SoSe 2021

Sofortiges Feedback zu Ihres Lösung finden Sie wie immer auf Ihrer individuellen Testseite:
[http://students.pages.st.archi-lab.io/st2/ss21/m4/tests/st2-praktikum_tests_group_e0708822-91b6-4498-b679-9e8f6b1089bd]([http://students.pages.st.archi-lab.io/st2/ss21/m4/tests/st2-praktikum_tests_group_e0708822-91b6-4498-b679-9e8f6b1089bd])

Da wir aus der Aufgabenbeschreibung direkt Coding-Aufgaben ableiten, ist die komplette Beschreibung in Englisch
gehalten. 

## Milestone M4 - Implementing a REST-API

Last time you defined a REST-API for your project. Now it is time to implement it.

### Exercise 1 - Implementing a REST-controller

Implement a REST-controller for your tidy-up robot aggregate.
You have to fulfill the following eight requirements:

|Requirement # | URI | VERB |
|---|---|---|
| 1. Get all tidy-up robots                                                                      | /tidyUpRobots | GET |
| 2. Create a new tidy-up robot                                                                  | /tidyUpRobots | POST |
| 3. Get a specific tidy-up robot by ID                                                          | /tidyUpRobots/{tidyUpRobot-id} | GET |
| 4. Delete a specific tidy-up robot                                                             | /tidyUpRobots/{tidyUpRobot-id} | DELETE |
| 5. Change the name of a specific tidy-up robot                                                 | /tidyUpRobots/{tidyUpRobot-id} | PATCH |
| 6. Give a specific tidy-up robot an order                                         | /tidyUpRobots/{tidyUpRobot-id}/orders | POST |
| 7. List all the orders a specific tidy-up robot has received so far                        | /tidyUpRobots/{tidyUpRobot-id}/orders | GET |
| 8. Delete the order history of a specific tidy-up robot                                    | /tidyUpRobots/{tidyUpRobot-id}/orders | DELETE | 

Use the provided class Tidy-up robotController to implement your REST-API. 

**Important:** 
1. Your tidy-up robot class needs to have an attribute called `name`.
1. The `TidyUpRobotService` from M3 has been split into three service classes, each dedicated to one aggregate. 
    Please migrate your M3 service class implementation to these three locations, accordingly.
    * `TidyUpRobotService`
    * `RoomService`
    * `TransportCategoryService`
1. Please do not change the given package structure in your repo. (You can add own packages, but don't move or
    rename those that came with the repo.)

