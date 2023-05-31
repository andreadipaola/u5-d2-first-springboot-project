package classes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
public abstract class Topping extends Pizza {

	protected Pizza pizza;
	protected String nomeTopping;
	protected double prezzo;
	protected int calorie;

	public Topping() {
		super();
	}

	@Override
	public String toString() {
		return getNome() + "prezzo: " + getPrezzo() + " calorie: " + getCalorie();
	}

	
	
	



}
