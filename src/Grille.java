import java.io.Serializable;
import java.util.EnumMap;

/**
 *  Classe : Grille
 *  @author : Malik Makkes et Anis Khalfallah
 *  date 	: 04/05/2022
 *  Explication : On initialise la grille et on a toutes les opérations liées à grille
 */
public class Grille implements Serializable
{
	/**
	 * Définition du serialVersionUID
	 */
	private static final long   serialVersionUID = 1L;

	/**
	 * Les éléments du jeu
	 */
	private static final int    NB_LIGNE_JEU      = 6;      // L'attribut renseigne sur le nombre de lignes par défaut.
	private static final int    NB_COLONNE_JEU    = 7;      // L'attribut renseigne sur le nombre de colonnes par défaut.
	private final char[][]      grille; 					// L'attribut renseigne sur la grille du Puissance4.
	private final int 			nbLigne; 					// L'attribut renseigne sur le nombre de lignes.
	private final int 			nbColonne; 					// L'attribut renseigne sur le nombre de colonnes.
	private final boolean 		partieGagne; 				// L'attribut renseigne sur si la partie est gagnante ou non gagnante.
	private final Joueur[]		tabJoueur;					// L'attribut renseigne sur les joueurs.
	private       boolean       couleurAffichage; 			// L'attribut renseigne sur la couleur d'affichage.


	/**
	 * initGrille : Initialise la grille et connecte chaque case avec toutes les cases à cote d'elle.
	 */
	public void initGrille()
	{
		for(int i = 0; i < NB_LIGNE_JEU; i++)
		{
			for(int j = 0; j < NB_COLONNE_JEU; j++)
			{
				EnumMap<Position, Character> a = new EnumMap<>(Position.class);

				if (i-1 >= 0)
				{
					a.put(Position.nord, this.grille[i-1][j]);
				}

				if (i+1 < NB_LIGNE_JEU)
				{
					a.put(Position.sud, this.grille[i+1][j]);
				}

				if (j+1 < NB_COLONNE_JEU)
				{
					a.put(Position.est, this.grille[i][j+1]);
				}

				if (j-1 >= 0)
				{
					a.put(Position.ouest, this.grille[i][j-1]);
				}

				if (j-1 >= 0 && i+1 < NB_LIGNE_JEU)
				{
					a.put(Position.sudEst, this.grille[i+1][j-1]);
				}

				if (j-1 >= 0 && i - 1 >= 0)
				{
					a.put(Position.nordOuest, this.grille[i-1][j-1]);
				}

				if (j+1 < NB_COLONNE_JEU && i+1 < NB_LIGNE_JEU)
				{
					a.put(Position.sudOuest, this.grille[i+1][j+1]);
				}

				if (j+1 < NB_COLONNE_JEU && i-1 >= 0)
				{
					a.put(Position.nordEst, this.grille[i-1][j+1]);
				}
			}
		}
	}

	/**
	 * Constructeur Grille
	 * À l'instanciation d'un objet Grille(), une partie de jeu est initialisée de manière non graphique.
	 */
	public Grille()
	{
		this.nbLigne 			= Grille.NB_LIGNE_JEU;			            // Ligne : 6
		this.nbColonne 			= Grille.NB_COLONNE_JEU;			        // Colonne : 7
		this.grille             = new char[this.nbLigne][this.nbColonne]; 	// Création d'une grille 6x7.
		this.partieGagne 		= false; 									// Au début du jeu, aucun des joueurs ne gagne.
		this.tabJoueur 			= new Joueur[2]; 							// Le jeu se joue à deux joueurs.
		this.couleurAffichage 	= false;									// Au début du jeu, l'affichage est clair.

		for (int cptLigne = 0; cptLigne < this.nbLigne; cptLigne++)
		{
			for (int cptColonne = 0; cptColonne < this.nbColonne; cptColonne++)
			{
				this.grille[cptLigne][cptColonne] = 'V'; 					// La grille est initialisée avec des cases vides.
			}
		}
		this.initJoueurs();
	}

