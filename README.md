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
