import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class investigation {
    static class Pair{
        int node;
        long dis;
        public Pair(int node,long dis){
            this.node=node;
            this.dis=dis;
        }
    }
    static final long  mod=1_000_000_007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i =0; i<=n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i =1 ; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            adj.get(u).add(new Pair(v, w));
        }
        long[] distance = new long[n+1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[1] = 0;

        long[] ways = new long[n+1];
        ways[1]=1;

        long[] maxArr = new long[n+1];
        long[] minArr = new long[n+1];

        PriorityQueue<Pair> pq=new PriorityQueue<>((a, b)->Long.compare(a.dis,b.dis));
        pq.offer(new Pair(1,0));

        while(!pq.isEmpty()){
            Pair curr=pq.poll();
            int u=curr.node;
            long d=curr.dis;

            if( d > distance[u] ) continue;

            for(Pair p:adj.get(u)){
                int v=p.node;
                long cost_u_v=p.dis;

                if(distance[v] > distance[u] + cost_u_v ) {

                    distance[v] = distance[u] + cost_u_v;
                    ways[v]=ways[u];
                    minArr[v] = 1+ minArr[u];
                    maxArr[v] = 1+ maxArr[u];
                    pq.offer(new Pair(v,distance[v]) );

                }
                else  if(distance[v] == distance[u] + cost_u_v ){
                    ways[v]=(ways[v]+ways[u])%mod;
                    minArr[v]= Math.min(minArr[v], minArr[u]+1);
                    maxArr[v]= Math.max(maxArr[v], maxArr[u]+1);
                }
            }
        }
        System.out.println(distance[n]+ " " + ways[n] + " " + minArr[n] + " " + maxArr[n]);

    }
}
