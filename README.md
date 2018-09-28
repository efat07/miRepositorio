http://localhost:8090/login

Request:
{
"usernameOrEmail":"customerUpdate71@gmail.com",
"password":"password745"
}


Response Ok
{
   "id": "2",
   "value": "OK",
   "user": "customerUpdate71@gmail.com",
   "message": "Success login"
}
y en el Header viene:
Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lclVwZGF0ZTcxQGdtYWlsLmNvbSIsImlhdCI6MTUzODEwNTY1MSwiZXhwIjoxNTM4MTQ4ODUxfQ.0bzxyz449t0C-S8FnKbRFS9Ys_wHWdqhCVGaP3sDjkYv_RJbUcs9Q1JiQIbZPxaCQOuBHNCM2lDdNauzDaEf1w


Response cuando no se autentica:
{
   "timestamp": "2018-09-28T12:08:55.233+0000",
   "status": 401,
   "error": "Unauthorized",
   "message": "Sorry, You're not authorized to access this resource.",
   "path": "/login"
}


*************************************************************************

http://localhost:8092/registerCustomer

Request:
{
	"idCategory": 3,
	"email": "customerRegister71@gmail.com",
	"username": "customerRegister71@gmail.com",
	"password": "password745"
}

Response exitoso
{
   "id": "23",
   "success": true,
   "message": "User registered successfully"
}

Response cuando la URL no existe
{
   "timestamp": "2018-09-28T03:31:50.519+0000",
   "status": 403,
   "error": "Forbidden",
   "message": "Access Denied",
   "path": "/registerCustomerd"
}



*************************************************************************

http://localhost:8092/updateCustomer

Request:
{
	"idCustomer": 2,
	"idCategory": 3,
	"email": "customerRegister71@gmail.com",
	"username": "customerRegister71@gmail.com",
	"password": "password745"
}

Header:
Key                                value
Authorization        

Response exitoso
{
   "id": "2",
   "success": true,
   "message": "User updated successfully"
}

Resonse con error de validaciones
{
   "id": null,
   "success": false,
   "message": "Username is already taken!"
}

Response cuando la url no existe
{
   "timestamp": "2018-09-28T03:30:45.606+0000",
   "status": 403,
   "error": "Forbidden",
   "message": "Access Denied",
   "path": "/registerCustomerw"
}
