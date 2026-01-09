package humano;

public interface Humano {

	default void respirar() {
		System.out.println("uff *respira");
	}

}
