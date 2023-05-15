/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author goudam
 */
public class LongestDist{
    public static int findLongestDistance(int[][] maze, int x,int y){
        if(maze[x][y]==1){
            return -1;
        }
        if(maze[x][y]==9){
            return 1;
        }
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        int maxDist=Integer.MIN_VALUE;
        int flag=0;
        if(maze[x][y]==0){
            maze[x][y]=2;
        flag++;
        for(int d=0;d<dx.length;d++){
            int newx=x+dx[d];
            int newy=y+dy[d];
            
            if(newx>=0 && newx<maze.length && newy>=0 && newy<maze[0].length){
                int dist=findLongestDistance(maze,newx,newy);
                if(dist==-1){
                    continue;
                }
                maxDist=Math.max(maxDist, dist);
            }
           }
        }  
      
      if(maze[x][y]==2 && flag==1){
          maze[x][y]=0;
      }
      if(maxDist==Integer.MIN_VALUE){
          return -1;
      }
      
      return 1+maxDist;
   }
    
    public static boolean searchPath(int[][] maze, int x, int y, List<Integer> path, int dist, int longDist){
        
        
        if(dist==longDist){
            return false;
        }
        if(maze[x][y]==9){
         if(dist==longDist-1){
            path.add(x);
            path.add(y);
            return true;
            }
        }
        
        int flag=0;
        if(maze[x][y]==0){
            maze[x][y]=2;
            flag++;
            int[] dx={1,0,-1,0};
            int[] dy={0,-1,0,1};
            
            for(int d=0; d<dx.length; d++){
                int newx = x + dx[d];
                int newy= y + dy[d];
                //System.out.print(x+""+y+"-"+dist+" ");
                if(newx>=0 && newx<maze.length && newy>=0 && newy<maze[0].length && searchPath(maze, newx,
                        newy, path, dist+1, longDist)){
                   
                    path.add(x);
                    path.add(y);
                    return true;     
                }
            }
        }
        if(maze[x][y]==2 && flag==1){
            maze[x][y]=0;
        }
        return false;
      
        
    }
    
    
    
    public static void search(int[][] maze, int x,int y, List<Integer> path){
        int longDist=findLongestDistance(maze, x, y);
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[0].length;j++){
                    if(maze[i][j]==2){
                        maze[i][j]=0;
                    }
            }
        }
  
        searchPath(maze, x, y, path, 0, longDist);
        
    }
    
    public static void main(String[] args){
       
    }
}