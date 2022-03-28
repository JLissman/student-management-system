

GET ALL
GET REQUEST
http://127.0.0.1:8080/student-management-system/api/v1/students/all
returns all students

Get Student via ID
GET request
http://127.0.0.1:8080/student-management-system/api/v1/students/get/1
done via url parameter, last number is ID


Create new student
POST REQUEST
http://127.0.0.1:8080/student-management-system/api/v1/students/add

payload example:
{
"firstName":"Anders",
"lastName":"Andersson",
"email":"Anders@hotbrev.se"
"phoneNumber":"optional"
}

Query Database
GET REQUEST
http://127.0.0.1:8080/student-management-system/api/v1/students/query?column=firstName&value=Bosse

queries database based on column & value (e.g can query for first name by adding firstName to column or lastName for lastname queries)

Update student
PUT REQUEST
http://127.0.0.1:8080/student-management-system/api/v1/students/update

payload example
{
"email": "Anders@Anderssyster.se",
"firstName": "Anders",
"id": 1,
"lastName": "Andersdotter",
"phoneNumber":"1234576"
}

include ID to make sure you edit the right one.


Remove Student
DELETE REQUEST
http://127.0.0.1:8080/student-management-system/api/v1/students/delete/1

done via url parameter, last number is ID

