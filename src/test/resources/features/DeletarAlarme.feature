# language: pt

Funcionalidade: Deletar um Alarme
  Como usuário autenticado
  Quero registrar deletar um alarme
  Para o alarme seja deletado corretamente no sistema
  Contexto: Login bem-sucedido de usuário
    Dado que eu tenha os seguintes dados para o login
      | key       | value                |
      | email     | emerson@abcde.com    |
      | password  | Mortadela@4321       |
    Quando enviar a requisição para endpoint "auth/login" de login
    Então status code da resposta 200
    Dado que tenha realizado o login e recebido um token
    E eu tenha os seguintes dados da alarme
      | key     | value         |
      | type    | agglomeration |
      | status  | pending       |
    Quando enviar requisição para o endpoint "api/alarm" com o token
    Então o status code da resposta deve ser, 201
    E que o arquivo de contrato esperado "Cadastro bem-sucedido de alarme"
    Então a resposta da requisição deve estar em conformidade com o contrato

  Cenário: Deve ser possivel deletar um alarme
    Dado que eu recupere o ID do alarme criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "api/alarm" de deleção
    Então o status code da resposta deve ser, 204