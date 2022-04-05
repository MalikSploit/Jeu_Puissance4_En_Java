import javax.swing.*;
import java.awt.*;

/**
 *  Classe : GraphiqueAccueil
 *  @author : Malik Makkes et Anis Khalfallah
 *  date 	: 04/05/2022
 *  Explication : On configure la taille et le titre de la fenêtre d'accueil du Jeu
 */
public class GraphiqueAccueil extends JFrame
{
	/**
	 * Définition du serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * L'élément pour l'affichage graphique
	 */
	private final Accueil accueil; // L'attribut renseigne sur le Panel d'Accueil.

	/**
	 * Constructeur GraphiqueAccueil
	 * @param ctrl : Le contrôleur.
	 * @param largeurEcran : La largeur de l'écran de l'utilisateur.
	 * @param hauteurEcran : La hauteur de l'écran de l'utilisateur.
	 */
	public GraphiqueAccueil(Main ctrl, int largeurEcran, int hauteurEcran)
	{
		this.setTitle("Accueil - Jeu de Puissance 4");

		double coeffLargeur = (largeurEcran / (double) 850);
		double coeffHauteur = (hauteurEcran / (double) 650);
		int largeurFrameAccueil = (int) (largeurEcran / coeffLargeur);
		int hauteurFrameJoueur = (int) (hauteurEcran / coeffHauteur);

		this.setSize(largeurFrameAccueil,hauteurFrameJoueur);
		this.setLocation((largeurEcran/2)-(this.getWidth()/2),(hauteurEcran/2)-(this.getHeight()/2));
		this.setLayout( new BorderLayout() );
		this.accueil = new Accueil(ctrl);
		this.add(accueil);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * fermerAccueil : Ferme la fenêtre d'Accueil.
	 */
	public void fermerAccueil()
	{
		this.accueil.fermerAccueil();
		this.removeAll();
		this.setVisible(false);
		this.dispose();
	}
}