	/**
	 * Constructeur Grille(String nomJeu)
	 * À l'instanciation d'un objet Grille(String nomJeu), une partie est alors chargée avec le nom de la partie renseignée.
	 * @param nomJeu : le nom de la partie renseignée pour être chargé.
	 */
	public Grille(String nomJeu)
	{
		Jeu jeu = new SauvegardeJeu().chargerJeu(nomJeu); 					// Chargement d'une partie.
		this.nbLigne 			= jeu.getJeu().getNbLigne(); 				// Récupère le nombre de lignes de la partie chargé.
		this.nbColonne 			= jeu.getJeu().getNbColonne(); 				// Récupère le nombre de colonnes de la partie chargé.
		this.grille             = new char[this.nbLigne][this.nbColonne];   // Création d'une grille avec le nombre de lignes x le nombre de colonnes.
		this.partieGagne 		= jeu.getJeu().getPartieGagne(); 			// Récupère la valeur booléenne vraie si la partie est gagné par l'un des joueurs et false si la partie est non gagnante de la partie chargée.
		this.tabJoueur 			= new Joueur[2];							// Le jeu se joue à deux joueurs.
		this.couleurAffichage 	= jeu.getJeu().getCouleurAffichage(); 		// Récupère la couleur d'affichage.

		// Récupère la grille
		for (int cptLigne = 0; cptLigne < this.nbLigne; cptLigne++)
		{
			for (int cptColonne = 0; cptColonne < this.nbColonne; cptColonne++)
			{
				this.grille[cptLigne][cptColonne] = jeu.getJeu().getJeton(cptLigne, cptColonne); // Récupère le jeton de la grille de la partie chargé.
			}
		}

		// Récupère la grille
		for (int cptJoueur = 0; cptJoueur < this.tabJoueur.length; cptJoueur++)
		{
			this.tabJoueur[cptJoueur] = jeu.getJeu().getJoueur(cptJoueur);
		}
	}

	/**
	 * @return Retourne le nombre de lignes.
	 */
	public int getNbLigne()
	{
		return this.nbLigne;
	}

	/**
	 * @return Retourne le nombre de colonnes.
	 */
	public int getNbColonne()
	{
		return this.nbColonne;
	}

	/**
	 * @return Retourne le nombre de joueurs.
	 */
	public int getNbJoueur()
	{
		return this.tabJoueur.length;
	}

	/**
	 * @return Retourne le jeton présent à l'indice de la ligne et de la colonne précisé.
	 * @param numLigne 	: le numéro de la ligne.
	 * @param numColonne : le numéro de la colonne.
	 */
	public char getJeton(int numLigne, int numColonne)
	{
		if ( (numLigne >= 0 && numLigne < this.nbLigne) && (numColonne >= 0 && numColonne < this.nbColonne) )
		{
			return this.grille[numLigne][numColonne];
		}
		return 'V';
	}

	/**
	 * @return Retourne true si la partie est gagnée sion false si la partie n'est pas gagnée.
	 */
	public boolean getPartieGagne()
	{
		return this.partieGagne;
	}

