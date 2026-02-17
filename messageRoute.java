import java.io.*;
import java.util.*;

public class messageRoute {
//    static final int mod = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<=n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v= Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] distance = new int[n+1];

        Arrays.fill(distance, -1);
        distance[1]=0;

        int[] parent = new int[n+1];


        Queue<Integer> q = new LinkedList<>();

        q.offer(1);
        parent[1]=0;


        while(!q.isEmpty()){
            int u = q.poll();
            for(int v : adj.get(u)){
                if(distance[v]==-1){
                    distance[v] = distance[u]+1;
                    parent[v]=u;
                    q.offer(v);
                }
            }
        }

        if(distance[n]==-1) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        ArrayList<Integer> path = new ArrayList<>();

        for(int curr = n; curr!=0; curr=parent[curr]){
            path.add(curr);
        }
        Collections.reverse(path);
        int pathL = path.size();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(pathL+"\n");
        for(int val:path){
            bw.write(val+" ");
        }
        bw.flush();

    }

}