package ud5_a1_ej1_application.order.app;

import java.io.InputStream;
import java.util.Properties;

import javax.naming.ldap.UnsolicitedNotification;

import ud5_a1_ej1.core.order.service.OrderService;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Iniciando aplicación de pedidos...");
		
		try {
			
			// Cargar configuración para ver en qué perfil estamos
			Properties prop = new Properties();
			
			// Leemos el archivo creado prop
			InputStream input = App.class.getClassLoader().getResourceAsStream("config.properties");
			
			if(input == null) {
				System.out.println("Lo siento, no se encuentra config.properties");
				return;
			}
			
			prop.load(input);
			
			System.out.println("Entorno actual: " + prop.getProperty("ambiente"));
			
			OrderService servicio = new OrderService();
			servicio.crearPedido();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
