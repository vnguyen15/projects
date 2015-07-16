/* Viet Nguyen
 * TCSS 342 
 * HW 5
 * Spring 2014
 */
 
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

// Represents an edge in the graph.
class Edge
{
    public Vertex     dest;   // Second vertex in Edge
    public double     cost;   // Edge cost
    
    public Edge( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
}


// Represents a vertex in the graph.
class Vertex
{
    public String     name;   // Vertex name
    public List<Edge> adj;    // Adjacent vertices
    public double     dist;   // Cost
    public Vertex     prev ;   // Previous vertex on shortest path
    public int        scratch;// Extra variable used in algorithm

    public Vertex( String nm )
      { name = nm; adj = new LinkedList<Edge>( ); reset( ); }

    public void reset( )
      { dist = Graph.INFINITY; prev = null; scratch = 0; }    
      

}

// Graph Class, build an undirected graph from input file
// and return redundantly connected graph with its minimum cost
public class Graph
{
    public static final double INFINITY = Double.MAX_VALUE;
    private Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );
    private Set<FullEdge> edges = new HashSet<FullEdge>( );
    private Set<Set<FullEdge>> powerSet = new HashSet<Set<FullEdge>>( );
    private List<Graph> redList = new ArrayList<Graph>();
    
    // Constructor with name of input file as argument
    public Graph(String fileName) {
       try
        {
            File fin = new File( fileName );
            Scanner graphFile = new Scanner( fin );

            // Read the edges and insert
            String line;
            while( graphFile.hasNextLine( ) )
            {
                line = graphFile.nextLine( );
                StringTokenizer st = new StringTokenizer( line );

                try
                {
                    if( st.countTokens( ) != 3 )
                    {
                        System.err.println( "Skipping ill-formatted line " + line );
                        continue;
                    }
                    String source  = st.nextToken( );
                    String dest    = st.nextToken( );
                    int    cost    = Integer.parseInt( st.nextToken( ) );
                    addEdge( source, dest, cost );
                   // addEdge( dest, source, cost );
                }
                catch( NumberFormatException e )
                  { System.err.println( "Skipping ill-formatted line " + line ); }
             }
         }
         catch( IOException e )
           { System.err.println( e ); }
        
        // create a set of full edges
        createFullEdges();
        createPwrSet();
    }
    
    // create a new graph with a subMap
    private Graph(Map<String,Vertex> subMap) {
        vertexMap = subMap;
    }
    
    private void createFullEdges() {
       
       for(String vertexName : vertexMap.keySet()) {
          // get vertex
          Vertex v = vertexMap.get(vertexName);
          if(!v.adj.isEmpty()) {
             // adding all adjencent edges to a set of edges
             for(Edge edge : v.adj) {
                Vertex desVertex = edge.dest;
                edges.add(new FullEdge(v.name, desVertex.name, edge.cost));
                
             }
          }
          
       }
    }
    
    // create power set from set of full edges
    private void createPwrSet() {
      powerSet = SetMaker.powerSet(edges);
         
    }
    
    // remove All edges from the original graph
    private void removeAllEgdes( ) {
    
        for(String vertexName : vertexMap.keySet()) {
          // get vertex
          Vertex v = vertexMap.get(vertexName);
          v.adj.clear();   // remove adjecent edges from this vertex      
       }
    }
    
    // create and return the least expensive of Recdundantly Connected Graph
    public Graph rcSubgraph( ) {
        
        removeAllEgdes(); // remove all edge from original graph
        double minCost = 0;
        int minCostIndex = 0; // keep track the index of array that contains least expensive redundant subgraph
        boolean firstVal = true; // control the cost of the 1st redundant graph
        
        // switch powerset to arraylist of subset.
        List<Set<FullEdge>> listSubSet = new ArrayList<Set<FullEdge>>();
        
        
        
        for(Set<FullEdge> subSet : powerSet)
           listSubSet.add(subSet);    
        powerSet = new HashSet<Set<FullEdge>>();
                 
        // create each subgraph-subSet with all the full edges from array of subset.
        for(int i = 0; i < listSubSet.size(); i++) {
           
           for(FullEdge edge : listSubSet.get(i)) {
           
              addEdge(edge.start, edge.dest, edge.cost);
              addEdge(edge.dest, edge.start, edge.cost);
              
           }
          // System.out.println("graph " + i + " " + toString());
           if(isConnected()) {
              if( isRedConnected() ) { 
              
              double currentCost = totalEdgeCost();
              if(firstVal) { // for the 1st time of redundant graph
                 minCost = currentCost;
                 firstVal = false;
                 minCostIndex = i;
                 
              } else if (totalEdgeCost() < minCost) {
                 minCost = currentCost; // update least expensive cost
                 minCostIndex = i; // update index of array which has least cost redundant graph
              } 
              
              }
           }
           
           // create a new graph by using set of all edges of least expensive redundant graph
           removeAllEgdes();
        }
        
        
        for(FullEdge edge : listSubSet.get(minCostIndex)) {
           addEdge(edge.start, edge.dest, edge.cost);
        }
        
       // Graph redSubGraph = new Graph(Map<String, Vertex> redMap);
        return this; // retun new grahp;
    }
    
