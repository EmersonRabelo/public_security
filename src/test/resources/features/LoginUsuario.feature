# language: pt

  Funcionalidade: Login de um usuário
    Como usuário cadastrado
    Quero conseguir realizar login na aplicação
    Para que o usuário seja logado corretamente na aplicação
    Cenário: Login bem-sucedido de usuário
    Dado que eu tenha os seguintes dados de login
      | key       | value                |
      | email     | emerson@abcde.com    |
      | password  | Mortadela@4321       |
    Quando enviar a requisição para o endpoint "auth/login" de login de usuário
    Então o status code da resposta deve ser o 200

    Cenário: Login de um usuário sem sucesso ao passar um email ou senha inválido
    Dado que eu tenha os seguintes dados de login
      | key       | value                |
      | email     | emerson@abcde.com    |
      | password  | Mortadela@43215      |
    Quando enviar a requisição para o endpoint "auth/login" de login de usuário
    Então o status code da resposta deve ser o 403
