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
	
	Font font2 = new Font("SansSerif", Font.BOLD, 30);
	public int size = 0;
	ConfirmButton conf;
	JTextField gridsize;
	JLabel setSizeText;
	JLayeredPane lp;
	JLabel placeShipsText;
	JButton place, start;
	Spielfeld feld;
	Object[][] myGrid;
	Object[][] aigrid;
	
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
		
		// TEXTEINGABE
		gridsize = new JTextField(3);
		gridsize.setBounds(855, 22, 70, 50);
		gridsize.setFont(font2);
		gridsize.setHorizontalAlignment(JTextField.CENTER);
	    
		
		// CONFIRMBUTTON
		conf = new ConfirmButton();
		conf.setBounds(825, 550, 200, 70);
		lp.add(conf,(1));
		conf.setVisible(false);
		
	    lp.add(setSizeText, (1));
		lp.add(gridsize, (1));
		
		
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
					lp.add(feld, (2));
					conf.setVisible(true); 
					lp.validate();
				}
			}
		});
		
		
		setVisible(true);
		
		
	}
	
	public Object goPlaceShipsScreen() {
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
		lp.add(place, (1));
		lp.add(placeShipsText,(1));
		lp.validate();
		
		//********* MOUSELISTENER *********
		place.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e){
		
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
				
				feld.setVisible(false);
				Spielfeld newFeld = new Spielfeld (myGrid, size);
				lp.add(newFeld, (44));
				newFeld.setBounds(50, 100, 520, 520);
				lp.validate();
				
				
				
				start = new JButton();
				ImageIcon starti = new ImageIcon(Window.class.getResource("startbutton.png"));
				Image startim= starti.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
				
				start.setIcon(new ImageIcon(startim));
				start.setContentAreaFilled(false);
				start.setBorderPainted(false);
				start.setOpaque(false);
				start.setBounds(775, 500 , 300, 100);
				
				//********* MOUSELISTENER *********
				start.addMouseListener(new MouseAdapter() {
				
					public void mouseClicked(MouseEvent e){
				
						start.setVisible(false);
						place.setVisible(false);
						placeShipsText.setVisible(false);
						
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
				        
				        Spielfeld aiFeld = new Spielfeld (size, aigrid);
						lp.add(aiFeld, (44));
						aiFeld.setBounds(710,100, 520, 520);
						lp.validate();
						
						
						
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
				
				lp.add(start, (1));
				lp.validate();
				
			}
			
		});
		
		

		return myGrid;
	}
	
	public Object[][] getmyGrid() {
		return myGrid;
	}
	
	public Object[][] getaigrid() {
		return aigrid;
	}

	public int getBoardsize(){ return size; }
	
	
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
	
	
}


