import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class roadReparation {
    static class edge{
        int u;
        int v;
        long w;
        public edge(int u, int v, long w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    static int[] parent;
    static int[] rank;
    static int components;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent =  new int[n+1];
        rank = new int[n+1];

        for(int i=1; i<=n ; i++){
            parent[i]=i;
        }

        long cost =0;

        PriorityQueue<edge> pq =new PriorityQueue<>((a,b)->Long.compare(a.w,b.w));


        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            pq.offer(new edge(a,b,c));

        }

        components = n;
        while(!pq.isEmpty()){
            edge e = pq.poll();
            int a = e.u;
            int b = e.v;
            long c = e.w;

            if(unionRank(a,b)){
                cost+=c;


            }
        }
       if(components>1){
           System.out.println("IMPOSSIBLE");
           return;
       }
        System.out.println(cost);

    }
    public static int find(int x){
        if(x==parent[x]) return x;
        return parent[x]= find(parent[x]);
    }
    public static boolean unionRank(int x, int y){
        int p_x = find(x);
        int p_y = find(y);
        if(p_x == p_y){
            return false;
        }
        if(rank[p_x]> rank[p_y]){
            parent[p_y] = p_x;
        }
        else if(rank[p_x]> rank[p_y]){
            parent[p_x] = p_y;
        }
        else{
            parent[p_x]=p_y;
            rank[p_y]++;
        }
        components--;
        return true;
    }
}
