import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Classe : Menu
 *  @author : Malik Makkes et Anis Khalfallah
 *  date 	: 04/05/2022
 *  Explication : On place tous les éléments nécessaires pour le menu du jeu
 */
public class Menu extends JPanel implements ActionListener
{
	/**
	 * Définition du serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Les éléments pour l'affichage graphique du menu
	 */
	private final Main 		main; 					// L'attribut renseigne sur le contrôleur.
	private final JMenuBar  menuBar; 				// L'attribut pour la barre de menu.
	private final JMenu     menuPartie; 			// L'attribut pour l'onglet parti.
	private final JMenu     menuAffichage;			// L'attribut pour l'affichage.
	private final JMenu     menuAide; 				// L'attribut pour l'onglet aide.
	private final JMenuItem menuItemEnregistrer; 	// L'attribut pour l'élément enregistrer.
	private final JMenuItem menuItemOuvrir; 		// L'attribut pour l'élément ouvrir.
	private final JMenuItem menuItemQuitter; 		// L'attribut pour l'élément quitter.
	private final JMenuItem menuItemModeSombre;		// L'attribut pour mettre le jeu en mode sombre.
	private final JMenuItem menuItemRegles; 		// L'attribut pour l'élément règles du jeu.

	/**
	 * Constructeur PanelMenu
	 * @param main : Le contrôleur.
	 */
	public Menu(Main main)
	{
		this.main = main;
		this.setLayout( new FlowLayout() );
		this.setBackground(Color.WHITE);
		this.menuBar 		       = new JMenuBar();
		this.menuPartie 		   = new JMenu("Partie");
		this.menuAffichage 	       = new JMenu("Affichage");
		this.menuAide 		       = new JMenu("Aide");
		this.menuItemEnregistrer   = new JMenuItem("Enregistrer");
		this.menuItemOuvrir 	   = new JMenuItem("Ouvrir");
		this.menuItemQuitter	   = new JMenuItem("Quitter");
		this.menuItemModeSombre    = new JMenuItem("Mode Sombre");
		this.menuItemRegles 	   = new JMenuItem("Règles du jeu");
		this.menuBar.setBackground(Color.WHITE);
		this.menuBar.setBorder(null);
		this.menuPartie.setBorder(null);

		Font policeMenu 	= new Font("Calibri", Font.BOLD, 15);
		Font policeMenuItem = new Font("Calibri", Font.BOLD, 15);

		this.setModeClair();
		this.menuPartie.add(this.menuItemEnregistrer);
		this.menuPartie.add(this.menuItemOuvrir);
		this.menuPartie.add(this.menuItemQuitter);
		this.menuAffichage.add(this.menuItemModeSombre);
		this.menuAide.add(this.menuItemRegles);
		this.menuBar.add(this.menuPartie);
		this.menuBar.add(this.menuAffichage);
		this.menuBar.add(this.menuAide);
		this.add(this.menuBar);
		this.menuPartie.setFont(policeMenu); 
		this.menuAffichage.setFont(policeMenu);
		this.menuAide.setFont(policeMenu);
		this.menuItemEnregistrer.setFont(policeMenuItem);
		this.menuItemOuvrir.setFont(policeMenuItem);
		this.menuItemQuitter.setFont(policeMenuItem);
		this.menuItemModeSombre.setFont(policeMenuItem);
		this.menuItemRegles.setFont(policeMenuItem);
		this.menuItemEnregistrer.addActionListener(this);
		this.menuItemOuvrir.addActionListener(this);
		this.menuItemQuitter.addActionListener(this);
		this.menuItemModeSombre.addActionListener(this);
		this.menuItemRegles.addActionListener(this);
		this.setVisible(true);
	}

