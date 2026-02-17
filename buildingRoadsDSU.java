import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class buildingRoadsDSU {
    static int[] parent;
    static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] edges= new int[m][2];

        parent = new int[n+1];
        for(int i =1; i<=n; i++){
            parent[i]=i;
        }
        rank = new int[n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0]= Integer.parseInt(st.nextToken());;
            edges[i][1]= Integer.parseInt(st.nextToken());;
        }

        for(int[] e: edges){
            int u=e[0];
            int v = e[1];
            unionRank(u,v);
        }


        ArrayList<Integer> ans= new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (find(i) == i) ans.add(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans.size() - 1).append("\n");

        for (int i = 1; i < ans.size(); i++) {
            sb.append(ans.get(i - 1)).append(" ").append(ans.get(i)).append("\n");
        }

        System.out.print(sb.toString());

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
