package femenino;

import humano.Humano;
import masculino.Chicos;
import masculino.GymBro;
import masculino.Informatico;

public class Feminista implements Chicas{

	@Override
	public boolean mentir(Chicos chico) {
		
		boolean isMentir = false;
		
		if(chico instanceof Informatico) {
			isMentir = true;
		}else if (chico instanceof GymBro) {
			isMentir = false;
		} else {
			isMentir = true;
		}
		return isMentir;
	}

	@Override
	public Humano parir(Chicos chico) {
		
		if(chico instanceof Informatico) {
			return new Chicos() {
				
				@Override
				public void respirar() {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void reproducirse(Chicas chica) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void ligar(Chicas chica) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void hablar(Chicas chica) {
					// TODO Auto-generated method stub
					
				}
			};
		}
		throw new RuntimeException();
		
	}

}
