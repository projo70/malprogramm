package malprogramm;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class FXMLController {
	//fuer die Zeichenflaeche
	@FXML private Canvas zeichenflaeche;
	//fuer die Farbauswahl
	@FXML private ColorPicker farbauswahl;
	//fuer den Grafikkontext
	private GraphicsContext gc;
	//fuer die Position
	private int xPos, yPos;
	//fuer das Werkzeug
	private int werkzeug;
	//fuer die Farbe
	private Color farbe;
	
	//die Methode zum Beenden
	@FXML protected void beendenKlick(ActionEvent event) {
		Platform.exit();
	}
	
	//die Methode setzt die Initialwerte
	//wird automatisch ausgefuehrt
	@FXML void initialize() {
		//Standardfarbe ist schwarz
		farbe = Color.BLACK;
		farbauswahl.setValue(farbe);
		//das Standardwerkzeug ist die Linie
		werkzeug = 1;
		//Grafikkontext beschaffen
		gc = zeichenflaeche.getGraphicsContext2D();
		
	}
	
	//fuer die Symbole mit dem Zeichenwerkzeug
	//gesetzt wird ein Wert für das jeweilige Werkzeug
	@FXML protected void linieKlick(ActionEvent event) {
		werkzeug = 1;
	}
	
	@FXML protected void kreisKlick(ActionEvent event) {
		werkzeug = 2;
	}
	
	@FXML protected void rechteckKlick(ActionEvent event) {
		werkzeug = 3;
	}
	
	//Methode fuer den gefuellten Kreis
	@FXML protected void gefuellterKreisKlick(ActionEvent event) {
		werkzeug = 4;
	}
	
	//Methode fuer das Druecken der Maustaste
	@FXML protected void mausGedrueckt(MouseEvent e) {
		//die Koordinate als Startpunkt speichern
		//wenn die linke Maustaste gedrueckt wurde
		if (e.getButton() == MouseButton.PRIMARY) {
			xPos = (int)e.getX();
			yPos = (int)e.getY();
		}
	}
	
	//Methode fuer das Loslassen der Maustaste
	@FXML protected void mausLos(MouseEvent e) {
		//wenn die linke Taste losgelassen wird, wird von der alten zur neuen Position gezeichnet
		//je nach Werkzeug etwas anderes
		if(e.getButton() == MouseButton.PRIMARY) {
			//die Linie
			if(werkzeug == 1)
				linie((int)e.getX(), (int)e.getY());
			//ein Kreis
			if(werkzeug == 2)
				kreis((int)(e.getX() - xPos), (int)(e.getY() - yPos));
			//das Rechteck
			if(werkzeug == 3)
				rechteck((int)(e.getX() - xPos), (int)(e.getY() - yPos));
			//der gefuellte Kreis
			if(werkzeug == 4)
				gefuellterKreis((int)(e.getX() - xPos), (int)(e.getY() - yPos));
		}
	}
	
	//Methode fuer neue Farbe
	@FXML protected void farbAuswahlZeigen() {
		farbe = farbauswahl.getValue();
		gc.setStroke(farbe);
		//die Fuellfarbe des Kreises ist die gleiche wie im Color Picker
		gc.setFill(farbe);
	}
	
	//die Methode zeichnet ein Rechteck
	//die Breite und Hoehe als Parameter uebergeben
	protected void rechteck(int breite, int hoehe) {
		//Figur zeichnen
		gc.strokeRect(xPos, yPos, breite, hoehe);
	}
	
	//die Methode zeichnet einen Kreis
	//die Breite und Hoehe des umgebenden Rechtecks werden als Parameter uebergeben
	protected void kreis (int breite, int hoehe) {
		gc.strokeOval(xPos, yPos, breite, hoehe);
	}
	
	//die Methode zeichnet einen gefuellten Kreis
	//die Breite und Hoehe des umgebenden Rechtecks werden als Parameter uebergeben
	protected void gefuellterKreis (int breite, int hoehe) {
		gc.fillOval(xPos, yPos, breite, hoehe);
	}
	
	//die Methode zeichnet eine Linie
	//der Endpunkt wird als Parameter uebergeben
	protected void linie(int x2, int y2) {
		gc.strokeLine(xPos, yPos, x2, y2);
	}
}
