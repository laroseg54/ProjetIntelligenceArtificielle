package miashs.dciss.GuillaumeFaustin;

abstract public class heuristique {

	

	//cette methode va évaluer tout les alignements possibles dans la grille
	public static int calculerBeneficeGrille(Jeu jeu, Pion couleurJoueur, int poidsAdversaire) {
		Pion vainqueur = jeu.getVainqueur();
		if (vainqueur != Pion.VIDE) {
			return  (int) (vainqueur == couleurJoueur ? 10000 : vainqueur == Pion.EGALITE ? 0 : -(Math.pow(poidsAdversaire, 4)));
		}
		
		int benef = 0;
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 7; j++) {
				benef += calculerBeneficeCoup(jeu.getGrille(), i, j, couleurJoueur,poidsAdversaire);
			}
		}
		return benef;
	}
	//methode qui évalue le score de tous les alignements qui partent de la case de coordonnée i,j
	private static int calculerBeneficeCoup(Pion[][] grille, int i, int j, Pion couleurJoueur,int poidsAdversaire) {

		int benefice = 0;
		if (i <= 2) {
			benefice += BenefAlignement(grille, i, j, i + 4, j, couleurJoueur,poidsAdversaire);
		}
		if (j <= 3) {
			benefice += BenefAlignement(grille, i, j, i, j + 4, couleurJoueur,poidsAdversaire);
		}
		if (i <= 2 && j <= 3) {
			benefice += BenefAlignement(grille, i, j, i + 4, j + 4, couleurJoueur,poidsAdversaire);
		}
		if (i > 2 && j > 2) {
			benefice += BenefAlignement(grille, i - 3, j, i + 1, j - 4, couleurJoueur,poidsAdversaire);
		}
		return benefice;

	}
//	méthode qui calcule le score pour un alignement en particulier, le score sera positif si l'alignement ne comporte que des 
//	pions du joueur,neutre si au moins un pion de chaque couleur se trouvent dans l'alignement ou si aucun pion ne se trouve 
//	dans l'alignement et négatif si l'alignement ne comporte que des pions de l'adversaire
	private static int BenefAlignement(Pion[][] grille, int ligneDep, int colonneDep, int ligneFin, int colonneFin,
			Pion couleurJoueur, int poidsAdversaire) {
		Pion pionAdversaire = couleurJoueur == Pion.JAUNE ? Pion.ROUGE : Pion.JAUNE;
		// System.out.println(pionAdversaire);
		int nbPionJoueur = 0;
		int nbPionAdversaire = 0;
		int i = ligneDep;
		int j = colonneDep;
		while (i < ligneFin || j < colonneFin) {

			if (grille[i][j] == couleurJoueur) {
				nbPionJoueur++;
			}
			if (grille[i][j] == pionAdversaire) {
				nbPionAdversaire++;
			}
			if (nbPionJoueur > 0 && nbPionAdversaire > 0) {
				// System.out.println(nbPionJoueur);
				return 0;
			}
			if (ligneDep != ligneFin) {
				i++;
				// System.out.println(i);
			}
			if (colonneDep != colonneFin) {
				j += colonneFin < colonneDep ? -1 : 1;

			}
		}
		if (nbPionJoueur == 0 && nbPionAdversaire == 0) {
			return 0;
		}
		return (int) (nbPionJoueur > 0 ? (Math.pow(10, nbPionJoueur)) : -(Math.pow(poidsAdversaire, nbPionAdversaire)));

	}

}
