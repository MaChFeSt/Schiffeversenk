package gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Screen{
	
	
	// ****** KLASSE FÜR SPIELFELD
	@SuppressWarnings("serial")
	class Grid extends JPanel implements ActionListener {
		
		JPanel panel;
		JLabel label;
		int size;
		Object[][] grid;
		
		public Grid(int size) {
			
			// *** PANEL ANLEGEN ***
			this.size=size;
			JPanel panel = new JPanel(new GridLayout(size,size));
			panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			panel.setPreferredSize(new Dimension(520,520));
			panel.setOpaque(false);

			

			//*** BILD RESIZABLE ICON FÜR GRID ***
			ImageIcon water = new ImageIcon(Grid.class.getResource("wasser.png"));
			Image waterimage = water.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
			ImageIcon waterred = new ImageIcon(Grid.class.getResource("wasserrot.png"));
			Image waterimagered = waterred.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
			ImageIcon watergreen = new ImageIcon(Grid.class.getResource("wassergreen.png"));
			Image waterimagegreen = watergreen.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
			
			
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
		            
					
			// ********** MOUSE LISTENER FÜR GRID ************
		            label.addMouseListener(new MouseAdapter() {
		                public void mouseClicked(MouseEvent e){
		                    //label.setBackground(Color.red);
		                }
		                @Override
		                public void mouseEntered(MouseEvent e) {
		                    
		                	//label.setBackground(Color.black);
		                	int col;
		                	int row;
				        	int cellwidth = 520/size;
				        	col = e.getX() / cellwidth;
				        	row = e.getY()  / cellwidth;
				        	
				        	if (grid[col][row].hashCode() == 0) {	  
				        		//label.setBackground(Color.green);
				        		label.setIcon(null);
				        		label.setIcon(new ImageIcon(waterimagegreen));
				        	}
				        	if (grid[col][row].hashCode() == 1 || grid[col][row].hashCode() == 2) {	  
				        		label.setIcon(null);
				        		label.setIcon(new ImageIcon(waterimagered));
				        	}
		                }

		                @Override
		                public void mouseExited(MouseEvent e) {
		                    label.setIcon(null);
		                    label.setIcon(new ImageIcon(waterimage));
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
				}
			}
		}	
			
			


		
		public void mouseEntered(MouseEvent e) {
			ImageIcon waterred = new ImageIcon(Grid.class.getResource("wasserrot.png"));
			Image waterimagered = waterred.getImage().getScaledInstance(520/size, 520/size, Image.SCALE_SMOOTH);
			JLabel label = (JLabel)e.getSource();
			label.setIcon(new ImageIcon(waterimagered));
		}

		
		public void mouseExited(MouseEvent e) {
			JLabel label = (JLabel)e.getSource();
			label.setBackground(Color.MAGENTA);
			
		}


		
		
		
		public  int boardSize() {
			return size;
		}


		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	
	}	
	 

		@SuppressWarnings("serial")
		// *** TEXTFELD FÜR GRIDSIZE ***


	class SetGridSize extends JPanel {
		JPanel setsize;
		public int size = 0;
		
		public SetGridSize() {
			JPanel setsize = new JPanel(new FlowLayout());
 			Font font1 = new Font("SansSerif", Font.BOLD, 20);
			Font font2 = new Font("SansSerif", Font.BOLD, 30);
			
			
			JLabel sizetext = new JLabel("SET BOARD SIZE :");
			sizetext.setFont(font2);
			
			// *** Textfeld ***
			JTextField gridsize = new JTextField(3);
			gridsize.setSize(50,50);
			gridsize.setFont(font1);
			gridsize.setHorizontalAlignment(JTextField.CENTER);
			setsize.add(sizetext);
			setsize.add(gridsize);
			ConfirmButton conf = new ConfirmButton();
			setsize.add(conf);
			conf.setVisible(false);
			
			// *** ACTIONLISTENER ***
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
						

						conf.setVisible(true);
						boardmain.add(new Grid(size), BorderLayout.WEST);
						boardmain.validate();
						
					}
				}
			});
			

			
			add(setsize);
		}
		
		
//		public  int boardSize() {
//			return size;
//		}
		
		// ********** CONFIRMBUTTON **********
		class ConfirmButton extends JPanel{
			JButton confirm;
			ImageIcon confB = new ImageIcon(ConfirmButton.class.getResource("confirmbutton.png"));
			Image confBu= confB.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
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
								boardtop.remove(topText);
								boardtop.repaint();
								boardtop.add(new PlaceShipsText(), BorderLayout.SOUTH);
								boardtop.validate();
								boardmain.add(new Ships(size), BorderLayout.EAST);
								boardmain.validate();
								
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
		
	
		
	@SuppressWarnings("serial")
	class PlaceShipsText extends JPanel{
		JPanel placePls = new JPanel();
	
		public PlaceShipsText() {
		Font font2 = new Font("SansSerif", Font.BOLD, 40);
		
		
		JLabel Pls = new JLabel("PLACE YOUR SHIPS");
		Pls.setFont(font2);	
		placePls.add(Pls);
		add(placePls);
		}
		
	}



	
	
	// frame
	public JFrame frame = new JFrame();

	
	// Hier wird der Spielbildschirm zusammengebaut
	public JPanel boardpanel = new JPanel();
	
	
	//*************HINTERGRUND FÜR MAIN PANEL*****************
	
	
	public JPanel boardtop = new JPanel(), boardbottom = new JPanel(),
			boardleft = new JPanel(), boardright = new JPanel();
	
	public JPanel placeholder1 = new JPanel(); JPanel topText = new JPanel();

	// Panel für das Spielfeld
	public JPanel boardmain = new JPanel();
	
	public Screen() { 
		
		//Frame erstellen
		frame.setLayout(new BorderLayout());

		
		//Layout Boardpanel
		boardpanel.setLayout(new BorderLayout());
		//boardpanel.setBackground(new Color( 69,120,210 ));
		

	    //*********MAIN*********
	  	boardpanel.add(boardmain, BorderLayout.CENTER);
	  	boardmain.setLayout(new BorderLayout());
	  	
	  	// *** BOARD EINFÜGEN ***
	  	//boardmain.add(new Grid(10), BorderLayout.WEST);
	  	boardmain.setOpaque(false);
	  	boardmain.setPreferredSize(new Dimension(1000,520));
		
		
	  	
	  	//einzelne Borderpanels in das Spielfeld panel
		boardpanel.add(boardtop, BorderLayout.NORTH);
		boardtop.setPreferredSize(new Dimension (1280,70));
		boardtop.add(topText);
		topText.add(new SetGridSize());

		
		
		
		boardpanel.add(boardbottom, BorderLayout.SOUTH);
		//boardbottom.setOpaque(false);
		boardbottom.setPreferredSize(new Dimension (1280, 70));
		
		boardpanel.add(boardleft, BorderLayout.WEST);
		boardleft.setOpaque(false);
		boardleft.setPreferredSize(new Dimension (70,60));
		
		boardpanel.add(boardright, BorderLayout.EAST);
		boardright.setOpaque(false);
		boardright.setPreferredSize(new Dimension (70,60));
		
		
		
		
		//*** Spielfeldpanel im Frame Anzeigen ***
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(boardpanel);
		frame.setResizable(false);
		frame.pack();
		frame.setSize(1280,720);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	  
   
	//public static void main(String[] args) {
		
		
		

//		   SwingUtilities.invokeLater(new Runnable(){
//			   public void run() {
//				   new Screen();
//			   }
//		   });
//	}

}  

