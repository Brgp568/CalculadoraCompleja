package dad.calculadora;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class SumadorApplication extends Application {

	public void start(Stage primaryStage) throws Exception {

		ComboBox<String> operadorCombo = new ComboBox<>();
		operadorCombo.getItems().addAll("+", "-", "*", "/");

		VBox primer = new VBox(5, operadorCombo);

		TextField aText = new TextField();
		aText.setPrefColumnCount(3);
		TextField bText = new TextField();
		bText.setPrefColumnCount(3);
		Label operador1 = new Label();
		
		HBox primerH = new HBox(5, aText, operador1, bText, new Label("i"));
		primerH.setAlignment(Pos.CENTER);

		TextField cText = new TextField();
		cText.setPrefColumnCount(3);
		TextField dText = new TextField();
		dText.setPrefColumnCount(3);
		Label operador2 = new Label();
		
		HBox segundoH = new HBox(5, cText, operador2, dText, new Label("i"));
		segundoH.setAlignment(Pos.CENTER);

		TextField r1Text = new TextField();
		r1Text.setDisable(true);
		r1Text.setPrefColumnCount(3);
		TextField r2Text = new TextField();
		r2Text.setPrefColumnCount(3);
		r2Text.setDisable(true);
		Label operador3 = new Label();

		HBox tercerH = new HBox(5, r1Text, operador3, r2Text, new Label("i"));
		tercerH.setAlignment(Pos.CENTER);

		VBox segundo = new VBox(5, primerH, segundoH, new Separator(), tercerH);

		HBox root = new HBox(5, primer, segundo);
		root.setAlignment(Pos.CENTER);
		root.setFillHeight(false);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Calculadora");
		primaryStage.setScene(scene);
		primaryStage.show();

		Complejo par1 = new Complejo();
		Complejo par2 = new Complejo();
		Complejo resultado = new Complejo();
		
		
		
		
		aText.textProperty().bindBidirectional(par1.realProperty(), new NumberStringConverter());
		bText.textProperty().bindBidirectional(par1.imaginarioProperty(), new NumberStringConverter());
		cText.textProperty().bindBidirectional(par2.realProperty(), new NumberStringConverter());
		dText.textProperty().bindBidirectional(par2.imaginarioProperty(), new NumberStringConverter());
		r1Text.textProperty().bindBidirectional(resultado.realProperty(), new NumberStringConverter());
		r2Text.textProperty().bindBidirectional(resultado.imaginarioProperty(), new NumberStringConverter());
		
		operador1.textProperty().bind(operadorCombo.valueProperty());
		operador2.textProperty().bind(operadorCombo.valueProperty());
		operador3.textProperty().bind(operadorCombo.valueProperty());
		
		// LISTENER
		operadorCombo.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			switch (operadorCombo.getValue()) {
			case "+":
				resultado.realProperty().bind(par1.realProperty().add(par2.realProperty()));
				resultado.imaginarioProperty().bind(par1.imaginarioProperty().add(par2.imaginarioProperty()));
				break;
			case "-":
				resultado.realProperty().bind(par1.realProperty().subtract(par2.realProperty()));
				resultado.imaginarioProperty().bind(par1.imaginarioProperty().subtract(par2.imaginarioProperty()));
				break;
			case "*":
				resultado.realProperty().bind(par1.realProperty().multiply(par2.realProperty()).subtract(par1.imaginarioProperty().multiply(par2.imaginarioProperty())));
				resultado.imaginarioProperty().bind(par1.realProperty().multiply(par2.imaginarioProperty()).add(par1.imaginarioProperty().multiply(par2.realProperty())));
				break;
			case "/":
				resultado.realProperty().bind(par1.realProperty().multiply(par2.realProperty()).add(par1.imaginarioProperty().multiply(par2.imaginarioProperty())).divide(par2.realProperty().multiply(par2.realProperty()).add(par2.imaginarioProperty().multiply(par2.imaginarioProperty()))));
                resultado.imaginarioProperty().bind(par1.imaginarioProperty().multiply(par2.realProperty()).subtract(par1.realProperty().multiply(par2.imaginarioProperty())).divide(par2.realProperty().multiply(par2.realProperty()).add(par2.imaginarioProperty().multiply(par2.imaginarioProperty()))));
				break;
			}
		});
		operadorCombo.getSelectionModel().selectFirst();
	}

}
