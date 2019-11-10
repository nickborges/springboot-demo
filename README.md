# springboot-demo

| Versões		  | Java		|
| ----------------------- | ------------------- |
| Spring Boot 2	  	  | Java 8 e 9		|
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
- Banco de dados H2, configurar o console do H2 com propriedades no arquivo src/main/resources/application.properties;

### SWAGGER: 
- colocar aqui a descrição

### CONTROLLER’S:
- Anotações @Controller ou @RestController e @RequestMapping
- Para não retornar a requisição como uma página JSP, ou Thymeleaf, devemos utilizar a anotação @ResponseBody caso utilizado @Controller;
- Para não repetir a anotação @ResponseBody em todos os métodos do controller, devemos utilizar a anotação @RestController;
- Para evitar repetir a URL em todos os métodos, devemos utilizar a anotação @RequestMapping em cima da classe controller;

### VERBOS HTTP:
- GET @GetMapping; (recupera a representação de um recurso)
	* status 200: sucesso
	* status 301: Moved Permanently
	* status 404: caso não ache o regitro para deletar

- POST @PostMapping; (cria um novo recurso)
	* @RequestBody, para informar no parâmetro para o spring que é para pegar o conteúdo do corpo do método.
	* status 201: created, devolver o recurso criado no response o no cabeçalho a URI do novo recurso
	* status 202: accepted, intenção de criar o novo recurso mas não criou ainda.
	* Boa prática para métodos que cadastram informações é devolver o código HTTP 201, ao invés do código 200;
	* Para montar uma resposta a ser devolvida ao cliente da API, devemos utilizar a classe ResponseEntity do Spring;
	* Receber UriComponentesBuilder no parâmetro para devolver a URI com o novo recurso criado.

- PUT @PutMapping;
	* @Transactional, colocar no método do controller, é usado para o caso de apenas consultar o registro pelo {id} alterar os dados e pronto(não precisa usar o método save do jpa).
	* status 200: sucesso e retorna uma mensagem.
	* status 204: sucesso e retorna o corpo vazio
	* status 404: caso não ache o regitro para alterar

- DELETE @DeleteMapping; (excluí um recurso).
	* @Transactional, colocar no método do controller, é usado para o caso de apenas consultar o registro pelo {id} alterar os dados e pronto(não precisa usar o método save do jpa).
	* status 200: sucesso e retorna uma mensagem.
	* status 202: sucesso e vai deletar depois.
	* status 204: sucesso e retorna o corpo vazio
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

### AUTENTICAÇÃO:
	http://localhost:8080/auth
	{
		“email”: “aluno@email.com”,
		“senha”: “123456”
	}

	1) habilitar o spring security com a dependência
	1) colocar a dependência do JJWT 
	2) Criar a classe de configuração, SecurityConfigurations:
	3) Criar a classe de serviço, AutenticacaoService:
	4) Criar a Entity, Perfil que implementa GrantedAuthority:
		GrantedAuthority = diz para o spring que é a classe que representa o perfil de acesso.
	5) Colocar na Entity Usuario, implements UserDetails

- Security: autenticação tradicional, com usuário e senha, sempre que o usuário efetua login, o servidor cria uma sessão para guardar essas informações e a cada requisição o servidor valida os dados desta requisição com a sessão armazenada em memória (precisando ter bastante espaço em memória para armezenar cada requisição). Isso não atende a boa prática(princípio) do modelo REST, o ideal é que a nossa autenticação seja stateless(no caso do token).
- Security JWT: Não cria uma sessão como no modo tradicional, usa o padrão Stateless ou “Sem estado”
- Stateless ou Sem estado(é um protocolo de comunicação que considera cada requisição como uma transação independente que não está relacionada a qualquer requisição anterior, depois do retorno da requisição nenuma sessão é armazenada.)
- OAuth2:




### PAGINAÇÃO:
- colocar a descrição aqui


### CACHE:
- colocar a descrição aqui


### MONITORAMENTO ACTUATOR:
	http://localhost:8080/actuator
	só devolve json: mas pode ser usado o Spring Bood Admin (uma aplicação web criada por uma empresa alemã)
	1) criar um projeto com Spring initialazer 
	2) seguir o readme do https://github.com/codecentric/spring-boot-admin


	

### HTTP Status:
<h6>
* 2xx: Successful
	* 200 (Ok): retornado para indicar que o estado da transação está completo.
	* 201 (Created): retornado para indicar que um recurso foi criado.
	Response headers: o Location do cabeçalho deveria conter a URL do novo recurso
	Entity-body: deve retornar a representação do novo recurso criado(objeto).
	* 202 (Accepted): 
	* 203 (Non-Authoritative Information): 
	* 204 (No Content): retornado caso o recurso esteja vazio.
	* 205 (Reset Content): 
	* 206 (Partial Content): 

* 3xx: Redirection
	* 300(Multiple Choices):
	* 301(Moved Permanently):
	* 302(Found):
	* 303(See Other):
	* 304(Not Modified):
	* 305(Use Proxy):
	* 306(Unused):
	* 307(Temporary Redirect):
	* 308(Permanent Redirect):

4xx: Client-Side Error:
400 (Bad Request):
401 (Unauthorized):
402 (Payment Required):
403 (Forbidden):
404 (Not Found):
405 (Method Not Allowed):
406 (Not Acceptable):
407 (Proxy Authentication Required):
408 (Request Timeout):
409 (Conflict):
410 (Gone):
411 (Length Required):
412 (Precondition Failed):
413 (Request Entity Too Large):
414 (Request-URL Too Long):
415 (Unsupported Media Type):
416 (Requested Range Not Satisfiable):
417 (Expectation Failed):
428 (Precondition Required):
429 (Too Many Requests):
431 (Request Header Fields Too Large):
451 (Unavailable For Legal Reasons):

5xx: Server-Side Error
500 (Internal Server Error):
501 (Not Implemented):
502 (Bad Gateway):
503 (Service Unavailable):
504 (Gateway Timeout):
505 (HTTP Version Not Supported):
511 (Network Authentication Required):

</h6>