	/**
	 * @return Retourne true si la grille est pleine sion false si la grille n'est pas pleine
	 */
	public boolean getGrillePleine()
	{
		for (int cptLigne = 0; cptLigne < this.nbLigne; cptLigne++)
		{
			for (int cptColonne = 0; cptColonne<this.nbColonne; cptColonne++)
			{
				if ( this.grille[cptLigne][cptColonne] == 'V' )
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @return Retourne un joueur.
	 * @param  indiceJoueur : indice du joueur qui est retourné.
	 */
	public Joueur getJoueur(int indiceJoueur)
	{
		return this.tabJoueur[indiceJoueur];
	}

	/**
	 * @return Retourne le joueur actif
	 */
	public Joueur getJoueurActif()
	{
		for (int cptJoueur=0; cptJoueur < this.tabJoueur.length; cptJoueur++)
		{
			if ( this.tabJoueur[cptJoueur].getActif() )
			{
				return this.getJoueur(cptJoueur);
			}
		}
		return null;
	}

	/**
	 * @return Retourne le joueur inactif
	 */
	public Joueur getJoueurInactif()
	{
		for (int cptJoueur=0; cptJoueur < this.tabJoueur.length; cptJoueur++)
		{
			if ( !this.tabJoueur[cptJoueur].getActif() )
			{
				return this.getJoueur(cptJoueur);
			}
		}
		return null;
	}

	/**
	 * @return Retourne la couleur d'affichage.
	 */
	public boolean getCouleurAffichage()
	{
		return this.couleurAffichage;
	}

	/**
	 * @return Retourne true si le jeton a été positionné sur la grille sinon false si le jeton n'a pas été positionné sur la grille.
	 * @param numColonne : le numéro de la colonne.
	 */
	public boolean setJeton(int numColonne)
	{
		// Vérification de la colonne
		if ( numColonne < 0 || numColonne > this.nbColonne )
		{
			return false;
		}

		// Vérification que la colonne n'est pas pleine
		if ( this.grille[0][numColonne] != 'V' )
		{
			return false;
		}

		int cptLigne = this.nbLigne - 1;
		while (this.grille[cptLigne][numColonne] != 'V')
		{
			cptLigne--;
		}
		this.grille[cptLigne][numColonne] = this.getJoueurActif().getCouleur();
		return true;
	}

	/**
	 * Indique que c'est au joueur suivant de jouer.
	 */
	public void setJoueurSuivant()
	{
		for (Joueur joueur : this.tabJoueur)
		{
			joueur.setActif();
		}
	}

	/**
	 * Fixe la couleur d'affichage.
	 */
	public void setCouleurAffichage()
	{
		this.couleurAffichage = !this.couleurAffichage;
	}

	/**
	 * Réinitialise la partie.
	 */
	public void reinitialisePartie()
	{
		// Réinitialisation du plateau
		for(int cptLigne = 0; cptLigne < this.nbLigne; cptLigne++)
		{
			for(int cptColonne = 0; cptColonne < this.nbColonne; cptColonne++)
			{
				this.grille[cptLigne][cptColonne] = 'V';
			}
		}
	}

	/**
	 * Initialise les joueurs.
	 */
	private void initJoueurs()
	{
		char[] tabCouleur = {'J', 'R'};
		int joueurCouleur 	= (int) (Math.random() * this.getNbJoueur());

		for (int cpt = 0; cpt < this.tabJoueur.length; cpt++)
		{
			if (cpt % 2 != 0)
			{
				if (joueurCouleur < cpt)
				{
					joueurCouleur++;
				}
				else
				{
					joueurCouleur--;
				}
			}
			this.tabJoueur[cpt] = new Joueur(tabCouleur[joueurCouleur]);
		}

		for (int cpt = 0; cpt < this.tabJoueur.length; cpt++)
		{
			if ( this.tabJoueur[cpt].getNumJoueur() > this.getNbJoueur() )
			{
				this.tabJoueur[cpt].setNumJoueur(cpt + 1);
			}
		}

		// Initialisation du joueur actif
		int joueurActif = (int) (Math.random() * this.getNbJoueur());
		this.tabJoueur[joueurActif].setActif();
	}

	/**
	 * @return Retourne true si le joueur a gagné sinon false si le joueur n'a pas gagné.
	 */
	public boolean estGagnant()
	{
		for(int indiceLig = 0; indiceLig < this.nbLigne; indiceLig++)
		{
			for(int indiceCol = 0; indiceCol < this.nbColonne; indiceCol++)
			{
				if ( !(this.grille[indiceLig][indiceCol] == 'V') )
				{
					// nord = -1 et sud = 1 au niveau de ligDir
					// ouest = -1 et est = 1 au niveau de colDir
					// diagonale : vers le bas et à droite
					// vers le haut et à droite
					// horizontal vers la droite
					if (compterJeton(this.grille, indiceLig, indiceCol, 1, 1) == 4 || compterJeton(this.grille, indiceLig, indiceCol, -1, 1) == 4 || compterJeton(this.grille, indiceLig, indiceCol, 0, 1) == 4 || compterJeton(this.grille, indiceLig, indiceCol, 1, 0) == 4)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @return Retourne le nombre de jetons aligné dans une direction précise.
	 * @param grille 	: la grille.
	 * @param lig 		: indice de la ligne du plateau.
	 * @param col 		: indice de la colonne du plateau.
	 * @param ligDir 	: direction de la ligne (nord=-1 ou sud=1) pour compter les jetons.
	 * @param colDir 	: direction de la colonne (ouest=-1 ou est=1) pour compter les jetons.
	 */
	private int compterJeton(char[][] grille, int lig, int col, int ligDir, int colDir)
	{
		int cpt 	=	0; 		// compte le nombre de jetons aligné
		int ligCpt 	=	lig;	// s'occupe de la direction de la ligne (nord ou sud) du comptage des jetons
		int colCpt 	=	col; 	// s'occupe de la direction la colonne (ouest ou est) du comptage des jetons

		while (ligCpt >= 0 && ligCpt < this.nbLigne && colCpt >= 0 && colCpt < this.nbColonne && grille[ligCpt][colCpt] == grille[lig][col])
		{
			ligCpt += ligDir;
			colCpt += colDir;
			cpt++;
		}
		return cpt;
	}
}