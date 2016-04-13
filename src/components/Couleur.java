package components;

import java.awt.Color;

final class Couleur {
	private static final int size = 4;

	public static final Color getCouleur(int couleur) {
		Color color = Color.white;
		switch (couleur) {
		case 0: {
			color = new Color(255, 128, 128);
			break;
		}
		case 1: {
			color = new Color(128, 255, 128);
			break;
		}
		case 2: {
			color = new Color(128, 128, 255);
			break;
		}
		case 3: {
			color = new Color(191, 191, 191);
		}
		}
		return color;
	}

	public static final String getNomCouleur(int couleur) {
		String nom = "";
		switch (couleur) {
		case 0: {
			nom = "Rouge clair";
			break;
		}
		case 1: {
			nom = "Vert clair";
			break;
		}
		case 2: {
			nom = "Bleu clair";
			break;
		}
		case 3: {
			nom = "Gris clair";
		}
		}
		return nom;
	}

	public static final int getSize() {
		return 4;
	}

	Couleur() {
	}
}
