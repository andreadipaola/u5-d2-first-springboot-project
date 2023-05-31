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
public class AnanasDecorator extends Topping {

	public AnanasDecorator(Pizza pizza) {
		this.pizza = pizza;
		this.nomeTopping = "ananas";
		this.calorie = 50;
		this.prezzo = 0.25;
	}

	public AnanasDecorator() {
		this.nomeTopping = "ananas";
		this.calorie = 50;
		this.prezzo = 0.25;
	}

	@Override
	public String getNome() {
		return pizza.getNome() + "ananas ";
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
