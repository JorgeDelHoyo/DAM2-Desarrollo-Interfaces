package masculino;

import femenino.Chicas;

public class GymBro implements Chicos{

	@Override
	public void hablar(Chicas chica) {
		System.out.println("Hola guapa");
	}

	@Override
	public void ligar(Chicas chica) {
		System.out.println("mira mi brazo");
	}

	@Override
	public void reproducirse(Chicas chica) {
		System.out.println("Traeme a tu hermana");
	}

}
