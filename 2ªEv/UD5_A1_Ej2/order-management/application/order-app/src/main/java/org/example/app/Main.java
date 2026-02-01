package org.example.app;
import org.example.service.OrderService;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));
        String env = prop.getProperty("env.name");

        System.out.println("Entorno actual: " + env);
        System.out.println("Iniciando aplicación de pedidos...");

        OrderService service = new OrderService();
        service.process();

        System.out.println("Pedido creado correctamente");
    }
}