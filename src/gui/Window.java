package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import Ai.*;
import Logik.*;
import Network.*;
import gui.Window;


//********** 2er SCHIFF : 		Waka
//********** 3er SCHIFF :		Tentacle
//********** 4er SCHIFF :		Crab
//********** 5er SCHIFF :		Ghost
//********** 6er SCHIFF : 		Rose
//********** 7er SCHIFF :		Dragon



/**	<h1>Window</h1>
 *	Die Klasse Window erstellt unseren Spielbildschirm,
 *	auf dem das Spielfeld gebaut wird.
 */
@SuppressWarnings("serial")
public class Window extends JFrame{
	
	static int pos [] = new int[2];
	public static int startint = 0;
	public static int l = 3;
	public Font font2 = new Font("SansSerif", Font.BOLD, 30);
	static public int size;
	static int mode;
	public JTextField gridsize;
	public JLabel setSizeText;
	public static JLayeredPane lp;
	public JLabel placeShipsText;
	public static JLabel myT;
	public static JLabel aiT;
	public static JLabel won;
	public static JLabel lost;
	public JButton place, start;
	public static JButton menu;
	public static JButton quit;
	public Spielfeld feld;
	public static Spielfeld myFeld, aiFeld;
	public static Object[][] myGrid;
	public static Object[][] aigrid;
	public static boolean myTurn = false, aiTurn = false, clicked=false, setclicked=false;

	
	/** <h1>Konstruktor</h1>
	 * 	Hier werden alle JComponents auf den Panel gebaut.
	 * 	Das Spiel wird im Spielfeldgroesse-Bildschirm gestartet.
	 */
	public Window () {
		
		startint= 0;
		size = 0;
	
		lp = getLayeredPane();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();
		setSize(1280,720);
		setLocationRelativeTo(null);
		
		ImageIcon sizeText = new ImageIcon(Window.class.getResource("SetBoardSize.png"));
		setSizeText = new JLabel();
		setSizeText.setIcon(sizeText);
		setSizeText.setBounds(355, 0, 500, 100);
		
		JLabel early = new JLabel("EARLY ACCESS v.0.42");
		early.setBounds(50, 0, 200, 50);
		lp.add(early);
		// DARKMODE
		if(Gui.getDark()) {
			JPanel darkback = new JPanel ();
			darkback.setBounds(0, 0, 1280, 720);
			darkback.setBackground(Color.BLACK);
			lp.add(darkback, (0), 0);
		}
		
		// TURN HINTERGRUND		
		ImageIcon myBG = new ImageIcon(Window.class.getResource("myturn.png"));
		myT = new JLabel();
	    myT.setIcon(myBG);
	    myT.setBounds(666, 0, 600, 680);
	      
		ImageIcon aiBG = new ImageIcon(Window.class.getResource("enemyturn.png"));
		aiT = new JLabel();
	    aiT.setIcon(aiBG);
	    aiT.setBounds(11, 0, 600, 680);
	    lp.add(myT,(0),0);
	    lp.add(aiT,(0),0);
	    myT.setVisible(false);
	    aiT.setVisible(false);

	    
	    //WINNING - LOSING - SCREEN
	    ImageIcon wonim = new ImageIcon(Window.class.getResource("won.png"));
		won = new JLabel();
		won.setIcon(wonim);
	    won.setBounds(170,0, 900, 506);
	    lp.add(won, (100),100);
	    
	    ImageIcon lostim = new ImageIcon(Window.class.getResource("Losing.png"));
		lost = new JLabel();
		lost.setIcon(lostim);
	    lost.setBounds(170,0, 900, 506);
	    lp.add(lost, (100),100);
	    
	    ImageIcon iconquit = new ImageIcon(Gui.class.getResource("quit.png"));
		 quit = new JButton(iconquit);
		 quit.setIcon(iconquit);
		 quit.setContentAreaFilled(false);
		 quit.setBorderPainted(false);
		 quit.setOpaque(false);
		 quit.setBounds(380, 500, 200, 71);
		 lp.add(quit,(102),102);
		
		 quit.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			 }
		 });
	    
		 ImageIcon iconmenu = new ImageIcon(Gui.class.getResource("menu.png"));
			menu = new JButton(iconmenu);
			menu.setIcon(iconmenu);
			menu.setContentAreaFilled(false);
			menu.setBorderPainted(false);
			menu.setOpaque(false);
			menu.setBounds(700, 500, 200, 71);
			lp.add(menu,(102),102);

			menu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					dispose();
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							new Gui();
						}
					});
					
					
	            	lp.setVisible(false);
				}
			});
		 
		menu.setVisible(false);
		quit.setVisible(false);
		lost.setVisible(false);
		won.setVisible(false);
		 
		// TEXTEINGABE
		gridsize = new JTextField(3);
		gridsize.setBounds(855, 22, 70, 50);
		gridsize.setFont(font2);
		gridsize.setHorizontalAlignment(JTextField.CENTER); 
	    
		
		// CONFIRMBUTTON
		ImageIcon confB = new ImageIcon(Window.class.getResource("confirmbutton.png"));
		Image confBu= confB.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH);
		
		JButton confirm = new JButton();
		confirm.setIcon(new ImageIcon(confBu) );
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(false);
		confirm.setOpaque(false);
					
					
			//********* MOUSELISTENER *********
			confirm.addMouseListener(new MouseAdapter() {
			
				public void mouseClicked(MouseEvent e){
			
					if (size >= 5 && size <=30) {
						mode=Gui.getMode();
						//////// HOST GAME BOARD SIZE SENDEN
						if (mode == 1) {
							Gui.host1.setSize(size);
							Gui.host1.messageWithSize();
					
						}
						confirm.setVisible(false);
						setSizeText.setVisible(false);
						gridsize.setVisible(false);
						myGrid = new Object [size][size];
						aigrid = new Object [size][size];
						lp.validate();
						//In den Platzierbildschirm wechseln
						goPlaceShipsScreen();
							
							
									
					}
					else {
							JOptionPane.showMessageDialog(null,
										    "Nope.");
					}

				}
			});
		
		
		
		
		confirm.setBounds(825, 550, 200, 70);
		lp.add(confirm,(1),1);
		confirm.setVisible(false);
		
	    lp.add(setSizeText, (1),1);
		lp.add(gridsize, (1),1);
		
		
		gridsize.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				     Integer.parseInt(gridsize.getText());
				     
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,
						    "Please Choose a Number between 5 and 30.");
					return;
				}
				size = Integer.parseInt(gridsize.getText());
				if (size > 30 || size < 5) {
					JOptionPane.showMessageDialog(null,
						    "Please Choose a Number between 5 and 30.");	
				}
				else {
					
					feld = new Spielfeld(size);
					feld.setBounds(50, 100, 520, 520);
					lp.add(feld, (20),20);
					confirm.setVisible(true); 
					lp.validate();
				}
			}
		});
		
		setVisible(true);
		
		mode = Gui.getMode();	
		
		if (mode == 1) {   ///HOST SPIEL
			
			// wir müssen die Boardsize senden
			// METHODE, DIE IN AI GRID 20 oder 21 reinschreibt!
			// überprüfen ob gewonnen 
		}
		
		if (mode == 2) {   ///JOIN SPIEL
			
			/// WIR MÜSSEN DIE BOARDSIZE BEKOMMEN

			size = Client.size;
			setSizeText.setVisible(false);
			gridsize.setVisible(false);
			myGrid = new Object [size][size];
			aigrid = new Object [size][size];
			lp.validate();
			goPlaceShipsScreen();
		}
		if (mode ==3) {
			setSizeText.setVisible(false);
			gridsize.setVisible(false);
			lp.validate();

			try {

	            Scanner sc = new Scanner(new File(System.getProperty("user.dir") + File.separator+ "size.txt"));
	            int rows = 1;
	            int columns = 1;
	            int[][] fieldsize = new int[rows][columns];
	            while (sc.hasNextLine()) {
	                for (int i = 0; i < fieldsize.length; i++) {
	                    String[] line = sc.nextLine().trim().split(" ");
	                    for (int j = 0; j < line.length; j++) {
	                        fieldsize[i][j] = Integer.parseInt(line[j]);
	                    size=fieldsize[0][0];
	                    }
	                }
	            }

	        }catch (IOException i){

	        }
			//--------------------------------------------------------------------------------
			myGrid = new Object [size][size];
			aigrid = new Object [size][size];
		        try {

		            Scanner sc = new Scanner(new File(System.getProperty("user.dir") + File.separator+ "myGrid.txt"));
		            int rows = size;
		            int columns = size;
		            Object[][] myfield = new Object[rows][columns];
		            while (sc.hasNextLine()) {
		                for (int i = 0; i < myfield.length; i++) {
		                    String[] line = sc.nextLine().trim().split(" ");
		                    for (int j = 0; j < line.length; j++) {
		                        myfield[i][j] = Integer.parseInt(line[j]);
		                        myGrid=myfield;
		                   }
		                }
		            }
		            System.out.println(Arrays.deepToString(myfield));

		            for (int i = 0; i < myfield.length; i++) {
		                for (int j = 0; j < myfield[i].length; j++)
		                    System.out.print(myfield[i][j].toString() + ", ");
		                System.out.println();
		                System.out.println();
		            }

		        }catch (IOException i){

		        }

		        try {

		            Scanner sc = new Scanner(new File(System.getProperty("user.dir") + File.separator+ "aigrid.txt"));
		            int rows = size;
		            int columns = size;
		            Object[][] aifield = new Object[rows][columns];
		            while (sc.hasNextLine()) {
		                for (int i = 0; i < aifield.length; i++) {
		                    String[] line = sc.nextLine().trim().split(" ");
		                    for (int j = 0; j < line.length; j++) {
		                        aifield[i][j] = Integer.parseInt(line[j]);
		                        aigrid=aifield;
		                   }
		                }
		            }
		            System.out.println(Arrays.deepToString(aifield));

		            for (int i = 0; i < aifield.length; i++) {
		                for (int j = 0; j < aifield[i].length; j++)
		                    System.out.print(aifield[i][j].toString() + ", ");
		                System.out.println();
		                System.out.println();
		            }

		        }catch (IOException i){

		        }

		        lp.repaint();
		        lp.revalidate();
		        updateGrid(myGrid, aigrid);

		        myTurn=true;
		        myTurn();

		    }

		 
		
		lp.validate();
	}
	
	/** <h1>Methode goPlaceShipsScreen</h1>
	 * 	
	 * hier wechselt man in den Bildschirm, bei dem man die Schiffe platzieren kann.
	 * Mit einem Click auf den Place Button werden die Schiffe zufÃ¤llig platziert.
	 * Diesen Vorgang kann man beliebig oft wiederholen.
	 * Bei erfolgreichem Platzieren erscheint der Start-Button, mit dem man das Spiel startet. 
	 */
	public void goPlaceShipsScreen() {
		ImageIcon placeShip = new ImageIcon(Window.class.getResource("PlaceShips.png"));
		placeShipsText = new JLabel();
		placeShipsText.setIcon(placeShip);
		placeShipsText.setBounds(355, 0, 550, 100);
		
		if(mode==2) {
			feld = new Spielfeld(size);
			feld.setBounds(50, 100, 520, 520);
			lp.add(feld, (20),20);
			lp.validate();
		}
		
		place = new JButton();
		ImageIcon placeI = new ImageIcon(Window.class.getResource("place.png"));
		Image placeIm= placeI.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH);
		
		place.setIcon(new ImageIcon(placeIm));
		place.setContentAreaFilled(false);
		place.setBorderPainted(false);
		place.setOpaque(false);
		place.setBounds(825, 300 , 200, 70);
		lp.add(place, (1),2);
		lp.add(placeShipsText,(1),2);
		
		//********* MOUSELISTENER *********
		place.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e){
				
				
				lp.remove(feld);
				lp.validate();

				myGrid = new Object[size][size];
				
				AI me = new AI();
		        int end=0;
		        myGrid = me.setaiships(size);
		        while (end==0){

		            if(!myGrid[0][0].equals(-1)){
		                end=1;
		            }
		            else{
		                me = new AI();
		                myGrid = me.setaiships(size);
		            }
		        } 
		        
				
				Spielfeld newFeld = new Spielfeld (myGrid, size);
				lp.add(newFeld, (l++),l++);
				System.out.println(l);
				newFeld.setBounds(50, 100, 520, 520);

				
				if(startint==0) {
					start = new JButton();
					ImageIcon starti = new ImageIcon(Window.class.getResource("startbutton.png"));
					Image startim= starti.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
					startint = 2;
					start.setIcon(new ImageIcon(startim));
					start.setContentAreaFilled(false);
					start.setBorderPainted(false);
					start.setOpaque(false);
					start.setBounds(775, 500 , 300, 100);
					lp.add(start, (1),2);
				}
				
				
					//********* MOUSELISTENER *********
					start.addMouseListener(new MouseAdapter() {
					
						public void mouseClicked(MouseEvent e){
							
							lp.remove(start);
							lp.remove(place);
							lp.remove(placeShipsText);
							lp.remove(newFeld);
							lp.validate();
							myFeld = new Spielfeld (myGrid, size);
							myFeld.setBounds(50, 100, 520, 520);
							lp.add(myFeld, (l++),l++);
							

							if (mode == 1 || mode == 2) {  // wenn host oder joined, dann ist aiGrid leer
								aigrid = new Object[size][size];
								
								for (int i = 0; i < aigrid.length; i++) {
						            for (int j = 0; j < aigrid[i].length; j++)
						                aigrid[i][j]=0;
								}
								
								if (mode==2) {
									Gui.client1.messageGameReady();
									
									aiFeld = new Spielfeld (size, aigrid);
									lp.add(aiFeld, (l+1),l+1);
									aiFeld.setBounds(710,100, 520, 520);
								
									start.setVisible(false);
									
									aiTurn();
									
									lp.repaint();
									lp.validate();
									
									SwingUtilities.invokeLater(new Runnable(){
										public void run(){
											Gui.client1.listen();
										}
									});
								}
								if (mode==1) {
									Gui.host1.listen();
									start.setVisible(false);
									
								}
							}
							else { // wenn versusAI, dann wird der array mit Schiffen gefüllt.
								AI a = new AI();
								int end=0;
								aigrid = a.setaiships(size);
								while (end==0){
									if(!aigrid[0][0].equals(-1)){
					                end=1;
									}
									else{
					                a = new AI();
					                aigrid = a.setaiships(size);
									}
								} 
					        
					        aiFeld = new Spielfeld (size, aigrid);
							lp.add(aiFeld, (21),l);
							aiFeld.setBounds(710,100, 520, 520);
						
							start.setVisible(false);
							
							myTurn();
							
							lp.repaint();
							lp.validate();
							}
							
						}
						
					});
				
				lp.repaint();
				lp.validate();
				
			}
			
		});
		
		
		lp.repaint();
		lp.validate();
	}
	
	public static void hostStart() {
		aiFeld = new Spielfeld (size, aigrid);
		lp.add(aiFeld, (21),l);
		aiFeld.setBounds(710,100, 520, 520);

		
		myTurn();
		
		lp.repaint();
		lp.validate();
	}
	
	/**<h1>Methode getmyGrid</h1>
	 * 
	 * @return myGrid 	mein Spielfeld wird zurueckgegeben
	 */
	static public Object[][] getmyGrid() {
		return myGrid;
	}
	
	
	/**<h1>Methode getaigrid</h1>
	 * 
	 * @return aigrid   das gegnerische Spielfeld wird zurueckgegeben
	 */
	static public Object[][] getaigrid() {
		return aigrid;
	}

	
	/**<h1>Methode getBoardsize</h1>
	 * 
	 * @return boardsize	Die eingegeben Spielfeldgroesse wird zurueckgegeben
	 */
	static public int getBoardsize() {
		return size; 
	}
	
	/**<h1>Methode myTurn</h1>
	 * 
	 * Diese Methode wird aufgerufen, sobald der gegnerische Zug vorbei ist, oder
	 * nach einem erfolgreichen Treffer vom Spieler.
	 * 
	 * myTurn wird auf true gesetzt, damit der MouseClicker auf den gegnerischen
	 * Feld aktiviert wird.
	 */
	public static void myTurn(){
		
		myTurn = true;
		aiT.setVisible(false);
		myT.setVisible(true);

		if(myTurn=true) {
			ImageIcon iconmenu = new ImageIcon(Window.class.getResource("settingsingame.png"));
			Image immenu= iconmenu.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			JButton igmenu = new JButton(new ImageIcon(immenu));
			igmenu.setIcon(iconmenu);
			igmenu.setContentAreaFilled(false);
			igmenu.setBorderPainted(false);
			igmenu.setOpaque(false);
			igmenu.setBounds(50, 40, 50, 50);
			lp.add(igmenu,(102),102);
			
			setclicked=false;
			ImageIcon backg = new ImageIcon(Window.class.getResource("settingsback.png"));
			JLabel setback = new JLabel();
			setback.setIcon(backg);
			setback.setBounds(20, 18, 256, 400);
			lp.add(setback,(101), 101);
			setback.setVisible(false);
			
			ImageIcon iconquit = new ImageIcon(Gui.class.getResource("quit.png"));
			JButton quiti = new JButton(iconquit);
			quiti.setIcon(iconquit);
			quiti.setContentAreaFilled(false);
			quiti.setBorderPainted(false);
			quiti.setOpaque(false);
			quiti.setBounds(50, 300, 200, 71);
			lp.add(quiti,(102),102);
			quiti.setVisible(false);
			
			quiti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);	
				}
			 });
			
			ImageIcon iconsave = new ImageIcon(Window.class.getResource("save.png"));
			JButton savi = new JButton(iconsave);
			savi.setIcon(iconsave);
			savi.setContentAreaFilled(false);
			savi.setBorderPainted(false);
			savi.setOpaque(false);
			savi.setBounds(50, 120, 200, 71);
			lp.add(savi,(102),102);
			savi.setVisible(false);
			
			savi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//--------------------------------------------myGrid------------------------------------------------
					Object[][] myGrid = getmyGrid();
			        try (
			        		//myGrid.txt
			                PrintStream output = new PrintStream(new File(System.getProperty("user.dir") + File.separator+ "myGrid.txt"));) {
			            for (int i = 0; i < myGrid.length;i++) {
			                String s= "";
			                for (int j = 0; j < myGrid[i].length; j++) {
			                    s+=  myGrid[i][j] + " ";
			                }
			                output.println(s);
			            }
			            output.close();
			        } catch (FileNotFoundException z) {
			            z.printStackTrace();
			        }
			        
			        //-------------------------------------------aigrid.txt---------------------------------------------------
			        Object[][] aiGrid = getaigrid();
			        try (
			                PrintStream output = new PrintStream(new File(System.getProperty("user.dir") + File.separator+ "aigrid.txt"));) {
			            for (int i = 0; i < aiGrid.length;i++) {
			                String s= "";
			                for (int j = 0; j < aiGrid[i].length; j++) {
			                    s+=  aiGrid[i][j] + " ";
			                }
			                output.println(s);
			            }
			            output.close();
			        } catch (FileNotFoundException z) {
			            z.printStackTrace();
			        }
			        //--------------------------------------------size.txt-----------------------------------------------


			        try (
			                PrintStream output = new PrintStream(new File(System.getProperty("user.dir") + File.separator+ "size.txt"));) {
			        	int s=size;
			        	output.println(s);
			            output.close();
			        } catch (FileNotFoundException z) {
			            z.printStackTrace();
			        }

			        quiti.setVisible(false);
					setback.setVisible(false);
					savi.setVisible(false);
					setclicked=false;
					lp.revalidate();
	
				}
			 });
			
			igmenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(setclicked==false) {
						setback.setVisible(true);
						quiti.setVisible(true);
						savi.setVisible(true);
						lp.revalidate();
						setclicked=true;
					}
					else if(setclicked==true) { 
						quiti.setVisible(false);
						setback.setVisible(false);
						savi.setVisible(false);
						setclicked=false;
						lp.revalidate();
					}
				}
			 });
		}
		lp.validate();
		}
	
	
	/**<h1>Methode aiTurn</h1>
	 * 
	 * Diese Methode wird aufgerufen, sobald der Gegner einen erfolgreichen Treffer hatte
	 * oder der Zug des Spielers vorbei ist.
	 * Hier wird auch die Methode aufgerufen, mit der man sieht ob der Gegner getroffen hat
	 * und ob das Spiel damit beendet ist.
	 * 
	 */
	public static void aiTurn(){

		
		myTurn = false;
		myT.setVisible(false);
		aiT.setVisible(true);
		
		mode=Gui.getMode();
		
		
		if(mode == 1) {
//			Gui.host1.listen();
		}
		if(mode==2 ) {
			//WArten auf treffer
//			Gui.client1.listen();
		}
		if(mode==0 || mode == 3 || mode == 4) {
			if(aishot.aischiesst(size, myGrid)) {
				updateGrid(myGrid, aigrid);	
				lp.validate();
				//CHECK IF WON
			
				if(SpielLogikAI.aiSpielfeldDurchlaufen()) {
					System.out.println("YOU WIN");
					won.setVisible(true);
					menu.setVisible(true);
					quit.setVisible(true);
				}
				if(SpielLogikAI.spielerSpielfeldDurchlaufen()) {
					System.out.println("YOU LOST");
					lost.setVisible(true);
					menu.setVisible(true);
					quit.setVisible(true);
				}
			
			aiTurn();
			}
			else {
				updateGrid(myGrid, aigrid);
				lp.validate();
				myTurn();
			}
		}	
		lp.validate();
		}
	
	
			
	
	public static void setClickedFalse() {
		clicked = false;
	}
	
	public static void setClickedTrue() {
		clicked = true;
	}
	
	public static boolean getClicked() {
		return clicked;
	}
	
	
	/**<h1>Methode updateGrid</h1>
	 * @param mein Spielfeld
	 * @param gegnerisches Spielfeld
	 */
	public static void updateGrid(Object [][] my, Object [][] ai) {
		
		if (mode != 3) {
			lp.remove(myFeld);
			lp.remove(aiFeld);
			lp.validate();

		}
		if(mode==3) {
			mode=4;
		}
		myGrid = my;
		aigrid= ai;

		myFeld = new Spielfeld (myGrid, size);
		myFeld.setBounds(50, 100, 520, 520);
		lp.add(myFeld, (l+5),l+5);
		
		aiFeld = new Spielfeld (size, aigrid);
		aiFeld.setBounds(710,100, 520, 520);
		lp.add(aiFeld, (l+6),l+6);
		l++;
		lp.repaint();
		lp.validate();
		
	}
}
