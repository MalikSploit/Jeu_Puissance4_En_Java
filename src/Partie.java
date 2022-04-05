import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *  Classe : Partie
 *  @author : Malik Makkes et Anis Khalfallah
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
	private final fenetreGrille fenetreGrille; 	// L'attribut renseigne sur le PanelGrille.
	private final MessageJoueur messageJoueur; 	// L'attribut renseigne sur le PanelMessageJoueur.

	/**
	 * Constructeur Partie
	 * @param ctrl : Le contrôleur.
	 */
	public Partie(Main ctrl)
	{
		this.setLayout( new BorderLayout(0,10) );
		this.setBackground(Color.WHITE);

		this.fenetreGrille = new fenetreGrille(ctrl);
		this.messageJoueur = new MessageJoueur(ctrl);

		this.add(this.fenetreGrille, 			BorderLayout.CENTER);
		this.add(this.messageJoueur, 	BorderLayout.SOUTH);

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
	 *  @author : Malik Makkes et Anis Khalfallah
	 *  date 	: 04/05/2022
	 *  Explication : On configure l'affichage de la grille de jeu avec une image
	 */
	private static class fenetreGrille extends JPanel implements ActionListener
	{
		/**
		 * Définition du serialVersionUID
		 */
		private static final long serialVersionUID = 1L;
		private final Main main; 				            // L'attribut renseigne sur le contrôleur.

		/**
		 * Les éléments pour l'affichage graphique des boutons insérer et des jetons
		 */
		private final JPanel 		panelEnsBtn; 			// L'attribut renseigne sur le panel contenant les boutons.
		private final JButton[] 	tabBtnJeton; 			// L'attribut renseigne sur les boutons jetons.

		/**
		 * Constructeur fenetreGrille
		 * @param ctrl : Le contrôleur.
		 */
		public fenetreGrille(Main ctrl)
		{
			this.main = ctrl;
			this.setLayout( new BorderLayout() );
			this.setBackground( Color.WHITE );

			this.panelEnsBtn = new JPanel();
			this.panelEnsBtn.setLayout( new GridLayout(1, this.main.getNbColonne()) );
			this.panelEnsBtn.setBackground( Color.WHITE );

			this.tabBtnJeton = new JButton[this.main.getNbColonne()];
			
			File 		fichierBtnJeton 	= new File("src/images/fleche_position_jeton.png");
			ImageIcon 	imgIconBtnJeton 	= new ImageIcon(fichierBtnJeton.getPath());

			// Re-dimension de l'icône pour les boutons
			Image 		imgBtnJetonPrepare 	= imgIconBtnJeton.getImage();
			Image 		imgBtnJetonResize 	= imgBtnJetonPrepare.getScaledInstance(90,30, Image.SCALE_SMOOTH);
			imgIconBtnJeton 				= new ImageIcon(imgBtnJetonResize);

			for(int cptColonne = 0; cptColonne < this.main.getNbColonne(); cptColonne++)
			{
				this.tabBtnJeton[cptColonne] = new JButton(imgIconBtnJeton);
			}

			for(int cptColonne = 0; cptColonne < this.main.getNbColonne(); cptColonne++)
			{
				this.panelEnsBtn.add(this.tabBtnJeton[cptColonne]);
			}
			this.add(this.panelEnsBtn, BorderLayout.NORTH);

			for(int cptColonne = 0; cptColonne < this.main.getNbColonne(); cptColonne++)
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
			Image      img;
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g);

			// Dessine les jetons
			int largeurEspace 	= (int) ( this.getWidth() 	* ( (double) 4/100 	) );
			int tailleLargeur 	= (int) ( this.getWidth() 	* ( (double) 13/100 ) );
			int tailleHauteur 	= (int) ( this.getHeight() 	* ( (double) 15/100 ) );

			for(int cptColonne = 0; cptColonne < this.main.getNbColonne(); cptColonne++)
			{
				int hauteurEspace 	= (int) ( this.getHeight() 	* ( (double) 9/100 	) );
				for(int cptLigne = 0; cptLigne < this.main.getNbLigne(); cptLigne++)
				{
					char sCouleur = this.main.getJeton(cptLigne, cptColonne);
					img = getToolkit().getImage ( "src/images/jeton_" + sCouleur + ".png" );

					g2.drawImage ( img, largeurEspace, hauteurEspace, tailleLargeur, tailleHauteur, this );
					hauteurEspace += (int) ( this.getHeight() * ( (double) 15/100 ) );
				}
				largeurEspace += (int) ( this.getWidth() * ( (double) 13/100 ) );
			}

			// Bordure du plateau
			largeurEspace = (int) ( this.getWidth() 	* ( (double) 4/100 ) );
			int hauteurEspace = (int) ( this.getHeight() 	* ( (double) 9/100 ) );
			tailleLargeur = (int) ( this.getWidth() 	* ( (double) 90/100 ) );
			tailleHauteur = (int) ( this.getHeight() 	* ( (double) 89/100 ) );

			int bordureEpaisseur = 5;

			g2.setStroke( new BasicStroke(bordureEpaisseur, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
			g2.setColor( new Color(0,41,255) );
			g2.drawRect( largeurEspace, hauteurEspace, tailleLargeur, tailleHauteur );

			// Réajuste le panel contenant les boutons
			int margeGauche = (int) ( this.getWidth() 	* ( (double) 4/100 	) );
			int margeDroite = (int) ( this.getWidth() 	* ( (double) 5/100 	) );
			this.panelEnsBtn.setBorder( BorderFactory.createEmptyBorder(0, margeGauche, 0, margeDroite) );

			// Redimensionne les boutons
			tailleLargeur 	= (int) ( this.getWidth() 	* ( (double) 5/100 ) );
			tailleHauteur 	= (int) ( this.getHeight() 	* ( (double) 6/100 ) );
			for(int cptColonne = 0; cptColonne<this.main.getNbColonne(); cptColonne++)
			{
				this.tabBtnJeton[cptColonne].setPreferredSize( new Dimension(tailleLargeur, tailleHauteur) );
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
			this.panelEnsBtn.setBackground(Color.DARK_GRAY);
			this.setBackground(Color.DARK_GRAY);
		}

		/**
		 * Fixe le mode clair.
		 */
		public void setModeClair()
		{
			this.panelEnsBtn.setBackground(Color.WHITE);
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
				if ( e.getSource() == this.tabBtnJeton[cptColonne] )
				{
					boolean bAction = this.main.setJeton(cptColonne);

					if ( bAction )
					{
						// Mis à jour du plateau après l'action jouée
						this.main.majGraphique();

						// Vérification du gagnant ou Vérification égalité
						if ( this.main.estGagnant() || this.main.getGrillePleine() )
						{
							if ( this.main.estGagnant() )
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
							this.main.setJoueurSuivant();

							// Mis à jour du plateau pour le label message joueur
							this.main.majGraphique();
						}
					}
					else
					{
						String pseudoJoueur = this.main.getJoueurActif().getPseudoJoueur();
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
			String 	pseudoJoueur1 	= this.main.getJoueurActif().getPseudoJoueur();
			String 	pseudoJoueur2 	= this.main.getJoueurInactif().getPseudoJoueur();

			String messagePartie 	= "Bravo " + pseudoJoueur1 + ", vous avez défoncé " + pseudoJoueur2;
			messagePartie 	+= "\n" + "Voulez vous rejouer ?";
			String messageTitre 	= "Victoire";
			int optConfirme 	= JOptionPane.showConfirmDialog(this, messagePartie, messageTitre, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if ( optConfirme == JOptionPane.YES_OPTION )
			{
				this.lancerNouvellePartie();
			}
			else
			{
				this.main.fermerJeu();
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
				this.main.fermerJeu();
			}
		}

		/**
		 * Lancer une nouvelle partie
		 */
		private void lancerNouvellePartie()
		{
			Joueur joueur1 = null;
			Joueur joueur2 = null;

			for (int cptJoueur = 0; cptJoueur<this.main.getNbJoueur(); cptJoueur++)
			{
				if ( (cptJoueur % 2) == 0 )  
				{
					joueur1 = this.main.getJoueur(cptJoueur);
				}
				else
				{
					joueur2 = this.main.getJoueur(cptJoueur);
				}
			}

			this.main.reinitialisePartie();
			this.main.relancerJeu(joueur1, joueur2);
			this.majGraphique();
		}
	}

	/**
	 *  Classe : MessageJoueur
	 *  @author : Malik Makkes et Anis Khalfallah
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
		private final Main main; 					     // L'attribut renseigne sur le contrôleur.
		private final JPanel 	 panelLblMessageJoueur;  // L'attribut renseigne sur le panel contenant le message pour les joueurs.
		private final JLabel 	 lblMessage; 	 // L'attribut renseigne sur le message du label.

		/**
		 * Constructeur PanelMessageJoueur
		 * @param ctrl : Le contrôleur.
		 */
		public MessageJoueur(Main ctrl)
		{
			this.main = ctrl;
			this.setLayout( new BorderLayout() );

			this.panelLblMessageJoueur = new JPanel();
			this.panelLblMessageJoueur.setLayout( new FlowLayout() );
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
			String pseudoJoueur = this.main.getJoueurActif().getPseudoJoueur();
			String messageJoueur = ", c'est à vous de jouer !";

			if ( this.main.estGagnant() || this.main.getGrillePleine() )
			{
				if ( this.main.getGrillePleine() )
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
			
			if ( this.main.getJoueurActif().getCouleur() == 'J' && (! (this.main.getGrillePleine()) ) )
			{
				this.lblMessage.setForeground( Color.ORANGE );
			}
			else if ( this.main.getJoueurActif().getCouleur() == 'R' && (! (this.main.getGrillePleine()) ) )
			{
				this.lblMessage.setForeground( Color.RED );
			}
			else
			{
				this.lblMessage.setForeground( Color.BLACK );
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