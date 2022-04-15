import java.io.*;
import java.util.Objects;
import java.util.Stack;

/**
 *  Classe : SauvegardeJeu
 *  @author : Malik Makkes
 *  date 	: 04/05/2022
 *  Explication : Enregistre ou Charge une partie
 */
public class SauvegardeJeu
{
	/**
	 * L'élément des parties de jeux.
	 */
	private final JeuData jeuData; // L'attribut renseigne sur les parties stockées.

	/**
	 * Constructeur SauvegardeJeu
	 * À l'instanciation d'un objet SauvegardeJeu(), les parties sont enregistrées dans la pile de jeu.
	 */
	public SauvegardeJeu()
	{
		this.jeuData = new JeuData(); // La pile de jeu est initialisée.

		// Enregistrement des parties dans la pile de jeu.
		// Si la liste des fichiers n'est pas vide, lecture des fichiers puis ajoute les fichiers dans la pile de jeu.
		File fichier = new File("src/sauvegardes");
		if ( !fichier.exists() )
		{
			fichier.mkdirs();
		}
		if ( Objects.requireNonNull(fichier.list()).length > 0  )
		{
			for(String str : Objects.requireNonNull(fichier.list()))
			{
				if ( str.contains(".ser") )
				{
					try
					{
						ObjectInputStream in = new ObjectInputStream( new FileInputStream("src/sauvegardes/" + str) );
						this.jeuData.ajouterJeu( (Jeu) in.readObject() );
						in.close();
					}
					catch(Exception e )
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * @return Retourne la pile de jeux.
	 */
	public Stack<Jeu> getPileJeux()
	{
		return this.jeuData.getPileJeux();
	}

	/**
	 * @return Retourne une partie de la pile de jeux.
	 * @param nomJeu : le nom de la partie.
	 */
	public Jeu chargerJeu(String  nomJeu)
	{
		return this.jeuData.getJeu(nomJeu);
	}

	/**
	 * Sauvegarde une partie de jeu.
	 * @param nomJeu : le nom de la partie.
	 * @param metier : la partie qui doit être enregistrée.
	 */
	public void sauvegardeJeu(String nomJeu, Grille metier)
	{
		Jeu jeu = new Jeu(nomJeu, metier);
		this.jeuData.ajouterJeu(jeu);
		
		File fichier = new File("src/sauvegardes");

		if ( !fichier.exists() )
		{
			fichier.mkdirs();
		}
		try
		{
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("src/sauvegardes/" + nomJeu + ".ser") );
			out.writeObject(jeu);
			out.close();
		}
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}
	}
}