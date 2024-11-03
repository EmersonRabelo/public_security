# language: pt
@regressivo
Funcionalidade: Criação de Emergência
  Como usuário autenticado
  Quero criar uma nova Emergência
  Para registrar um incidente
  Contexto: Login bem-sucedido de usuário
    Dado que eu tenha os seguintes dados para efetuar o login
      | key       | value                |
      | email     | emerson@abcde.com    |
      | password  | Mortadela@4321       |
    Quando enviar a requisição para endpoint "auth/login" de login de usuário
    Então status code da resposta deve ser 200
  Cenário: Criar uma nova emergência com sucesso
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
    E que o arquivo de contrato esperado para emergência "Cadastro bem-sucedido de emergência"
    Então resposta da requisição deve estar em conformidade com o contrato
