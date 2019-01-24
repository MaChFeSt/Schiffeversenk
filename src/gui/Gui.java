package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Network.*;


/**
 * @author Feyza Grms
 *
 */
public class Gui extends Thread {
	
	
	public JPanel boardpanel = new JPanel();
	public JPanel boardtop = new JPanel(),
				  boardbottom = new JPanel(),
				  boardleft = new JPanel(),
				  boardright = new JPanel();
	public static Host host1;
	public static Client client1;
	public static boolean darkm = false;
	public static int mode = 0; // 1 = host , 2 = join, 3 = loaded game
	public static JFrame frame;
	/**
	 *  Im Konstruktor wird das JFrame, die JPanel, die Hintergrundfarben, die JButtons und das Logo eingebettet
	 *  
	 */
	public Gui(){
	
	//*****************JFRAME******************
	 frame = new JFrame();	 
	 frame.setTitle("Schiffe versenken");
	 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 frame.setSize(1280,720);
	 frame.setVisible(true);
	 frame.setLocationRelativeTo(null);
	 frame.setResizable(false);
 	
	
	 //****************JPANEL LOGO**************
	 JPanel panellogo = new JPanel();
	 boardtop.add(panellogo);
	 panellogo.setOpaque(false);
	 
	 ImageIcon iconlogo = new ImageIcon(Gui.class.getResource("logo.png"));
	 Image iconlogoi = iconlogo.getImage().getScaledInstance(580,260, Image.SCALE_SMOOTH);
	 JLabel logo = new JLabel(iconlogo);
	 logo.setIcon(new ImageIcon(iconlogoi));
	 
	 Color customColor = new Color(85,100,191);
	 boardtop.setBackground(customColor);
	 boardtop.setOpaque(true);
	 panellogo.add(logo);
	 
	 
	 //**************JPANEL BUTTONS**************
	 JPanel panel = new JPanel(new GridLayout(5, 2));
	 panel.setOpaque(false);
	 panel.setPreferredSize(new Dimension(300, 400));
	 
	 boardpanel.setLayout(new BorderLayout());
	 boardpanel.add(panel, BorderLayout.CENTER);
	 
	 

  	//einzelne Borderpanels in das Spielfeld panel
	boardpanel.add(boardtop, BorderLayout.NORTH);
	boardtop.setOpaque(true);
	boardtop.setPreferredSize(new Dimension (1280,270));
 
	boardpanel.add(boardleft, BorderLayout.WEST);
	boardleft.setOpaque(false);
	boardleft.setPreferredSize(new Dimension (490,400));
	
	boardpanel.add(boardright, BorderLayout.EAST);
	boardright.setOpaque(false);
	boardright.setPreferredSize(new Dimension (490,400));
	
	
	//****************JPANEL NAME********************
	 boardpanel.add(boardbottom, BorderLayout.SOUTH);
	 boardbottom.setOpaque(true);
	 boardbottom.setPreferredSize(new Dimension(1280,50));
	 JPanel panelname = new JPanel();
	 boardbottom.add(panelname);	 
	 JLabel name = new JLabel("Created by: Stefanie, Feyzanur, Christian, Marcus");
	 Font schriftart = new Font("SansSerif", Font.BOLD, 15 );
	 name.setFont(schriftart);
	 name.setForeground(Color.BLACK);
	 panelname.setOpaque(false);
	 panelname.add(name);
		
	 	
	 /**
	  * Hier werden die JButton erstellt und die dazu gehoerigen Bilder auf diese eingefuegt
	  *
	  */ 
	//****************VERSUS KI********************
	 ImageIcon iconversusai = new ImageIcon(Gui.class.getResource("Versus AI.png"));
	 JButton versusai = new JButton(iconversusai);
	 versusai.setIcon(iconversusai);
	 versusai.setContentAreaFilled(false);
	 versusai.setBorderPainted(false);
	 versusai.setOpaque(false);
	 panel.add(versusai);
	 
	 //****************NETWORK GAME***************
	 ImageIcon iconnetwork = new ImageIcon(Gui.class.getResource("network.png"));
	 JButton network = new JButton(iconnetwork);
	 network.setIcon(iconnetwork);
	 network.setContentAreaFilled(false);
	 network.setBorderPainted(false);
	 network.setOpaque(false);
	 panel.add(network);

	//****************SETTINGS********************
	 ImageIcon iconsettings = new ImageIcon(Gui.class.getResource("settings.png"));
	 JButton settings = new JButton(iconsettings);
	 settings.setIcon(iconsettings);
	 settings.setContentAreaFilled(false);
	 settings.setBorderPainted(false);
	 settings.setOpaque(false);
	 panel.add(settings);
	
	//****************HELP********************
	 ImageIcon iconhelp = new ImageIcon(Gui.class.getResource("help.png"));
	 JButton help = new JButton(iconhelp);
	 help.setIcon(iconhelp);
	 help.setContentAreaFilled(false);
	 help.setBorderPainted(false);
	 help.setOpaque(false);
	 panel.add(help);
		
	//****************QUIT********************
	 ImageIcon iconquit = new ImageIcon(Gui.class.getResource("quit.png"));
	 JButton quit = new JButton(iconquit);
	 quit.setIcon(iconquit);
	 quit.setContentAreaFilled(false);
	 quit.setBorderPainted(false);
	 quit.setOpaque(false);
	 panel.add(quit);
	
	 
	 quit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 System.exit(0);
		 }
	 });
	 
	//****************ACTION LISTENER VERSUS KI********************
	 versusai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	panel.setVisible(false);
             	JPanel panelversusai = new JPanel();
             	panelversusai.setOpaque(false);
             	boardpanel.add(panelversusai);

             	ImageIcon iconnewgame = new ImageIcon(Gui.class.getResource("newgame.png"));
             	JButton newgame = new JButton(iconnewgame);
             	newgame.setIcon(iconnewgame);
             	newgame.setContentAreaFilled(false);
             	newgame.setBorderPainted(false);
         		newgame.setOpaque(false);
         		panelversusai.add(newgame);


         		 newgame.addActionListener(new ActionListener() {
    	     	            public void actionPerformed(ActionEvent e)
    	     	            {
    	     	            	panel.setVisible(false);
    	     	            	new Window();
    	     	            	frame.setVisible(false);
    								}
    							});


         		ImageIcon iconload = new ImageIcon(Gui.class.getResource("load.png"));
             	JButton load = new JButton(iconload);
             	load.setIcon(iconload);
             	load.setContentAreaFilled(false);
             	load.setBorderPainted(false);
         		load.setOpaque(false);
         		panelversusai.add(load);
            	
         		load.addActionListener(new ActionListener() {
     	            public void actionPerformed(ActionEvent e)
     	            {
     	            	mode=3;
     	            	new Window();
     	            	frame.setVisible(false);			   
     	            }
    			});
         		
         		ImageIcon iconmenu = new ImageIcon(Gui.class.getResource("menu.png"));
    			JButton menu = new JButton(iconmenu);
    			menu.setIcon(iconmenu);
    			menu.setContentAreaFilled(false);
    			menu.setBorderPainted(false);
    			menu.setOpaque(false);
    			panelversusai.add(menu);

    			menu.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					panelversusai.setVisible(false);
    					panel.setVisible(true);
    				}
    			});
	 
            }
        });
	 
	//****************ACTION LISTENER NETWORK********************
	 network.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	panel.setVisible(false);
            	JPanel panelnw = new JPanel();
        		panelnw.setOpaque(false);
        		boardpanel.add(panelnw);
        		 
        		 ImageIcon iconjoin = new ImageIcon(Gui.class.getResource("join.png"));
        		 JButton join = new JButton(iconjoin);
        		 join.setIcon(iconjoin);
        		 join.setContentAreaFilled(false);
        		 join.setBorderPainted(false);
        		 join.setOpaque(false);
        		 panelnw.add(join);
        		 
        		 join.addActionListener(new ActionListener() {
	     	            public void actionPerformed(ActionEvent e)
	     	            {
	     	            	panelnw.setVisible(false);
	     	            	JPanel paneljoin = new JPanel();
		   	        		paneljoin.setOpaque(false);
		   	        		 
		   	        		JLabel labeljoin = new JLabel("Enter IP adress:");
		   	        		Font schriftart = new Font("SansSerif", Font.BOLD, 18);
		   	        		labeljoin.setFont(schriftart);
			        		paneljoin.add(labeljoin);
			        		 
			        		JTextField textjoin = new JTextField(20);
			        		paneljoin.add(textjoin);
			        		textjoin.addActionListener(new java.awt.event.ActionListener(){
								public void actionPerformed(ActionEvent e){
									mode = 2;
									String eingabe = textjoin.getText();
									client1 = new Client(eingabe, 4444);
									client1.start();
									client1.listen();
								}
							});

			        		
			        		boardpanel.add(paneljoin);
			        		 
			        		
			        		
			        		textjoin.addActionListener(new java.awt.event.ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				//Spiel Joinen
			        			}
			        			
			        		});
			        		
	     	            }
	        		 });
        		 
        		 
        		 
        		 ImageIcon iconhost = new ImageIcon(Gui.class.getResource("host.png"));
        		 JButton host = new JButton(iconhost);
	        		 host.setIcon(iconhost);
	        		 host.setContentAreaFilled(false);
	        		 host.setBorderPainted(false);
	        		 host.setOpaque(false);
        		 panelnw.add(host);
        		 
        		 host.addActionListener(new ActionListener() {
	     	            public void actionPerformed(ActionEvent e)
	     	            {
	     	            	panelnw.setVisible(false);
	     	            	JPanel panelhost = new JPanel();
	     	            	panelhost.setVisible(true);

	     	            	host1 = new Host(4444);
							String ip = host1.getIP();
							host1.start();
	   	        		 
	     	            	JLabel labelhost = new JLabel("Your IP adress: " + ip);
	     	            	Font schriftart = new Font("SansSerif", Font.BOLD, 18);
	     	        		labelhost.setFont(schriftart);
	     	            	panelhost.add(labelhost);
							boardpanel.add(panelhost);
							panelhost.setVisible(true);

							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									warteAufConnection(host1);

									if(host1.isConnected()){
										mode= 1;
										panel.setVisible(false);
										new Window();
										frame.setVisible(false);
									}
								}
							});

	     	            }
        		 });

				ImageIcon iconmenu = new ImageIcon(Gui.class.getResource("menu.png"));
				JButton menu = new JButton(iconmenu);
				menu.setIcon(iconmenu);
				menu.setContentAreaFilled(false);
				menu.setBorderPainted(false);
				menu.setOpaque(false);
				panelnw.add(menu);

				menu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						panelnw.setVisible(false);
						panel.setVisible(true);
					}
				});

        		 
            }
        });

	//****************ACTION LISTENER HELP********************
	 help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	panel.setVisible(false);
            	JPanel panelhelp = new JPanel();
        		panelhelp.setOpaque(false);
        		boardpanel.add(panelhelp);
        		
        		//*****************JLABEL**********************
        		JLabel howtoplay= new JLabel("How to play Battle PIRATE Ships:");
        		JLabel howtoplay1= new JLabel("Each player places ships on the left grid.");
        		JLabel howtoplay2= new JLabel("Ships are not allowed to be placed");
        		JLabel howtoplay3= new JLabel("next to each other. After that you take turns");
        		JLabel howtoplay4= new JLabel("firing shots by clicking on grid coordinates.");
        		JLabel howtoplay5= new JLabel("Your goal is to shoot down enemy ships");
        		JLabel howtoplay6= new JLabel("before he shoots down yours.");
        		//Schriftart aendern****************
        		Font schriftart = new Font("SansSerif", Font.BOLD, 18);
        		Font schriftart1 = new Font("SansSerif", Font.PLAIN, 15);
        		howtoplay.setFont(schriftart);
        		howtoplay1.setFont(schriftart1);
        		howtoplay2.setFont(schriftart1);
        		howtoplay3.setFont(schriftart1);
        		howtoplay4.setFont(schriftart1);
        		howtoplay5.setFont(schriftart1);
        		howtoplay6.setFont(schriftart1);
        		//Farbe aendern**********************
        		howtoplay.setForeground(Color.BLACK);
        		howtoplay1.setForeground(Color.BLACK);
        		howtoplay2.setForeground(Color.BLACK);
        		howtoplay3.setForeground(Color.BLACK);
        		howtoplay4.setForeground(Color.BLACK);
        		howtoplay5.setForeground(Color.BLACK);
        		howtoplay6.setForeground(Color.BLACK);
        		//JLabel hinzufuegen******************
        		panelhelp.add(howtoplay);
        		panelhelp.add(howtoplay1); 
        		panelhelp.add(howtoplay2);
        		panelhelp.add(howtoplay3);
        		panelhelp.add(howtoplay4);
        		panelhelp.add(howtoplay5);
        		panelhelp.add(howtoplay6);
        		
        		//Zurueck zum Menu BUTTON**************
        		ImageIcon iconmenu = new ImageIcon(Gui.class.getResource("menu.png"));
	        	JButton menu = new JButton(iconmenu);
       		 		menu.setIcon(iconmenu);
       		 		menu.setContentAreaFilled(false);
       		 		menu.setBorderPainted(false);
       		 		menu.setOpaque(false);
	        	panelhelp.add(menu);
	        		 
        		menu.addActionListener(new ActionListener() {
     	            public void actionPerformed(ActionEvent e)
     	            {
     	            	panelhelp.setVisible(false);
     	            	panel.setVisible(true);
     	            }
        		 });
        		
            }
       });
	 
	 
	//****************ACTION LISTENER SETTINGS********************
	 settings.addActionListener(
	/**
	 * Hier werden Einstellungsmoeglichkeiten wie Darkmode und Lightmode eingestellt
	 *
	 */
	new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	panel.setVisible(false);
            	JPanel panelsettings = new JPanel();
            	panelsettings.setOpaque(false);
        		boardpanel.add(panelsettings);
        		
        		ImageIcon iconmenu = new ImageIcon(Gui.class.getResource("menu.png"));
            	JButton menu = new JButton(iconmenu);
       		 		menu.setIcon(iconmenu);
       		 		menu.setContentAreaFilled(false);
       		 		menu.setBorderPainted(false);
       		 		menu.setOpaque(false);
        		
        		ImageIcon iconlightmode = new ImageIcon(Gui.class.getResource("lightmode.png"));
	        	JButton lightmode = new JButton(iconlightmode);
	        		lightmode.setIcon(iconlightmode);
   		 			lightmode.setContentAreaFilled(false);
   		 			lightmode.setBorderPainted(false);
   		 			lightmode.setOpaque(false);
   		 		
   		 		ImageIcon icondarkmode = new ImageIcon(Gui.class.getResource("darkmode.png"));
   		 		JButton darkmode = new JButton(icondarkmode);
   		 			darkmode.setIcon(icondarkmode);
   		 			darkmode.setContentAreaFilled(false);
   		 			darkmode.setBorderPainted(false);
   		 			darkmode.setOpaque(false);
   		 		
   		 		//*****************LIGHTMODE***********************
   		 	lightmode.addActionListener(new ActionListener() {
 	            public void actionPerformed(ActionEvent e)
 	            {
 	            	
 	            	Color customColor = new Color(85,100,191);
 	            	boardtop.setBackground(customColor);
 	            	
 	            	boardbottom.setBackground(null);
 	            	boardpanel.setBackground(null);
 	            	darkm = false;
 	            }
    		 });
   		 	

		 	//*****************DARKMODE***********************
   		 darkmode.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	  
	           	 	boardtop.setBackground(Color.BLACK);
	           	 	Color customColor = new Color(139,137,132);
	            	boardbottom.setBackground(customColor);
	            	boardpanel.setBackground(customColor);
	            	darkm = true;
	            }
		 });
        		
        	
   		 		panelsettings.add(lightmode);
   		 		panelsettings.add(darkmode);
	        	panelsettings.add(menu);
	        		 
        		menu.addActionListener(new ActionListener() {
     	            public void actionPerformed(ActionEvent e)
     	            {
     	            	panelsettings.setVisible(false);
     	            	panel.setVisible(true);
     	            }
        		 });
            }
       });
	 
	 frame.add(boardpanel);
	 frame.setPreferredSize(new Dimension(1280,720));
	 frame.pack();
}

	/**
	 * @return Diese Methode sorgt dafuer, dass beim auswaehlen des Darkmodes auch die Klasse Windows in den Darkmode uebergeht
	 */
	public static boolean getDark() {
		if(darkm == true) return true;
		else return false;
	}
	
	
	
	public void warteAufConnection(Host host){
		int sec = 120;
		System.out.print("warte auf Verbindung");
		int j = 0;
		for(int i = 0; i < sec; i++){
			try{
				if(j == 4){
					System.out.println();
					System.out.print("warte auf Verbindung");
					j = 0;
				}
				sleep(1000);
				System.out.print(".");
				j++;

			}catch(Exception e){
				e.printStackTrace();
			}
			if(host1.isConnected() == true){
				break;
			}
		}
}
	
	
	/**Damit die anderen Klassen wissen, in welchem Modus das Spiel ausgefuehrt wird, 
	 * kann man das hier abfragen
	 * 0 = AI Spiel, 1 = Hosted Game, 2 = Joined Game, 3 = Loaded Game
	 * @mode
	 */
	public static int getMode() {
		return mode;
	}
	
	public void run() {

	}

	
	//************************MAIN METHODE*******************
	/**
	 * @param args
	 * Die Main fuehrt die Klasse GUI aus
	 */
	public static void main(String[] args) {
			new Gui();


	}
}
