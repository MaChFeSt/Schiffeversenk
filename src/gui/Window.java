package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Ai.*;
import Logik.*;
import gui.*;
import gui.Window;


//********** 2er SCHIFF : 		Waka
//********** 3er SCHIFF :		Tentacle
//********** 4er SCHIFF :		Crab
//********** 5er SCHIFF :		Ghost
//********** 6er SCHIFF : 		Rose
//********** 7er SCHIFF :		Dragon



@SuppressWarnings("serial")
public class Window extends JFrame{
	
	static int pos [] = new int[2];
	public static int startint = 0;
	public static int l = 3;
	Font font2 = new Font("SansSerif", Font.BOLD, 30);
	static public int size = 0;
	
	JTextField gridsize;
	JLabel setSizeText;
	public static JLayeredPane lp;
	JLabel placeShipsText;
	static JLabel myT;
	static JLabel aiT;
	public static JLabel won;
	public static JLabel lost;
	JButton place, start;
	public static JButton menu;
	public static JButton quit;
	Spielfeld feld;
	public static Spielfeld myFeld, aiFeld;
	public static Object[][] myGrid;
	public static Object[][] aigrid;
	public static boolean myTurn = false, aiTurn = false, clicked=false;
	
	
	public Window () {
		
		
	
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
					new Gui();
					
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
							confirm.setVisible(false);
							setSizeText.setVisible(false);
							gridsize.setVisible(false);
							lp.validate();
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
		
		
	}
	
	public void goPlaceShipsScreen() {
		ImageIcon placeShip = new ImageIcon(Window.class.getResource("PlaceShips.png"));
		placeShipsText = new JLabel();
		placeShipsText.setIcon(placeShip);
		placeShipsText.setBounds(355, 0, 550, 100);
		
		
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
		        
		        
				// test
		        System.out.println("********** MY FELD **********************");
				for (int i = 0; i < myGrid.length; i++) {
		            for (int j = 0; j < myGrid[i].length; j++)
		                System.out.print(myGrid[i][j].toString() + ", ");
		            System.out.println();
		            System.out.println();
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
							lp.add(myFeld, (20),l++);
							
//							// test
//					        System.out.println("********** MY FELD **********************");
//							for (int i = 0; i < myGrid.length; i++) {
//					            for (int j = 0; j < myGrid[i].length; j++)
//					                System.out.print(myGrid[i][j].toString() + ", ");
//					            System.out.println();
//					            System.out.println();
//					        }
	
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
							
							
							// ********* SPIELLOGIK STARTEN ***********
//							SwingUtilities.invokeLater(new Runnable(){
//								public void run() {
//										SpielLogikAI logik = new SpielLogikAI();
//										logik.spielStart();
//								}
//							});
//							
							
							// test
							System.out.println("********** AI FELD **********************");
							for (int i = 0; i < aigrid.length; i++) {
					            for (int j = 0; j < aigrid[i].length; j++)
					                System.out.print(aigrid[i][j].toString() + ", ");
					            System.out.println();
					            System.out.println();
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
	
	static public Object[][] getmyGrid() {
		return myGrid;
	}
	
	static public Object[][] getaigrid() {
		return aigrid;
	}

	static public int getBoardsize() {
		return size; 
	}
	
	public static void myTurn(){
		
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
		myTurn = true;
		aiT.setVisible(false);
		myT.setVisible(true);
		lp.validate();
		}
	
	public static void aiTurn(){
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
		myTurn = false;
		myT.setVisible(false);
		aiT.setVisible(true);
		
		if(aishot.aischiesst(size, myGrid)) {
			updateGrid(myGrid, aigrid);	
			lp.validate();
			aiTurn();
		}
		else {
			updateGrid(myGrid, aigrid);
			lp.validate();
			myTurn();
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
	
	public static void updateGrid(Object [][] my, Object [][] ai) {
		
		lp.remove(myFeld);
		lp.remove(aiFeld);
		lp.validate();
		myGrid = my;
		aigrid= ai;
		// test
		System.out.println("********** AI FELD **********************");
		for (int i = 0; i < aigrid.length; i++) {
            for (int j = 0; j < aigrid[i].length; j++)
                System.out.print(aigrid[i][j].toString() + ", ");
            System.out.println();
            System.out.println();
		}
		myFeld = new Spielfeld (myGrid, size);
		myFeld.setBounds(50, 100, 520, 520);
		lp.add(myFeld, (l+1),l+1);
		aiFeld = new Spielfeld (size, aigrid);

		aiFeld.setBounds(710,100, 520, 520);
		lp.add(aiFeld, (l+1),l+1);
		
		lp.repaint();
		lp.validate();
	}
}


