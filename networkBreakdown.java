import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class networkBreakdown {
    static int[] parent;
    static int[] rank;
    static class Pair{
        int u;
        int v;
        public Pair(int u, int v){
            this.u = u;
            this.v = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        rank = new int[n+1];

        for(int i =1; i<=n ; i++){
            parent[i] = i;
        }
        ArrayList<Pair> edge = new ArrayList<>();
        HashSet<Pair> breakdown = new HashSet<>();
        for(int i=0; i<m ; i++){
            int u = Integer.parseInt(br.readLine());
            int v = Integer.parseInt(br.readLine());
            edge.add(new Pair(u,v));
        }
        for(int i=0; i<k ; i++){
            int u = Integer.parseInt(br.readLine());
            int v = Integer.parseInt(br.readLine());
            breakdown.add(new Pair(u,v));
            breakdown.add(new Pair(v,u));
        }
        for(Pair p: edge){
            int a =p.u, b=p.v;
            if(unionRank(a,b)){

            }
        }



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
        else if(rank[p_x] < rank[p_y]){
            parent[p_x] = p_y;
        }
        else{
            parent[p_x]=p_y;
            rank[p_y]++;
        }
        return true;
    }
}
