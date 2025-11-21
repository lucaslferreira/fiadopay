# FiadoPay Simulator (Spring Boot + H2)

Gateway de pagamento **FiadoPay** para a AVI/POOA.
Substitui PSPs reais com um backend em memória (H2).

## Rodar
```bash
./mvnw spring-boot:run
# ou
mvn spring-boot:run
```

H2 console: http://localhost:8080/h2  
Swagger UI: http://localhost:8080/swagger-ui.html

## Fluxo

1) **Cadastrar merchant**
```bash
curl -X POST http://localhost:8080/fiadopay/admin/merchants   -H "Content-Type: application/json"   -d '{"name":"MinhaLoja ADS","webhookUrl":"http://localhost:8081/webhooks/payments"}'
```

2) **Obter token**
```bash
curl -X POST http://localhost:8080/fiadopay/auth/token   -H "Content-Type: application/json"   -d '{"client_id":"<clientId>","client_secret":"<clientSecret>"}'
```

3) **Criar pagamento**
```bash
curl -X POST http://localhost:8080/fiadopay/gateway/payments   -H "Authorization: Bearer FAKE-<merchantId>"   -H "Idempotency-Key: 550e8400-e29b-41d4-a716-446655440000"   -H "Content-Type: application/json"   -d '{"method":"CARD","currency":"BRL","amount":250.50,"installments":12,"metadataOrderId":"ORD-123"}'
```

4) **Consultar pagamento**
```bash
curl http://localhost:8080/fiadopay/gateway/payments/<paymentId>
```


Alterações, melhorias implementadas:


### Anotações Implementadas
- `@AsyncProcessor` - Para métodos de processamento assíncrono
	Marca métodos que devem rodar em threads separadas

- `@PaymentMethod` - Para tipos de pagamento
	Preparado para marcar diferentes tipos de pagamento (PIX, cartão, etc.)
	Facilita adicionar novos métodos de pagamento
- `@AntiFraud` - Para regras de antifraude
	Preparado para regras de segurança (bloquear pagamentos suspeitos)

### Reflexão
- `AnnotationScanner` detecta automaticamente anotações durante a inicialização
- Logs mostram métodos anotados e configurações


### Threads e Concorrência
- `paymentPool` - 5 threads para processar pagamentos
- `webhookPool` - 3 threads para enviar webhooks
- `ExecutorService` substitui `CompletableFuture.runAsync()`


### Arquitetura & padrões
- Strategy pattern implementado:
	Separando o cálculo de juros do resto do código, bom para fazer alterações no futuro
- código organizado em packages:
	annotations/: Só as anotações customizadas

	strategy/: Só os cálculos de pagamento

	config/: Só configurações de threads


### Testes:
Compilação: Código compila limpo

Execução: Sistema sobe normalmente

API: endpoints funcionando

Threads: Processamento assíncrono ativo



Instruções para executar:

### Pré-requisitos:
- Java 21 instalado
- Git instalado

### Passos:
1. **Baixe o projeto:**
   Terminal:

   git clone https://github.com/lucaslferreira/fiadopay.git
   cd fiadopay

2. **Execute o projeto:**
   ./mvnw spring-boot:run
   Abra: http://localhost:8080/swagger-ui.html

