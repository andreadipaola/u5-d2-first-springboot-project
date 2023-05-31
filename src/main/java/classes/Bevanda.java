package classes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
public class Bevanda extends Prodotto{

	public Bevanda(double prezzo, String nome, int calorie) {
		super(prezzo, nome, calorie);
	}

	@Override
	public String toString() {
		return nome + " Prezzo: €" + prezzo + ", Calorie: " + calorie;
	}

}
