import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class jzzhuAndCities {

    static class state{
        int city;
        long cost;
        int flag;
        public state(int city, long cost, int flag){
            this.city=city;
            this.cost= cost;
            this.flag = flag;
        }
    }
    static class Pair{
        int to_city;
        long cost;
        public Pair(int to_city, long cost){
            this.to_city=to_city;
            this.cost= cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());//citites
        int m = Integer.parseInt(st.nextToken());//roads
        int k = Integer.parseInt(st.nextToken());//train

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i =0; i<=n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i =1; i<=m; i++){
            st=new StringTokenizer(br.readLine());
            int u=  Integer.parseInt(st.nextToken());
            int v= Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            adj.get(u).add(new Pair(v,w));
            adj.get(v).add(new Pair(u,w));
        }
        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1]=0;
        PriorityQueue<state> pq = new PriorityQueue<>((a,b)-> Long.compare(a.cost, b.cost));
        pq.offer(new state(1,0,0));

        for(int i=0; i<k; i++){
            st= new StringTokenizer(br.readLine());
            int newCity= Integer.parseInt(st.nextToken());
            long newCityCost = Long.parseLong(st.nextToken());
            pq.offer(new state(newCity, newCityCost,1));
        }
        int remove = 0;
        boolean[] vis = new boolean[n+1];
        while(!pq.isEmpty()){
            state curr = pq.poll();
            int u = curr.city;
            long weight = curr.cost;
            int via = curr.flag;

            if( dist[u]<=weight && via==1){
                remove++;
                continue;
            }
            else if(dist[u]>weight && via==1){
                dist[u]=weight;

            }
            if(vis[u]) continue;
            vis[u]=true;

            if(dist[u]<weight) continue;
            for(Pair adjNodde: adj.get(u)){
                int v = adjNodde.to_city;
                long c = adjNodde.cost;
                if(dist[v]>weight+c){
                    dist[v]=weight+c;
                    pq.offer(new state(v,dist[v],0));
                }
            }

        }
        System.out.println(remove);
    }
}
