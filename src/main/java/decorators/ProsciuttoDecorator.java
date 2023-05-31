package decorators;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import classes.Pizza;
import classes.Topping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
public class ProsciuttoDecorator extends Topping {

	public ProsciuttoDecorator(Pizza pizza) {
		this.pizza = pizza;
		this.nomeTopping = "prosciutto";
		this.calorie = 100;
		this.prezzo = 0.20;
	}

	public ProsciuttoDecorator() {
		this.nomeTopping = "prosciutto";
		this.calorie = 100;
		this.prezzo = 0.20;
	}

	@Override
	public String getNome() {
		return pizza.getNome() + "prosciutto ";
	}

	@Override
	public double getPrezzo() {
		return pizza.getPrezzo() + prezzo;
	}

	@Override
	public int getCalorie() {
		return pizza.getCalorie() + calorie;
	}

	@Override
	public String toString() {
		return nomeTopping + ", prezzo=" + prezzo + ", calorie=" + calorie + "]";
	}

}
