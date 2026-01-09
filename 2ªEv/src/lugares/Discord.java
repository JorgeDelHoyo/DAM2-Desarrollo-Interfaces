package lugares;

import java.util.ArrayList;

import exceptions.LigarException;
import femenino.Chicas;
import humano.Humano;
import masculino.Chicos;

public class Discord implements MeetingPoint{

	@Override
	public void unir(ArrayList<Chicos> chicos, ArrayList<Chicas> chicas) {
		
		ArrayList<Humano> bebes = new ArrayList<Humano>();
		
		
		for(Chicas ca : chicas) {
			for(Chicos co : chicos) {
				co.hablar(ca);
				ca.mentir(co);
				try {
					co.ligar(ca);
				} catch (LigarException e) {
					new RuntimeException(e);
				}
				ca.respirar();
				co.respirar();
				co.reproducirse(ca);
				Humano bebe = ca.parir(co);
				bebes.add(bebe);
			}
		}
		
		System.out.println();
		System.out.println(bebes.size());
	}

}
