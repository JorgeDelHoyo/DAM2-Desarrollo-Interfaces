package masculino;

import exceptions.LigarException;
import femenino.Chicas;
import humano.Humano;

public interface Chicos extends Humano{
	
	@Override
	default void respirar() {
		// TODO Auto-generated method stub
		Humano.super.respirar();
	}
	
	void hablar(Chicas chica);
	
	void ligar(Chicas chica) throws LigarException;
	
	void reproducirse(Chicas chica);
}
