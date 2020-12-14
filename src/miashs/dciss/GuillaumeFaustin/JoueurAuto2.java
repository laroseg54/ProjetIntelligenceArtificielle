package miashs.dciss.GuillaumeFaustin;

public class JoueurAuto2 extends JoueurAuto {

	public JoueurAuto2(Pion couleur, Jeu jeu) {
		super(couleur, jeu);
		// TODO Auto-generated constructor stub
	}
	@Override
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
				benef = heuristique2.heuristic(jeuFuture,this.couleur);
				// System.out.println(benef);
				if (benefMax == null || benefMax <= benef) {
					benefMax = benef;
					colonneChoisie = colonne;
				}

				jeuFuture.getGrille()[ligne][colonne] = Pion.VIDE;
			}

		}
		long stopTime = System.nanoTime();
		System.out.println("Joueur Auto2 simple "+(stopTime - startTime));
		return colonneChoisie;

	}

	public String toString() {
		return "Joueur Auto2";
	}
}
