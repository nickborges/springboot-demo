# springboot-demo

| Versões		      | Java		|
| ------------------- | ----------- |
| Spring Boot 2	  	  | Java 8 e 9	|
| Spring Boot 2.0.1	  | Java 10		|
| Spring Boot 2.1.0	  | Java 11		|
| Spring Boot 2.2	  | Java 12		|


### CRIAR O PROJETO:
- Através do Spring Initialazer https://start.spring.io;

### CONFIGURAÇÕES:
- Por padrão, converte os dados no formato JSON, utilizando a biblioteca Jackson;
- Dependência spring-boot-starter-web;
- Dependência spring-boot-devtools, hot deploy;
- Dependência spring-boot-starter-data-jpa, Spring Data;
- Configurar o Lombok;
- Banco de dados H2, configurar o console do H2 com propriedades no arquivo src/main/resources/application.yml;

### SWAGGER: 
- colocar a dependência: 
	* implementation 'io.springfox:springfox-swagger2:2.9.2'
	* implementation 'io.springfox:springfox-swagger-ui:2.9.2'
- criar a classe:
    * SwaggerConfigurations
- exemplo de chamada:
    * http://localhost:8080/swagger-ui.html

### CONTROLLER’S:
- Anotações @Controller ou @RestController e @RequestMapping
- Para não retornar a requisição como uma página JSP, ou Thymeleaf, devemos utilizar a anotação @ResponseBody caso utilizado @Controller;
- Para não repetir a anotação @ResponseBody em todos os métodos do controller, devemos utilizar a anotação @RestController;
- Para evitar repetir a URL em todos os métodos, devemos utilizar a anotação @RequestMapping em cima da classe controller;

### VERBOS HTTP:
- GET @GetMapping; (recupera a representação de um recurso)
	* status 200: sucesso
	* status 301: Moved Permanently
	* status 401: não autorizado
    * status 403: Forbidden
	* status 404: caso não ache o regitro para deletar

- POST @PostMapping; (cria um novo recurso)
	* @RequestBody, para informar no parâmetro para o spring que é para pegar o conteúdo do corpo do método.
	* Boa prática para métodos que cadastram informações é devolver o código HTTP 201, ao invés do código 200;
	* Para montar uma resposta a ser devolvida ao cliente da API, devemos utilizar a classe ResponseEntity do Spring;
	* Receber UriComponentesBuilder no parâmetro para devolver a URI com o novo recurso criado.
	* status 201: created, devolver o recurso criado no response o no cabeçalho a URI do novo recurso
    * status 202: accepted, intenção de criar o novo recurso mas não criou ainda.
    * status 400: badrequest para validação de campos obrigatórios no body
    * status 401: não autorizado
    * status 403: Forbidden
    

- PUT @PutMapping;
	* @Transactional, colocar no método do controller, é usado para o caso de apenas consultar o registro pelo {id} alterar os dados e pronto(não precisa usar o método save do jpa).
	* status 200: sucesso e retorna uma mensagem.
	* status 204: sucesso e retorna o corpo vazio
	* status 400: badrequest para validação de campos obrigatórios no body
	* status 401: não autorizado
    * status 403: Forbidden
	* status 404: caso não ache o regitro para alterar

- DELETE @DeleteMapping; (excluí um recurso).
	* @Transactional, colocar no método do controller, é usado para o caso de apenas consultar o registro pelo {id} alterar os dados e pronto(não precisa usar o método save do jpa).
	* status 200: sucesso e retorna uma mensagem.
	* status 202: sucesso e vai deletar depois.
	* status 204: sucesso e retorna o corpo vazio
	* status 401: não autorizado
	* status 403: Forbidden
	* status 404: caso não ache o regitro para deletar

- HEAD (versão mais leve do GET)
	* status 200: sucesso 

- PATCH 
	* status 200:
	* status 204:

- LINK and UNLINK
	* colocar aqui a descrição
	
- OPTIONS (verbo primitivo de HTTP)
	* status 200: sucesso

### OBSERVAÇÕES:
- Para receber parâmetros dinâmicos no path da URL, devemos utilizar a anotação @PathVariable;
- Utilizar a classe ResponseEntity para montar a resposta de not found;
- Controle transacional automático, devemos utilizar a anotação @Transactional nos métodos do controller;

### VALIDATIONS:
- Para fazer validações das informações enviadas pelos clientes da API, podemos utilizar a especificação Bean Validation, com as anotações @NotNull, @NotEmpty, @Size, dentre outras;
- Para o Spring disparar as validações do Bean Validation e devolver um erro 400, caso alguma informação enviada pelo cliente esteja inválida, devemos utilizar a anotação @Valid;

### EXCEPTIONS:
- Para interceptar as exceptions que forem lançadas nos métodos das classes controller, devemos criar uma classe anotada com @RestControllerAdvice;
- Para tratar os erros de validação do Bean Validation e personalizar o JSON, que será devolvido ao cliente da API, com as mensagens de erro, devemos criar um método na classe @RestControllerAdvice e anotá-lo com @ExceptionHandler e @ResponseStatus

