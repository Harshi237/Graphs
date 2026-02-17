import java.io.*;
import java.util.*;

public class LongestFlightRoute {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<=n; i++){
            adj.add(new ArrayList<>());
        }

        int[] parent = new int[n+1];
        Arrays.fill(parent,-1);

        int[] countCities = new int[n+1];
        Arrays.fill(countCities,0);

        int[] indegree = new int[n+1];

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v= Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            indegree[v]++;
        }

        countCities[1]= 1;

        Queue<Integer> q = new LinkedList<>();
        for(int i =1; i<=n; i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int u = q.poll();
            for(int v : adj.get(u)){
                if(countCities[u]>0 && 1+countCities[u]>countCities[v]){
                    countCities[v]=1+countCities[u];
                    parent[v]=u;
                }
                indegree[v]--;
                if(indegree[v]==0){
                    q.offer(v);
                }
            }
        }

        if(countCities[n]==0) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        ArrayList<Integer> path = new ArrayList<>();

        for(int curr = n; curr!=-1; curr=parent[curr]){
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
