# 💱 Conversor de Moedas - Projeto de Estudo Back-End

Este é um projeto desenvolvido como parte de um desafio de programação para aprender conceitos fundamentais de **desenvolvimento back-end em Java**. O conversor permite realizar conversões entre moedas da América Latina utilizando taxas de câmbio em tempo real obtidas através de uma API REST.

## 🎯 Objetivo do Projeto

Desenvolver um Conversor de Moedas que oferece interação textual via console com os usuários, proporcionando **no mínimo 6 opções distintas de conversões de moedas**. A taxa de conversão não é estática, mas sim **dinamicamente obtida por meio de uma API**, garantindo dados precisos e em tempo real.

## 📚 Conceitos de Back-End Aplicados

Este projeto demonstra os seguintes conceitos essenciais de desenvolvimento back-end:

### 1. Consumo de APIs REST
- Utilização da classe `HttpClient` do Java 11 para realizar requisições HTTP
- Construção de requisições GET para endpoints REST
- Processamento de respostas HTTP e tratamento de códigos de status

### 2. Manipulação de JSON
- Uso da biblioteca **Gson** para parsing de JSON
- Conversão automática de JSON para objetos Java (desserialização)
- Mapeamento de campos JSON para atributos de classes

### 3. Arquitetura em Camadas
- **Camada de Modelo**: `RespostaAPI.java` - representa os dados
- **Camada de Serviço**: `ConsumidorAPI.java` - lógica de negócio e comunicação com API
- **Camada de Apresentação**: `ConversorDeMoedas.java` - interface com usuário

### 4. Tratamento de Exceções
- Try-catch para capturar erros de rede (`IOException`)
- Tratamento de interrupções (`InterruptedException`)
- Validação de entrada do usuário

## 💰 Moedas Suportadas (Currency Codes)

O projeto trabalha com as seguintes moedas conforme especificado no desafio:

| Currency Code | Moeda | País |
|---------------|-------|------|
| **USD** | Dólar Americano | Estados Unidos |
| **BRL** | Real Brasileiro | Brasil |
| **ARS** | Peso Argentino | Argentina |
| **BOB** | Boliviano Boliviano | Bolívia |
| **CLP** | Peso Chileno | Chile |
| **COP** | Peso Colombiano | Colômbia |

## 🛠️ Tecnologias Utilizadas

- **Java 11**: Linguagem de programação
- **HttpClient**: Biblioteca nativa do Java para requisições HTTP
- **Gson 2.10.1**: Biblioteca do Google para manipulação de JSON
- **ExchangeRate-API**: API REST para obtenção de taxas de câmbio

## 📋 Pré-requisitos

- **JDK (Java Development Kit) 11 ou superior**
- **Biblioteca Gson** (incluída no diretório `lib/`)
- Conexão com a internet para acessar a API

## 🚀 Como Executar o Projeto

### Passo 1: Clone o Repositório

```bash
git clone https://github.com/seu-usuario/conversor-de-moedas.git
cd conversor-de-moedas
```

### Passo 2: Compile o Projeto

No terminal, execute o seguinte comando na raiz do projeto:

```bash
javac -cp lib/gson-2.10.1.jar -d src/ src/com/conversor/*.java
```

**Explicação do comando:**
- `-cp lib/gson-2.10.1.jar`: Adiciona a biblioteca Gson ao classpath
- `-d src/`: Define o diretório de destino para os arquivos compilados
- `src/com/conversor/*.java`: Compila todos os arquivos Java do pacote

### Passo 3: Execute a Aplicação

```bash
java -cp src:lib/gson-2.10.1.jar com.conversor.ConversorDeMoedas
```

**Para Windows, use ponto-e-vírgula (;) ao invés de dois-pontos (:):**

```bash
java -cp src;lib/gson-2.10.1.jar com.conversor.ConversorDeMoedas
```

## 📖 Como Usar

1. Ao executar o programa, um menu com 10 opções de conversão será exibido
2. Digite o número da opção desejada e pressione Enter
3. Digite o valor que deseja converter
4. O programa consultará a API em tempo real e exibirá:
   - Valor original
   - Valor convertido
   - Taxa de câmbio utilizada
5. Para sair, digite `0`

### Exemplo de Uso

```
*********************************************************
*        CONVERSOR DE MOEDAS - AMÉRICA LATINA          *
*              Taxas de Câmbio em Tempo Real            *
*********************************************************

Escolha uma opção de conversão:

1) Dólar (USD) =>> Real Brasileiro (BRL)
2) Real Brasileiro (BRL) =>> Dólar (USD)
...

Digite sua opção: 1

Digite o valor em Dólar (USD): 100

[INFO] Consultando API ExchangeRate...
[INFO] Obtendo taxas de câmbio em tempo real...

=========================================================
              RESULTADO DA CONVERSÃO                     
=========================================================

[ORIGEM] 100.00 USD (Dólar)
[DESTINO] 549.11 BRL (Real Brasileiro)
[TAXA] 1 USD = 5.4911 BRL

=========================================================
```

