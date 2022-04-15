import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *  Classe : Partie
 *  @author : Malik Makkes
 *  date 	: 04/05/2022
 *  Explication : On configure la fenêtre de la grille et le message du joueur qui doit jouer
 */
public class Partie extends JPanel
{
	/**
	 * Définition du serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Les éléments pour l'affichage graphique de la fenêtre durant la partie de jeu
	 */
	private final fenetreGrille fenetreGrille; 	// L'attribut renseigne sur la fenêtre de la Grille.
	private final MessageJoueur messageJoueur; 	// L'attribut renseigne sur le Message du Joueur qui doit jouer.

	/**
	 * Constructeur Partie
	 * @param fenetreEcran : La taille de l'écran
	 */
	public Partie(Main fenetreEcran)
	{
		this.setLayout(new BorderLayout(0,10));
		this.setBackground(Color.WHITE);

		this.fenetreGrille = new fenetreGrille(fenetreEcran);
		this.messageJoueur = new MessageJoueur(fenetreEcran);

		this.add(this.fenetreGrille, BorderLayout.CENTER);
		this.add(this.messageJoueur, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	/**
	 * Met à jour le fenêtre grille et le message joueur.
	 */
	public void majGraphique()
	{
		this.fenetreGrille.majGraphique();
		this.messageJoueur.majGraphique();
	}

	/**
	 * Fixe le mode sombre.
	 */
	public void setModeSombre()
	{
		this.fenetreGrille.setModeSombre();
		this.messageJoueur.setModeSombre();
		this.setBackground(Color.DARK_GRAY);
	}

	/**
	 * Fixe le mode clair.
	 */
	public void setModeClair()
	{
		this.fenetreGrille.setModeClair();
		this.messageJoueur.setModeClair();
		this.setBackground(Color.WHITE);
	}

	/**
	 * Ferme la Fenêtre de la Partie.
	 */
	public void fermerFenetrePartie()
	{
		this.fenetreGrille.fermerFenetreGrille();
		this.messageJoueur.fermerMessageJoueur();

		this.removeAll();
		this.setVisible(false);
	}

	/**
	 *  Classe : fenetreGrille
	 *  @author : Malik Makkes
	 *  date 	: 04/05/2022
	 *  Explication : On configure l'affichage de la grille de jeu avec une image
	 */
	private static class fenetreGrille extends JPanel implements ActionListener
	{
		/**
		 * Définition du serialVersionUID
		 */
		private static final long serialVersionUID = 1L;
		private final Main fenetreEcran; 	 // L'attribut renseigne sur la taille de l'écran

		/**
		 * Les éléments pour l'affichage graphique des boutons insérer et des jetons
		 */
		private final JPanel    EnsBtn; 	 // L'attribut renseigne sur l'ensemble des boutons.
		private final JButton[] tabBtnJeton; // L'attribut renseigne sur les boutons jetons.

		/**
		 * Constructeur fenetreGrille
		 * @param fenetreEcran : // La taille de l'écran
		 */
		public fenetreGrille(Main fenetreEcran)
		{
			this.fenetreEcran = fenetreEcran;
			this.setLayout(new BorderLayout());
			this.setBackground(Color.WHITE);

			this.EnsBtn = new JPanel();
			this.EnsBtn.setLayout(new GridLayout(1, this.fenetreEcran.getNbColonne()));
			this.EnsBtn.setBackground( Color.WHITE );

			this.tabBtnJeton = new JButton[this.fenetreEcran.getNbColonne()];
			
			File 		fichierBtnJeton 	= new File("src/images/fleche_position_jeton.png");
			ImageIcon 	imgIconBtnJeton 	= new ImageIcon(fichierBtnJeton.getPath());

			// Re-dimension de l'icône pour les boutons
			Image 		imgBtnJetonPrepare 	= imgIconBtnJeton.getImage();
			Image 		imgBtnJetonResize 	= imgBtnJetonPrepare.getScaledInstance(90,30, Image.SCALE_SMOOTH);
			imgIconBtnJeton 				= new ImageIcon(imgBtnJetonResize);

			for(int cptColonne = 0; cptColonne < this.fenetreEcran.getNbColonne(); cptColonne++)
			{
				this.tabBtnJeton[cptColonne] = new JButton(imgIconBtnJeton);
			}

			for(int cptColonne = 0; cptColonne < this.fenetreEcran.getNbColonne(); cptColonne++)
			{
				this.EnsBtn.add(this.tabBtnJeton[cptColonne]);
			}
			this.add(this.EnsBtn, BorderLayout.NORTH);

			for(int cptColonne = 0; cptColonne < this.fenetreEcran.getNbColonne(); cptColonne++)
			{
				this.tabBtnJeton[cptColonne].addActionListener(this);
			}
			
			this.setVisible(true);
		}

		/**
		 * Dessine le fond de la Grille
		 * @param g : Le graphisme
		 */
		public void paintComponent(Graphics g)
		{
			Image img;
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g);

			// Dessine les jetons
			int largeurEspace  = (int) (this.getWidth()  * ((double) 4/100 ));
			int tailleLargeur  = (int) (this.getWidth()  * ((double) 13/100));
			int tailleHauteur  = (int) (this.getHeight() * ((double) 15/100));

			for(int cptColonne = 0; cptColonne < this.fenetreEcran.getNbColonne(); cptColonne++)
			{
				int hauteurEspace = (int) (this.getHeight() * ((double) 9/100));
				for(int cptLigne = 0; cptLigne < this.fenetreEcran.getNbLigne(); cptLigne++)
				{
					char sCouleur = this.fenetreEcran.getJeton(cptLigne, cptColonne);
					img = getToolkit().getImage ( "src/images/jeton_" + sCouleur + ".png" );

					g2.drawImage ( img, largeurEspace, hauteurEspace, tailleLargeur, tailleHauteur, this);
					hauteurEspace += (int) (this.getHeight() * ((double) 15/100));
				}
				largeurEspace += (int) (this.getWidth() * ((double) 13/100));
			}

			// Bordure du plateau
			largeurEspace     = (int) (this.getWidth()  * ((double) 4/100 ));
			int hauteurEspace = (int) (this.getHeight() * ((double) 9/100 ));
			tailleLargeur     = (int) (this.getWidth()  * ((double) 90/100));
			tailleHauteur     = (int) (this.getHeight() * ((double) 89/100));

			int bordureEpaisseur = 5;

			g2.setStroke( new BasicStroke(bordureEpaisseur, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
			g2.setColor( new Color(0,41,255) );
			g2.drawRect( largeurEspace, hauteurEspace, tailleLargeur, tailleHauteur );

			// Réajuste la fenêtre contenant les boutons
			int margeGauche = (int) (this.getWidth() * ((double) 4/100));
			int margeDroite = (int) (this.getWidth() * ((double) 5/100));
			this.EnsBtn.setBorder( BorderFactory.createEmptyBorder(0, margeGauche, 0, margeDroite) );

			// Redimensionne les boutons
			tailleLargeur 	= (int) (this.getWidth()  * ((double) 5/100));
			tailleHauteur 	= (int) (this.getHeight() * ((double) 6/100));
			for(int cptColonne = 0; cptColonne<this.fenetreEcran.getNbColonne(); cptColonne++)
			{
				this.tabBtnJeton[cptColonne].setPreferredSize( new Dimension(tailleLargeur, tailleHauteur));
			}
		}

		/**
		 * Met à jour la grille.
		 */
		public void majGraphique()
		{
			this.repaint();
		}

		/**
		 * Fixe le mode sombre.
		 */
		public void setModeSombre()
		{
			this.EnsBtn.setBackground(Color.DARK_GRAY);
			this.setBackground(Color.DARK_GRAY);
		}

		/**
		 * Fixe le mode clair.
		 */
		public void setModeClair()
		{
			this.EnsBtn.setBackground(Color.WHITE);
			this.setBackground(Color.WHITE);
		}

		/**
		 * Ferme la fenêtre de la Grille.
		 */
		public void fermerFenetreGrille()
		{
			this.removeAll();
			this.setVisible(false);
		}

		/**
		 * Si le joueur clique sur l'un des boutons des actions.
		 */
		public void actionPerformed(ActionEvent e)
		{
			for(int cptColonne=0; cptColonne<this.tabBtnJeton.length; cptColonne++)
			{
				if (e.getSource() == this.tabBtnJeton[cptColonne])
				{
					boolean bAction = this.fenetreEcran.setJeton(cptColonne);

					if (bAction)
					{
						// Mis à jour du plateau après l'action jouée
						this.fenetreEcran.majGraphique();

						// Vérification du gagnant ou Vérification égalité
						if (this.fenetreEcran.estGagnant() || this.fenetreEcran.getGrillePleine())
						{
							if (this.fenetreEcran.estGagnant())
							{
								this.optionPaneVictoire();
							}
							else
							{
								this.optionPaneEgalite();
							}	
						}
						else
						{
							// Joueur suivant
							this.fenetreEcran.setJoueurSuivant();

							// Mis à jour du plateau pour le label message joueur
							this.fenetreEcran.majGraphique();
						}
					}
					else
					{
						String pseudoJoueur = this.fenetreEcran.getJoueurActif().getPseudoJoueur();
						String sMessErreur = pseudoJoueur + ", cette colonne est remplie de jetons.";
						JOptionPane.showMessageDialog(this, sMessErreur, "Action impossible", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		/**
		 * Option de choix lorsqu'un joueur gagne, soit il quitte soit il rejoue.
		 */
		private void optionPaneVictoire()
		{
			String 	pseudoJoueur1 	= this.fenetreEcran.getJoueurActif().getPseudoJoueur();
			String 	pseudoJoueur2 	= this.fenetreEcran.getJoueurInactif().getPseudoJoueur();

			String messagePartie 	= "Bravo " + pseudoJoueur1 + ", vous avez défoncé " + pseudoJoueur2;
			messagePartie 	+= "\n" + "Voulez vous rejouer ?";
			String messageTitre 	= "Victoire";
			int optConfirme 	= JOptionPane.showConfirmDialog(this, messagePartie, messageTitre, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (optConfirme == JOptionPane.YES_OPTION)
			{
				this.lancerNouvellePartie();
			}
			else
			{
				this.fenetreEcran.fermerJeu();
			}
		}

		/**
		 * Option de choix lorsque la partie est à égalité (aucuns des joueurs ne gagne), soit il quitte soit il rejoue.
		 */
		private void optionPaneEgalite()
		{
			String messagePartie = "Vous êtes à égalité, la grille est pleine";
			messagePartie += "\n" + "Voulez vous rejouer ?";
			String messageTitre 	= "Égalité";
			int optConfirme = JOptionPane.showConfirmDialog(this, messagePartie, messageTitre, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if ( optConfirme == JOptionPane.YES_OPTION)
			{
				this.lancerNouvellePartie();
			}
			else
			{
				this.fenetreEcran.fermerJeu();
			}
		}

		/**
		 * Lancer une nouvelle partie
		 */
		private void lancerNouvellePartie()
		{
			Joueur joueur1 = null;
			Joueur joueur2 = null;

			for (int cptJoueur = 0; cptJoueur<this.fenetreEcran.getNbJoueur(); cptJoueur++)
			{
				if ((cptJoueur % 2) == 0)
				{
					joueur1 = this.fenetreEcran.getJoueur(cptJoueur);
				}
				else
				{
					joueur2 = this.fenetreEcran.getJoueur(cptJoueur);
				}
			}

			this.fenetreEcran.reinitialisePartie();
			this.fenetreEcran.relancerJeu(joueur1, joueur2);
			this.majGraphique();
		}
	}

	/**
	 *  Classe : MessageJoueur
	 *  @author : Malik Makkes
	 *  date 	: 04/05/2022
	 *  Explication : On configure l'affichage du message de qui joue
	 */
	private static class MessageJoueur extends JPanel
	{
		/**
		 * Définition du serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Les éléments pour l'affichage graphique du message de qui joue
		 */
		private final Main   fenetreEcran; 			 // L'attribut renseigne sur la taille de l'écran
		private final JPanel panelLblMessageJoueur;  // L'attribut renseigne sur le panel contenant le message pour les joueurs.
		private final JLabel lblMessage; 	         // L'attribut renseigne sur le message du label.

		/**
		 * Constructeur PanelMessageJoueur
		 * @param fenetreEcran : La taille de l'écran
		 */
		public MessageJoueur(Main fenetreEcran)
		{
			this.fenetreEcran = fenetreEcran;
			this.setLayout(new BorderLayout());

			this.panelLblMessageJoueur = new JPanel();
			this.panelLblMessageJoueur.setLayout(new FlowLayout());
			this.panelLblMessageJoueur.setBackground(Color.WHITE);

			String pseudoJoueur = "";
			this.lblMessage = new JLabel(pseudoJoueur + ", c'est à vous de jouer !");
			this.lblMessage.setFont(new Font("Calibri", Font.BOLD, 40));
			this.panelLblMessageJoueur.add(this.lblMessage, JLabel.CENTER);
			this.add(this.panelLblMessageJoueur, BorderLayout.CENTER);
			this.setVisible(true);
		}

		/**
		 * Met à jour le message du joueur qui joue.
		 */
		public void majGraphique()
		{
			String pseudoJoueur = this.fenetreEcran.getJoueurActif().getPseudoJoueur();
			String messageJoueur = ", c'est à vous de jouer !";

			if (this.fenetreEcran.estGagnant() || this.fenetreEcran.getGrillePleine())
			{
				if (this.fenetreEcran.getGrillePleine())
				{
					pseudoJoueur 	= "";
					messageJoueur 	= "Égalité";
				}
				else
				{
					messageJoueur 	= " : Victoire";
				}	
			}

			this.lblMessage.setText(pseudoJoueur + messageJoueur);
			
			if (this.fenetreEcran.getJoueurActif().getCouleur() == 'J' && (!(this.fenetreEcran.getGrillePleine())))
			{
				this.lblMessage.setForeground(Color.ORANGE);
			}
			else if (this.fenetreEcran.getJoueurActif().getCouleur() == 'R' && (!(this.fenetreEcran.getGrillePleine())))
			{
				this.lblMessage.setForeground(Color.RED);
			}
			else
			{
				this.lblMessage.setForeground(Color.BLACK);
			}
		}

		/**
		 * Fixe le mode sombre.
		 */
		public void setModeSombre()
		{
			this.panelLblMessageJoueur.setBackground(Color.DARK_GRAY);
			this.setBackground(Color.DARK_GRAY);
		}

		/**
		 * Fixe le mode clair.
		 */
		public void setModeClair()
		{
			this.panelLblMessageJoueur.setBackground(Color.WHITE);
			this.setBackground(Color.WHITE);
		}

		/**
		 * Ferme la fenêtre du message du joueur qui joue.
		 */
		public void fermerMessageJoueur()
		{
			this.removeAll();
			this.setVisible(false);
		}
	}
}