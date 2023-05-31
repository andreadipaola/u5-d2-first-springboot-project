package classes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
public class Tavolo {

	private int numero;
	private int maxCoperti;
	private boolean isOccupato;
	
	public Tavolo(int numero, int maxCoperti, boolean isOccupato) {
		this.numero = numero;
		this.maxCoperti = maxCoperti;
		this.isOccupato = isOccupato;
	}

	@Override
	public String toString() {
		return "Tavolo n° " + numero + ", occupato " + isOccupato + " (n° massimo coperti " + maxCoperti + ")";
	}
	
}
