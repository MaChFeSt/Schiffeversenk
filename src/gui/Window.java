package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Ai.*;
import Logik.*;

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




// KLASSE FUER SPIELFELD
@SuppressWarnings("serial")
class Spielfeld extends JPanel{
	
	JPanel panel;
	JLabel label;
	int size;
	Object[][] grid;
	int pos [] = new int[2];

	
	//*** SCHIFFSBILDER
	
	Image crabim;
	Image ghostim; 

	Image tentacleim;

	Image wakaim;
	
	Image roseim;

	Image dragonim;
	


	//*** BILD RESIZABLE ICON F�R GRID ***

	Image waterimage;

	Image waterimagered;

	Image waterimagegreen;
	
	
	public Spielfeld(int size) {
		this.size=size;
		JPanel panel = new JPanel(new GridLayout(size,size));
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		panel.setPreferredSize(new Dimension(520,520));
		panel.setOpaque(false);
		

		


		//*** BILD RESIZABLE ICON F�R GRID ***
		ImageIcon water = new ImageIcon(Spielfeld.class.getResource("wasser.png"));
		 waterimage = water.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon waterred = new ImageIcon(Spielfeld.class.getResource("wasserrot.png"));
		 waterimagered = waterred.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon watergreen = new ImageIcon(Spielfeld.class.getResource("wassergreen.png"));
		 waterimagegreen = watergreen.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		
		// ********** GRID BAUEN *********
		Object[][] grid = new Object[size][size];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				JLabel label = new JLabel();
			    label.setIcon(new ImageIcon(waterimage));
			    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
			    panel.add(label);
			    add(panel);
				grid[i][j] = 0;
				label.setOpaque(true);
	            
				
//		// ********** MOUSE LISTENER F�R GRID ************
//	            label.addMouseListener(new MouseAdapter() {
//	                public void mouseClicked(MouseEvent e){
//	                    //label.setBackground(Color.red);
//	                }
//	                @Override
//	                public void mouseEntered(MouseEvent e) {
//	                    
//	                	//label.setBackground(Color.black);
//	                	int col;
//	                	int row;
//			        	int cellwidth = 520/size;
//			        	col = e.getX() / cellwidth;
//			        	row = e.getY()  / cellwidth;
//			        	
//			        	if (grid[col][row].hashCode() == 0) {	  
//			        		//label.setBackground(Color.green);
//			        		label.setIcon(null);
//			        		label.setIcon(new ImageIcon(waterimagegreen));
//			        	}
//			        	if (grid[col][row].hashCode() == 1 || grid[col][row].hashCode() == 2) {	  
//			        		label.setIcon(null);
//			        		label.setIcon(new ImageIcon(waterimagered));
//			        	}
//	                }
//
//	                @Override
//	                public void mouseExited(MouseEvent e) {
//	                    label.setIcon(null);
//	                    label.setIcon(new ImageIcon(waterimage));
//	                } 
//	        		@Override
//	        		public void mousePressed(MouseEvent e) {
//	        			// TODO Auto-generated method stub
//	        			
//	        		}
//
//	        		@Override
//	        		public void mouseReleased(MouseEvent e) {
//	        			// TODO Auto-generated method stub
//	        			
//	        		}
//	            });
			}
			
		}
	}
	
	public Spielfeld (Object[][] my, int size) {
		
		this.size=size;
		JPanel panel = new JPanel(new GridLayout(size,size));
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		panel.setPreferredSize(new Dimension(520,520));
		panel.setOpaque(false);
		
		//*** SCHIFFSBILDER
		ImageIcon crab = new ImageIcon(Spielfeld.class.getResource("crab.png"));
		 crabim = crab.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon ghost = new ImageIcon(Spielfeld.class.getResource("ghost.png"));
		 ghostim = ghost.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon tentacle = new ImageIcon(Spielfeld.class.getResource("tentacle.png"));
		 tentacleim = tentacle.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon waka = new ImageIcon(Spielfeld.class.getResource("waka.png"));
		 wakaim = waka.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon rose = new ImageIcon(Spielfeld.class.getResource("rose.png"));
		 roseim = rose.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon dragon = new ImageIcon(Spielfeld.class.getResource("dragon.png"));
		 dragonim = dragon.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		


		//*** BILD RESIZABLE ICON F�R GRID ***
		ImageIcon water = new ImageIcon(Spielfeld.class.getResource("wasser.png"));
		 waterimage = water.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);

		
		 Object [][] myGrid = my;
			
			for (int i = 0; i < myGrid.length; i++) {
				for (int j = 0; j < myGrid[i].length; j++) {
					
					if(myGrid[i][j].hashCode() == 2) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(wakaim));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					    
					}
					if(myGrid[i][j].hashCode() == 3) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(tentacleim));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
					if(myGrid[i][j].hashCode() == 4) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(crabim));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
					if(myGrid[i][j].hashCode() == 5) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(ghostim));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
					if(myGrid[i][j].hashCode() == 6) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(roseim));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
					if(myGrid[i][j].hashCode() == 7) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(dragonim));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
					
					if(myGrid[i][j].hashCode() == 0 || myGrid[i][j].hashCode() == 9) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(waterimage));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}

				}
			}
			add(panel);
		
	}
	
	
public Spielfeld (int size,Object[][] my){
		
		this.size=size;
		JPanel panel = new JPanel(new GridLayout(size,size));
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		panel.setPreferredSize(new Dimension(520,520));
		panel.setOpaque(false);
		
		SpielLogikAI logik = new SpielLogikAI();
		
		logik.spielStart();
		
		// ********** MOUSE LISTENER F�R GRID ************
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				//label.setBackground(Color.black);
				int col;
				int row;
				int cellwidth = 520/size;
				col = e.getX() / cellwidth;
				row = e.getY() / cellwidth;
				
				pos [0] = col;
				pos [1] = row;
				
				System.out.println("CLICKED ON: " + col + " " + row);

				//label.setBackground(Color.red);
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		//*** SCHIFFSBILDER
		ImageIcon crab = new ImageIcon(Spielfeld.class.getResource("crab.png"));
		 crabim = crab.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon ghost = new ImageIcon(Spielfeld.class.getResource("ghost.png"));
		 ghostim = ghost.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon tentacle = new ImageIcon(Spielfeld.class.getResource("tentacle.png"));
		 tentacleim = tentacle.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon waka = new ImageIcon(Spielfeld.class.getResource("waka.png"));
		 wakaim = waka.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon rose = new ImageIcon(Spielfeld.class.getResource("rose.png"));
		 roseim = rose.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon dragon = new ImageIcon(Spielfeld.class.getResource("dragon.png"));
		 dragonim = dragon.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		


		//*** BILD RESIZABLE ICON F�R GRID ***
		ImageIcon water = new ImageIcon(Spielfeld.class.getResource("wasser.png"));
		 waterimage = water.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);

		
		 Object [][] myGrid = my;
			
			for (int i = 0; i < myGrid.length; i++) {
				for (int j = 0; j < myGrid[i].length; j++) {
					if(myGrid[i][j].hashCode() == 20 || myGrid[i][j].hashCode() == 21) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(roseim));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
					
					else {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(waterimage));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);

					}

				}
			}

			add(panel);
		
	}

	public int [] getPos() {
		return pos;
	}
		
}
