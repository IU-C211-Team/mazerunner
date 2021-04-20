// @author Chloe Uphaus

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MazeGenerator extends JFrame{
    public static int rows = 20;
    public static int columns = 20;
    public static int panelSize = 25;
    public static int map[][] = new int[columns][rows];
    public static int endLevelLoc;
    Player p;
    
    private AlertBox winBox = new AlertBox("Winner", "Congratulations, you made it through!");
    
    public MazeGenerator(String str){
        loadMap(str);
        this.setResizable(false);
        this.setSize((columns*panelSize)+50, (rows*panelSize)+70);
        this.setTitle("Maze");
        this.setLayout(null);
        
        this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				
				revalidate();
				repaint();
				
				//Player movement
				if(key == KeyEvent.VK_W){
					p.moveUp();
				}
				if(key == KeyEvent.VK_A){
					p.moveLeft();
				}
				if(key == KeyEvent.VK_S){
					p.moveDown();
				}
				if(key == KeyEvent.VK_D){
					p.moveRight();
				}
				
				if(p.x == columns-1 && p.y == endLevelLoc){
					winBox.getCongratsBox();
					dispose();
					//new MainMenu();
					
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                //System.out.println((columns*panelSize)+50+"-"+((rows*panelSize)+70));
                System.exit(0);
            }
        });
        
        this.setLocationRelativeTo(null);
        
        //Create player
    	p = new Player();
    	p.setVisible(true);
    	this.add(p);
    	
        //Color map
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
                Space space = new Space(x, y);
                space.setSize(panelSize, panelSize);
                space.setLocation((x*panelSize)+23, (y*panelSize)+25);
                if(map[x][y] == 0){
                	space.setBackground(Color.GRAY);
                }else{
                	space.setBackground(Color.WHITE);
                	space.setWall(false);
                    if(x == 0){
                    	p.setLocation((x*panelSize)+23, (y*panelSize)+25);
                    	p.y = y;
                    }
                    if(x == columns-1){
                    	endLevelLoc = y;
                    }
                }
                
                space.setVisible(true);
                this.add(space);
            }
        }
        this.setVisible(true);
    }
    
    public void loadMap(String str){
        try{
            BufferedReader br = new BufferedReader(new FileReader(str));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            
            String mapStr = sb.toString();
            char[] mapCharArray = sb.toString().toCharArray();
            System.out.println(mapCharArray.length);
            
            int counter = 0;
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    String mapChar = mapStr.substring(counter, counter+1);
                    if(!mapChar.equals("\r\n") && !mapChar.equals("\n")&& !mapChar.equals("\r")){//If it's a number
                        //System.out.print(mapChar);
                        map[x][y] = Integer.parseInt(mapChar);
                    }else{//If it is a line break
                        x--;
                        System.out.print(mapChar);
                    }
                    counter++;
                }
            }
        }catch(Exception e){
            System.out.println("Unable to load existing map(if exists), creating new map.");
        }
    }
}
