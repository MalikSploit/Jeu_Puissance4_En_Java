import java.io.Serializable;
import java.util.Objects;

/**
 *  Classe : Joueur
 *  @author : Malik Makkes et Khalfallah Anis
 *  date 	: 04/05/2022
 *  Explication : Contient les méthodes qu'on utilisera pour les joueurs
 */
public class Joueur implements Serializable
{
	/**
	 * Définition du serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Les éléments du joueur
	 */
	private static 	int 	  nbJoueur; 	// L'attribut renseigne sur le nombre de joueurs (numéro séquentiel auto-increment).
	private 		int 	  numJoueur; 	// L'attribut renseigne sur le numéro du joueur.
	private 		String 	  pseudoJoueur; // L'attribut renseigne sur le pseudo du joueur.
	private 		char 	  couleur; 		// L'attribut renseigne sur la couleur du joueur (jaune ou rouge).
	private 		boolean   estActif; 	// L'attribut renseigne si le joueur doit jouer.

	/**
	 * Constructeur Joueur
	 * À l'instanciation d'un objet Joueur(char couleur), un joueur est créé.
	 * @param couleur : couleur du joueur.
	 */
	public Joueur(char couleur)
	{
		this.numJoueur 		= ++Joueur.nbJoueur; // Le numéro du joueur.
		this.pseudoJoueur 	= "";				 // Le joueur possède un pseudo qu'il communique à l'accueil du jeu.
		this.couleur 		= couleur; 			 // La couleur du joueur (jaune ou rouge).
		this.estActif 		= false; 			 // Au début de la partie, le joueur n'est pas actif.
	}

	/**
	 * @return Retourne le numéro du joueur.
	 */
	public int getNumJoueur()
	{
		return this.numJoueur;
	}

	/**
	 * @return Retourne le pseudo du joueur.
	 */
	public String getPseudoJoueur()
	{
		return this.pseudoJoueur;
	}

	/**
	 * @return Retourne la couleur du joueur (jaune : 'J' ou rouge : 'R').
	 */
	public char getCouleur()
	{
		return this.couleur;
	}

	/**
	 * @return Retourne true : si le joueur est actif sinon false si le joueur n'est pas actif.
	 */
	public boolean getActif()
	{
		return this.estActif;
	}

	/**
	 * setNumJoueur : Fixe le numéro du joueur lors d'une réinitialisation.
	 * @param numJoueur : le numéro du joueur.
	 */
	public void setNumJoueur(int numJoueur)
	{
		this.numJoueur = Joueur.nbJoueur = numJoueur;
	}

	/**
	 * setPseudo : Fixe le pseudo du joueur.
	 * @param pseudo : le pseudo du joueur.
	 */
	public void setPseudo(String pseudo)
	{
		this.pseudoJoueur = Objects.requireNonNullElseGet(pseudo, () -> "Joueur" + this.numJoueur);
	}

	/**
	 * setCouleur : Fixe la couleur du joueur.
	 * @param couleur : la couleur du joueur.
	 */
	public void setCouleur(char couleur)
	{
		if ( couleur == 'J' || couleur == 'R' )
		{
			this.couleur = couleur;
		} 
	}

	/**
	 * setActif : Si le joueur n'était pas actif, il devient actif. Sinon, il devient inactif.
	 */
	public void setActif()
	{
		this.estActif = !this.estActif;
	}

	/**
	 * setActifJoueur : Fixe si le joueur est actif ou inactif.
	 * @param bActifJoueur : active le bouton du joueur.
	 */
	public void setActifJoueur(boolean bActifJoueur)
	{
		this.estActif = bActifJoueur;
	}

	/**
	 * @return Affiche les caractéristiques(numéro, pseudo, couleur et actif) du joueur.
	 */
	public String toString()
	{
		return "Joueur numéro " + this.numJoueur + " pseudo : " + this.pseudoJoueur + " couleur : " + this.couleur + " estActif : " + this.estActif;
	}
}