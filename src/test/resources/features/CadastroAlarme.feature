# language: pt

Funcionalidade: Criação de Alarme
  Como usuário autenticado
  Quero registrar um novo Alarme
  Para registrar um alarme
  Contexto: Login bem-sucedido de usuário
    Dado que eu tenha os seguintes dados para o login
      | key       | value                |
      | email     | emerson@abcde.com    |
      | password  | Mortadela@4321       |
    Quando enviar a requisição para endpoint "auth/login" de login
    Então status code da resposta 200

  Cenário: Criar um novo alarme com sucesso
    Dado que tenha realizado o login e recebido um token
    E eu tenha os seguintes dados da alarme
      | key     | value         |
      | type    | agglomeration |
      | status  | pending       |
    Quando enviar requisição para o endpoint "api/alarm" com o token
    Então o status code da resposta deve ser, 201
    E que o arquivo de contrato esperado "Cadastro bem-sucedido de alarme"
    Então a resposta da requisição deve estar em conformidade com o contrato

  Cenário: Criar um novo alarm sem sucesso ao não passar o token de acesso
    Dado que não tenha realizado o login e recebido um token
    E eu tenha os seguintes dados da alarme
      | key     | value         |
      | type    | agglomeration |
      | status  | pending       |
    Quando enviar requisição para o endpoint "api/alarm" com o token
    Então o status code da resposta deve ser, 403