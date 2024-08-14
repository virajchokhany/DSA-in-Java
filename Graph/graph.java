package Graph;

import java.util.*;

public class graph {
    public static class Edge{
        int src;
        int nbr;
        int wt;
        public Edge(int src,int nbr, int wt) {
            this.src=src;
            this.nbr=nbr;
            this.wt=wt;
        }
    }

    public static boolean hasPath(ArrayList<ArrayList<Edge>> graph, int src, int dest, boolean vis[]){
        if(src==dest) return true;
        vis[src]=true;
        for(Edge edge : graph.get(src)){
            if(vis[edge.nbr]==false){
                boolean doesNbrHavePath = hasPath(graph, edge.nbr, dest, vis);
                if(doesNbrHavePath)
                return true;
            }
        }
        return false;
    }
    public static void printAllPaths(ArrayList<ArrayList<Edge>> graph, int src,int dest, boolean vis[],String psf){
        if(src==dest){
            System.out.println(psf+dest);
            return;
        }
        vis[src]=true;
        for(Edge edge : graph.get(src)){
            if(vis[edge.nbr]==false){
                printAllPaths(graph, edge.nbr, dest, vis, psf+src);
            }
        }
        vis[src]=false;
    }
    static int smallest=Integer.MAX_VALUE;
    static String smallestPath = "";
    static int largest = Integer.MIN_VALUE;
    static String largestPath = "";
    static int ceil = Integer.MAX_VALUE;
    static String ceilPath = "";
    static int floor = Integer.MIN_VALUE;
    static String floorPath = "";

    public static void multiSolver(ArrayList<ArrayList<Edge>> graph, int src, int dest, boolean vis[],int wsf, String psf, int criteria,int k,PriorityQueue<Pair> pq){
        if(src==dest){
            if(wsf > largest){ // largest
                largest = wsf;
                largestPath = psf + dest;
            }
            if(wsf<smallest){ // smallest
                smallest = wsf;
                smallestPath = psf + dest;
            }
            pq.add(new Pair(wsf,psf+dest));
            if(pq.size()>k){
                pq.remove();
            }
            if(wsf>criteria && wsf<ceil){
                ceil = wsf;
                ceilPath = psf + dest;
            }
            if(wsf<criteria && wsf>floor){
                floor = wsf;
                floorPath = psf+ dest;
            }
            return;
        }

        vis[src]=true;
        for(Edge edge : graph.get(src)){
            if(vis[edge.nbr]==false){
                multiSolver(graph, edge.nbr, dest, vis, wsf + edge.wt, psf + src,criteria,k,pq);
            }
        }
        vis[src]=false;
    }
    static class Pair{
        int wt;
        String path;
        public Pair(int wt,String path) {
            this.wt = wt;
            this.path = path;
        }
    }

    public static void getConnectedComponents_(ArrayList<ArrayList<Edge>> graph, int src,boolean vis[],ArrayList<Integer> list){
        vis[src]=true;  
        list.add(src);
        for(Edge edge : graph.get(src)){
            if(vis[edge.nbr]==false){
                getConnectedComponents_(graph, edge.nbr, vis,list);
            }
        }

    }

    public static List<ArrayList<Integer>> getConnectedComponents(ArrayList<ArrayList<Edge>> graph){
        boolean vis[]=new boolean[graph.size()];
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();        
        for(int i=0;i<graph.size();i++){
            if(vis[i]==false){
                ArrayList<Integer> list = new ArrayList<>();
                getConnectedComponents_(graph, i, vis,list);
                ans.add(list);
            }
        }
        return ans;

    }

    public static boolean isgraphBipartite(ArrayList<ArrayList<Edge>> graph){
        int src = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(src);
        int vis[]=new int[graph.size()];
        int level = 1;
        while(queue.size()>0){
            int size = queue.size();
            while(size-->0){
                int rn = queue.remove();
                if(vis[rn]==0){     // unvisited
                    vis[rn] = level;
                }
                if(vis[rn]==level){
                    continue;
                }
                else if(vis[rn]!=level)
                    return false;
                for(Edge edge : graph.get(rn)){
                    if(vis[edge.nbr]==0){
                        queue.add(edge.nbr);
                    }
                }
            }
            level++;
        }
        return true;
    }

    public static int spreadInfection(ArrayList<ArrayList<Edge>> graph,int src, int time){
        int sum = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(src);
        int level = 1;
        boolean isBreak = false;
        boolean vis[]=new boolean[graph.size()];
        while(queue.size()>0){
            int size = queue.size();
            while(size-->0){
                int rn = queue.remove();
                if(level>time){
                    isBreak=true;break;
                }
                if(vis[rn]==true){
                    continue;
                }
                sum++;
                vis[rn]=true;
                for(Edge edge : graph.get(rn)){
                    if(vis[edge.nbr]==false){
                        queue.add(edge.nbr);
                    }
                }
            }
            level++;
            if(isBreak)break;
        }

        return sum;
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for(int i=0;i<7;i++){
            graph.add(new ArrayList<>());
        }
        // graph.get(0).add(new Edge(0, 1, 10));
        // graph.get(1).add(new Edge(1, 0, 10));

        // graph.get(2).add(new Edge(2, 3, 10));
        // graph.get(3).add(new Edge(3, 2, 10));

        // graph.get(4).add(new Edge(4, 5, 10));
        // graph.get(5).add(new Edge(5, 4, 10));

    
        // graph.get(5).add(new Edge(5, 6, 10));
        // graph.get(6).add(new Edge(6, 5, 10));

        // graph.get(4).add(new Edge(4, 6, 10));
        // graph.get(6).add(new Edge(6, 4, 10));

        // List<ArrayList<Integer>> ans = getConnectedComponents(graph);
        // if(ans.size()>1){
        //     System.out.println("Graph is not connected");
        // }
        graph.get(0).add(new Edge(0,1,10));
        graph.get(1).add(new Edge(1,0,10));

        graph.get(0).add(new Edge(0,3,40));
        graph.get(3).add(new Edge(3,0,40));

        graph.get(1).add(new Edge(1,2,10));
        graph.get(2).add(new Edge(2,1,10));

        graph.get(2).add(new Edge(2,3,10));
        graph.get(3).add(new Edge(3,2,10));

        graph.get(3).add(new Edge(3,4,2));
        graph.get(4).add(new Edge(4,3,2));

        graph.get(4).add(new Edge(4,5,3));
        graph.get(5).add(new Edge(5,4,3));

        graph.get(5).add(new Edge(5,6,3));
        graph.get(6).add(new Edge(6,5,3));

        graph.get(4).add(new Edge(4,6,8));
        graph.get(6).add(new Edge(6,4,8));

        System.out.println(spreadInfection(graph,6,4));

        // boolean vis[]=new boolean[7];
        // // boolean hasPath = hasPath(graph, 0, 6, vis);
        // // System.out.println(hasPath);
        // // vis=new boolean[7];
        // // printAllPaths(graph,0,6,vis,"");
        // PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
        //     return a.wt-b.wt;
        // });
        // int criteria = 40;
        // multiSolver(graph, 0, 6, vis, 0, "", criteria, 3,pq);
        // System.out.println("Largest : "+largestPath+"@"+largest);
        // System.out.println("Smallest Path :" + smallestPath+"@"+smallest);
        // System.out.println("kth largest is : "+pq.peek().path +"@"+pq.peek().wt);
        // System.out.println("Ceil : "+ ceilPath + "@"+ceil);
        // System.out.println("Floor "+ floorPath+"@"+floor);
    }
}

