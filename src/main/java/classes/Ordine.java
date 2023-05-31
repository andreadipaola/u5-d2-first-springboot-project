package classes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import enums.StatoOrdine;
import lombok.Setter;


@Setter
@Component
@Scope("prototype")
public class Ordine {

	private Tavolo tavolo;
	private int nOrdine;
	private StatoOrdine stato;
	private int nCoperti;
	private LocalDate oraAcquisizione;
	private double importoTotale; //somma dei costi dei suoi elementi e dei coperti
	private List<Elemento> elementi;
	
	public Ordine(Tavolo tavolo, int nOrdine, StatoOrdine stato, int nCoperti, LocalDate oraAcquisizione, List<Elemento> elementi) {
	    if (nCoperti > tavolo.getMaxCoperti()) {
	        throw new IllegalArgumentException("Numero di coperti non valido, nCoperti non può essere maggiore di maxCoperti del Tavolo");
	    }
		
		this.tavolo = tavolo;
		this.nOrdine = nOrdine;
		this.stato = stato;
		this.nCoperti = nCoperti;
		this.oraAcquisizione = oraAcquisizione;
		this.elementi = elementi;
	}
	
	public double getImportoTotale() {
		importoTotale = 0;
		for(int i = 0; i < elementi.size(); i++) {
			importoTotale += elementi.get(i).prezzo;
		}
		
		return importoTotale + (nCoperti * 2);
	}
	
	public void getListaOrdine() {
		System.out.println("Data " + oraAcquisizione.toString());
		System.out.println("N° Ordine " + nOrdine + " - N° Tavolo " + tavolo.getNumero());
		System.out.println("N° Coperti " + nCoperti);
		
		for(int i = 0; i < elementi.size(); i++) {
			System.out.println(elementi.get(i));
		}
	}

	@Override
	public String toString() {
		return "Ordine n° " + nOrdine + " del tavolo n° " + tavolo.getNumero() + ", stato " + stato + ", numero coperti " + nCoperti + ", importo totale €" + importoTotale 
				+ ", ora acquisizione " + oraAcquisizione;
	}
	
}
