package miashs.dciss.GuillaumeFaustin;

public class JoueurAutoMinMax2 extends JoueurAuto {
	private boolean alphaBeta;
	public JoueurAutoMinMax2(Pion couleur, Jeu jeu,boolean alphaBeta) {
		super(couleur, jeu);
		this.alphaBeta = alphaBeta;
		this.poidsAversaire = 10;
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public int choisirColonne() {
		long startTime = System.nanoTime();
	
		Jeu jeuFuture = new Jeu(this.jeu);
		ArbreMinMax2  minMax;
		//important , ne choisir que des valeurs impairs pour la profondeur de l'arbre 
		//sinon l'ia calculera les bénéfices du joueur adverse au lieu des siens 
		if(alphaBeta) {
			minMax = new ArbreMinMax2(9, jeuFuture, this.couleur,true,this.poidsAversaire);
			long stopTime = System.nanoTime();
			System.out.println("Joueur Auto MinMax2 AlphaBeta "+(stopTime - startTime));
		}
		else {
			minMax = new ArbreMinMax2(7, jeuFuture, this.couleur,false,this.poidsAversaire);
			long stopTime = System.nanoTime();
			System.out.println("Joueur Auto MinMax2 "+(stopTime - startTime));
		}
		
		return minMax.MeilleurChoix().getY();
	}
	
	public String toString() {
		if(alphaBeta) {
			
				return "Auto2 Alpha Beta";
			
		}
		else {
			return "Auto2 Min Max";
		}
	}
}
	