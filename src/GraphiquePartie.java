import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *  Classe : GraphiquePartie
 *  @author : Malik Makkes
 *  date 	: 04/05/2022
 *  Explication : On configure la taille de la fenêtre de jeu
 */
public class GraphiquePartie extends JFrame
{
	/**
	 * Définition du serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Les éléments pour l'affichage graphique
	 */
	private final Main   fenetreEcran; // L'attribut renseigne sur la taille de l'écran
	private final Menu   menu; 		   // L'attribut renseigne sur le PanelMenu.
	private final Partie partie;	   // L'attribut renseigne sur le PanelPlateau.

	/**
	 * Constructeur GraphiquePartie
	 * @param fenetreEcran 	: La taille de l'écran du joueur.
	 * @param largeurEcran 	: La largeur de l'écran de l'utilisateur.
	 * @param hauteurEcran 	: La hauteur de l'écran de l'utilisateur.
	 */
	public GraphiquePartie(Main fenetreEcran, int largeurEcran, int hauteurEcran)
	{
		this.fenetreEcran = fenetreEcran;
		this.setTitle("Partie du jeu de Puissance 4");
		double coeffLargeur = (largeurEcran / (double) 700);
		double coeffHauteur = (hauteurEcran / (double) 600);

		int largeurFrameJeu = (int) (largeurEcran / coeffLargeur);
		int hauteurFrameJeu = (int) (hauteurEcran / coeffHauteur);
		
		this.setSize(largeurFrameJeu, hauteurFrameJeu); 
		this.setLocation( (largeurEcran/2)-(this.getWidth()/2), (hauteurEcran/2)-(this.getHeight()/2) );
		this.setLayout( new BorderLayout() );
		this.menu = new Menu(fenetreEcran);
		this.partie = new Partie(fenetreEcran);
		this.add(menu, 	BorderLayout.NORTH);
		this.add(partie, 	BorderLayout.CENTER);
		this.addWindowListener( new FermetureFenetre() );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Met à jour la frame plateau.
	 */
	public void majGraphique()
	{
		this.partie.majGraphique();
	}

	/**
	 * Fixe le mode sombre.
	 */
	public void setModeSombre()
	{
		this.menu.setModeSombre();
		this.partie.setModeSombre();
	}

	/**
	 * Fixe le mode clair.
	 */
	public void setModeClair()
	{
		this.menu.setModeClair();
		this.partie.setModeClair();
	}

	/**
	 * Ferme la fenêtre de la partie.
	 */
	public void fermerPartie()
	{
		this.menu.fermerMenu();
		this.partie.fermerFenetrePartie();
		this.dispose();
	}

	/**
	 * Fermeture de la fenêtre graphique.
	 */
	private class FermetureFenetre extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			GraphiquePartie.this.fenetreEcran.fermerJeu();
		}
	}
}