## BrasileiraoApp

Um aplicativo para você ver os jogos da rodada do brasileirão e os lances de cada jogo!

|Tela de Games|Tela de Detalhes|
|---|---|
|![image](https://user-images.githubusercontent.com/71989413/120498044-4def3d80-c395-11eb-8601-2df5722a7387.png)|![alt text](https://i.ibb.co/h7zf4pS/Screenshot-at-Oct-21-13-31-33.png)|
---
### Arquitetura
Esse projeto se basea nos conceitos de arqutiterura, do [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) e se utilizando da estrutura do **MVVM**. Abaixo segue o gráfico de dependencia do aplicativo.

![alt text](https://i.ibb.co/vvYhyms/Screenshot-at-Oct-21-14-28-10.png)

##### Camada de App (Android):
- Módulo que gera o arquivo .apk do aplicativo
- Módulo conhece todos os módulos para prover as implementações de interfaces entre módulos
- Possibilita criar apks de aplicativo light, aplicativos com features dinamicas, ou demos de uma feature separada
- Dependente de todos as camadas

##### Camada de Feature (Android):
- Onde ficam as funcionalidades do aplicativo
- Camada mais instável do aplicativo e mais dependente dos outros módulos
- Contém o módulo de Implementação e os módulos públicos:
  - Impl : É onde fica de fato a implementação do código
  - Public: Fica apenas interfaces e objetos de transferência de dados que podem ser utilizados por outros módulos de **feature**
  - Dependente da camada de Business
- Deve expor só o gráfico de dependencia pertencente a feature

##### Camada de Usecase (Kotlin)
- Camada onde ficam as regras de negócios separadas em casos de usos
- Camada com módulos de preferencia apenas 
- Contém o módulo de Implementação e os módulos públicos:
  - Impl : É onde fica de fato a implementação do código
  - Public: Fica apenas interfaces e objetos de transferência de dados que podem ser utilizados por outros módulos de **UseCase ou Feature**
- Dependente da camada de Data
 
##### Camada de Repositorio (Android/Kotlin)
- Camada onde ficam as fontes de dados
- Contém módulos de repositório para pegar informações das API's
- Contém módulo de **database** para guardar os dados offline
- Camada com módulos de preferencia Kotlin, porém por causa do **room** é necessário os módulos que o utilizam ser android, a solução encontrada é inversão de dependencia e deixando os módulos public como kotlin.
- Contém o módulo de Implementação e os módulos públicos:
  - Impl : É onde fica de fato a implementação do código
  - Public: Fica apenas interfaces e objetos de transferência de dados que podem ser utilizados por outros módulos de **UseCase ou Repository**
  
##### Camada de Core (Android/Kotlin)
- Camada onde ficam as classes utilizadas por todo o sistema, por exemplo classes bases.
- Camada mais independente do aplicativo
- Dividido nos módulos de Android e Kotlin

###### Foi escolhido utilizar a arquitetura Clean pelos seguintes motivos:
- Divisão de responsabilidades
- Facilidade de testar o código
- Projeto mais escalável
- Projeto com partes desacopladas
___
### Bibliotecas utilizadas
- [Koin](https://insert-koin.io/) - Biblioteca de injeção de dependencia, foi escolhida pela simplicidade da implementação e baixa curva de aprendizado para profissionais com menos experiência.
- [Room](https://developer.android.com/topic/libraries/architecture/room) - Biblioteca de persistencia local de dados
- [Glide](https://github.com/bumptech/glide) - Biblioteca de Download de imagens
- [Coroutines](https://developer.android.com/kotlin/coroutines) - Biblioteca para ajudar a gerenciar tarefas assincronas, escolhida por suspender, e não bloquear, as threads utilizadas
- [Retrofit](https://square.github.io/retrofit/) - Biblioteca utilizada para fazer chamadas Rest
- [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Biblioteca Utilizada para faciltar a navegação entre telas 
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=pt-br) - Biblioteca Utilizada para faciltar a gestão de lifecycle no viewModel
---
#### Informações relevantes
 - Todos os dados utilizados no aplicativo são buscados pela API feita que retorna apenas dados fictícios.
 - Para rodar os testes unitários é necessário entrar na pasta do projeto e rodar o comando
 ```./gradlew test```
 - Para tirar o relatório de lint do aplicativo é necessário entrar na pasta do projeto e rodar o comando
 ```./gradle check```

### TO-DOs para futuro

- Colocar CI do github actions
- Colocar testes instrumentados para garantir integridade das telas 
- Fazer filtros de time e data para pesquisa de jogos
- Inserir mais informações sobre o jogo, como Escalações

----
This project is licensed under the MIT License - see the [LICENSE](https://github.com/hubotio/hubot/blob/master/LICENSE.md) file for details
