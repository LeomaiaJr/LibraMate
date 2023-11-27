# LibraMate

## Visão Geral

"LibraMate" é um sistema de gerenciamento de biblioteca desenvolvido em Java, projetado para facilitar o gerenciamento de livros, usuários e empréstimos em uma biblioteca. Este projeto incorpora princípios de programação orientada a objetos e práticas recomendadas de desenvolvimento de software.

## Funcionalidades

- **Gestão de Livros**: Adicionar, atualizar, remover e buscar livros.
- **Gestão de Usuários**: Registrar e gerenciar informações dos usuários.
- **Gestão de Empréstimos**: Controlar o empréstimo de livros aos usuários.
- **Persistência de Dados**: Salvar e ler dados de livros e usuários em arquivos.
- **Tratamento de Exceções**: Gerenciamento robusto de exceções para garantir a estabilidade do sistema.

## Persistência de Dados

### Arquivos de Dados

O sistema "LibraMate" utiliza dois arquivos principais para a persistência de dados, que estão localizados na raiz do diretório do projeto:

- `livro.txt`: Este arquivo é usado para armazenar informações sobre os livros da biblioteca. Cada livro é registrado com detalhes como título, autor, gênero literário, quantidade de páginas, editora e ano de lançamento.

- `pessoa.txt`: Este arquivo é utilizado para guardar informações dos usuários da biblioteca. Cada registro de usuário contém dados como nome, CPF e outros detalhes pertinentes.