    // check whether a graph is redundently connected
    private boolean isRedConnected() {
       
       for (String key : vertexMap.keySet() ) {
          Vertex v = vertexMap.get(key);
          
          // processing all adjancent edges of each vertex list of edges
          List<Edge> adjList = v.adj;
          if(!adjList.isEmpty() ) {
             for(int i = 0; i < adjList.size(); i++) {
                Edge temp = adjList.remove(i); // remove an edge out of the list
                if (!isConnected())
                  return false;
                else 
                  adjList.add(i, temp); // add edge back to the list
             } 
          }
       }
       
       return true;
    }
    
    // calulate cost of all edges of a graph
    public double totalEdgeCost( ) {
       double total = 0;
       for(String key : vertexMap.keySet( )) {
          
          Vertex v = vertexMap.get(key);
          if (!v.adj.isEmpty( )) {
             for (Edge e : v.adj) {
               total += e.cost;
             }
          }
       }
       
       return total;
    }
    
    // check if this graph is connected
    public boolean isConnected() {
         clearAll();

         ArrayList<Vertex> vertices = new ArrayList<Vertex>(vertexMap.values());
         Vertex start = vertices.get(0);
         List<Vertex> toExplore = new LinkedList<Vertex>();
         toExplore.add(start);
         start.scratch = 1;
         
         while (!toExplore.isEmpty()) {
             Vertex curr = toExplore.remove(0);
            // System.out.println("Found "+curr.name);
             for(Edge e : curr.adj) {
                 if (e.dest.scratch == 0) { 
                     toExplore.add(0,e.dest);
                     e.dest.scratch = 1;
                 }
             } 
         
         }
         
         // check all vertices whether they all were reached / pass thru.
         for(int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i).scratch == 0) {
               return false;
            }
            
         }
         
         return true;
    }   
    
    public String toString() {
    
       String s = "\n";
       for ( String key : vertexMap.keySet() ) {
       
          Vertex v = vertexMap.get(key);
          if(!v.adj.isEmpty() ) {
             for(Edge edge : v.adj) {
                 s += key + " " + edge.dest.name + " " + edge.cost + "\n";
             }
          }
       }
       
       return s;
    }
    
    /**
     * Add a new edge to the graph.
     */
    public void addEdge( String sourceName, String destName, double cost )
    {
        Vertex v = getVertex( sourceName );
        Vertex w = getVertex( destName );
        v.adj.add( new Edge( w, cost ) );
    }

    /**
     * Driver routine to handle unreachables and print total cost.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     */
    public void printPath( String destName )
    {
        Vertex w = vertexMap.get( destName );
        if( w == null )
            throw new NoSuchElementException( "Destination vertex not found" );
        else if( w.dist == INFINITY )
            System.out.println( destName + " is unreachable" );
        else
        {
            System.out.print( "(Cost is: " + w.dist + ") " );
            printPath( w );
            System.out.println( );
        }
    }

    /**
     * If vertexName is not present, add it to vertexMap.
     * In either case, return the Vertex.
     */
    private Vertex getVertex( String vertexName )
    {
        Vertex v = vertexMap.get( vertexName );
        if( v == null )
        {
            v = new Vertex( vertexName );
            vertexMap.put( vertexName, v );
        }
        return v;
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     */
    private void printPath( Vertex dest )
    {
        if( dest.prev != null )
        {
            printPath( dest.prev );
            System.out.print( " to " );
        }
        System.out.print( dest.name );
    }
    
    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    private void clearAll( )
    {
        for( Vertex v : vertexMap.values( ) )
            v.reset( );
    }

    /**
     * Single-source unweighted shortest-path algorithm.
     */
    public void unweighted( String startName )
    {
        clearAll( ); 

        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        Queue<Vertex> q = new LinkedList<Vertex>( );
        q.add( start ); start.dist = 0;

        while( !q.isEmpty( ) )
        {
            Vertex v = q.remove( );

            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                if( w.dist == INFINITY )
                {
                    w.dist = v.dist + 1;
                    w.prev = v;
                    q.add( w );
                }
            }
        }
    }
    /**
     * Process a request; return false if end of file.
     */
    public static boolean processRequest( Scanner in, Graph g )
    {
        try
        {
        
            System.out.println( "Unweighted shortest distance:" );        
            System.out.print( "Enter start node:" );
            String startName = in.nextLine( );

            System.out.print( "Enter destination node:" );
            String destName = in.nextLine( );

            g.unweighted( startName );
            g.printPath( destName );
        }
        catch( NoSuchElementException e ) { 
            return false; 
        }
        return true;
    }
    

    /**
     * A main routine that:
     * 1. Reads a file containing edges (supplied as a command-line parameter);
     * 2. Forms the graph;
     * 3. Repeatedly prompts for two vertices and
     *    runs the shortest path algorithm.
     * The data file is a sequence of lines of the format
     *    source destination cost
     */
