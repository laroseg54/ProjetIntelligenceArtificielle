package miashs.dciss.GuillaumeFaustin;

abstract public class heuristique2 {
	public static int grilleBenef[][] = {
			
			{3,4,5,7,5,4,3},
			{4,6,8,10,8,6,4},
			{5,8,11,13,11,8,5},
			{5,8,11,13,11,8,5},
			{4,6,8,10,8,6,4},
			{3,4,5,7,5,4,3},
			
			};
			
			
			
	public static int heuristic (Jeu game,Pion couleurJoueur) {
		int benef = 0;
		if(game.getVainqueur()==couleurJoueur) {
			return 1000;
		}
		if(game.getVainqueur()==(couleurJoueur==Pion.JAUNE?Pion.ROUGE:Pion.JAUNE)) {
			return -1000;
		}
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 7; j++) {
				
				if (game.getGrille()[i][j] == couleurJoueur) {
					benef += grilleBenef[i][j];
				}
				else if(game.getGrille()[i][j] == (couleurJoueur==Pion.JAUNE?Pion.ROUGE:Pion.JAUNE)) {
					benef -= grilleBenef[i][j];
				}
			}
		}
		return benef;
	}
}
