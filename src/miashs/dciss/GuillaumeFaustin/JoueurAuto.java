package miashs.dciss.GuillaumeFaustin;

import java.util.Arrays;

public class JoueurAuto extends Joueur {
	public int poidsAversaire = 15;
	

	public JoueurAuto(Pion couleur, Jeu jeu ) {
		super(couleur, jeu);
		

	}

	public int choisirColonne() {
		
		long startTime = System.nanoTime();
		Jeu jeuFuture = new Jeu(this.jeu);
		int colonneChoisie = -1;
		Integer benefMax = null;
		int benef;
		for (int colonne = 0; colonne < 7; colonne++) {
			int ligne = Jeu.choisirCase(jeuFuture.getGrille(), colonne);

			if (ligne > -1) {
				jeuFuture.placerPion(this.couleur, new Coordonnee(ligne, colonne));
				if (jeuFuture.getVainqueur() == this.couleur) {
					return colonne;
				}
				benef = heuristique.calculerBeneficeGrille(jeuFuture, this.couleur,this.poidsAversaire);

				// System.out.println(benef);
				if (benefMax == null || benefMax <= benef) {
					benefMax = benef;
					colonneChoisie = colonne;
				}

				jeuFuture.getGrille()[ligne][colonne] = Pion.VIDE;
			}

		}
		long stopTime = System.nanoTime();
		System.out.println("Joueur Auto simple "+(stopTime - startTime));
		return colonneChoisie;

	}

	public String toString() {
		return "Joueur Auto";
	}

}
