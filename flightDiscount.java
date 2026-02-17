import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class flightDiscount {
    static class Pair{
        int node;
        long dis;

        public Pair(int node, long dis){
            this.node = node;
            this.dis=dis;
        }
    }

    static class edge{
        int src, dest;
        long cost ;
        public edge(int src, int dest, long cost){
            this.src=src;
            this.dest= dest;
            this.cost=cost;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        ArrayList<ArrayList<Pair>> adj2 = new ArrayList<>();
        for (int i = 0; i <=n; i++) {
            adj2.add(new ArrayList<>());
        }

        ArrayList<edge> edges = new ArrayList<>();

        for (int i = 1; i <=m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            adj.get(u).add(new Pair(v, w));
            adj2.get(v).add(new Pair(u, w));
            edges.add(new edge(u,v,w));

        }

        long[] bfsbegin = bfs(1,n, adj);


        long[] bfsend = bfs(n,n, adj2);// first n is source .. and seconf=d n is for how many times it run

        long ans= Long.MAX_VALUE;

        for(edge e : edges){
            if(bfsbegin[e.src]!=Long.MAX_VALUE && bfsend[e.dest]!= Long.MAX_VALUE) {
                long cand = bfsbegin[e.src] + bfsend[e.dest] + (e.cost / 2) ;
                ans = Math.min(cand, ans);
            }

        }

        System.out.println(ans);

    }

    public static long[] bfs(int src, int n , ArrayList<ArrayList<Pair>> list){
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> Long.compare(a.dis,b.dis));
         pq.offer(new Pair(src,0));

         long[] distance = new long[n+1];
         Arrays.fill(distance, Long.MAX_VALUE);
         distance[src]=0;

         while(!pq.isEmpty()){
             Pair curr = pq.poll();
             int u = curr.node;
             long w = curr.dis;

             if(w > distance[u]) continue;

             for(Pair p : list.get(u)){
                 int v = p.node;
                 long costu_v= p.dis;

                 if(distance[v]> distance[u] + costu_v){
                     distance[v] = distance[u] + costu_v;
                     pq.offer(new Pair(v, distance[v]));
                 }
             }
         }
         return distance;


    }
}
