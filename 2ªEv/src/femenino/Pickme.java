package femenino;

import humano.Humano;
import masculino.Chicos;

public class Pickme implements Chicas{

	@Override
	public boolean mentir(Chicos chico) {
		
		return true;
	}

	@Override
	public Humano parir(Chicos chico) {
		// TODO Auto-generated method stub
		return new Humano() {};
	}
	
}
