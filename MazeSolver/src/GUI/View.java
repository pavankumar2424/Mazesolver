/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Project.Dfs;
import Project.ShortestDist;
import Project.LongestDist;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author goudam
 */
public class View extends JFrame implements ActionListener, MouseListener {
    
    // 0 = not visited
    // 1 = blocked wall
    // 2 = visited
    // 9 = target block
    private int[][] maze={{1,1,1,1,1,1,1,1,1,1,1,1,1},
                          {1,0,1,0,1,0,1,0,0,0,0,0,1},
                          {1,0,1,0,0,0,1,0,1,1,1,0,1},
                          {1,0,0,0,1,1,1,0,0,0,0,0,1},
                          {1,0,1,0,0,0,0,0,1,1,1,0,1},
                          {1,0,1,0,1,1,1,0,1,0,0,0,1},
                          {1,0,1,0,1,0,0,0,1,1,1,0,1},
                          {1,0,1,0,1,1,1,0,1,0,1,0,1},
                          {1,0,0,0,0,0,0,0,0,0,1,9,1},
                          {1,1,1,1,1,1,1,1,1,1,1,1,1}};
    private int[] target={8,11};
    
    //declaring the JButtons
    JButton cancelButton;
    JButton clearButton;
    JButton shortPathButton;
    JButton longPathButton;
    
    private List<Integer> path=new ArrayList<>();
    boolean isPathVisible=false;
    public View(){
        this.setTitle("Maze Solver");
        this.setSize(520,580);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //initialising the shortPathButton
        shortPathButton = new JButton("Short Path");
        shortPathButton.addActionListener(this);
        shortPathButton.setBounds(130, 420, 110, 30);
        
        //initialising the longPathButton
        longPathButton = new JButton("Long Path");
        longPathButton.addActionListener(this);
        longPathButton.setBounds(250, 420, 110, 30);
      
        //initialising the cancelButton
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setBounds(150, 460, 90, 30);
        
        //initialising the clearButton
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(250, 460, 90, 30);
        
        this.addMouseListener(this);
        this.add(cancelButton);
        this.add(clearButton);
        this.add(shortPathButton);
        this.add(longPathButton);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
            //getting the shortest path between starting point and target point
            if(e.getSource()==shortPathButton){
                
                try{
                    if(isPathVisible==true) clear();
                    isPathVisible=true;
                    ShortestDist.search(maze, 1, 1,path);
                    this.repaint();
                }
                catch(Exception excp){
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
             
            //getting the longest path between the starting and target point
            if(e.getSource()==longPathButton){
                
                try{
                    if(isPathVisible==true) clear();
                    isPathVisible=true;
                    LongestDist.search(maze, 1, 1,path);
                    this.repaint();
                }
                catch(Exception excp){
                    JOptionPane.showMessageDialog(null, excp.toString());
                }
            }
            
            //exiting from the application
            if(e.getSource()==cancelButton){
                
                int flag=JOptionPane.showConfirmDialog(null, "Are you sure you want to exist",
                        "Submit",JOptionPane.YES_NO_OPTION);            
                if(flag==0){
                    System.exit(0);
                }
            }
            
            //clearing the path to set new target point
            if(e.getSource()==clearButton){
                isPathVisible=false;
                clear();
                
            }
                    
    }
    
    //function to clear the path
    public void clear(){
        path.clear();
        //changing the red squares in the maze to white
        for(int row=0;row<maze.length;row++){
            for(int col=0;col<maze[0].length;col++){
                if(maze[row][col]==2){
                      maze[row][col]=0;
                    }
                  }
             }
        this.repaint();
    }
    
    @Override
    //function to set the target point
    public void mouseClicked(MouseEvent e){
        if(isPathVisible==true){
            return;
        }
        //getting the position of the square on which the mouse is clicked 
        if(e.getX()>=0 && e.getX()<=520 && e.getY()>=0 && e.getY()<=400){
            int x=e.getY()/40;
            int y=e.getX()/40;
            if(maze[x][y]==1){
                return;
            }
            Graphics g=getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(40*target[1], 40*target[0],40,40);
            g.setColor(Color.BLACK);
            g.drawRect(40*target[1], 40*target[0], 40, 40);
            g.setColor(Color.RED);
            maze[target[0]][target[1]]=0;
            maze[x][y]=9;
            g.fillRect(40*y, 40*x,40,40);
            target[0]=x;
            target[1]=y;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        
    }
    
    @Override 
    public void mouseExited(MouseEvent e){
        
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        
    }
     
    @Override
    public void mouseReleased(MouseEvent e){
        
    }
    
    @Override
    //function to paint the maze
    public void paint(Graphics g){
        super.paint(g);
        
        for(int row=0;row<maze.length;row++){
                for(int col=0;col<maze[0].length;col++){
                    Color color;
                    switch(maze[row][col]){
                        case 1 : color=Color.BLACK; break;
                        case 9 : color=Color.RED; break; 
                        default : color=Color.WHITE;
                    }
                    
                    g.setColor(color);
                    g.fillRect(40*col,40*row, 40, 40);
                    g.setColor(Color.BLACK);
                    g.drawRect(40*col, 40*row, 40, 40);
                }
        }
        
        for(int p=0;p<path.size();p+=2){
            int pathX=path.get(p);
            int pathY=path.get(p+1);
            g.setColor(Color.GREEN);
            g.fillRect(40*pathY, 40*pathX, 40, 40);
            g.setColor(Color.BLACK);
            g.drawRect(40*pathY, 40*pathX, 40, 40);
        } 
        
    }
    
    public static void main(String[] args){
        View view=new View();
        view.setVisible(true);
    }
}

