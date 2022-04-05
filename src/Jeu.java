import java.io.Serializable;

 /**
 *  Classe : Jeu
 *  @author : Malik Makkes et Khalfallah Anis
 *  date 	: 04/05/2022
 *  Explication : Enregistre une partie
 */
public class Jeu implements Serializable
{
	/**
	 * Définition du serialVersionUID
	 */
	private static final long serialVersionUID = 1L; //Etre sûr que ça soit bien une chaine de bytes (principe de serialisation)

	/**
	 * Les éléments du jeu
	 */
	private final String 		nomJeu; 	        // L'attribut renseigne sur le nom de la partie.
	private final Grille puissance4; 	        // L'attribut renseigne sur la partie de jeu.

	/**
	 * Constructeur de Jeu
	 * À l'instanciation d'un objet Jeu(String nomJeu, Puissance4 metier), une partie de jeu est insérée et enregistrée dans l'objet Jeu.
	 * @param nomJeu : le nom de la partie.
	 * @param puissance4 : la partie de puissance4 enregistrée.
	 */
	public Jeu(String nomJeu, Grille puissance4)
	{
		this.nomJeu = nomJeu; // Le nom de la partie.
		this.puissance4 = puissance4; // La partie enregistrée.
	}

	/**
	 * @return Retourne le nom de la partie.
	 */
	public String getNom()
	{
		return this.nomJeu;
	}

	/**
	 * @return Retourne la partie.
	 */
	public Grille getJeu()
	{
		return this.puissance4;
	}

	/**
	 * @return Retourne true si cette partie est la même que la partie comparée et false si cette partie n'est pas la même que la partie comparée.
	 * @param jeu : le jeu qui est comparé.
	 */
	public boolean equals(Jeu jeu)
	{
		return this.nomJeu.equals(jeu.getNom());
	}

	/**
	 * @return Affiche le nom d'une partie.
	 */
	public String toString()
	{
		String sRet="";
		sRet += " Nom de jeu de la partie enregistrée : " + this.nomJeu + "\n";
		return sRet;
	}
}