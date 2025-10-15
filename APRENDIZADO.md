# 📖 Diário de Aprendizado - Conversor de Moedas

Este documento descreve o processo de aprendizado e os conceitos de back-end aplicados durante o desenvolvimento deste projeto.

## 🎯 Etapas do Desafio

### 1️⃣ Configuração do Ambiente Java

**O que aprendi:**
- Como configurar o JDK (Java Development Kit) no sistema
- Diferença entre JRE (Java Runtime Environment) e JDK
- Verificação da versão do Java com `java -version` e `javac -version`

**Comandos utilizados:**
```bash
java -version    # Verifica a versão do Java instalada
javac -version   # Verifica a versão do compilador Java
```

### 2️⃣ Criação do Projeto

**O que aprendi:**
- Estrutura de pacotes em Java (`com.conversor`)
- Organização de código em diretórios
- Separação entre código-fonte (`src/`) e bibliotecas (`lib/`)

**Estrutura criada:**
```
ConversorDeMoedas/
├── lib/                    # Bibliotecas externas
│   └── gson-2.10.1.jar
└── src/                    # Código-fonte
    └── com/
        └── conversor/
            ├── RespostaAPI.java
            ├── ConsumidorAPI.java
            └── ConversorDeMoedas.java
```

### 3️⃣ Consumo da API

**O que aprendi:**
- Como usar a classe `HttpClient` do Java 11 para fazer requisições HTTP
- Construção de requisições GET com `HttpRequest`
- Processamento de respostas com `HttpResponse`

**Código aplicado:**
```java
// Criando o cliente HTTP
HttpClient client = HttpClient.newHttpClient();

// Criando a requisição
HttpRequest requisicao = HttpRequest.newBuilder()
    .uri(URI.create(url))
    .GET()
    .build();

// Enviando e recebendo resposta
HttpResponse<String> resposta = client.send(requisicao, 
    HttpResponse.BodyHandlers.ofString());
```

**Conceitos importantes:**
- **URI (Uniform Resource Identifier)**: Endereço único da API
- **Método GET**: Usado para buscar dados de um servidor
- **Status Code**: Código 200 significa sucesso

### 4️⃣ Análise da Resposta JSON

**O que aprendi:**
- JSON (JavaScript Object Notation) é o formato padrão para troca de dados em APIs
- A biblioteca Gson converte JSON em objetos Java automaticamente
- Importância do mapeamento correto entre campos JSON e atributos Java

**Exemplo de JSON da API:**
```json
{
  "result": "success",
  "base_code": "USD",
  "conversion_rates": {
    "BRL": 5.4911,
    "ARS": 1359.83,
    "BOB": 6.9100,
    "CLP": 950.50,
    "COP": 4350.25
  }
}
```

**Como o Gson funciona:**
```java
// O Gson mapeia automaticamente os campos do JSON
// para os atributos da classe RespostaAPI
Gson gson = new Gson();
RespostaAPI resposta = gson.fromJson(jsonString, RespostaAPI.class);
```

**Ferramenta recomendada:**
- **Postman**: Permite testar APIs e visualizar respostas JSON de forma amigável

### 5️⃣ Filtro de Moedas

**O que aprendi:**
- Como usar o atributo "Currency Code" do JSON
- Trabalhar com `Map<String, Double>` para armazenar taxas de conversão
- Filtrar dados específicos de uma coleção

**Currency Codes utilizados:**
- **ARS**: Peso argentino
- **BOB**: Boliviano boliviano
- **BRL**: Real brasileiro
- **CLP**: Peso chileno
- **COP**: Peso colombiano
- **USD**: Dólar americano

**Código de filtragem:**
```java
// Obtendo a taxa específica para uma moeda
Map<String, Double> taxas = resposta.getConversionRates();
Double taxaBRL = taxas.get("BRL");  // Filtra apenas o BRL
```

### 6️⃣ Exibição de Resultados

**O que aprendi:**
- Como criar uma interface de console amigável
- Uso da classe `Scanner` para ler entrada do usuário
- Formatação de números com `printf`

**Conceitos aplicados:**
```java
// Lendo entrada do usuário
Scanner scanner = new Scanner(System.in);
double valor = scanner.nextDouble();

// Formatando saída com 2 casas decimais
System.out.printf("Resultado: %.2f BRL\n", valorConvertido);
```

### 7️⃣ Lógica de Conversão

**O que aprendi:**
- Como implementar cálculos de conversão de moedas
- Criação de métodos modulares e reutilizáveis
- Separação de responsabilidades entre classes

**Fórmula de conversão:**
```
Valor Convertido = Valor Original × Taxa de Câmbio
```

**Exemplo:**
```
100 USD × 5.4911 (taxa BRL) = 549.11 BRL
```

**Implementação:**
```java
public double converterMoeda(String origem, String destino, double valor) {
    RespostaAPI resposta = obterTaxasCambio(origem);
    Double taxa = resposta.getConversionRates().get(destino);
    return valor * taxa;  // Cálculo da conversão
}
```

### 8️⃣ Interação com Usuário

**O que aprendi:**
- Implementação de menu interativo com loop `while`
- Uso de `switch-case` para processar opções
- Validação de entrada do usuário

**Estrutura do loop:**
```java
int opcao = -1;
while (opcao != 0) {
    exibirMenu();
    opcao = scanner.nextInt();
    processarOpcao(opcao);
}
```

### 9️⃣ Tratamento de Exceções

**O que aprendi:**
- Importância do tratamento de erros em aplicações back-end
- Diferentes tipos de exceções: `IOException`, `InterruptedException`
- Uso de blocos `try-catch` para capturar erros

