package com.conversor;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

/**
 * Classe responsável por consumir a API ExchangeRate-API
 * 
 * Esta classe demonstra conceitos importantes de back-end:
 * 1. Consumo de APIs REST usando HttpClient (Java 11+)
 * 2. Manipulação de requisições e respostas HTTP
 * 3. Parsing de JSON usando a biblioteca Gson
 * 4. Tratamento de exceções em operações de rede
 */
public class ConsumidorAPI {
    // Chave de API obtida no site ExchangeRate-API
    // Esta chave permite fazer até 1.500 requisições por mês no plano gratuito
    private static final String API_KEY = "81a869adbf10f4e4d0a2aab7";
    
    // URL base da API - todas as requisições começam com esta URL
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    
    // Cliente HTTP - responsável por enviar as requisições
    // O HttpClient é uma classe moderna do Java 11 para comunicação HTTP
    private HttpClient client;
    
    // Gson - biblioteca do Google para trabalhar com JSON
    // Converte JSON (texto) em objetos Java e vice-versa
    private Gson gson;
    
    /**
     * Construtor da classe
     * Inicializa o HttpClient e o Gson que serão usados nas requisições
     */
    public ConsumidorAPI() {
        // Criando o cliente HTTP que será reutilizado em todas as requisições
        this.client = HttpClient.newHttpClient();
        
        // Criando a instância do Gson para parsing de JSON
        this.gson = new Gson();
    }
    
    /**
     * Busca as taxas de câmbio para uma moeda base específica
     * 
     * Este método demonstra:
     * - Como construir uma URL dinâmica
     * - Como criar e enviar uma requisição HTTP GET
     * - Como processar a resposta HTTP
     * - Como converter JSON em objeto Java usando Gson
     * 
     * @param codigoMoedaBase Código ISO 4217 da moeda base (ex: USD, BRL, ARS)
     * @return Objeto RespostaAPI contendo todas as taxas de conversão
     * @throws IOException Se houver erro na comunicação com a API
     * @throws InterruptedException Se a requisição for interrompida
     */
    public RespostaAPI obterTaxasCambio(String codigoMoedaBase) throws IOException, InterruptedException {
        // Construindo a URL completa da requisição
        // Exemplo: https://v6.exchangerate-api.com/v6/81a869adbf10f4e4d0a2aab7/latest/USD
        String url = BASE_URL + API_KEY + "/latest/" + codigoMoedaBase;
        
        // Criando a requisição HTTP
        // O padrão Builder facilita a construção de objetos complexos
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))  // Define a URI (endereço) da requisição
                .GET()                  // Define o método HTTP como GET
                .build();               // Constrói o objeto HttpRequest
        
        // Enviando a requisição e obtendo a resposta
        // HttpResponse.BodyHandlers.ofString() indica que queremos a resposta como String
        HttpResponse<String> resposta = client.send(requisicao, HttpResponse.BodyHandlers.ofString());
        
        // Verificando se a resposta foi bem-sucedida
        // Código 200 significa "OK" - a requisição foi processada com sucesso
        if (resposta.statusCode() == 200) {
            // Convertendo o JSON (String) para objeto Java usando Gson
            // O Gson automaticamente mapeia os campos do JSON para os atributos da classe RespostaAPI
            return gson.fromJson(resposta.body(), RespostaAPI.class);
        } else {
            // Se o código não for 200, algo deu errado
            throw new IOException("Erro na requisição à API. Código HTTP: " + resposta.statusCode());
        }
    }
    
    /**
     * Realiza a conversão entre duas moedas
     * 
     * Este método implementa a lógica de conversão de moedas:
     * 1. Busca as taxas de câmbio da moeda de origem
     * 2. Filtra a taxa específica para a moeda de destino
     * 3. Calcula o valor convertido
     * 
     * @param moedaOrigem Código da moeda de origem (ex: USD)
     * @param moedaDestino Código da moeda de destino (ex: BRL)
     * @param valor Valor a ser convertido
     * @return Valor convertido para a moeda de destino
     * @throws IOException Se houver erro na comunicação com a API
     * @throws InterruptedException Se a requisição for interrompida
     */
    public double converterMoeda(String moedaOrigem, String moedaDestino, double valor) 
            throws IOException, InterruptedException {
        // Obtendo todas as taxas de câmbio da moeda de origem
        RespostaAPI resposta = obterTaxasCambio(moedaOrigem);
        
        // Verificando se a requisição foi bem-sucedida
        if (resposta.getResult().equals("success")) {
            // Filtrando a taxa específica para a moeda de destino
            // O Map conversion_rates contém todas as moedas, mas queremos apenas uma
            Double taxa = resposta.getConversionRates().get(moedaDestino);
            
            if (taxa != null) {
                // Realizando o cálculo da conversão
                // Exemplo: 100 USD * 5.65 (taxa BRL) = 565 BRL
                return valor * taxa;
            } else {
                throw new IOException("Moeda de destino não encontrada: " + moedaDestino);
            }
        } else {
            throw new IOException("Erro ao obter taxas de câmbio da API");
        }
    }
    
    /**
     * Obtém a taxa de câmbio entre duas moedas
     * 
     * Este método é útil para exibir a taxa de conversão ao usuário
     * 
     * @param moedaOrigem Código da moeda de origem
     * @param moedaDestino Código da moeda de destino
     * @return Taxa de conversão
     * @throws IOException Se houver erro na comunicação com a API
     * @throws InterruptedException Se a requisição for interrompida
     */
    public double obterTaxaConversao(String moedaOrigem, String moedaDestino) 
            throws IOException, InterruptedException {
        RespostaAPI resposta = obterTaxasCambio(moedaOrigem);
        
        if (resposta.getResult().equals("success")) {
            Double taxa = resposta.getConversionRates().get(moedaDestino);
            
            if (taxa != null) {
                return taxa;
            } else {
                throw new IOException("Moeda de destino não encontrada: " + moedaDestino);
            }
        } else {
            throw new IOException("Erro ao obter taxas de câmbio da API");
        }
    }
}

