package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Ai.*;
import Logik.*;



/** <h1>Klasse Spielfeld</h1>
 * 
 * In dieser Klasse wird fÃ¼r jedes Spielfeld ein zweidimensionaler
 * Array erstellt mit dessen Hilfe, das Spielfeld graphisch dargestellt wird.
 *
 */
@SuppressWarnings("serial")
public class Spielfeld extends JPanel{
	
	JPanel panel;
	JLabel label;
	int size;
	Object[][] grid;
	static int pos [] = new int[2];
	
	//*** SCHIFFSBILDER
	Image crabim;
	Image ghostim;
	Image tentacleim;
	Image wakaim;
	Image roseim;
	Image dragonim;
	
	//*** BILD RESIZABLE ICON Fï¿½R GRID ***
	Image waterimage, aimi, leeri, hiti;
	Image waterimagered;
	Image waterimagegreen;
	
	
	/**<h1>Konstruktor</h1>
	 * 
	 * Hier wird ein Standard Array erstellt, bei dem alle Werte auf 0 gesetzt werden.
	 * Dieses ist ein leeres Spielfeld, auf dem noch keine Schiffe platziert wurden.
	 * 
	 * @param Spielfeldgroesse
	 */
	public Spielfeld(int size) {
		this.size=size;
		JPanel panel = new JPanel(new GridLayout(size,size));
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		panel.setPreferredSize(new Dimension(520,520));
		panel.setOpaque(false);
		

		//*** BILD RESIZABLE ICON Fï¿½R GRID ***
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
			}	
		}
	}
	
	
	
	// ********** MY GRID ERSTELLEN **********
	/**<h1>Konstruktor um Spielfeld des Spielers zu bauen</h1>
	 * 
	 * Hier wird das eigene Spielfeld mit den Platzierten Schiffen, Leerschuessen und Treffern abgebildet.
	 * 
	 * @param mein Spielfeld
	 * @param Spielfeldgroesse
	 */
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
		
		//*** BILD RESIZABLE ICON Fï¿½R GRID ***
		ImageIcon water = new ImageIcon(Spielfeld.class.getResource("wasser.png"));
		 waterimage = water.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		 ImageIcon leeric  = new ImageIcon(Spielfeld.class.getResource("empty.png"));
		 leeri = leeric.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		 ImageIcon hitic  = new ImageIcon(Spielfeld.class.getResource("hit.png"));
		 hiti = hitic.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);

		
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
					if(myGrid[i][j].hashCode() == 21) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(hiti));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
					if(myGrid[i][j].hashCode() == 20) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(leeri));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
				}
			}
			add(panel);
		
	}
	

// ********** AI GRID ERSTELLEN **********
/**<h1>Konstruktor zum generieren des gegnerischen Spielfeldes</h1>
 * 
 * Hier wird das gegnerische Spielfeld erstellt, auf dem Treffer und Leerschuesse dargestellt werden.
 * 
 * @param Spielfeldgroesse
 * @param gegnerisches Feld
 */
public Spielfeld (int size,Object[][] my){
		
		pos[0] = -1;
		pos[1] = -1;
		this.size=size;
		JPanel panel = new JPanel(new GridLayout(size,size));
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		panel.setPreferredSize(new Dimension(520,520));
		panel.setOpaque(false);
		
		//SpielLogikAI logik = new SpielLogikAI();
		//logik.spielStart();
		
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
		


		//*** BILD RESIZABLE ICON Fï¿½R GRID ***
		ImageIcon water = new ImageIcon(Spielfeld.class.getResource("wasser.png"));
		 waterimage = water.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		ImageIcon aimic  = new ImageIcon(Spielfeld.class.getResource("aim.png"));
		 aimi = aimic.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		 ImageIcon leeric  = new ImageIcon(Spielfeld.class.getResource("empty.png"));
		 leeri = leeric.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		 ImageIcon hitic  = new ImageIcon(Spielfeld.class.getResource("hit.png"));
		 hiti = hitic.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
		 
		 Object [][] myGrid = my;
			
			for (int i = 0; i < myGrid.length; i++) {
				for (int j = 0; j < myGrid[i].length; j++) {
					if(myGrid[i][j].hashCode() == 21) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(hiti));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
					if(myGrid[i][j].hashCode() == 20) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(leeri));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);
					}
					if (myGrid[i][j].hashCode() == 2 || myGrid[i][j].hashCode() == 3 || myGrid[i][j].hashCode() == 4 || 
							myGrid[i][j].hashCode() == 5 || myGrid[i][j].hashCode() == 6 || myGrid[i][j].hashCode() == 7 || 
							myGrid[i][j].hashCode() == 9 || myGrid[i][j].hashCode() == 0) {
						JLabel label = new JLabel();
					    label.setIcon(new ImageIcon(waterimage));
					    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
					    panel.add(label);

					}

				}
			}
			
			// ********** MOUSE LISTENER Fï¿½R GRID ************
			panel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					
					if(Window.myTurn == true) {
						//label.setBackground(Color.black);
						int col;
						int row;
						int cellwidth = 520/size;
						col = e.getX() / cellwidth;
						row = e.getY() / cellwidth;
						
						pos [0] = col;
						pos [1] = row;
						
						int mode = Gui.getMode();
						
						if(mode == 1 || mode == 2) {
							SpielLogikAI.sendeSchuss(col, row);
						}else {
							SpielLogikAI.schussVonMenschAufAI(row, col);

						}
											
						//System.out.println("CLICKED ON: " + col + " " + row);
					}
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
//					
//					if(Window.myTurn=true) {
//						int col;
//	                	int row;
//			        	int cellwidth = 520/size;
//			        	col = e.getX() / cellwidth;
//			        	row = e.getY()  / cellwidth;
//			        	
//			        	if (grid[col][row].hashCode() != 20 || grid[col][row].hashCode() != 21 ) {	  
//			        		//label.setBackground(Color.green);
//			        	label.setIcon(null);
//			        	label.setIcon(new ImageIcon(aimi));
//
//			        	}
//					}
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
			
			add(panel);
		
	}

	// ******** GET POSITION ********
	/**<h1>Methode getPos</h1>
	 * 
	 * Hier wird die angeklickte Position im Spielfeld abgerufen.
	 * 
	 * @return geklickte Position im gegnerischen Spielfeld
	 */
	static public int [] getPos() {
		return pos;
	}
		
}