**Exceções tratadas:**
```java
try {
    double resultado = consumidorAPI.converterMoeda("USD", "BRL", 100);
} catch (IOException e) {
    // Erro de rede ou API
    System.out.println("Erro de comunicação: " + e.getMessage());
} catch (InterruptedException e) {
    // Requisição interrompida
    System.out.println("Requisição interrompida: " + e.getMessage());
} catch (Exception e) {
    // Outros erros (ex: entrada inválida)
    System.out.println("Erro geral: " + e.getMessage());
}
```

### 🔟 Testes e Validação

**O que aprendi:**
- Importância de testar o código antes de finalizar
- Como criar scripts de teste simples
- Validação de diferentes cenários de uso

**Testes realizados:**
1. ✅ Conversão USD → BRL
2. ✅ Conversão USD → ARS
3. ✅ Conversão BRL → USD
4. ✅ Validação de entrada negativa
5. ✅ Tratamento de erro de rede

## 🧠 Conceitos de Back-End Aprendidos

### 1. APIs REST

**O que é:**
- REST (Representational State Transfer) é um estilo de arquitetura para APIs
- Usa métodos HTTP padrão: GET, POST, PUT, DELETE
- Retorna dados em formato JSON

**Por que é importante:**
- Permite comunicação entre diferentes sistemas
- É o padrão da indústria para serviços web
- Facilita integração entre front-end e back-end

### 2. JSON (JavaScript Object Notation)

**O que é:**
- Formato leve de troca de dados
- Fácil de ler para humanos e máquinas
- Baseado em pares chave-valor

**Estrutura básica:**
```json
{
  "chave": "valor",
  "numero": 123,
  "array": [1, 2, 3],
  "objeto": {
    "subchave": "subvalor"
  }
}
```

### 3. HTTP Status Codes

**Códigos importantes:**
- **200 OK**: Requisição bem-sucedida
- **404 Not Found**: Recurso não encontrado
- **500 Internal Server Error**: Erro no servidor
- **401 Unauthorized**: Não autorizado

### 4. Arquitetura em Camadas

**Camadas do projeto:**
1. **Modelo (Model)**: `RespostaAPI.java` - Representa os dados
2. **Serviço (Service)**: `ConsumidorAPI.java` - Lógica de negócio
3. **Apresentação (View)**: `ConversorDeMoedas.java` - Interface com usuário

**Benefícios:**
- Código mais organizado
- Facilita manutenção
- Permite reutilização de código

### 5. Programação Orientada a Objetos (POO)

**Conceitos aplicados:**
- **Encapsulamento**: Atributos privados com getters/setters
- **Abstração**: Classes representam conceitos do mundo real
- **Modularização**: Separação de responsabilidades

## 🛠️ Ferramentas Utilizadas

### 1. Postman
- Testar APIs antes de implementar no código
- Visualizar respostas JSON de forma formatada
- Verificar headers e status codes

### 2. Git e GitHub
- Controle de versão do código
- Compartilhamento do projeto
- Documentação com README

### 3. Gson
- Parsing automático de JSON
- Conversão bidirecional (JSON ↔ Java)
- Fácil integração com projetos Java

## 📊 Resultados dos Testes

### Teste 1: USD → BRL
```
Entrada: 100 USD
Saída: 549.11 BRL
Taxa: 1 USD = 5.4911 BRL
Status: ✅ Sucesso
```

### Teste 2: USD → ARS
```
Entrada: 1000 USD
Saída: 1359830.0 ARS
Taxa: 1 USD = 1359.83 ARS
Status: ✅ Sucesso
```

### Teste 3: BRL → USD
```
Entrada: 50 BRL
Saída: 9.115 USD
Taxa: 1 BRL = 0.1823 USD
Status: ✅ Sucesso
```

## 🎓 Principais Aprendizados

1. **Consumo de APIs é fundamental no back-end**: A maioria das aplicações modernas consome ou fornece APIs

2. **JSON é o padrão da indústria**: Saber manipular JSON é essencial para qualquer desenvolvedor back-end

3. **Tratamento de erros é crucial**: Aplicações robustas devem prever e tratar erros adequadamente

4. **Organização de código importa**: Separar responsabilidades em classes diferentes facilita manutenção

5. **Testes são importantes**: Validar o código antes de finalizar evita problemas futuros

6. **Documentação é essencial**: Um bom README ajuda outros desenvolvedores a entender o projeto

## 🚀 Próximos Passos no Aprendizado

1. **Aprender sobre bancos de dados**: MySQL, PostgreSQL, MongoDB
2. **Estudar frameworks**: Spring Boot para Java
3. **Entender autenticação**: JWT, OAuth
4. **Praticar testes unitários**: JUnit, Mockito
5. **Aprender sobre Docker**: Containerização de aplicações
6. **Estudar microserviços**: Arquitetura moderna de back-end

## 💡 Dicas para Quem Está Aprendendo

1. **Pratique constantemente**: Código se aprende codificando
2. **Leia documentação oficial**: É a fonte mais confiável
3. **Teste seu código**: Não confie apenas na teoria
4. **Comente seu código**: Ajuda você e outros a entender
5. **Use Git desde o início**: Controle de versão é essencial
6. **Compartilhe seu código**: GitHub é seu portfólio
7. **Não tenha medo de errar**: Erros são parte do aprendizado

## 📚 Recursos Recomendados

### Documentação Oficial
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Gson User Guide](https://github.com/google/gson/blob/master/UserGuide.md)
- [ExchangeRate-API Docs](https://www.exchangerate-api.com/docs)

### Tutoriais e Cursos
- Oracle Java Tutorials
- Alura - Cursos de Java
- Udemy - Java Back-End

### Comunidades
- Stack Overflow em Português
- Reddit - r/javahelp
- Discord - Comunidades de programação

---

**Continue estudando e praticando! O back-end é uma área incrível! 🚀**

