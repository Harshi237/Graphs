import java.util.*;
import java.util.Scanner;

public class IceSkating {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] x = new int[n];
        int[] y = new int[n];

        for(int i =0; i<n ; i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i =0; i<n; i++){
            for(int j=i+1; j<n; j++ ){
                if(x[i]==x[j] || y[i]==y[j]){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        boolean[] visited = new boolean[n];
        int component=0;
        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfs(i,visited, adj);
                component++;
            }
        }
        System.out.println(component-1);
     }
     public static void dfs(int node, boolean[] visited, List<List<Integer>> adj){
        visited[node]=true;
        for(int nbr: adj.get(node)){
            if(!visited[nbr]){
                dfs(nbr, visited, adj);
            }
         }
     }
}
