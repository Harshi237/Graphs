import java.io.*;
import java.util.*;

public class gamesRoutes {
    static final int mod = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<=n; i++){
            adj.add(new ArrayList<>());
        }

        long[] d = new long[n+1];
        Arrays.fill(d,0);

        int[] indegree = new int[n+1];

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v= Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            indegree[v]++;
        }

        d[1]= 1;

        Queue<Integer> q = new LinkedList<>();
        for(int i =1; i<=n; i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int u = q.poll();
            for(int v : adj.get(u)){
                if(d[u]>0 && d[u]+d[v]>d[v]){
                    d[v]=(d[v]+d[u])%mod;
                }
                indegree[v]--;
                if(indegree[v]==0){
                    q.offer(v);
                }
            }
        }

        System.out.println(d[n]);

    }
}
