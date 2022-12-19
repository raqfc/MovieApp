Projeto Android utilizando Jetpack Compose.
Arquitetura utilizada: MVVM (alguns princípios CLEAN)

Neste projeto utilizei as seguintes (principais) bibliotecas:
- Hilt -> Injeção de dependências
- Retrofit -> Requisições HTTP (utilizada para se comunicar com a API do IMDb)
- Room -> Persistência local de dados (utilizada para armazenar os títulos favoritados)

Desta forma este projeto abrange os principais pilares na construção de uma aplicação: consumo de dados/integração com fonte interna, armazenamento interno de dados e (apesar de ser de maneira mais básica) uma arquitetura de desenvolvimento.

Porém existem alguns pontos (importantes) a serem melhorados posteriormente, como, principamente, a arquitetura, que deixa a desejar. Priorizei primeiramente finalizar algumas funcionalidades, deixando em segundo plano uma organização mais rigorosa do código. Um exemplo claro disso é o código repetido entre as 2 ViewModels utilizadas, isso não aconteceria caso tivesse implementado UseCases (como era o plano original). A lógica em geral de navegação da tela de listagem pode ser refatorado para ser mais direto, apesar de funcionar da maneira que foi feita.

Uma outra dificuldade foi pensar na UI, que é bem basica, e apesar de funcional deixa muito a desejar em questão de estética. O fluxo de desenvolvimento com um designer acompanhando é bem diferente. Em geral consigo projetar telas, mas com o tempo mais curto acabou sendo uma maior deficiência.

A escolha de desenvolver tudo utilizando Jetpack Compose foi mais como um desafio para mim mesma, já que possui (muito) mais experiência com xml. Assim tive a oportunidade de entender melhor como funciona a ferramenta, melhorar alguns componentes que já tinha feito e entender que outros que fiz ainda estão bem deficientes quando colocados em um outro contexto (ou seja, não são componentes verdadeiramente genéricos).

Quis elencar todos os maiores defeitos do projeto que acabei de fazer tanto para explicitá-los (pois os reconheço) como também para já começar a pensar em melhores maneiras de contornar todos esses problemas.

Mas de qualquer forma, segue uma breve explicação das funcionalidades do app:

Aplicativo de listagem de filmes consumindo a API do IMDb. Possui 3 áreas principais: Filmes, Séries e Favoritos (que lista tanto filmes como séries).
Nas Tabs de Filmes e séries é possível alternar entre 2 modos de busca: Top250 e Busca Avançada, na ultima aparece um input de text que permite o usuário fazer a busca que deseja.

Nas listagens é possível favoritar/desfavoritar um filme/série ou clicar no poster em si, e assim ter uma visualização expandida do título.

Tela inicial no modo Top 250 e no modo de busca:

<img src="readme_content/top_250_mode.png" alt="" height="500" /> <img src="readme_content/search_mode.png" alt="" height="500" />

Tela de favoritos:

<img src="readme_content/favorites.png" alt="" height="500" /> <img src="readme_content/favorites_empty.png" alt="" height="500" />

Para rodar o projeto é preciso criar arquivo um arquivo "apiKey.properties":
```
IMDb_API_KEY="{imdb_api_key}"
```
