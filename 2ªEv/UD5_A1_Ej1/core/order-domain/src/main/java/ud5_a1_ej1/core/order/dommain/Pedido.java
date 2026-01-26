package ud5_a1_ej1.core.order.dommain;

import ud5_a1_ej1.common.api.IOrder;

public class Pedido implements IOrder{
	
	private String id;
	
	public Pedido(String id) {
		this.id = id;
	}
	
	@Override
	public void procesar() {
		System.out.println("Procesando pedido: "+id);
	}
}
