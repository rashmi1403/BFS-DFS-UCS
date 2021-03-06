

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;


public class Depth_First_Search
{
	public int number_nodes;
	public String root, goal;
	public int edge[][] = new int [40][40];
	String [] g_nodes = new String[80]; 
	PriorityQueue<Node2> pqueue;
	
	public Depth_First_Search(int no_of_nodes, String src, String dst,int[][] graph_matrix, String[] nodes)
	{
		number_nodes = no_of_nodes;
		root 		 = src;
		goal		 = dst;
		edge 		 = graph_matrix;
		g_nodes 	 = nodes;
		pqueue= new PriorityQueue<Node2>(no_of_nodes,new Node2());
	}

	public void dfs() throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File ("output.txt").getAbsolutePath()));
		
		System.out.println("In DFS");
		
		List<String> traversed = new ArrayList<String>();
		List<String> visited = new ArrayList<String>();
		int []least_distance = new int[40];
		String[] parentchild=new String[40];
		Stack<String> rev_path=new Stack<String>();
		LinkedList<String> path=new LinkedList<String>();
		int goal_fnd = 0;
		int distance_from_root = 0;
		
		for(int x=1;x<=number_nodes;x++)
		{
			for(int j=1;j<=number_nodes;j++)
			{
				if (x == j)
                {
					edge[x][j] = 0;
					continue;
                }
                if (edge[x][j] == 0)
                {
                	edge[x][j] = 1000;
                }
			}
		}
		
		for(int a=1;a<=number_nodes;a++)
			least_distance[a] = 10000;
		
		System.out.println("Goal = " +goal);
		Node2 n = new Node2(root,distance_from_root);
		pqueue.add(n);
		visited.add(root);
		least_distance[1]=0;

		//While queue is not empty - Follow the DFS algorithm		 
		while(!pqueue.isEmpty())
		{
			Node2 nd = pqueue.remove();	
			traversed.add(nd.element);
			String popped=nd.element;
			int num=0;
		
			for(int i=1;i<=number_nodes;i++)
			{
				if(g_nodes[i].equals(popped))
					num=i;
			}
		
			if(popped.equals(goal))
			{
				goal_fnd = 1;
				//Goal reached
				break;
			}
			else
			{
				//visited.add(popped);
				distance_from_root++;
				//System.out.println("Expansion "+traversed);
				for(int i=1;i<=number_nodes;i++)
				{ 
					if(!visited.contains(g_nodes[i]))
					{	
						if (edge[num][i] != 1000)
						{
							if (least_distance[i] > edge[num][i] + least_distance[num])
							{
								least_distance[i] = edge[num][i] + least_distance[num]; 	
								String parent=popped;
								parentchild[i] = parent;
							}		
							Node2 node = new Node2(g_nodes[i],distance_from_root);
							System.out.println("Added element =" +g_nodes[i]);
							System.out.println("distance_from_root =" +distance_from_root);
							/*if (pqueue.contains(node))
							{
								pqueue.remove(node);
							}*/
							pqueue.add(node);
							visited.add(g_nodes[i]);
						}
					} //if not visited ends here
				}
			}
		}
		
		if(goal_fnd == 1)
		{
			System.out.println("Goal reached");
			System.out.println("DFS traversal is "+traversed);
			/*for(int i=1;i<=number_nodes;i++)
			{
				System.out.println("Parent Of "+g_nodes[i]+" is "+parentchild[i]);
			}*/
			
			Iterator<String> it = traversed.iterator();
			if (it.hasNext()) 
			{
				//System.out.print(i.next());
				bw.write(it.next());
			    while (it.hasNext())
			    {
			      //System.out.print("-"+i.next());
			      bw.write("-"+it.next());
			    }
			 }
			
			int goal_number=0;
			for(int i=1;i<=number_nodes;i++)
			{
				if(g_nodes[i].equals(goal))
				{
					goal_number=i;
				}
			}
			int start=0;
		
			for(int i=1;i<=number_nodes;i++)
			{
				if(g_nodes[i].equals(root))
					start=i;
			}
			
			int goal=goal_number;
			rev_path.push(g_nodes[goal_number]);
			String new_par=null;
			int found=0;
			while(found==0)
			{
				if(start==goal)
				{
					found=1;
					continue;
				}
				rev_path.push(parentchild[goal]);
				new_par=parentchild[goal];
				for(int i=1;i<=number_nodes;i++)
				{
					if(g_nodes[i].equals(new_par))
						goal=i;
				}	
			}
			
			while(!rev_path.isEmpty())
			{
				path.add(rev_path.pop());
			}
			System.out.println("Path is "+path);
			System.out.println("Path is "+path);
			bw.newLine();
			Iterator<String> ip = path.iterator();
			if (ip.hasNext()) 
			{
				//System.out.print(i.next());
				bw.write(ip.next());
			    while (ip.hasNext())
			    {
			      //System.out.print("-"+i.next());
			      bw.write("-"+ip.next());
			    }
			 }
			System.out.println("Cost is :"+least_distance[goal_number]);
			bw.newLine();
			String data=Integer.toString(least_distance[goal_number]);
			bw.write(data);
			bw.newLine();
			bw.close();
		}
		else
		{
			Iterator<String> it = traversed.iterator();
			if (it.hasNext()) 
			{
				//System.out.print(i.next());
				bw.write(it.next());
			    while (it.hasNext())
			    {
			      //System.out.print("-"+i.next());
			      bw.write("-"+it.next());
			    }
			 }
			//System.out.println("NoPathAvailable");
			bw.newLine();
			bw.write("NoPathAvailable");
			bw.close();
		}
	}	
}

class Node2 implements Comparator<Node2> 
{

	public String element;
	public int distance;
	
	public Node2(String node) 
	{	
		this.element = node;
	}
	
	public Node2(String element,int distance)
	{
		this.element=element;
		this.distance=distance;
	}
	public Node2()
	{
		
	}

    public boolean equals(Object object)
    {
        if (object instanceof Node1)
        {
            Node2 n = (Node2) object;
            if (this.element == n.element)
            {
                return true;
            }
        }
        return false;
    }
	
    public int compare(Node2 a, Node2 b)
    {
		//Cost Comparison in the Queue
        if (a.distance < b.distance)
            return 1;
        else if (a.distance > b.distance)
            return -1;
        else
        {
        if (a.element.compareTo(b.element)<0)
            return -1;
        else 
            return 1;
        }
    }
	
}







