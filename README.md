# teste-igor-creativedrivemvn # teste-igor

Projeto maven construído com o framework Spring Boot e Banco de Dados MongoDB

### Utilizando Docker para simplificar o desenvolvimento (Servidor)
A aplicação pode rodar em containers docker:
```
mvn clean package -DskipTests
docker-compose build --build-arg VERSION=$(date +%s)
docker-compose up -d
```

### Alimentação dos dados iniciais

Alguns dados iniciais foram adicionados para facilitar os teste, utilizando a classe:

```
src.main.java.com.creativedrive.testeigor.config.MongoConfig
```

A alimentação tem o objetivo de ter uma base de usuários, que serão necessários para obter o token jwt.

### Segurança da API
Para obter o token, deve-se chamar o método /oauth/token passando no body o username e password de um dos usuários, 
assim como o grant_type com o valor "password".
Nesta operação é utilizada uma Authorization "Basic Auth", com os seguintes username e password definidos em memória, na classe
src.main.java.com.creativedrive.testeigor.config.AuthorizationServerConfig:

```
Username: igor-app
Password: @321
```

A resposta dever ser conforme exemplo a seguir:

```
{
"access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NTg0NTgyNTUsInVzZXJfbmFtZSI6InVzZXJuYW1lMiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiIwMkNBWmJzMEZ3MzcwTm5PR1B5YzNXVm1vYmciLCJjbGllbnRfaWQiOiJpZ29yLWFwcCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.Z8gSSZn1mXdr4C-zyq1vAUFbgo3pihibtGVwxSI_xfA",
"token_type": "bearer",
"expires_in": 1799,
"scope": "read write",
"jti": "02CAZbs0Fw370NnOGPyc3WVmobg"
}
```

De posse do "access_token", os outros métodos da API podem ser utilizados, utilizando-o na Authorization do tipo "Bearer Token"

