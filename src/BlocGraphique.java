import java.awt.Color;
import java.awt.Graphics;

public class BlocGraphique {
	/** Abscisse en colonne. */
	private int posX;
	/** Ordonnee en ligne. */
	private int posY;
	/** Couleur du bloc graphique. */
	private Color couleur; 
/**
 * Constructeur du bloc graphique.
 * On donne sa couleur et sa position dans la matrice.
 * @param couleur Couleur de remplissage du bloc graphique.
 * @param posX Position en X du bloc graphique
 * @param posY Position en Y du bloc graphique
 */
public BlocGraphique(Color couleur, int posX, int posY) {
	this.couleur = couleur;
	this.posX = posX;
	this.posY = posY;
}
/**
 * Dessine le bloc graphique.
 * @param g Graphics d'affichage.
 */
 
public void draw(Graphics g) {
	g.setColor(couleur);
	g.fill3DRect(posX*24+1, posY*24+1, 24, 24, true);
}
/**
 * Retourne la position X du bloc dans la matrice.
 * @return L'abscisse du bloc. */
 
public int getPosX() {
	return posX;
}
/**
 * Retourne la position en Y du bloc dans la matrice.
 * @return L'ordonnee du bloc.  */
public int getPosY() {
	return posY;
}
/**
 * Positionne en X l'affichage du bloc dans la matrice.
 * @param posX L'abscisse du bloc.
 */
public void setPosX(int posX) {
	this.posX = posX;
}
/**
 * Positionne en Y l'affichage du bloc dans la matrice.
 * @param posY L'ordonnee du bloc. 
 */
public void setPosY(int posY) {
	this.posY = posY;
}
}