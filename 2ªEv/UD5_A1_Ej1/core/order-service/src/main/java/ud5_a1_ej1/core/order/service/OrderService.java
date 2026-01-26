package ud5_a1_ej1.core.order.service;

import ud5_a1_ej1.common.utils.LogUtils;
import ud5_a1_ej1.core.order.dommain.Pedido;

public class OrderService {
	public void crearPedido() {
		LogUtils.log("Iniciando creacion del pedido...");
		Pedido p = new Pedido("PED-001");
		p.procesar();
		System.out.println("Pedido creado correctamente");
	}
}