## 📁 Estrutura do Projeto

```
ConversorDeMoedas/
│
├── lib/
│   └── gson-2.10.1.jar          # Biblioteca Gson para parsing JSON
│
├── src/
│   └── com/
│       └── conversor/
│           ├── ConversorDeMoedas.java    # Classe principal com menu
│           ├── ConsumidorAPI.java        # Classe para consumir a API
│           └── RespostaAPI.java          # Modelo de dados (JSON mapping)
│
├── TesteAPI.java                # Script de teste da API
└── README.md                    # Este arquivo
```

## 🔍 Detalhamento das Classes

### RespostaAPI.java

Classe que mapeia a resposta JSON da API para um objeto Java. Utiliza o padrão de mapeamento automático do Gson, onde os nomes dos atributos correspondem aos campos do JSON.

**Principais atributos:**
- `result`: Status da requisição ("success" ou "error")
- `base_code`: Código da moeda base (ex: "USD")
- `conversion_rates`: Map contendo as taxas de conversão para todas as moedas

### ConsumidorAPI.java

Classe responsável por toda a comunicação com a API ExchangeRate-API.

**Principais métodos:**
- `obterTaxasCambio(String codigoMoedaBase)`: Busca todas as taxas de câmbio de uma moeda base
- `converterMoeda(String origem, String destino, double valor)`: Realiza a conversão entre duas moedas
- `obterTaxaConversao(String origem, String destino)`: Retorna apenas a taxa de conversão

### ConversorDeMoedas.java

Classe principal que implementa a interface de console e o loop de interação com o usuário.

**Principais métodos:**
- `exibirMenu()`: Mostra as opções de conversão
- `processarOpcao(int opcao)`: Processa a escolha do usuário
- `realizarConversao(...)`: Executa a conversão e exibe os resultados
- `iniciar()`: Loop principal do programa

## 🌐 Sobre a API ExchangeRate-API

O projeto utiliza a [ExchangeRate-API](https://www.exchangerate-api.com/), uma API confiável que fornece taxas de câmbio atualizadas.

**Endpoint utilizado:**
```
GET https://v6.exchangerate-api.com/v6/{API_KEY}/latest/{BASE_CODE}
```

**Exemplo de resposta JSON:**
```json
{
  "result": "success",
  "base_code": "USD",
  "conversion_rates": {
    "BRL": 5.4911,
    "ARS": 1359.83,
    "BOB": 6.9100,
    "CLP": 950.50,
    "COP": 4350.25,
    ...
  }
}
```

## 🧪 Testando o Projeto

O projeto inclui um script de teste (`TesteAPI.java`) que valida o funcionamento da API:

```bash
javac -cp src:lib/gson-2.10.1.jar TesteAPI.java
java -cp .:src:lib/gson-2.10.1.jar TesteAPI
```

Este teste realiza 3 conversões diferentes e verifica se a API está respondendo corretamente.

## 📝 Etapas de Desenvolvimento (Conforme Desafio)

Este projeto foi desenvolvido seguindo as etapas propostas no desafio:

1. ✅ **Configuração do Ambiente Java**: JDK 11 instalado e configurado
2. ✅ **Criação do Projeto**: Estrutura de pacotes organizada
3. ✅ **Consumo da API**: Implementado com `HttpClient`
4. ✅ **Análise da Resposta JSON**: Utilização do Gson para parsing
5. ✅ **Filtro de Moedas**: Implementação dos 6 Currency Codes solicitados
6. ✅ **Exibição de Resultados**: Interface de console amigável
7. ✅ **Testes**: Validação do funcionamento completo
8. ✅ **Documentação**: README detalhado

## 🔧 Ferramentas Recomendadas

Para facilitar o desenvolvimento e testes, recomenda-se o uso de:

- **Postman**: Para testar a API e visualizar as respostas JSON
- **IntelliJ IDEA** ou **Eclipse**: IDEs para desenvolvimento Java
- **Git**: Para controle de versão do código

## 🎓 Aprendizados

Este projeto proporcionou aprendizado prático sobre:

- Como consumir APIs REST em Java
- Manipulação de JSON com bibliotecas especializadas
- Uso de `HttpClient` para requisições HTTP modernas
- Organização de código em classes e pacotes
- Tratamento adequado de exceções
- Interação com usuário via console
- Validação de entrada de dados
- Boas práticas de documentação de código

## 🚀 Possíveis Melhorias Futuras

- Implementar cache local para reduzir chamadas à API
- Adicionar histórico de conversões
- Criar interface gráfica (GUI) com JavaFX
- Implementar testes unitários com JUnit
- Adicionar mais moedas
- Salvar conversões em arquivo
- Gráficos de variação cambial

## 👨‍💻 Autor

Desenvolvido como projeto de estudo em desenvolvimento back-end com Java.

## 📄 Licença

Este projeto é de código aberto e está disponível para fins educacionais.

---

**Desenvolvido com 💙 enquanto aprendo back-end em Java**