### REPOSITORY:
- Para mapear as classes de domínio da aplicação como entidade JPA, devemos utilizar as anotações @Entity, @Id, @GeneratedValue, @ManyToOne, @OneToMany e @Enumerated;
- O método getOne lança uma exception quando o id passado como parâmetro não existir no banco de dados;
- O método findById retorna um objeto Optional<>, que pode ou não conter um objeto.

### PAGINAÇÃO:
- habilitar a paginação na classe application:
    * @EnableSpringDataWebSupport
- incluir no parâmetro método do controller:
    * @PageableDefault(sort = "nomedocampo", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao
    * @PageableDefault, não é obrigatório é usado apenas se quiser ter valores default
- exemplo de chamada:
    * http://localhost:8080/user/all?page=0&size=10&sort=name,asc
    * localhost:8080/user/all?page=0&size=10&sort=name,asc&sort=id,asc

### CACHE:
- incluir dependência:
    * implementation 'org.springframework.boot:spring-boot-starter-cache'
- Criar a classe: 
    * CacheConfiguration
    * @EnableCaching e @Configuration
- habilitar o cache na classe application:
    * @EnableCaching
- incluir no método do controller: 
    * @Cacheable(value = "nomeDoAtributoDeCache")
- incluir no método do controller(alteração, para limpar o cache): 
    * @CacheEvict(value = "nomeDoAtributoDeCache", allEntries = true)

### MONITORAMENTO ACTUATOR:
- http://localhost:8080/actuator
    * Actuator, só devolve json mas pode ser usado o Spring Bood Admin (uma aplicação web criada por uma empresa alemã para mostrar graficamente)
    * Incluir as dependências: 
        * implementation 'org.springframework.boot:spring-boot-starter-actuator'
        * implementation 'de.codecentric:spring-boot-admin-starter-client:2.1.4'
    * application.propertires
        * management.endpoint.health.show-details=always
        * management.endpoints.web.exposure.include=*
        * info.app.name=@project.name@
        * info.app.description=@project.description@
        * info.app.version=@project.version@
        * info.app.encoding=@project.build.sourceEncoding@
        * info.app.java.version=@java.version@	    
        * spring.boot.admin.client.url=http://localhost:8081


### AUTENTICAÇÃO:
	
- Habilitar o spring security com a dependência(JJWT) 
- Criar a classe de configuração, SecurityConfigurations:
- Criar a classe de serviço, AuthenticationTokenFilter:
    * responsável por recuperar o token do header da requisição
    
- Exemplo de chamada:
    * primeiro deve chamar o autorizador jwt para recuperar o token:
        curl -X POST \
          http://localhost:8083/auth \
          -d '{
        	"email": "user@gmail.com",
        	"password": "abcd"
        }'
    * depois a chamada para api passando o token recuperado:
        curl -X GET \
          'http://localhost:8082/user/all?page=0&size=5&sort=name,asc,id,asc' \
          -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBdXRlbnRpY2Fkb3IgRGVtbyIsInN1YiI6IjEiLCJpYXQiOjE1NzM1OTQwNjUsImV4cCI6MTU3MzU5NDEyNX0.1h0m9qCW0KmGgwwI1qRZJ5YXzXjaA6ZZ0fycNjNCsbE' \
          -H 'Cache-Control: no-cache' 
          

### HTTP Status:

* <b>2xx: Successful</b>
	* 200 (Ok): retornado para indicar que o estado da transação está completo.
	* 201 (Created): retornado para indicar que um recurso foi criado.
	Response headers: o Location do cabeçalho deveria conter a URL do novo recurso
	Entity-body: deve retornar a representação do novo recurso criado(objeto).
	* 202 (Accepted): 
	* 203 (Non-Authoritative Information): 
	* 204 (No Content): retornado caso o recurso esteja vazio.
	* 205 (Reset Content): 
	* 206 (Partial Content): 

* <b>3xx: Redirection</b>
	* 300(Multiple Choices):
	* 301(Moved Permanently):
	* 302(Found):
	* 303(See Other):
	* 304(Not Modified):
	* 305(Use Proxy):
	* 306(Unused):
	* 307(Temporary Redirect):
	* 308(Permanent Redirect):

* <b>4xx: Client-Side Error:</b>
	* 400 (Bad Request):
	* 401 (Unauthorized):
	* 402 (Payment Required):
	* 403 (Forbidden):
	* 404 (Not Found):
	* 405 (Method Not Allowed):
	* 406 (Not Acceptable):
	* 407 (Proxy Authentication Required):
	* 408 (Request Timeout):
	* 409 (Conflict):
	* 410 (Gone):
	* 411 (Length Required):
	* 412 (Precondition Failed):
	* 413 (Request Entity Too Large):
	* 414 (Request-URL Too Long):
	* 415 (Unsupported Media Type):
	* 416 (Requested Range Not Satisfiable):
	* 417 (Expectation Failed):
	* 428 (Precondition Required):
	* 429 (Too Many Requests):
	* 431 (Request Header Fields Too Large):
	* 451 (Unavailable For Legal Reasons):

* <b>5xx: Server-Side Error</b>
	* 500 (Internal Server Error):
	* 501 (Not Implemented):
	* 502 (Bad Gateway):
	* 503 (Service Unavailable):
	* 504 (Gateway Timeout):
	* 505 (HTTP Version Not Supported):
	* 511 (Network Authentication Required):

