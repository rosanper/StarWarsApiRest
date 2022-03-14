# Atividades Let's Code

Atividade do curso Santander Coders | Web Full-Stack da Let's Code.

## Star Wars Resistence Social Network

Trabalho final do modulo 8 - Desenvolvimento web, realizado pela equipe:
1) [Rebeca Rodrigues](https://github.com/rodriguesrebeca);
2) [Rodrigo Santana](https://github.com/rosanper);
3) [Thaís Mazzo da Costa]();
4) [Welder Askowiks Fagundes Vanderlei](https://github.com/askowiks).

### Requisitos da atividade:

Desenvolver uma API REST, com as seguintes especificações:
- Adicionar rebeldes;
- Atualizar localização do rebelde;
- Reportar o rebelde como um traidor;
- Negociar itens;
- Gerar relatório com:
    -  Porcentagem de traidores.;
    -  Porcentagem de rebeldes;
    -  Quantidade média de cada tipo de recurso por rebelde;
    -  Pontos perdidos devido a traidores.

### Desenvolvimento e Organização do código:

O código é desenvolvido a partir do Spring boot, utilizando o banco de dados H2 e a dependência JPA para manipulação do banco de dados. 
Outras dependências utilizadas no projeto são:

- Swagger: Para a documentação da API;
- Lombok: Remover a verbosidade do código;
- Bean Validation: Auxiliar na validação dos dados.
   
O código está organizado nas Camadas: Configuration; Controllers; DTOs; Enums; Excceptions; Models; Repositories e services. Já na pasta test podem ser encontrados os testes realizados no código.

### Endpoints:

Os Endpoints da REST API são:
- /rebels:  Obter a lista de rebeldes e criar um novo rebelde;
- /rebels/{id}: Obter os dados de um rebelde;
- /rebels/{id}/accusation: Acusa o rebelde selecionado de traição;
- /rebels/{id}/updateLocation: Atualizar a localização do rebelde selecionado;
- /rebels/tradeEquipment: Trocar equipamentos;
- /rebels/report: Gerar relatório.
    