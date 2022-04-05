import java.awt.*;
import java.util.Stack;

 /**
 *  Classe : Main
 *  @author : Malik Makkes et Anis Khalfallah
 *  date 	: 04/05/2022
 *  Explication : On exécute le jeu pour y jouer
 */
public class Main
{
	/**
	 * Les éléments du jeu
	 */
	private Grille puissance4;
	private GraphiqueAccueil  accueil;
	private GraphiquePartie   partie;

	 /**
	 *  À l'instanciation du Main, une fenêtre d'accueil du jeu apparait avec le nom des joueurs à saisir.
	 */
	public Main()
	{
		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;
		dimEcran     = Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		this.accueil = new GraphiqueAccueil(this, largeurEcran, hauteurEcran);
	}

	/**
	 * lancerJeu : Lance une partie du jeu Puissance 4
	 * @param pseudoJoueur1 : Le pseudo du joueur 1.
	 * @param pseudoJoueur2 : Le pseudo du joueur 2.
	 */
	public void lancerJeu(String pseudoJoueur1, String pseudoJoueur2)
	{
		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;

		dimEcran     = Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		if ( this.puissance4 == null )
		{
			this.puissance4 = new Grille();
		}

		puissance4.initGrille();

		this.partie = new GraphiquePartie(this, largeurEcran, hauteurEcran);

		for(int cptJoueur=0; cptJoueur<this.getNbJoueur(); cptJoueur++)
		{
			if ( (cptJoueur % 2) == 0 )  
			{
				this.getJoueur(cptJoueur).setPseudo(pseudoJoueur1);
			}
			else
			{
				this.getJoueur(cptJoueur).setPseudo(pseudoJoueur2);
			}
		}

		// Mis à jour graphique pour le pseudo du joueur
		this.majGraphique();
	}

	/**
	 * relancerJeu : Relance une partie de jeu
	 * @param joueur1 	: Le joueur 1.
	 * @param joueur2 	: Le joueur 2.
	 */
	public void relancerJeu(Joueur joueur1, Joueur joueur2)
	{
		Toolkit.getDefaultToolkit().getScreenSize();
		for(int cptJoueur=0; cptJoueur<this.getNbJoueur(); cptJoueur++)
		{
			if ( (cptJoueur % 2) == 0 )
			{
				this.getJoueur(cptJoueur).setPseudo(joueur1.getPseudoJoueur() 	);
				this.getJoueur(cptJoueur).setCouleur(joueur1.getCouleur() 		);
				this.getJoueur(cptJoueur).setNumJoueur(joueur1.getNumJoueur() 	);
				this.getJoueur(cptJoueur).setActifJoueur(joueur1.getActif() 	);
			}
			else
			{
				this.getJoueur(cptJoueur).setPseudo(joueur2.getPseudoJoueur() 	);
				this.getJoueur(cptJoueur).setCouleur(joueur2.getCouleur() 		);
				this.getJoueur(cptJoueur).setNumJoueur(joueur2.getNumJoueur() 	);
				this.getJoueur(cptJoueur).setActifJoueur(joueur2.getActif() 	);
			}
		}
		// Mis à jour graphique pour le pseudo du joueur
		this.majGraphique();
	}

	/**
	 * @return Retourne le nombre de lignes.
	 */
	public int getNbLigne()
	{
		return this.puissance4.getNbLigne();
	}

	/**
	 * @return Retourne le nombre de colonnes.
	 */
	public int getNbColonne()
	{
		return this.puissance4.getNbColonne();
	}

	/**
	 * @return Retourne le nombre de joueurs.
	 */
	public int getNbJoueur()
	{
		return this.puissance4.getNbJoueur();
	}

	/**
	 * @return Retourne le jeton présent à l'indice de la ligne et de la colonne précisé.
	 * @param numLigne 	: le numéro de la ligne.
	 * @param numColonne : le numéro de la colonne.
	 */
	public char getJeton(int numLigne, int numColonne)
	{
		return this.puissance4.getJeton(numLigne, numColonne);
	}

	/**
	 * @return Retourne true si la grille est pleine sinon elle retourne false si la grille n'est pas pleine
	 */
	public boolean getGrillePleine()
	{
		return this.puissance4.getGrillePleine();
	}

	/**
	 * @return Retourne l'indice du joueur.
	 * @param  indiceJoueur : indice du joueur qui est retourné.
	 */
	public Joueur getJoueur(int indiceJoueur)
	{
		return this.puissance4.getJoueur(indiceJoueur);
	}

