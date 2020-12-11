package miashs.dciss.GuillaumeFaustin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ArbreMinMax {

	private Noeud racine;
	private int profondeur;
	private boolean alphaBeta;
	public int poidsAdversaire;

	public ArbreMinMax(int profondeur, Jeu jeu, Pion couleurJoueur,boolean alphaBeta, int poidsAdversaire) {
		this.poidsAdversaire = poidsAdversaire;
		this.profondeur = profondeur;
		this.racine = new Noeud();
		this.alphaBeta = alphaBeta;
		if(alphaBeta) {
			racine.AjouterEnfants(jeu, couleurJoueur, profondeur,-100000000,100000000,poidsAdversaire);
		}
		else {
			racine.AjouterEnfants(jeu, couleurJoueur, profondeur,poidsAdversaire);

		}
		
	}
	


	public Coordonnee MeilleurChoix() {

		for (Noeud enfant : racine.enfants) {
			if (enfant.benefice == racine.benefice) {
				return enfant.coordonnee;
			}
		}
		return null;
	}

	public static class Noeud {

		private LinkedList<Noeud> enfants;
		private Coordonnee coordonnee;
		private int benefice;

		public Noeud(Coordonnee coordonnee) {
			this.coordonnee = coordonnee;
			this.enfants = new LinkedList<Noeud>();
		}

		public Noeud() {
			this.enfants = new LinkedList<Noeud>();
		}

		public int AjouterEnfants(Jeu jeu, Pion couleurJoueur, int profondeur,int poidsAdversaire) {

			if (jeu.getVainqueur() != Pion.VIDE || profondeur == 0) {

				benefice = heuristique.calculerBeneficeGrille(jeu, couleurJoueur,poidsAdversaire);
				enfants = null;

				return benefice;
			}

			else {
				ArrayList<Integer> benefices = new ArrayList<Integer>();
				for (int j = 0; j < 7; j++) {
					int ligne = Jeu.choisirCase(jeu.getGrille(), j);
					if (ligne != -1) {
						Pion couleur = profondeur % 2 == 1 ? couleurJoueur
								: couleurJoueur == Pion.JAUNE ? Pion.ROUGE : Pion.JAUNE;
						Coordonnee cor = new Coordonnee(ligne, j);
						jeu.placerPion(couleur, cor);
						Noeud enfant = new Noeud(cor);
						int benefEnfant = enfant.AjouterEnfants(jeu, couleurJoueur, profondeur - 1,poidsAdversaire);
						benefices.add(benefEnfant);
						enfants.add(enfant);

						jeu.getGrille()[ligne][j] = Pion.VIDE;
						jeu.nbrCasesLibres++;
						jeu.setVainqueur(Pion.VIDE);
						if(benefEnfant==10000 && profondeur % 2 == 1 || benefEnfant==-10000 && profondeur % 2 == 0 ) {
							// Si on est un noeud max et qu'on trouve 10 000 cela veut dire qu'on a une position gagnante et 
							// donc on peut s'arrêter de chercher et l'inverse pour si on est un noeud  min
							break;
						}
						
					}

				}
				this.benefice = MinMax(benefices, profondeur);

				return benefice;
			}
		}

		private int MinMax(ArrayList<Integer> benefices, int profondeur) {
			// System.out.println(benefices);
			if (profondeur % 2 == 1) {
				return Collections.max(benefices);
			} else {
				return Collections.min(benefices);
			}

		}

		public int AjouterEnfants(Jeu jeu, Pion couleurJoueur, int profondeur, int Alpha, int Beta, int poidsAdversaire) {

			if (jeu.getVainqueur() != Pion.VIDE || profondeur == 0) {

				benefice = heuristique.calculerBeneficeGrille(jeu, couleurJoueur, poidsAdversaire);
				enfants = null;

				return benefice;
			}
			boolean coupure = false;
			int benefEnfant = profondeur % 2 == 1 ? -100000000 : 100000000;
			for (int j = 0; j < 7 && !coupure; j++) {
				int ligne = Jeu.choisirCase(jeu.getGrille(), j);
				if (ligne != -1) {
					Pion couleur = profondeur % 2 == 1 ? couleurJoueur
							: couleurJoueur == Pion.JAUNE ? Pion.ROUGE : Pion.JAUNE;
					Coordonnee cor = new Coordonnee(ligne, j);
					jeu.placerPion(couleur, cor);
					Noeud enfant = new Noeud(cor);
					if (profondeur % 2 == 1) {

						benefEnfant = Math.max(benefEnfant,
								enfant.AjouterEnfants(jeu, couleurJoueur, profondeur - 1, Alpha, Beta,poidsAdversaire));

						if (benefEnfant >= Beta) {
							coupure = true;
						} else {
							Alpha = Math.max(Alpha, benefEnfant);
						}
					} else {
						benefEnfant = Math.min(benefEnfant,
								enfant.AjouterEnfants(jeu, couleurJoueur, profondeur - 1, Alpha, Beta,poidsAdversaire));
						if (benefEnfant <= Alpha) {
							coupure = true;
						} else {
							Beta = Math.min(Beta, benefEnfant);
						}

					}
					
					enfants.add(enfant);
					jeu.getGrille()[ligne][j] = Pion.VIDE;
					jeu.nbrCasesLibres++;
					jeu.setVainqueur(Pion.VIDE);
					if(benefEnfant==10000 && profondeur % 2 == 1 || benefEnfant==-10000 && profondeur % 2 == 0 ) {
						// Si on est un noeud max et qu'on trouve 10 000 cela veut dire qu'on a une position gagnante et 
						// donc on peut s'arrêter de chercher et l'inverse pour si on est un noeud  min
						break;
					}
				}

			}
			this.benefice = benefEnfant;
			return benefice;
		}

	}

}
