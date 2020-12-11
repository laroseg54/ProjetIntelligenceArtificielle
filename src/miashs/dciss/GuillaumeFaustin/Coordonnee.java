package miashs.dciss.GuillaumeFaustin;

public class Coordonnee {
	
	private int x;
	private int y;
	
	public Coordonnee(int x, int y) {
		if(x<0||x>5||y<0||y>6) {
			throw new IllegalArgumentException("coordonnee invalide. x doit être compris entre 0 et 5 , y entre entre 0 et 6");
		}
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
