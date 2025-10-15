package com.conversor;

import java.util.Map;

/**
 * Classe que representa a resposta JSON da API ExchangeRate-API
 * 
 * Esta classe utiliza o padrão de mapeamento automático do Gson.
 * Os nomes dos atributos correspondem exatamente aos campos do JSON retornado pela API,
 * permitindo que o Gson faça a conversão automática de JSON para objeto Java.
 * 
 * Exemplo de resposta JSON da API:
 * {
 *   "result": "success",
 *   "base_code": "USD",
 *   "conversion_rates": {
 *     "BRL": 5.65,
 *     "ARS": 350.25,
 *     ...
 *   }
 * }
 */
public class RespostaAPI {
    // Resultado da requisição: "success" ou "error"
    private String result;
    
    // Documentação da API
    private String documentation;
    
    // Termos de uso da API
    private String terms_of_use;
    
    // Timestamp da última atualização (formato Unix)
    private long time_last_update_unix;
    
    // Data/hora da última atualização (formato UTC)
    private String time_last_update_utc;
    
    // Timestamp da próxima atualização (formato Unix)
    private long time_next_update_unix;
    
    // Data/hora da próxima atualização (formato UTC)
    private String time_next_update_utc;
    
    // Código da moeda base utilizada na consulta (ex: "USD", "BRL")
    private String base_code;
    
    // Map contendo as taxas de conversão
    // Chave: Currency Code (ex: "BRL", "ARS")
    // Valor: Taxa de conversão (ex: 5.65 significa que 1 USD = 5.65 BRL)
    private Map<String, Double> conversion_rates;

    // Getters e Setters
    // Estes métodos permitem acessar e modificar os atributos privados da classe
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getTermsOfUse() {
        return terms_of_use;
    }

    public void setTermsOfUse(String terms_of_use) {
        this.terms_of_use = terms_of_use;
    }

    public long getTimeLastUpdateUnix() {
        return time_last_update_unix;
    }

    public void setTimeLastUpdateUnix(long time_last_update_unix) {
        this.time_last_update_unix = time_last_update_unix;
    }

    public String getTimeLastUpdateUtc() {
        return time_last_update_utc;
    }

    public void setTimeLastUpdateUtc(String time_last_update_utc) {
        this.time_last_update_utc = time_last_update_utc;
    }

    public long getTimeNextUpdateUnix() {
        return time_next_update_unix;
    }

    public void setTimeNextUpdateUnix(long time_next_update_unix) {
        this.time_next_update_unix = time_next_update_unix;
    }

    public String getTimeNextUpdateUtc() {
        return time_next_update_utc;
    }

    public void setTimeNextUpdateUtc(String time_next_update_utc) {
        this.time_next_update_utc = time_next_update_utc;
    }

    public String getBaseCode() {
        return base_code;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    /**
     * Retorna o Map com as taxas de conversão
     * Este Map contém todos os Currency Codes suportados pela API
     * e suas respectivas taxas de conversão em relação à moeda base
     */
    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public void setConversionRates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}

