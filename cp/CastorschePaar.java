package cp;

public class CastorschePaar {
	
	/**
	 * c: N² --> N bijektiv: c(x,y) = ½ (x+y-1) (x+y-2) + x
	 */
	private double c (int x, int y) {
		return ((x + y - 1) * (x + y - 2)) / 2 + x;
	}
	
	/**
	 * c: N0² --> N bijektiv: z= c(x,y) = ½ (x+y+1) (x+y) + x
	 */
	public double z (int x, int y) {
		return ((x + y + 1) * (x + y)) / 2 + x;
	}
	
	/**
	 * INVERSE
	 */
	/**
	 * x = e (z) = z - invZ(z)
	 */
	public double e (int z) {
		return (z - inverseZ(z));
	}
	
	/**
	 * y = f(z) = n(z) - z + invZ(z)
	 */
	public double f (int z) {
		return n(z) - z + inverseZ(z) + 2;
	}
	
	/**
	 * n(z) = Floor[-0,5 + SQRT(0,25 + 2 * z)]
	 */
	public double n (int z) {
		return Math.floor(-0.5 + (Math.sqrt((0.25 + 2 * z))));
	}
	
	/**
	 * invZ(z) = 0,5 * n(z) * (n(z) + 1)
	 */
	public double inverseZ (int z) {
		return 0.5 * n(z) * (n(z) + 1);
	}
	
	/**
	 * x = e(c(x,y))
	 */
	public double calcXvalue (int x, int y) {
		double sol = 0;
		int tmpZ = 0;
		//
		tmpZ = (int) c(x, y);
		sol = e(tmpZ);
		//
		return sol;
	}
	
	/**
	 * y = f(c(x,y))
	 */
	public double calcYvalue (int x, int y) {
		double sol = 0;
		int tmpZ = 0;
		//
		tmpZ = (int) c(x, y);
		sol = f(tmpZ);
		//
		return sol;
	}
	
	/**
	 * n = c(e(n),f(n))
	 */
	public double calcNvalue (int z) {
		double sol = 0;
		int tmpN = 0;
		int tmpE = 0;
		int tmpF = 0;
		//
		tmpN = (int) n(z);
		tmpE = (int) e(tmpN);
		tmpF = (int) f(tmpN);
		//
		sol = c(tmpE, tmpF);
		//
		return sol;
	}
	
	public boolean checkXvalue(int xEingabe, int xBerechnet) {
		return ((xEingabe == xBerechnet) ? true : false);
	}
	
	public boolean checkYvalue(int yEingabe, int yBerechnet) {
		return ((yEingabe == yBerechnet) ? true : false);
	}
	
	public boolean checkNvalue(int zEingabe, int zBerechnet) {
		return ((zEingabe == zBerechnet) ? true : false);
	}
} 