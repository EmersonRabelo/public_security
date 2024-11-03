# language: pt
@regressivo
Funcionalidade: Cadastrar um usuário
  Como um novo usuário
  Quero conseguir me registrar na aplicação
  Para que o usuário seja registrado corretamente na aplicação

  Cenário: Cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados de entrega:
      | key       | value                |
      | name      | Emerson Teles Rabelo |
      | email     | teste2@teste.com     |
      | password  | Mortadela@4321       |
      | role      | ADMIN                |
    Quando enviar a requisição para o endpoint "user/register" de cadastro de usuários
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido de usuario"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado

  Cenário: Cadastro de usuário sem sucesso ao passar a key role inválida
    Dado que eu tenha os seguintes dados de entrega:
      | key       | value                |
      | name      | Emerson Teles Rabelo |
      | email     | teste2@teste.com     |
      | password  | Mortadela@3333       |
      | role      | SYS                  |
    Quando enviar a requisição para o endpoint "user/register" de cadastro de usuários
    Então o status code da resposta deve ser 403

  Cenário: Cadastro de usuário sem sucesso ao passar um usuário já registrado
    Dado que eu tenha os seguintes dados de entrega:
      | key       | value                |
      | name      | Emerson Teles Rabelo |
      | email     | teste@teste.com      |
      | password  | Mortadela@4321       |
      | role      | ADMIN                |
    Quando enviar a requisição para o endpoint "user/register" de cadastro de usuários
    Então o status code da resposta deve ser 409