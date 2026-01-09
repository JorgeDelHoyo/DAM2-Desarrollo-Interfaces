package masculino;

import exceptions.LigarException;
import femenino.Chicas;

public class Informatico implements Chicos, Orangutan{

	@Override
	public void hablar(Chicas chica) {
		System.out.println("Se pone nervioso");
	}

	@Override
	public void ligar(Chicas chica) throws LigarException{
		throw new LigarException();
	}

	@Override
	public void reproducirse(Chicas chica) {
		System.out.println("Pro fabor");
	}

}