//     public static void main( String [ ] args )
//     {
//        Graph myGraph = new Graph("test1.txt"); 
//        Graph subGraph = myGraph.rcSubgraph(); 
//        System.out.println("Total cost = "+subGraph.totalEdgeCost()); 
//        System.out.println(subGraph);
       
        // Graph g = new Graph( "test1.text");
//         try
//         {
//             FileReader fin = new FileReader( args[0] );
//             Scanner graphFile = new Scanner( fin );
// 
//             // Read the edges and insert
//             String line;
//             while( graphFile.hasNextLine( ) )
//             {
//                 line = graphFile.nextLine( );
//                 StringTokenizer st = new StringTokenizer( line );
// 
//                 try
//                 {
//                     if( st.countTokens( ) != 3 )
//                     {
//                         System.err.println( "Skipping ill-formatted line " + line );
//                         continue;
//                     }
//                     String source  = st.nextToken( );
//                     String dest    = st.nextToken( );
//                     int    cost    = Integer.parseInt( st.nextToken( ) );
//                     g.addEdge( source, dest, cost );
//                 }
//                 catch( NumberFormatException e )
//                   { System.err.println( "Skipping ill-formatted line " + line ); }
//              }
//          }
//          catch( IOException e )
//            { System.err.println( e ); }
// 
//          System.out.println( "File read..." );
//          System.out.println( g.vertexMap.size( ) + " vertices" );
// 
//          Scanner in = new Scanner( System.in );
//          while( processRequest( in, g ) )
//              ;
    //}
}

// full edge class
class FullEdge {
    String start;
    String dest;
    double cost;
    
    FullEdge(String st, String dst, double cst) {
        start = st;
        dest = dst;
        cost = cst;
    }
    
    public String toString() {
        return "("+start+","+dest+","+cost+")";
    }
}


class SetMaker {

    // http://stackoverflow.com/questions/1670862/obtaining-powerset-of-a-set-in-java

    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        
        if (originalSet.isEmpty()) {    // an empty set has only one subset
            sets.add(new HashSet<T>()); // and that's the empty set
            return sets;   // sets contains only the empty set
        }
        
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);   // first element
        Set<T> rest = new HashSet<T>(list.subList(1, list.size())); // all the rest
        
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }      
        return sets;
    }


//     public static void main(String[] args) {
//         Set<Integer> mySet = new HashSet<Integer>();
//         mySet.add(1);
//         mySet.add(2);
//         mySet.add(3);
//         for (Set<Integer> s : SetMaker.powerSet(mySet)) {
//             System.out.println(s);
//         }    
//         System.out.println();
//         System.out.println("Edge Power Set Demo");
//         Set<FullEdge> edges = new HashSet<FullEdge>();
//         edges.add(new FullEdge("A","B",5));
//         edges.add(new FullEdge("B","C",4));
//         edges.add(new FullEdge("C","D",3));
//         edges.add(new FullEdge("D","A",2));  
//         for (Set<FullEdge> s : SetMaker.powerSet(edges)) {
//             System.out.println(s);
//         }                                 
//     }

}