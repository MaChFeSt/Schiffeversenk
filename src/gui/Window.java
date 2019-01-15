package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Ai.*;
import Logik.*;
import gui.*;

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
	public int l = 3;
	Font font2 = new Font("SansSerif", Font.BOLD, 30);
	static public int size = 0;
	ConfirmButton conf;
	JTextField gridsize;
	JLabel setSizeText;
	JLayeredPane lp;
	JLabel placeShipsText, myT, aiT;
	JButton place, start;
	Spielfeld feld;
	public static Spielfeld myFeld, aiFeld;
	public static Object[][] myGrid;
	public static Object[][] aigrid;
	public static boolean myTurn = false, aiTurn = false;

	
	
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

		// TEXTEINGABE
		gridsize = new JTextField(3);
		gridsize.setBounds(855, 22, 70, 50);
		gridsize.setFont(font2);
		gridsize.setHorizontalAlignment(JTextField.CENTER); 
	    
		
		// CONFIRMBUTTON
		conf = new ConfirmButton();
		conf.setBounds(825, 550, 200, 70);
		lp.add(conf,(1),1);
		conf.setVisible(false);
		
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
					conf.setVisible(true); 
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
							SwingUtilities.invokeLater(new Runnable(){
								public void run() {
										SpielLogikAI logik = new SpielLogikAI();
										logik.spielStart();
								}
							});
							
							
	//						// test
	//						System.out.println("********** AI FELD **********************");
	//						for (int i = 0; i < aigrid.length; i++) {
	//				            for (int j = 0; j < aigrid[i].length; j++)
	//				                System.out.print(aigrid[i][j].toString() + ", ");
	//				            System.out.println();
	//				            System.out.println();
	//						}
							
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
	
	public void myTurn(){
		myTurn = true;
		myT.setVisible(true);
	
		lp.validate();
		
	}
	
	// ********** CONFIRMBUTTON **********
	class ConfirmButton extends JPanel {
		JButton confirm;
		ImageIcon confB = new ImageIcon(ConfirmButton.class.getResource("confirmbutton.png"));
		Image confBu= confB.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH);
		
		
		public ConfirmButton() {
			JButton confirm = new JButton();
			confirm.setIcon(new ImageIcon(confBu) );
			confirm.setContentAreaFilled(false);
			confirm.setBorderPainted(false);
			confirm.setOpaque(false);
			add(confirm);
						
						
				//********* MOUSELISTENER *********
				confirm.addMouseListener(new MouseAdapter() {
				
					public void mouseClicked(MouseEvent e){
				
						if (size >= 5 && size <=30) {
								conf.setVisible(false);
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
			}
	}		
	
	public void updateGrid(Object [][] my, Object [][] ai) {
		feld = new Spielfeld(size);
		feld.setBounds(50, 100, 520, 520);
		lp.add(feld, (2),2);
		conf.setVisible(true); 
		lp.validate();
	}
}


