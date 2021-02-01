# brasilprev-customer-apv1


Teste API.
No endpoint 
local : localhost/auth
EC2 Amazon : http://172.107.45.210:8080
  Ação:POST
  {
    "email" : "user@brasilprev.com.br",
    "passwd" : "123456"
  }

Obter o token e usar nos demais endpoint no header
Exemplo:
Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgQnJhc2lsUHJldiBSZWdpc3RlciBDdXN0b21lcnMiLCJzdWIiOiIxIiwia
