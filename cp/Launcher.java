package cp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Launcher {

	public static int exitWhile = 0;
	
	public static int userInput() {
		int userInput = 0;
		String uInput = null;
		//
		System.out.println("\nBitte z-Wert eingeben. x beendet das Programm: ");
		//
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			uInput = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		if (uInput.equals("x") || uInput.equals("X")) {
			//System.exit(0);
			exitWhile = 1;
		} else {
			userInput = Integer.parseInt(uInput);
		}
		//
		return userInput;
	}
	
	public static void main(String[] args) {
		//
		CastorschePaar casPaar = new CastorschePaar();
		//
		while (exitWhile != 1) {
			int z = userInput();
			//
			int n = (int) casPaar.n(z);
			int x = (int) casPaar.e(z);
			int y = (int) casPaar.f(z);	
			int intV = (int) casPaar.inverseZ(z);
			int calcX = (int) casPaar.calcXvalue(x, y);
			int calcY = (int) casPaar.calcYvalue(x, y);
			int calcN = (int) casPaar.calcNvalue(z);
			//
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Castorsche Paar:");
			System.out.println("Gegebene Zahl: " + z);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Berechneter n - Wert von z: \t" + n);
			System.out.println("........................................");
			System.out.println("Berechneter x - Wert von z: \t" + x);
			System.out.println("Berechneter y - Wert von z: \t" + y);
			System.out.println("........................................");
			System.out.println("Berechnete Inverse von z: \t" + intV);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Test x - Wert: \t" + casPaar.checkXvalue(x, calcX));
			System.out.println("Test y - Wert: \t" + casPaar.checkYvalue(y, calcY));
			System.out.println("Test z - Wert: \t" + casPaar.checkNvalue(n, calcN));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
}