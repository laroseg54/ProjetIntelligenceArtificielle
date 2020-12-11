package miashs.dciss.GuillaumeFaustin;

import java.util.Arrays;

public class Jeu {

	private Pion[][] grille;
	private Pion vainqueur;
	public int nbrCasesLibres;

	public Jeu() {

		grille = new Pion[6][7];
		for (Pion[] pions : grille) {
			Arrays.fill(pions, Pion.VIDE);
		}
		this.vainqueur = Pion.VIDE;
		nbrCasesLibres = 42;

	}

	public Jeu(Jeu jeu) {
		this.grille = Arrays.stream(jeu.getGrille()).map(Pion[]::clone).toArray(Pion[][]::new);
		this.vainqueur = jeu.vainqueur;
		nbrCasesLibres = jeu.nbrCasesLibres;
	}

	public Pion[][] getGrille() {
		return grille;
	}

	public Pion getVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(Pion vainqueur) {
		this.vainqueur = vainqueur;
	}

	// methode qui permet de faire tomber le pion dans la grille , retourne -1 si la
	// grille est pleine
	public static int choisirCase(Pion[][] grille, int colonne) {
		int ligne = 0;
		while (ligne < 6 && grille[ligne][colonne] == Pion.VIDE) {
			ligne++;
		}
		Coordonnee cor = null;
		try {
			cor = new Coordonnee(ligne - 1, colonne);
		} catch (IllegalArgumentException e) {
			return -1;
		}
		return ligne - 1;
	}

	public boolean placerPion(Pion pion, Coordonnee coordonnee) {
		if (vainqueur != Pion.VIDE) {
			return false;
		}
		if (this.grille[coordonnee.getX()][coordonnee.getY()] == pion.VIDE) {
			// si cette condition est remplie , on peut placer le pion 
			this.grille[coordonnee.getX()][coordonnee.getY()] = pion;
			nbrCasesLibres--;
			// System.out.println(this);
			if (verifierGagnant(coordonnee)) {
				vainqueur = pion;
				// aprés avoir placé le pion on vérifie si une des 2 joueurs a gagné ...
			}
			if (nbrCasesLibres == 0) {
				vainqueur = Pion.EGALITE;
				// ou bien si on est arrivée sur une partie nulle.
			}
			return true;
		}
		return false;
	}

	public boolean verifierGagnant(Coordonnee coordonnee) {
		
		//on cherche dans les 3 cases autour du pion dans tout les directions s'il y a un alignement 
		int i = coordonnee.getX();
		int j = coordonnee.getY();
		int alignementVerticale = 1 + compterAlignementVerticale(i, j);
		int alignementHorizontale = 1 + compterAlignementDroit(i, j) + compterAlignementGauche(i, j);
		int alignementDiagonaleDroite = 1 + compterAlignementDiagonaleDroiteHaut(i, j)
				+ compterAlignementDiagonaleDroiteBas(i, j);
		int alignementDiagonaleGauche = 1 + compterAlignementDiagonaleGaucheHaut(i, j)
				+ compterAlignementDiagonaleGaucheBas(i, j);

		return (alignementVerticale >= 4 || alignementHorizontale >= 4 || alignementDiagonaleDroite >= 4
				|| alignementDiagonaleGauche >= 4);

	}

	private int compterAlignementVerticale(int i, int j) {

		if (i + 1 < 6 && grille[i + 1][j] == grille[i][j]) {
			return 1 + compterAlignementVerticale(i + 1, j);
		} else
			return 0;

	}

	private int compterAlignementDroit(int i, int j) {

		if (j + 1 < 7 && grille[i][j + 1] == grille[i][j]) {
			return 1 + compterAlignementDroit(i, j + 1);
		} else
			return 0;

	}

	private int compterAlignementGauche(int i, int j) {

		if (j - 1 >= 0 && grille[i][j - 1] == grille[i][j]) {

			return 1 + compterAlignementGauche(i, j - 1);
		} else
			return 0;

	}

	private int compterAlignementDiagonaleDroiteBas(int i, int j) {

		if (j + 1 < 7 && i + 1 < 6 && grille[i + 1][j + 1] == grille[i][j]) {
			return 1 + compterAlignementDiagonaleDroiteBas(i + 1, j + 1);
		} else
			return 0;

	}

	private int compterAlignementDiagonaleDroiteHaut(int i, int j) {

		if (j - 1 >= 0 && i - 1 >= 0 && grille[i - 1][j - 1] == grille[i][j]) {
			return 1 + compterAlignementDiagonaleDroiteHaut(i - 1, j - 1);
		} else
			return 0;

	}

	private int compterAlignementDiagonaleGaucheBas(int i, int j) {

		if (j - 1 >= 0 && i + 1 < 6 && grille[i + 1][j - 1] == grille[i][j]) {

			return 1 + compterAlignementDiagonaleGaucheBas(i + 1, j - 1);
		} else
			return 0;

	}

	private int compterAlignementDiagonaleGaucheHaut(int i, int j) {

		if (j + 1 < 7 && i - 1 >= 0 && grille[i - 1][j + 1] == grille[i][j]) {

			return 1 + compterAlignementDiagonaleGaucheHaut(i - 1, j + 1);
		} else
			return 0;

	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		for (Pion[] pions : grille) {
			for (Pion pion : pions) {
				s.append(pion + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

}
