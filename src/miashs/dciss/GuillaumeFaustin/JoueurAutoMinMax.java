package miashs.dciss.GuillaumeFaustin;

public class JoueurAutoMinMax extends JoueurAuto {
	private boolean alphaBeta;
	public JoueurAutoMinMax(Pion couleur, Jeu jeu,boolean alphaBeta) {
		super(couleur, jeu);
		this.alphaBeta = alphaBeta;
		this.poidsAversaire = 10;
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public int choisirColonne() {
		long startTime = System.nanoTime();
	
		Jeu jeuFuture = new Jeu(this.jeu);
		ArbreMinMax  minMax;
		//important , ne choisir que des valeurs impairs pour la profondeur de l'arbre 
		//sinon l'ia calculera les bénéfices du joueur adverse au lieu des siens 
		if(alphaBeta) {
			minMax = new ArbreMinMax(7, jeuFuture, this.couleur,true,this.poidsAversaire);
			long stopTime = System.nanoTime();
			System.out.println("Joueur Auto MinMax AlphaBeta "+(stopTime - startTime));
		}
		else {
			minMax = new ArbreMinMax(7, jeuFuture, this.couleur,false,this.poidsAversaire);
			long stopTime = System.nanoTime();
			System.out.println("Joueur Auto MinMax "+(stopTime - startTime));
		}
		
		return minMax.MeilleurChoix().getY();
	}
	
	public String toString() {
		if(alphaBeta) {
			
				return "Joueur Alpha Beta";
			
		}
		else {
			return "Joueur Min Max";
		}
	}
	
	
	
	
	
	
	
	
	
	

}