	/**
	 * Fixe le mode sombre.
	 */
	public void setModeSombre()
	{
		this.menuItemModeSombre.setText("Mode Clair");
		this.menuBar.setBackground(Color.DARK_GRAY);
		this.menuPartie.setForeground(Color.WHITE);
		this.menuAffichage.setForeground(Color.WHITE);
		this.menuAide.setForeground(Color.WHITE);
		this.menuItemEnregistrer.setBackground(Color.DARK_GRAY);
		this.menuItemOuvrir.setBackground(Color.DARK_GRAY);
		this.menuItemQuitter.setBackground(Color.DARK_GRAY);
		this.menuItemModeSombre.setBackground(Color.DARK_GRAY);
		this.menuItemRegles.setBackground(Color.DARK_GRAY);
		this.menuItemEnregistrer.setForeground(Color.WHITE);
		this.menuItemOuvrir.setForeground(Color.WHITE);
		this.menuItemQuitter.setForeground(Color.WHITE);
		this.menuItemModeSombre.setForeground(Color.WHITE);
		this.menuItemRegles.setForeground(Color.WHITE);
		this.setBackground(Color.DARK_GRAY);
	}

	/**
	 * Fixe le mode clair.
	 */
	public void setModeClair()
	{
		this.menuItemModeSombre.setText("Mode Sombre");
		this.menuBar.setBackground(Color.WHITE);
		this.menuPartie.setForeground(Color.BLACK);
		this.menuAffichage.setForeground(Color.BLACK);
		this.menuAide.setForeground(Color.BLACK);
		this.menuItemEnregistrer.setBackground(Color.WHITE);
		this.menuItemOuvrir.setBackground(Color.WHITE);
		this.menuItemQuitter.setBackground(Color.WHITE);
		this.menuItemModeSombre.setBackground(Color.WHITE);
		this.menuItemRegles.setBackground(Color.WHITE);
		this.menuItemEnregistrer.setForeground(Color.BLACK);
		this.menuItemOuvrir.setForeground(Color.BLACK);
		this.menuItemQuitter.setForeground(Color.BLACK);
		this.menuItemModeSombre.setForeground(Color.BLACK);
		this.menuItemRegles.setForeground(Color.BLACK);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Ferme le Menu.
	 */
	public void fermerMenu()
	{
		this.removeAll();
		this.setVisible(false);
	}

	/**
	 * Si le joueur clique sur la barre de menu.
	 */
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() == this.menuItemEnregistrer)
		{
			String nomJeu = JOptionPane.showInputDialog(this,"Entrer le nom de la partie : ", "Enregistrement d'une partie", JOptionPane.QUESTION_MESSAGE); 

			if ( nomJeu == null || nomJeu.equals("") )
			{
				nomJeu = "partie_enregistree_par_defaut";
			}

			this.main.sauvegarder(nomJeu);
		}

		if ( e.getSource() == this.menuItemOuvrir) 
		{
			String[] ensJeux = this.main.getJeuxEnregistres();
			if ( !(ensJeux == null) )
			{
				JComboBox<String> cbox = new JComboBox<>(ensJeux);
				cbox.setEditable(true);
				int ouvertureOk = JOptionPane.showConfirmDialog(this, cbox, "Ouverture d'une partie", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if ( ouvertureOk == JOptionPane.OK_OPTION)
				{
					Object partieEnregistre = cbox.getSelectedItem();
					assert partieEnregistre != null;
					this.main.ouvrir(partieEnregistre.toString());
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Aucune partie enregistrée", "Ouverture d'une partie", JOptionPane.ERROR_MESSAGE);
			}
		}
		if ( e.getSource() == this.menuItemQuitter) 
		{
			this.main.fermerJeu();
		}
		if ( e.getSource() == this.menuItemModeSombre) 
		{
			if ( ! this.main.getCouleurAffichage() )
			{
				this.main.setModeSombre();
			}
			else 
			{
				this.main.setModeClair();
			}
		}
		if ( e.getSource() == this.menuItemRegles ) 
		{
			 JOptionPane.showMessageDialog(this, "Gagner une partie en étant le premier à aligner 4 jetons de même couleur horizontalement, verticalement ou diagonalement. \n Si lors d'une partie, tous les jetons ont été joués sans qu'il n'y est de jetons alignés, ca sera égalité.");
		}
	}
}