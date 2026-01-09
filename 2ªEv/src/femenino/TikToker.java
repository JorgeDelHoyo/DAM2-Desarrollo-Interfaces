package femenino;

import humano.Humano;
import masculino.Chicos;
import masculino.GymBro;

public class TikToker implements Chicas{

	@Override
	public boolean mentir(Chicos chico) {
		
		return false;
	}

	@Override
	public Humano parir(Chicos chico) {
		if(chico instanceof GymBro || chico instanceof Humano) {
			return new Humano() {};
		}
		return null;
	}

}
