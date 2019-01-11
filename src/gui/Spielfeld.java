package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Ai.*;
import Logik.*;


// KLASSE FUER SPIELFELD

@SuppressWarnings("serial")
public class Spielfeld extends JPanel{
	
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
