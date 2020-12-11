package miashs.dciss.GuillaumeFaustin;

import java.util.ArrayList;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
// ne pas toucher ,cette classe est utile uniquement pour l'interface graphique
public class RectangleAvecCercle extends Rectangle {
	
	public ArrayList<Circle> cercles = new ArrayList<Circle>();
	public int indiceColonne;

	public RectangleAvecCercle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RectangleAvecCercle(double arg0, double arg1, double arg2, double arg3,int indiceColonne) {
		super(arg0, arg1, arg2, arg3);
		this.indiceColonne = indiceColonne;
		// TODO Auto-generated constructor stub
	}

	public RectangleAvecCercle(double arg0, double arg1, Paint arg2,int indiceColonne) {
		super(arg0, arg1, arg2);
		this.indiceColonne = indiceColonne;
		// TODO Auto-generated constructor stub
	}

	public RectangleAvecCercle(double arg0, double arg1, int indiceColonne) {
		super(arg0, arg1);
		this.indiceColonne = indiceColonne;
		// TODO Auto-generated constructor stub
	}
	
	
}
