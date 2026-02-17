import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class buildingRoads {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] edges= new int[m+1][2];

        for(int i=0; i<m; i++){
            edges[i][0]= br.read();
            edges[i][1]= br.read();
        }

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int[] e: edges){
            int u=e[0];
            int v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        ArrayList<Integer> compRep= new ArrayList<>();
        boolean[] visited = new boolean[m+1];
        int count=0;
        for(int i=1; i<n; i++){
            if(!visited[i]){
                compRep.add(i);
                dfs(i,adj,visited);
                count++;
            }
        }
        int bridge=count-1;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(bridge+"\n");
        for(int i=0; i<compRep.size()-1; i++){
            bw.write(compRep.get(i)+ " " + compRep.get(i+1));
        }
        bw.flush();
    }

    private static void dfs(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
    }

}
