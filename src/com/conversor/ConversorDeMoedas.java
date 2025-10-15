package com.conversor;

import java.io.IOException;
import java.util.Scanner;

/**
 * Classe principal do Conversor de Moedas
 * 
 * Este projeto demonstra conceitos fundamentais de desenvolvimento back-end:
 * - Consumo de APIs REST
 * - Manipulação de JSON com Gson
 * - Requisições HTTP com HttpClient
 * - Interação com usuário via console
 * - Tratamento de exceções
 * - Organização de código em classes e métodos
 * 
 * Moedas suportadas (Currency Codes):
 * - USD: Dólar americano
 * - BRL: Real brasileiro
 * - ARS: Peso argentino
 * - BOB: Boliviano boliviano
 * - CLP: Peso chileno
 * - COP: Peso colombiano
 */
public class ConversorDeMoedas {
    
    // Instância da classe que consome a API
    private ConsumidorAPI consumidorAPI;
    
    // Scanner para ler entrada do usuário via console
    private Scanner scanner;
    
    /**
     * Construtor da classe
     * Inicializa os objetos necessários para o funcionamento do conversor
     */
    public ConversorDeMoedas() {
        this.consumidorAPI = new ConsumidorAPI();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Exibe o menu principal do conversor
     * 
     * O menu apresenta as 6 opções de conversão conforme solicitado no desafio:
     * - Conversões entre USD e as moedas latino-americanas (BRL, ARS, BOB, CLP, COP)
     */
    public void exibirMenu() {
        System.out.println("\n*********************************************************");
        System.out.println("*                                                       *");
        System.out.println("*        CONVERSOR DE MOEDAS - AMÉRICA LATINA          *");
        System.out.println("*              Taxas de Câmbio em Tempo Real            *");
        System.out.println("*                                                       *");
        System.out.println("*********************************************************");
        System.out.println("\nEscolha uma opção de conversão:\n");
        System.out.println("1) Dólar (USD) =>> Real Brasileiro (BRL)");
        System.out.println("2) Real Brasileiro (BRL) =>> Dólar (USD)");
        System.out.println("3) Dólar (USD) =>> Peso Argentino (ARS)");
        System.out.println("4) Peso Argentino (ARS) =>> Dólar (USD)");
        System.out.println("5) Dólar (USD) =>> Boliviano Boliviano (BOB)");
        System.out.println("6) Boliviano Boliviano (BOB) =>> Dólar (USD)");
        System.out.println("7) Dólar (USD) =>> Peso Chileno (CLP)");
        System.out.println("8) Peso Chileno (CLP) =>> Dólar (USD)");
        System.out.println("9) Dólar (USD) =>> Peso Colombiano (COP)");
        System.out.println("10) Peso Colombiano (COP) =>> Dólar (USD)");
        System.out.println("0) Sair");
        System.out.print("\nDigite sua opção: ");
    }
    
    /**
     * Processa a opção escolhida pelo usuário
     * 
     * Este método implementa a lógica de decisão usando switch-case,
     * uma estrutura de controle fundamental em programação
     * 
     * @param opcao Número da opção escolhida pelo usuário
     */
    public void processarOpcao(int opcao) {
        // Verifica se o usuário escolheu sair
        if (opcao == 0) {
            System.out.println("\n*********************************************************");
            System.out.println("*  Obrigado por usar o Conversor de Moedas!           *");
            System.out.println("*  Continue estudando e praticando back-end! :)        *");
            System.out.println("*********************************************************\n");
            return;
        }
        
        // Variáveis para armazenar os códigos das moedas
        String moedaOrigem = "";
        String moedaDestino = "";
        String nomeMoedaOrigem = "";
        String nomeMoedaDestino = "";
        
        // Switch-case para determinar qual conversão realizar
        // Cada case corresponde a uma opção do menu
        switch (opcao) {
            case 1:
                moedaOrigem = "USD";
                moedaDestino = "BRL";
                nomeMoedaOrigem = "Dólar";
                nomeMoedaDestino = "Real Brasileiro";
                break;
            case 2:
                moedaOrigem = "BRL";
                moedaDestino = "USD";
                nomeMoedaOrigem = "Real Brasileiro";
                nomeMoedaDestino = "Dólar";
                break;
            case 3:
                moedaOrigem = "USD";
                moedaDestino = "ARS";
                nomeMoedaOrigem = "Dólar";
                nomeMoedaDestino = "Peso Argentino";
                break;
            case 4:
                moedaOrigem = "ARS";
                moedaDestino = "USD";
                nomeMoedaOrigem = "Peso Argentino";
                nomeMoedaDestino = "Dólar";
                break;
            case 5:
                moedaOrigem = "USD";
                moedaDestino = "BOB";
                nomeMoedaOrigem = "Dólar";
                nomeMoedaDestino = "Boliviano Boliviano";
                break;
            case 6:
                moedaOrigem = "BOB";
                moedaDestino = "USD";
                nomeMoedaOrigem = "Boliviano Boliviano";
                nomeMoedaDestino = "Dólar";
                break;
            case 7:
                moedaOrigem = "USD";
                moedaDestino = "CLP";
                nomeMoedaOrigem = "Dólar";
                nomeMoedaDestino = "Peso Chileno";
                break;
            case 8:
                moedaOrigem = "CLP";
                moedaDestino = "USD";
                nomeMoedaOrigem = "Peso Chileno";
                nomeMoedaDestino = "Dólar";
                break;
            case 9:
                moedaOrigem = "USD";
                moedaDestino = "COP";
                nomeMoedaOrigem = "Dólar";
                nomeMoedaDestino = "Peso Colombiano";
                break;
            case 10:
                moedaOrigem = "COP";
                moedaDestino = "USD";
                nomeMoedaOrigem = "Peso Colombiano";
                nomeMoedaDestino = "Dólar";
                break;
            default:
                System.out.println("\n[ERRO] Opção inválida! Por favor, escolha uma opção válida.\n");
                return;
        }
        
        // Chama o método que realiza a conversão
        realizarConversao(moedaOrigem, moedaDestino, nomeMoedaOrigem, nomeMoedaDestino);
    }
    
    /**
     * Realiza a conversão entre duas moedas específicas
     * 
     * Este método demonstra:
     * - Leitura de entrada do usuário com Scanner
     * - Validação de dados
     * - Tratamento de exceções (try-catch)
     * - Chamada de métodos da API
     * - Formatação de saída
     * 
     * @param moedaOrigem Currency Code da moeda de origem
     * @param moedaDestino Currency Code da moeda de destino
     * @param nomeMoedaOrigem Nome completo da moeda de origem
     * @param nomeMoedaDestino Nome completo da moeda de destino
     */
    private void realizarConversao(String moedaOrigem, String moedaDestino, 
                                   String nomeMoedaOrigem, String nomeMoedaDestino) {
        System.out.print("\nDigite o valor em " + nomeMoedaOrigem + " (" + moedaOrigem + "): ");
        
        try {
            // Lendo o valor digitado pelo usuário
            double valor = scanner.nextDouble();
            
            // Validando se o valor é positivo
            if (valor < 0) {
                System.out.println("\n[ERRO] O valor não pode ser negativo!\n");
                return;
            }
            
            System.out.println("\n[INFO] Consultando API ExchangeRate...");
            System.out.println("[INFO] Obtendo taxas de câmbio em tempo real...\n");
            
            // Chamando o método que consome a API e realiza a conversão
            double valorConvertido = consumidorAPI.converterMoeda(moedaOrigem, moedaDestino, valor);
            
            // Obtendo também a taxa de conversão para exibir ao usuário
            double taxa = consumidorAPI.obterTaxaConversao(moedaOrigem, moedaDestino);
            
            // Exibindo os resultados formatados
            System.out.println("=========================================================");
            System.out.println("              RESULTADO DA CONVERSÃO                     ");
            System.out.println("=========================================================");
            System.out.printf("\n[ORIGEM] %.2f %s (%s)\n", valor, moedaOrigem, nomeMoedaOrigem);
            System.out.printf("[DESTINO] %.2f %s (%s)\n", valorConvertido, moedaDestino, nomeMoedaDestino);
            System.out.printf("[TAXA] 1 %s = %.4f %s\n", moedaOrigem, taxa, moedaDestino);
            System.out.println("\n=========================================================\n");
            
        } catch (IOException e) {
            // Tratamento de erros de comunicação com a API
            System.out.println("\n[ERRO] Falha ao comunicar com a API: " + e.getMessage());
            System.out.println("[DICA] Verifique sua conexão com a internet.\n");
        } catch (InterruptedException e) {
            // Tratamento de interrupção da requisição
            System.out.println("\n[ERRO] A requisição foi interrompida: " + e.getMessage() + "\n");
        } catch (Exception e) {
            // Tratamento de outros erros (ex: entrada inválida)
            System.out.println("\n[ERRO] Entrada inválida! Por favor, digite um número válido.\n");
            scanner.nextLine(); // Limpa o buffer do scanner
        }
    }
    
    /**
     * Inicia o loop principal do programa
     * 
     * Este método implementa um loop de repetição que mantém o programa
     * em execução até que o usuário escolha sair (opção 0)
     */
    public void iniciar() {
        int opcao = -1;
        
        // Mensagem de boas-vindas
        System.out.println("\n*********************************************************");
        System.out.println("*                                                       *");
        System.out.println("*         BEM-VINDO AO CONVERSOR DE MOEDAS!            *");
        System.out.println("*                                                       *");
        System.out.println("*  Este projeto demonstra conceitos de back-end:       *");
        System.out.println("*  - Consumo de APIs REST                               *");
        System.out.println("*  - Manipulação de JSON com Gson                       *");
        System.out.println("*  - Requisições HTTP com HttpClient                    *");
        System.out.println("*  - Tratamento de exceções                             *");
        System.out.println("*                                                       *");
        System.out.println("*********************************************************");
        
        // Loop principal do programa
        // Continua executando enquanto o usuário não escolher sair (opção 0)
        while (opcao != 0) {
            exibirMenu();
            
            try {
                // Lendo a opção escolhida pelo usuário
                opcao = scanner.nextInt();
                
                // Processando a opção
                processarOpcao(opcao);
                
            } catch (Exception e) {
                // Tratamento de erro caso o usuário digite algo que não seja um número
                System.out.println("\n[ERRO] Por favor, digite um número válido!\n");
                scanner.nextLine(); // Limpa o buffer do scanner
                opcao = -1; // Mantém o loop ativo
            }
        }
        
        // Fechando o scanner ao finalizar o programa
        scanner.close();
    }
    
    /**
     * Método principal (main) - ponto de entrada da aplicação
     * 
     * Este é o primeiro método executado quando o programa é iniciado
     * 
     * @param args Argumentos da linha de comando (não utilizados neste projeto)
     */
    public static void main(String[] args) {
        // Criando uma instância do conversor
        ConversorDeMoedas conversor = new ConversorDeMoedas();
        
        // Iniciando o programa
        conversor.iniciar();
    }
}

