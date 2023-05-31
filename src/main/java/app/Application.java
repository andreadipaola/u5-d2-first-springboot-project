package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import classes.Bevanda;
import classes.Elemento;
import classes.Franchising;
import classes.Ordine;
import classes.Pizza;
import classes.Tavolo;
import classes.Topping;
import decorators.AnanasDecorator;
import decorators.MozzarellaDecorator;
import decorators.PomodoroDecorator;
import decorators.ProsciuttoDecorator;
import decorators.SalameDecorator;
import enums.StatoOrdine;

//@Slf4j
@SpringBootApplication
@EnableAutoConfiguration(exclude = SpringApplicationAdminJmxAutoConfiguration.class)

public class Application {

	static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		ctx.scan("app");
		ctx.refresh();

		pizzeria();

		ctx.close();
	}

	public static void pizzeria() {
		Scanner scan = new Scanner(System.in);
		boolean attivo;
		ArrayList<Elemento> elementi = new ArrayList<Elemento>();

		// CLASSI ISTANZIATE
		Franchising f1 = new Franchising("Maglia", 21.99);
		Franchising f2 = new Franchising("Tazza", 4.99);

		System.out.printf("%n%nFRANCHISING:%n");
		System.out.println(f1);
		System.out.println(f2);
		System.out.printf("%n");

		Topping t1 = new AnanasDecorator();
		Topping t2 = new MozzarellaDecorator();
		Topping t3 = new PomodoroDecorator();
		Topping t4 = new ProsciuttoDecorator();
		Topping t5 = new SalameDecorator();

		Pizza p1 = (Pizza) ctx.getBean(Pizza.class);
		Pizza pMargherita = new PomodoroDecorator(new MozzarellaDecorator(p1));
		Pizza pProsciutto = new PomodoroDecorator(new MozzarellaDecorator(new ProsciuttoDecorator(p1)));
		Pizza pAnanas = new PomodoroDecorator(
				new MozzarellaDecorator(new ProsciuttoDecorator(new AnanasDecorator(p1))));
		Pizza pSalameProsciutto = new PomodoroDecorator(
				new MozzarellaDecorator(new ProsciuttoDecorator(new SalameDecorator(p1))));
		Pizza pSalame = new PomodoroDecorator(new MozzarellaDecorator(new SalameDecorator(p1)));

		Bevanda b1 = new Bevanda(1.29, "Limonata (0.33 cl)", 128);
		Bevanda b2 = new Bevanda(1.29, "Acqua (0.5l)", 0);
		Bevanda b3 = new Bevanda(7.49, "Vino (0.75l, 13%)", 607);

		Tavolo tav1 = ctx.getBean(Tavolo.class, 1, 4, false);
		Tavolo tav2 = ctx.getBean(Tavolo.class, 2, 4, false);
		Tavolo tav3 = ctx.getBean(Tavolo.class, 3, 4, false);

		// START PIZZERIA
		System.out.println("-------------------------------");
		System.out.println("BENVENUTI IN GODFATHER'S PIZZA!");
		System.out.println("-------------------------------");

		System.out.println("Quante persone siete?");
		int nPersone = scan.nextInt();

		if (nPersone > 4 || (tav1.isOccupato() && tav2.isOccupato() && tav3.isOccupato())) {
			System.out.println("Mi dispiace, non ci sono tavoli disponibili");
			System.exit(0);
		} else if (nPersone <= 4) {
			if (!tav1.isOccupato()) {
				System.out.println("Potete accomodarvi al tavolo n° " + tav1.getNumero());
				tav1.setOccupato(true);
			} else if (tav1.isOccupato() && !tav2.isOccupato()) {
				System.out.println("Potete accomodarvi al tavolo n° " + tav2.getNumero());
				tav2.setOccupato(true);
			} else if (tav1.isOccupato() && tav2.isOccupato() && !tav3.isOccupato()) {
				System.out.println("Potete accomodarvi al tavolo n° " + tav3.getNumero());
				tav3.setOccupato(true);
			}
		}

		do {
			// Stampa menù
			System.out.println("--------------------");
			System.out.println("Ecco il nostro menù:");
			System.out.println("--------------------");

			System.out.println("PIZZE:");
			System.out.println("1 - Pizza Margherita " + "(" + pMargherita.getNome() + ")" + " | Prezzo: €"
					+ (Math.round(pMargherita.getPrezzo() * 100.00)) / 100.00 + " | Calorie: "
					+ pMargherita.getCalorie());
			System.out.println("2 - Pizza Prosciutto " + "(" + pProsciutto.getNome() + ")" + " | Prezzo: €"
					+ (Math.round(pProsciutto.getPrezzo() * 100.00)) / 100.00 + " | Calorie: "
					+ pProsciutto.getCalorie());
			System.out.println("3 - Pizza Hawaiian " + "(" + pAnanas.getNome() + ")" + " | Prezzo: €"
					+ (Math.round(pAnanas.getPrezzo() * 100.00)) / 100.00 + " | Calorie: " + pAnanas.getCalorie());
			System.out.println("4 - Pizza Salame " + "(" + pSalame.getNome() + ")" + " | Prezzo: €"
					+ (Math.round(pSalame.getPrezzo() * 100.00)) / 100.00 + " | Calorie: " + pSalame.getCalorie());
			System.out.println("5 - Pizza Salame e prosciutto " + "(" + pSalameProsciutto.getNome() + ")"
					+ " | Prezzo: €" + (Math.round(pSalameProsciutto.getPrezzo() * 100.00)) / 100.00 + " | Calorie: "
					+ pSalameProsciutto.getCalorie());

			System.out.printf("%nTOPPING:%n");
			System.out.println(t1);
			System.out.println(t2);
			System.out.println(t3);
			System.out.println(t4);
			System.out.println(t5);

			System.out.printf("%nBEVANDE:%n");
			System.out.println("6 - " + b1);
			System.out.println("7 - " + b2);
			System.out.println("8 - " + b3);

			System.out.printf("%nINFO:%n");
			System.out.println("Coperto €2.00");
			System.out.println("--------------------");

			System.out.println("Cosa volete ordinare? (Digitare il numero corrispondente alla scelta)");
			int scelta = scan.nextInt();

			// Aggiunta della nota all'elemento Pizza o Bevanda
			String nota = null;

			if (scelta > 8) {
				System.out.println("Errore");
			} else if (!(scelta == 6 || scelta == 7 || scelta == 8)) {
				System.out.println("Preferenze di cottura o di impasto?");
				scan.next();
				nota = scan.nextLine();
			} else {
				System.out.println("Preferenze sulla bevanda?");
				scan.next();
				nota = scan.nextLine();
			}

			switch (scelta) {
			case (1):
				Elemento e1 = ctx.getBean(Elemento.class, pMargherita, nota);
				elementi.add(e1);
				break;
			case (2):
				Elemento e2 = ctx.getBean(Elemento.class, pProsciutto, nota);
				elementi.add(e2);
				break;
			case (3):
				Elemento e3 = ctx.getBean(Elemento.class, pAnanas, nota);
				elementi.add(e3);
				break;
			case (4):
				Elemento e4 = ctx.getBean(Elemento.class, pSalame, nota);
				elementi.add(e4);
				break;
			case (5):
				Elemento e5 = ctx.getBean(Elemento.class, pSalameProsciutto, nota);
				elementi.add(e5);
				break;
			case (6):
				Elemento e6 = ctx.getBean(Elemento.class, b1, nota);
				elementi.add(e6);
				break;
			case (7):
				Elemento e7 = ctx.getBean(Elemento.class, b2, nota);
				elementi.add(e7);
				break;
			case (8):
				Elemento e8 = ctx.getBean(Elemento.class, b3, nota);
				elementi.add(e8);
				break;
			default:
				System.out.println("Numero non presente nel menù");
				System.exit(0);
				break;
			}

			System.out.println("Desiderate altro? (Digitare Si o qualunque altro carattere per No)");
			String altro = scan.nextLine();
			attivo = altro.equalsIgnoreCase("Si");
		} while (attivo);

		// Creazione dell'ordine
		Ordine o1 = ctx.getBean(Ordine.class, tav1, 20, StatoOrdine.IN_CORSO, nPersone, LocalDate.now(), elementi);

		// System.out.println(tav1);
		// System.out.println(o1);

		// Creazione del conto
		System.out.printf("%n%nEcco il conto:%n");

		System.out.println("----GODFATHER'S PIZZA----");
		o1.getListaOrdine();
		System.out.println("-------------------------");
		System.out.printf("Totale: €%.2f%n", o1.getImportoTotale());
		System.out.println("GRAZIE PER AVERCI SCELTO, A PRESTO!!");

		scan.close();

	}

}
