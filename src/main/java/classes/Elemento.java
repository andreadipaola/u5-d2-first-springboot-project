package classes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
public class Elemento extends Prodotto {
	private String nota;

	public Elemento(Prodotto prodotto, String nota) {
		this.prezzo = prodotto.prezzo;
		this.nome = prodotto.nome;
		this.calorie = prodotto.calorie;
		this.nota = nota;
	}

	@Override
	public String toString() {
		return getNome() + "€" + getPrezzo() + " Nota: " + nota;
	}

}
