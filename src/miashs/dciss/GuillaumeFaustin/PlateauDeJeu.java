package miashs.dciss.GuillaumeFaustin;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PlateauDeJeu extends Application {

	private Joueur joueurActuel;
	private Joueur joueur1;
	private Joueur joueur2;
	private boolean statut = false;

	@Override
	public void start(Stage primaryStage) {
		Jeu jeu = new Jeu();
		ArrayList<RectangleAvecCercle> rectangles = new ArrayList<RectangleAvecCercle>();
		primaryStage.setTitle("Puissance4 coursIA");
		BorderPane bpane = new BorderPane();
		Group root = new Group();
		Scene scene = new Scene(bpane, 950, 600);
		scene.setFill(Color.TRANSPARENT);
		Rectangle plateau = new Rectangle(0, 0, 700, 600);
		plateau.setFill(Color.BLUE);
		plateau.setOpacity(0.7);
		Label etatJeu = new Label("");
		Label labelJoueur = new Label("");
		etatJeu.setStyle(" -fx-font-size: 1.5em ;");
		labelJoueur.setStyle("-fx-font-weight: bold; -fx-font-size: 2.5em ;");
		etatJeu.setPadding(new Insets(275, 30, 0, 0));
		labelJoueur.setPadding(new Insets(300, 0, 0, 25));
		AnchorPane paneDroit = new AnchorPane();
		root.getChildren().addAll(plateau);
		paneDroit.getChildren().add(etatJeu);
		paneDroit.getChildren().add(labelJoueur);
		bpane.setLeft(root);
		bpane.setRight(paneDroit);
		primaryStage.setResizable(false);
		ComboBox<Joueur> typeJoueurJaune = new ComboBox<Joueur>();
		typeJoueurJaune.getItems().addAll(new JoueurHumain(Pion.JAUNE, jeu), new JoueurAuto(Pion.JAUNE, jeu),
				new JoueurAutoMinMax(Pion.JAUNE, jeu, false), new JoueurAutoMinMax(Pion.JAUNE, jeu, true));
		typeJoueurJaune.getSelectionModel().select(1);
		Label labelJaune = new Label("joueur jaune :");
		AnchorPane.setTopAnchor(typeJoueurJaune, 40.0);
		AnchorPane.setLeftAnchor(typeJoueurJaune, 90.0);
		AnchorPane.setTopAnchor(labelJaune, 45.0);
		AnchorPane.setLeftAnchor(labelJaune, 0.0);
		ComboBox<Joueur> typeJoueurRouge = new ComboBox<Joueur>();
		typeJoueurRouge.getItems().addAll(new JoueurHumain(Pion.ROUGE, jeu), new JoueurAuto(Pion.ROUGE, jeu),
				new JoueurAutoMinMax(Pion.ROUGE, jeu, false), new JoueurAutoMinMax(Pion.ROUGE, jeu, true));
		typeJoueurRouge.getSelectionModel().select(1);
		Label labelRouge = new Label("joueur rouge :");
		AnchorPane.setTopAnchor(typeJoueurRouge, 80.0);
		AnchorPane.setLeftAnchor(typeJoueurRouge, 90.0);
		AnchorPane.setTopAnchor(labelRouge, 85.0);
		ToggleGroup group = new ToggleGroup();
		RadioButton button1 = new RadioButton("Jaune");
		RadioButton button2 = new RadioButton("Rouge");
		Label labelCommence = new Label("Qui commence ?");
		AnchorPane.setTopAnchor(labelCommence, 120.0);
		AnchorPane.setLeftAnchor(labelCommence, 60.0);
		AnchorPane.setTopAnchor(button1, 150.0);
		AnchorPane.setLeftAnchor(button1, 30.0);
		AnchorPane.setTopAnchor(button2, 150.0);
		AnchorPane.setLeftAnchor(button2, 120.0);
		button1.setToggleGroup(group);
		button1.setSelected(true);
		button2.setToggleGroup(group);
		Button start = new Button("commencer la partie");
		AnchorPane.setTopAnchor(start, 180.0);
		AnchorPane.setLeftAnchor(start, 40.0);
		paneDroit.getChildren().addAll(typeJoueurJaune, labelJaune, labelRouge, typeJoueurRouge, labelCommence, button1,
				button2, start);
		start.setOnMouseClicked(e -> {
			if (group.getSelectedToggle() == button1) {
				joueur1 = typeJoueurJaune.getValue();
				
				joueur2 = typeJoueurRouge.getValue();
				
				labelJoueur.setTextFill(Color.GOLD);
			} else {
				joueur2 = typeJoueurJaune.getValue();
				joueur1 = typeJoueurRouge.getValue();
				labelJoueur.setTextFill(Color.RED);
			}
			joueurActuel = joueur1;
			joueur1.jouePremier= true;
			joueur2.jouePremier= false;
			etatJeu.setText("C'est au tour du joueur :");
			labelJoueur.setText("" + joueurActuel.getCouleur());

			joueur1.setAdversaire(joueur2);
			joueur2.setAdversaire(joueur1);
			statut = true;
			button1.setVisible(false);
			button2.setVisible(false);
			typeJoueurJaune.setVisible(false);
			typeJoueurRouge.setVisible(false);
			labelRouge.setVisible(false);
			labelJaune.setVisible(false);
			((Node) e.getSource()).setVisible(false);
			labelCommence.setText("");
			if (joueur1 instanceof JoueurAuto) {
				rectangles.get(((JoueurAuto) joueurActuel).choisirColonne())
						.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true,
								true, true, true, true, true, true, true, true, true, null));
			}

		});

		for (int j = 0; j < 7; j++) {

			RectangleAvecCercle colonne = new RectangleAvecCercle(j * 100, 0, 100, 600, j);
			rectangles.add(colonne);

			colonne.setFill(Color.TRANSPARENT);
			colonne.setStrokeType(StrokeType.INSIDE);
			colonne.setStrokeWidth(3);
			colonne.setOpacity(0);

			for (int i = 0; i < 6; i++) {

				Circle c = new Circle(5 + 45 + 100 * j, 5 + 45 + 100 * i, 45);
				c.setFill(Color.ANTIQUEWHITE);
				colonne.cercles.add(c);
				root.getChildren().add(c);

			}
			colonne.setOnMouseMoved(e -> {
				if (!statut) {
					return;
				}
				((Node) e.getSource()).setOpacity(1);

			});
			colonne.setOnMouseExited(e -> {
				if (!statut) {
					return;
				}
				((Node) e.getSource()).setOpacity(0);
			});
			colonne.setOnMouseClicked(e -> {
				if (!statut) {
					return;
				}
				int result = joueurActuel.Jouer(((RectangleAvecCercle) e.getSource()).indiceColonne);
				if (result >= 0) {
					colonne.cercles.get(result)
							.setFill(joueurActuel.getCouleur() == Pion.JAUNE ? Color.YELLOW : Color.RED);

					this.joueurActuel = joueurActuel.getAdversaire();
					rectangles.forEach(rec -> {
						rec.setStroke(joueurActuel.getCouleur() == Pion.JAUNE ? Color.YELLOW : Color.RED);
					});
					if (jeu.getVainqueur() != Pion.VIDE) {
						if (jeu.getVainqueur() == Pion.EGALITE) {
							etatJeu.setText("Egalité entre les deux joueurs");
							labelJoueur.setTextFill(Color.TRANSPARENT);
						} else {
							etatJeu.setText("Victoire du joueur : ");
							etatJeu.setPadding(new Insets(275, 20, 0, 0));
						}
					} else {
						labelJoueur.setTextFill(joueurActuel.getCouleur() == Pion.JAUNE ? Color.GOLD : Color.RED);
						labelJoueur.setText("" + (this.joueurActuel.getCouleur() == Pion.JAUNE ? "Jaune" : "Rouge"));
						if (joueur1 instanceof JoueurAuto && joueur2 instanceof JoueurAuto) {
							// Si les 2 joueurs sont des ia on met une pause à chaque coup pour éviter que l'interface graphique se bloque
							PauseTransition pause = new PauseTransition(Duration.millis(500));
							pause.setOnFinished(a -> {
								rectangles.get(((JoueurAuto) joueurActuel).choisirColonne())
										.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0,
												MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true,
												true, true, null));
							});
							pause.play();

						} else if (joueurActuel instanceof JoueurAuto) {
							rectangles.get(((JoueurAuto) joueurActuel).choisirColonne())
									.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY,
											1, true, true, true, true, true, true, true, true, true, true, null));
						}

					}

				}

			});
			root.getChildren().add(colonne);

		}

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
}
