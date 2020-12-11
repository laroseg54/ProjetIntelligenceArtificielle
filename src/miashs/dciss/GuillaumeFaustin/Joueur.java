package miashs.dciss.GuillaumeFaustin;

public abstract class Joueur {

	protected Pion couleur;
	protected Jeu jeu;
	private Joueur adversaire;
	public boolean jouePremier;

	public Joueur(Pion couleur, Jeu jeu) {

		this.couleur = couleur;
		this.jeu = jeu;

	}

	public Pion getCouleur() {
		return couleur;
	}

	public void setCouleur(Pion couleur) {
		this.couleur = couleur;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public Joueur getAdversaire() {
		return adversaire;
	}

	public void setAdversaire(Joueur adversaire) {
		this.adversaire = adversaire;
	}

	public int Jouer(int colonne) {
		int ligne = 0;
		while (ligne < 6 && jeu.getGrille()[ligne][colonne] == Pion.VIDE) {
			ligne++;
		}
		Coordonnee cor = null;
		try {
		 cor = new Coordonnee(ligne - 1, colonne);
		}
		catch(IllegalArgumentException e) {
			return -1;
		}
		if (jeu.placerPion(couleur, cor)) {
			return ligne - 1;
		}
		return -1;
	}

	

	
	
}
