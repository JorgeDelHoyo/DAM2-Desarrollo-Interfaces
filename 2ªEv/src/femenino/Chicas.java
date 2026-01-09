package femenino;

import humano.Humano;
import masculino.Chicos;

public interface Chicas extends Humano{
	
	@Override
	default void respirar() {
		// TODO Auto-generated method stub
		Humano.super.respirar();
	}
	
	boolean mentir(Chicos chico);
	
	Humano parir(Chicos chico);
	
}
