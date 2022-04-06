import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  Classe : Main
 *  @author : Malik Makkes et Anis Khalfallah
 *  date 	: 04/05/2022
 *  Explication : On place tous les éléments nécessaires sur la fenêtre d'acceuil
 */
public class Accueil extends JPanel implements ItemListener
{
	/**
	 * Définition du serialVersionUID
	 */
	private static final long  serialVersionUID = 1L;

	/**
	 * Les éléments pour l'affichage graphique de la fenêtre d'accueil
	 */
	private final InfosJoueur  infosJoueur;  	 // L'attribut renseigne sur InfosJoueur.
	private final ChargePartie chargePartie; 	 // L'attribut renseigne sur ChargePartie.
	private final JRadioButton bNouvellePartie;  // L'attribut renseigne sur la préférence pour une nouvelle partie 1 vs 1.
	private final JRadioButton bNouvellePartie2; // L'attribut renseigne sur la préférence pour une nouvelle partie 1 vs CPU.
	private final JRadioButton bChargerPartie;   // L'attribut renseigne sur la préférence pour charger partie.

	/**
	 * Constructeur Accueil
	 * @param fenetreEcran : La taille de l'écran
	 */
	public Accueil(Main fenetreEcran)
	{
		this.setLayout(new BorderLayout());

		Font policeTitre = new Font("Calibri", Font.BOLD, 50);
		Font policeDroit = new Font("Calibri", Font.BOLD, 20);

		JLabel 	lblTitre;

		lblTitre = new JLabel("Jeu de Puissance 4", JLabel.CENTER);
		lblTitre.setFont(policeTitre);
		lblTitre.setForeground(Color.WHITE);

		ButtonGroup bgSelection = new ButtonGroup();
		this.bNouvellePartie = new JRadioButton("Nouvelle partie 1 VS 1");
		this.bNouvellePartie.setFont(policeDroit);
		this.bNouvellePartie.setForeground(Color.WHITE);

		this.bNouvellePartie2 = new JRadioButton("Nouvelle partie 1 VS CPU");
		this.bNouvellePartie2.setFont(policeDroit);
		this.bNouvellePartie2.setForeground(Color.WHITE);

		this.bChargerPartie = new JRadioButton("Charger une partie");
		this.bChargerPartie.setFont(policeDroit);
		this.bChargerPartie.setForeground(Color.WHITE);

		bgSelection.add(this.bNouvellePartie);
		bgSelection.add(this.bNouvellePartie2);
		bgSelection.add(this.bChargerPartie);

		this.infosJoueur = new InfosJoueur(fenetreEcran);
		this.chargePartie = new ChargePartie(fenetreEcran);

		JLabel lblDroit = new JLabel("Jeu de Puissance 4 Par Malik", JLabel.LEFT);
		lblDroit.setFont(policeDroit);
		lblDroit.setForeground(Color.WHITE);

		JPanel panelNord = new JPanel();
		JPanel panelCentre = new JPanel();
		JPanel panelCentreMilieu = new JPanel();
		JPanel panelCentreNord = new JPanel();
		JPanel panelCentreSud = new JPanel();
		JPanel panelSud = new JPanel();

		panelNord.setLayout(new FlowLayout());
		panelCentre.setLayout(new GridLayout(3,1));
		panelCentreMilieu.setLayout(new GridLayout(2, 2));
		panelCentreNord.setLayout(new GridLayout(1,2));
		panelCentreSud.setLayout(new GridLayout(3,2));
		panelSud.setLayout(new FlowLayout());

		this.bNouvellePartie.setOpaque(false);
		this.bNouvellePartie.setSelected(true);
		this.bNouvellePartie2.setOpaque(false);
		this.bNouvellePartie2.setSelected(false);
		this.bChargerPartie.setOpaque(false);
		this.bChargerPartie.setSelected(false);

		panelNord.setOpaque(false);
		panelCentre.setOpaque(false);
		panelCentreNord.setOpaque(false);
		panelCentreMilieu.setOpaque(false);
		panelCentreSud.setOpaque(false);
		panelSud.setOpaque(false);

		panelNord.add(lblTitre);
		panelCentreNord.add(this.bNouvellePartie);
		panelCentreMilieu.add(this.bNouvellePartie2);
		panelCentreNord.add(this.infosJoueur);
		panelCentreSud.add(this.bChargerPartie);
		panelCentreSud.add(this.chargePartie);

		panelCentre.add(panelCentreNord);
		panelCentre.add(panelCentreMilieu);
		panelCentre.add(panelCentreSud);
		panelSud.add(lblDroit, BorderLayout.SOUTH);

		this.add(panelNord, BorderLayout.NORTH);
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelSud, BorderLayout.SOUTH);

