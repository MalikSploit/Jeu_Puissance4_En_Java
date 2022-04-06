import java.io.Serializable;
import java.util.Stack;

/**
 *  Classe : JeuData
 *  @author : Malik Makkes et Khalfallah Anis
 *  date 	: 04/05/2022
 *  Explication : Enregistre les parties et les stockent sous forme de pile.
 */
public class JeuData implements Serializable
{
	/**
	 * Définition du serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * L'élément d'enregistrement de jeu.
	 */
	private final Stack<Jeu> pileJeu; // L'attribut renseigne sur la pile de jeux.

	/**
	 * Constructeur JeuData
	 * À l'instanciation d'un objet JeuData(), une pile de jeu est initialisée.
	 */
	public JeuData()
	{
		this.pileJeu = new Stack<>(); // La pile de jeux qui va contenir les parties enregistrées.
	}

	/**
	 * @return Retourne la pile de jeux.
	 */
	public Stack<Jeu> getPileJeux()
	{
		if ( this.pileJeu.isEmpty() )
		{
			return null;
		}
		return this.pileJeu;
	}

	/**
	 * @return Retourne une partie grâce au nom de la partie.
	 * @param nomJeu : le nom de la partie.
	 */
	public Jeu getJeu(String nomJeu)
	{
		for(Jeu jeu : this.pileJeu)
		{
			if ( jeu.getNom().equals( nomJeu ) ) 
			{
				return jeu;
			}
		}
		return null;
	}

	/**
	 * ajouterJeu : Ajoute une partie dans la pile de jeux.
	 * @param jeuAjoute : le nom de la partie.
	 */
	public void ajouterJeu(Jeu jeuAjoute)
	{
		boolean bOk = false;

		for(Jeu jeu : this.pileJeu) 
		{ 
			if ( jeu.equals(jeuAjoute) ) 
			{
				bOk = true;
			}
		}
		if (!bOk )
		{
			this.pileJeu.push(jeuAjoute); 
		}
		else
		{
			for(int cpt = 0; cpt < this.pileJeu.size(); cpt++)
			{
				if ( this.pileJeu.get(cpt).equals(jeuAjoute) )
				{
					this.pileJeu.set(cpt, jeuAjoute);
				}
			}
		}
	}

	/**
	 * @return Affiche les parties enregistrées dans la pile de jeux.
	 */
	public String toString()
	{
		StringBuilder sRet = new StringBuilder();

		for(Jeu jeu : this.pileJeu)
		{ 
			sRet.append(" ").append(jeu.getNom()).append("\n");
		}
		return sRet.toString();
	}
}