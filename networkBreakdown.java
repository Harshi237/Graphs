//package graph;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class networkBreakdown {

    static int[] size;
    static int[] parent;
    static int component;

    public static int find(int x){
        if(x==parent[x]) return x;
        return parent[x]=find(parent[x]);
    }

    public static boolean union(int x,int y){
        int parent_u=find(x);
        int parent_v=find(y);

        if(parent_u==parent_v) return false;

        if(size[parent_u]<size[parent_v]){
            int temp=parent_u;
            parent_u=parent_v;
            parent_v=temp;
        }

        parent[parent_v]=parent_u;
        size[parent_u]+=size[parent_v];
        component--;
        return true;
    }

    public static void main(String[] args)throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        HashSet<Long> set = new HashSet<>();

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        int[][] edges=new int[m][2];
        int[][] removedEdge=new int[k][2];

        parent=new int[n+1];
        size=new int[n+1];
        component=n;

        for(int i=1;i<=n;i++){
            parent[i]=i;
            size[i]=1;
        }

        // edges added
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());

            int a=Math.min(u,v);
            int b=Math.max(u,v);

            edges[i][0]=a;
            edges[i][1]=b;
        }

        // removed edges
        for(int i=0;i<k;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());

            int a=Math.min(u,v);
            int b=Math.max(u,v);

            removedEdge[i][0]=a;
            removedEdge[i][1]=b;

            set.add((long)a<<32|b);
        }

        // build  graph
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];

            if(!set.contains((long)u<<32|v)){
                union(u,v);
            }
        }

        int[] ans=new int[k];

        // reverse order add edges
        for(int i=k-1;i>=0;i--){
            ans[i]=component;
            union(removedEdge[i][0],removedEdge[i][1]);
        }

        StringBuilder sb=new StringBuilder();
        for(int x:ans){
            sb.append(x).append(" ");
        }

        System.out.println(sb.toString());
    }
}