		this.bNouvellePartie.addItemListener(this);
		this.bNouvellePartie2.addItemListener(this);
		this.bChargerPartie.addItemListener(this);
		this.chargePartie.setVisible(false);
		this.setVisible(true);
	}

	/**
	 * Dessine le fond de la fenêtre d'accueil
	 * @param g : Le graphisme
	 */
	public void paintComponent(Graphics g)
	{
		String     sImage;
		Image      img;
		Graphics2D g2 = (Graphics2D) g;

		super.paintComponent(g);
		// Dessine le fond
		sImage = "src/images/Puissance4_background.png";
		img = getToolkit().getImage ( sImage );
		g2.drawImage ( img, 0, 0, this.getWidth(), this.getHeight(), this );
	}

	/**
	 * Ferme la fenêtre d'Accueil.
	 */
	public void fermerAccueil()
	{
		this.infosJoueur.fermerAccueil();
		this.chargePartie.fermerAccueil();

		this.removeAll();
		this.setVisible(false);
	}

	/**
	 * Si le joueur sélectionne le bouton nouvelle partie 1vs1 ou nouvelle partie 1vsCPU ou charger partie.
	 */
	public void itemStateChanged(ItemEvent e)
	{
		if ( e.getSource() == this.bNouvellePartie)
		{
			this.infosJoueur.txtfldJoueur2.setText("");
			this.infosJoueur.txtfldJoueur2.setEditable(true);
			this.chargePartie.setVisible(false);
			this.infosJoueur.setVisible(true);
		}

		if (e.getSource() == this.bNouvellePartie2)
		{
			this.chargePartie.setVisible(false);
			this.infosJoueur.txtfldJoueur2.setText("Bot");
			this.infosJoueur.txtfldJoueur2.setEditable(false);
			this.infosJoueur.setVisible(true);
		}

		if (e.getSource() == this.bChargerPartie)
		{
			this.infosJoueur.setVisible(false);
			this.chargePartie.setVisible(true);
		} 
	}

	/**
	 *  Classe : InfosJoueur
	 *  @author : Malik Makkes et Anis Khalfallah
	 *  date 	: 04/05/2022
	 *  Explication : on configure les saisies de l'utilisateur, pour pseudo et boutton valider
	 */
	private static class InfosJoueur extends JPanel implements ActionListener
	{
		/**
		 * Définition du serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Les éléments pour l'affichage graphique des cases
		 */
		private final Main       fenetreEcran; 	// L'attribut renseigne sur la taille de l'écran
		private final JTextField txtfldJoueur1; // L'attribut renseigne sur le champ de saisie du joueur 1.
		private final JTextField txtfldJoueur2; // L'attribut renseigne sur le champ de saisie du joueur 2.
		private final JButton    btnValider; 	// L'attribut renseigne sur le bouton de validation.

		/**
		 * Constructeur InfosJoueur
		 * @param fenetreEcran : La taille de l'écran
		 */
		public InfosJoueur(Main fenetreEcran)
		{
			this.fenetreEcran = fenetreEcran;
			this.setLayout(new GridLayout(3,1));
			this.setOpaque(false);

			Font policeChamp = new Font("Calibri", Font.BOLD, 20);
			JLabel lblPseudoJoueur1 = new JLabel("Pseudo du joueur 1 : ");
			lblPseudoJoueur1.setFont(policeChamp);
			lblPseudoJoueur1.setForeground(Color.WHITE);

			this.txtfldJoueur1 = new JTextField(10);
			this.txtfldJoueur1.setOpaque(false);
			this.txtfldJoueur1.setCaretColor(Color.WHITE.brighter());
			this.txtfldJoueur1.setFont(policeChamp);
			this.txtfldJoueur1.setForeground(Color.WHITE);
			this.txtfldJoueur1.setPreferredSize( new Dimension(20,20) );

			JLabel lblPseudoJoueur2 = new JLabel("Pseudo du joueur 2 : ");
			lblPseudoJoueur2.setForeground(Color.WHITE);
			lblPseudoJoueur2.setFont(policeChamp);

			this.txtfldJoueur2 = new JTextField(10);
			this.txtfldJoueur2.setOpaque(false);
			this.txtfldJoueur2.setCaretColor(Color.WHITE.brighter());
			this.txtfldJoueur2.setFont(policeChamp);
			this.txtfldJoueur2.setForeground(Color.WHITE);

			this.btnValider = new JButton("Valider");
			this.btnValider.setFont(policeChamp);
			this.btnValider.setForeground(Color.BLACK);
			this.btnValider.setBorder(BorderFactory.createLineBorder(Color.white));

			// Création du panel interne
			JPanel panelChamp = new JPanel();
			panelChamp.setLayout( new GridLayout(2,1) );
			panelChamp.setOpaque(false);

			panelChamp.add( lblPseudoJoueur1 );
			panelChamp.add( this.txtfldJoueur1 );
			panelChamp.add( lblPseudoJoueur2 );
			panelChamp.add( this.txtfldJoueur2 );

			this.add(panelChamp);
			this.add(this.btnValider);
			this.btnValider.addActionListener(this);
			this.setVisible(true);
		}

		/**
		 * Ferme la fenêtre Infos Joueur.
		 */
		public void fermerAccueil()
		{
			this.removeAll();
			this.setVisible(false);
		}

		/**
		 * Si le joueur clique sur le bouton de validation.
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == this.btnValider)
			{
				if (!this.txtfldJoueur1.getText().equals("") && ! this.txtfldJoueur2.getText().equals(""))
				{
					if (!this.txtfldJoueur1.getText().equals(this.txtfldJoueur2.getText()))
					{
						String pseudoJoueur1 = this.txtfldJoueur1.getText();
						String pseudoJoueur2 = this.txtfldJoueur2.getText();
						this.fenetreEcran.lancerJeu(pseudoJoueur1, pseudoJoueur2);
						this.fenetreEcran.fermerAccueil();
					}
					else if ( this.txtfldJoueur1.getText().equals(this.txtfldJoueur2.getText()) )
					{
						JOptionPane.showMessageDialog(this, "Interdiction d'avoir le même pseudo.", "Erreur de pseudo", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					int cptJoueur=0;
					String sRet = "Champ manquant pour le joueur ";
					if ( this.txtfldJoueur1.getText().equals("") && this.txtfldJoueur2.getText().equals("") )
					{
						sRet = "Champs manquants pour les joueurs ";
						sRet += ++cptJoueur + " et " + ++cptJoueur;
					}
					else if ( this.txtfldJoueur1.getText().equals("") || this.txtfldJoueur2.getText().equals("") )
					{
						cptJoueur++;
						if ( this.txtfldJoueur1.getText().equals("") )
						{
							sRet += cptJoueur;
						}
						else if ( this.txtfldJoueur2.getText().equals("") ) 
						{
							sRet += ++cptJoueur;
						}
						cptJoueur--;
					}
					String sRetTitre = "Saisie manquante";

					if ( cptJoueur > 1 )
					{
						sRetTitre = "Plusieurs saisies manquantes";
					}
					JOptionPane.showMessageDialog(this, sRet, sRetTitre, JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 *  Classe : ChargePartie
	 *  @author : Malik Makkes et Anis Khalfallah
	 *  date 	: 04/05/2022
	 *  Explication : on configure les saisies de l'utilisateur, pour pseudo et boutton valider
	 */
	private static class ChargePartie extends JPanel implements ActionListener
	{
		/**
		 * Définition du serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Les éléments pour l'affichage graphique des cases
		 */
		private final Main    fenetreEcran; // L'attribut renseigne sur la taille de l'écran
		private final JButton btnCharger; 	// L'attribut renseigne sur le bouton pour charger une partie.

		/**
		 * Constructeur ChargePartie
		 * @param fenetreEcran : La taille de l'écran
		 */
		public ChargePartie(Main fenetreEcran)
		{
			this.fenetreEcran = fenetreEcran;
			this.setLayout( new BorderLayout() );
			this.setOpaque(false);

			Font policeBouton = new Font("Calibri", Font.BOLD, 20);

			this.btnCharger = new JButton("Charger une partie");
			this.btnCharger.setFont(policeBouton);
			this.btnCharger.setBorder(BorderFactory.createLineBorder(Color.black));
			this.add(this.btnCharger, BorderLayout.CENTER);
			this.btnCharger.addActionListener(this);
			this.setVisible(true);
		}

		/**
		 * Ferme la fenêtre Charge Partie.
		 */
		public void fermerAccueil()
		{
			this.removeAll();
			this.setVisible(false);
		}

		/**
		 * Si le joueur clique sur le bouton pour charger une partie.
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == this.btnCharger)
			{
				String[] ensJeux = this.fenetreEcran.getJeuxEnregistres();

				if (!(ensJeux == null))
				{
					JComboBox<String> cbox = new JComboBox<>(ensJeux);
					cbox.setEditable(true);
					int ouvertureOk = JOptionPane.showConfirmDialog(this, cbox, "Ouverture d'une partie", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (ouvertureOk == JOptionPane.OK_OPTION)
					{
						Object partieEnregistre = cbox.getSelectedItem();
						assert partieEnregistre != null;
						this.fenetreEcran.ouvrir(partieEnregistre.toString());
					}
					this.fenetreEcran.fermerAccueil();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Aucune partie enregistrée", "Ouverture d'une partie", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}