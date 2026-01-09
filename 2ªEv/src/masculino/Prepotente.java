package masculino;

import exceptions.LigarException;
import femenino.Chicas;

public class Prepotente implements Chicos{

	@Override
	public void hablar(Chicas chica) {
		System.out.println("si");
		
	}

	@Override
	public void ligar(Chicas chica) throws LigarException {
		System.out.println("si");
	}

	@Override
	public void reproducirse(Chicas chica) {
		System.out.println("si a todo");
	}

}
