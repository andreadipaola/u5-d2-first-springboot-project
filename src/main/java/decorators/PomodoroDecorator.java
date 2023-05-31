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
public class PomodoroDecorator extends Topping {

	public PomodoroDecorator(Pizza pizza) {
		this.pizza = pizza;
		this.nomeTopping = "pomodoro";
		this.calorie = 15;
		this.prezzo = 0.10;
	}

	public PomodoroDecorator() {
		this.nomeTopping = "pomodoro";
		this.calorie = 15;
		this.prezzo = 0.10;
	}

	@Override
	public String getNome() {
		return pizza.getNome() + "pomodoro ";
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
