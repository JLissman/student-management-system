

<B>GET ALL</B><BR>
GET REQUEST<BR>
http://127.0.0.1:8080/student-management-system/api/v1/students/all <br>
returns all students

<b>Get Student via ID</b><br>
GET request<br>
http://127.0.0.1:8080/student-management-system/api/v1/students/get/1 <br>
done via url parameter, last number is ID


<b>Create new student</b><br>
POST REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/students/add <br>

payload example:<br>
{<br>
"firstName":"Anders",<br>
"lastName":"Andersson",<br>
"email":"Anders@hotbrev.se"<br>
"phoneNumber":"optional"<br>
}

<b>Query Database</b><br>
GET REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/students/query?column=firstName&value=Bosse

queries database based on column & value (e.g can query for first name by adding firstName to column or lastName for lastname queries)

<b>Update student</b><br>
PUT REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/students/update <br>

payload example<br>
{<br>
"email": "Anders@Anderssyster.se",<br>
"firstName": "Anders",<br>
"id": 1,<br>
"lastName": "Andersdotter",<br>
"phoneNumber":"1234576"<br>
}

include ID to make sure you edit the right one.


<b>Remove Student</b><br>
DELETE REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/students/delete/1 <br>

done via url parameter, last number is ID

