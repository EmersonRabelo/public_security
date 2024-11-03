# language: pt

Funcionalidade: Deletar uma Emergência
  Como usuário autenticado
  Quero deletar uma Emergência
  Para a emergencia seja deletada corretamente do sistema
  Contexto: Login bem-sucedido de usuário
    Dado que eu tenha os seguintes dados para efetuar o login
      | key       | value                |
      | email     | emerson@abcde.com    |
      | password  | Mortadela@4321       |
    Quando enviar a requisição para endpoint "auth/login" de login de usuário
    Então status code da resposta deve ser 200
    Dado que eu tenha realizado o login e recebido um token
    E eu tenha os seguintes dados da emergência
      | key            | value                |
      | requesterEmail | emerson@abcde.com    |
      | address        | Rua das Flores, 123  |
      | type           | Incêndio             |
      | title          | Incêndio na sala     |
      | description    | Fogo em um armário   |
      | status         | Em andamento         |
    Quando enviar a requisição para o endpoint "api/emergency" com o token
    Então status code da resposta deve ser o 201

    Cenário: Deve ser possivel deletar uma emergencia
      Dado que eu recupere o ID do emergência criado no contexto
      Quando eu enviar a requisição com o Id da emergência para o endpoint "api/emergency" de deleção
      Então status code da resposta deve ser o 204