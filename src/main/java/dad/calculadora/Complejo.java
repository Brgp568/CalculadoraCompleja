package dad.calculadora;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo {
	
	private DoubleProperty real, imaginario;

	public Complejo() {
		real = new SimpleDoubleProperty();
		imaginario = new SimpleDoubleProperty();
	}

	public Complejo(double real, double imaginario) {
		this();
		setReal(real);
		setImaginario(imaginario);
	}

	public double getReal() {
		return real.get();
	}

	public void setReal(double real) {
		this.real.set(real);
	}

	public double getImaginario() {
		return imaginario.get();
	}

	public void setImaginario(double imaginario) {
		this.imaginario.set(imaginario);
	}
	
	public DoubleProperty realProperty() {
		return real;
	}
	
	public DoubleProperty imaginarioProperty() {
		return imaginario;
	}
}
