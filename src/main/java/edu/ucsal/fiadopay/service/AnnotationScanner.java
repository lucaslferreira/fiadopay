package edu.ucsal.fiadopay.service;

import edu.ucsal.fiadopay.annotations.AsyncProcessor;
import org.springframework.stereotype.Component;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import java.lang.reflect.Method;

@Component
public class AnnotationScanner {
    
    @EventListener(ContextRefreshedEvent.class)
    public void scanAsyncProcessors() {
        System.out.println("🔍 Iniciando scan de anotações @AsyncProcessor...");
        
        try {
            Class<?> paymentServiceClass = PaymentService.class;
            
            Method[] methods = paymentServiceClass.getDeclaredMethods();
            
            for (Method method : methods) {
                if (method.isAnnotationPresent(AsyncProcessor.class)) {
                    AsyncProcessor annotation = method.getAnnotation(AsyncProcessor.class);
                    System.out.println("Método encontrado: " + method.getName());
                    System.out.println("Pool: " + annotation.pool());
                    System.out.println("Anotação: @AsyncProcessor");
                }
            }
            
            System.out.println("Scan de anotações completado!");
            
        } catch (Exception e) {
            System.out.println("Erro no scan: " + e.getMessage());
        }
    }
}