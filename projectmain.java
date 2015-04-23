
import java.io.*;
import java.util.*;

public class projectmain 
{
	public static void main (String[] args) throws java.io.IOException
	 {
		 
		int no_of_reads=0, task_no=0, no_of_nodes=0,i=0;
		String str=null,src=null, dst=null;
		String [] nodes = new String[80]; 
		int graph_matrix[][] = new int [40][40];
		
		//BufferedReader br = new BufferedReader(new FileReader("C:\\USC\\AI\\input.txt"));
		BufferedReader br = new BufferedReader(new FileReader(new File ("input.txt").getAbsolutePath()));
		

	        while ((str = br.readLine()) != null) 
	        {
	        	no_of_reads = no_of_reads + 1;
	        	
	        	if(no_of_reads == 1)
	        	{
	        		task_no = Integer.valueOf(str);
	        		System.out.println(task_no);
	        	}
	        	
	        	if(no_of_reads == 2)
	        	{
	        		src = str;
	        		System.out.println(src);
	        	}
	        	
	        	if(no_of_reads == 3)
	        	{
	        		dst = str;
	        		System.out.println(dst);
	        	}
	        	
	        	if(no_of_reads == 4)
	        	{
	        		no_of_nodes = Integer.valueOf(str);
	        		System.out.println(no_of_nodes);
	        		//nodes = new String[no_of_nodes];
	        	}
	        	
	        	if(no_of_reads >= 5)
	        	{
	        		i=i+1;
	        		nodes[i] = str;
	        		System.out.println(nodes[i]);
	        	}
	        }
	        
	        
	        int graph_start = no_of_nodes + 1 ;
	        
	        
	        while(graph_start <= (2*no_of_nodes))
	        {	
	        	
	        	for(int m=1; m <= no_of_nodes; m++)
	        	{
	        		Scanner sc = new Scanner(nodes[graph_start]);
	        		for (int n=1; n <= no_of_nodes; n++)
	        		{
	        			
	        			if(sc.hasNext())
	        			{
	        				String s = sc.next();
        					graph_matrix[m][n] = Integer.valueOf(s);
        					
	        			}			 
	        		}
	        		graph_start = graph_start + 1;
		        	sc.close();
	        	}
	        	
	        	
	        }
	        
	        
	        System.out.println("After the values are assigned to the matrix:");
	        
	        for(int m=1; m <= no_of_nodes; m++)
        	{
        		for (int n=1; n <= no_of_nodes; n++)
        		{
        			System.out.print(graph_matrix[m][n]); 
        			System.out.print("\t");
        		}
        		System.out.println();
        	}
	        
	        
	        br.close();
	        
	        if (task_no == 1)
	        {
	        	Breadth_First_Search bfsobj = new Breadth_First_Search(no_of_nodes,src,dst,graph_matrix,nodes);  
	        	bfsobj.bfs();
	        }
	        	
	        if (task_no == 2)
	        {
	        	//DSF_Copy dfsobj = new DSF_Copy(no_of_nodes,src,dst,graph_matrix,nodes);  
	        	Depth_First_Search dfsobj = new Depth_First_Search(no_of_nodes,src,dst,graph_matrix,nodes); 
	        	// DFS dfsobj = new DFS(no_of_nodes,src,dst,graph_matrix,nodes);
	        	//Depth dfsobj = new Depth(no_of_nodes,src,dst,graph_matrix,nodes);
	        	dfsobj.dfs();
	        }
	        
	        if (task_no == 3)
	        {
	        	Uniform_Cost_Search ucsobj = new Uniform_Cost_Search(no_of_nodes,src,dst,graph_matrix,nodes);  
	        	ucsobj.ucs();
	        }
		         
		
	 }
}
		  