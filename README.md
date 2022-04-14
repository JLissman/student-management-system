<h1>Labb 2</h1>



<h3>Postman premade requests collection</h3>
https://www.getpostman.com/collections/4b90f0e80d555e89cd08 (Student)
https://www.getpostman.com/collections/db5a6cc18819d1dbd771 (Teachers & Subjects)<br>
(osäker på om de funkar med insomnia, använde själv postman)


<h2>Student</h2>




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
http://127.0.0.1:8080/student-management-system/api/v1/students/query?name=Andersson

queries database based on lastname

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



<b>Add course to student</b><br>
PUT request<br>
http://127.0.0.1:8080/student-management-system/api/v1/students/addSubject?studentid=1&subjectid=1 <br>

done via query parameters


<b>generate students</b><br>
GET request<br>
http://127.0.0.1:8080/student-management-system/api/v1/students/generate/10

generates a number of students via path param  (in this case 10)


-----------------------------------------------

<h2>Teachers</h2>
<B>GET ALL</B><BR>
GET REQUEST<BR>
http://127.0.0.1:8080/student-management-system/api/v1/teachers/all <br>
returns all teachers

<b>Get teacher via ID</b><br>
GET request<br>
http://127.0.0.1:8080/student-management-system/api/v1/teachers/get/1 <br>
done via url parameter, last number is ID


<b>Create new teachers</b><br>
POST REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/teachers/add <br>

payload example:<br>
{<br>
"firstName":"Göran",<br>
"lastName":"The Man",<br>
"email":"Anders@Andersbror.se"<br>
"phoneNumber":"optional"<br>
}


<b>Update teacher</b><br>
PUT REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/teachers/update <br>

payload example<br>
{<br>
"email": "Göran@Anderssyster.se",<br>
"firstName": "Göran",<br>
"id": 1,<br>
"lastName": "The WoMan",<br>
"phoneNumber":"1234576"<br>
}

include ID to make sure you edit the right one.


<b>Remove Teacher</b><br>
DELETE REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/teachers/delete/1 <br>

done via url parameter, last number is ID





<b>Add course to teacher</b><br>
PUT request<br>
http://127.0.0.1:8080/student-management-system/api/v1/teachers/addSubject?studentid=1&subjectid=1 <br>

done via query parameters


<b>generate teachers</b><br>
GET request<br>
http://127.0.0.1:8080/student-management-system/api/v1/teachers/generate/10

generates a number of teachers via path param  (in this case 10)


-----------------------------------------------

<h2>Subjects/courses</h2>
<B>GET ALL</B><BR>
GET REQUEST<BR>
http://127.0.0.1:8080/student-management-system/api/v1/subjects/all <br>
returns all Subjects

<b>Get subject via ID</b><br>
GET request<br>
http://127.0.0.1:8080/student-management-system/api/v1/subjects/get/1 <br>
done via url parameter, last number is ID


<b>Create new subject</b><br>
POST REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/subjects/add <br>

payload example:<br>
{<br>
"subjectName":"MAFF"
}


<b>Update subject</b><br>
PUT REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/subjects/update <br>

payload example<br>
{<br>
"subjectName":"MATH",<br>
"id": 1<br>
}

include ID to make sure you edit the right one.


<b>Remove Teacher</b><br>
DELETE REQUEST<br>
http://127.0.0.1:8080/student-management-system/api/v1/subjects/delete/1 <br>

done via url parameter, last number is ID





<b>generate subjects</b><br>
GET request<br>
http://127.0.0.1:8080/student-management-system/api/v1/subjects/generate/10

generates a number of subjects via path param  (in this case 10)