	/**
	 * @return Retourne le joueur actif
	 */
	public Joueur getJoueurActif()
	{
		return this.puissance4.getJoueurActif();
	}

	/**
	 * @return Retourne le joueur inactif
	 */
	public Joueur getJoueurInactif()
	{
		return this.puissance4.getJoueurInactif();
	}

	/**
	 * @return Retourne la couleur d'affichage.
	 */
	public boolean getCouleurAffichage()
	{
		return this.puissance4.getCouleurAffichage();
	}

	/**
	 * @return Retourne true si le joueur a gagné false si le joueur n'a pas gagné.
	 */
	public boolean estGagnant()
	{
		return this.puissance4.estGagnant();
	}

	/**
	 * setJeton : Positionne un jeton sur la grille
	 * @param numColonne : le numéro de la colonne.
	 */
	public boolean setJeton(int numColonne)
	{
		return this.puissance4.setJeton(numColonne);
	}

	/**
	 * setJoueurSuivant : Indique que c'est au joueur suivant de jouer.
	 */
	public void setJoueurSuivant()
	{
		this.puissance4.setJoueurSuivant();
	}

	/**
	 * reinitialisePartie : Réinitialise la partie.
	 */
	public void reinitialisePartie()
	{
		this.puissance4.reinitialisePartie();
	}

	/**
	 *  majGraphique : Met à jour le graphisme de la partie.
	 */
	public void majGraphique()
	{
		this.partie.majGraphique();
	}

	/**
	 * setModeSombre : Fixe le mode sombre.
	 */
	public void setModeSombre()
	{
		this.puissance4.setCouleurAffichage();
		this.partie.setModeSombre();
	}

	/**
	 *  setModeClair : Fixe le mode clair.
	 */
	public void setModeClair()
	{
		this.puissance4.setCouleurAffichage();
		this.partie.setModeClair();
	}

	/**
	 * fermerAccueil : Ferme l'interface d'accueil du jeu.
	 */
	public void fermerAccueil()
	{
		this.accueil.fermerAccueil();
	}

	/**
	 * fermerJeu : Quitte le jeu.
	 */
	public void fermerJeu()
	{
		this.accueil = null;
		this.puissance4 = null;
		this.partie.fermerPartie();
		this.partie = null;
	}

	/**
	 * nettoyerJeuOuverture : Permet de nettoyer la partie.
	 */
	private void nettoyerJeuOuverture()
	{
		this.puissance4 = null;

		if ( this.partie != null )
		{
			this.partie.fermerPartie();
		}

		this.partie = null;
	}

	/**
	 * @return Retourne les noms des parties enregistrées.
	 */
	public String[] getJeuxEnregistres()
	{
		Stack<Jeu> ensJeux = new SauvegardeJeu().getPileJeux();

		if (ensJeux!= null)
		{
			String[] tabNomJeu = new String[ensJeux.size()];
			for(int cpt=0; cpt<tabNomJeu.length; cpt++)
			{
				tabNomJeu[cpt] = ensJeux.get(cpt).getNom();
			}

			return tabNomJeu;
		}
		return null;
	}

	/**
	 * sauvegarder : Permet de sauvegarder une partie.
	 * @param nomJeu : le nom de la partie que le joueur sauvegarde.
	 */
	public void sauvegarder(String nomJeu)
	{
		SauvegardeJeu sauvegardeJeu = new SauvegardeJeu();
		sauvegardeJeu.sauvegardeJeu(nomJeu, this.puissance4);
	}

	/**
	 * ouvrir : Permet d'ouvrir une partie qui a été sauvegardé
	 * @param nomJeu : le nom de la partie que le joueur ouvre.
	 */
	public void ouvrir(String nomJeu)
	{
		this.nettoyerJeuOuverture();

		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;

		dimEcran     = Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		if (this.puissance4 == null && this.partie == null)
		{
			this.puissance4 = new Grille(nomJeu);
			this.partie = new GraphiquePartie(this, largeurEcran, hauteurEcran);
		}

		assert this.puissance4 != null;

		if (this.puissance4.getCouleurAffichage())
		{
			this.setModeSombre();
		}

		this.majGraphique();
	}

	/**
	 * main : Exécute le Jeu
	 */
	public static void main(String[] args)
	{
		new Main();
	}
}