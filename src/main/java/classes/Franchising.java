package classes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
public class Franchising extends Prodotto{

	public Franchising (String nome, double prezzo) {
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		return nome + " | PREZZO: " + prezzo;
	}
}
