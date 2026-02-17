//package Graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class shortestDist1 {
    static class Pair{
        int node;
        long dis;
        public Pair(int node, long dis){
            this.node = node;
            this.dis=dis;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        long[] distance = new long[n+1];
        Arrays.fill(distance, Long.MAX_VALUE);
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i =0; i<=n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i =1 ; i<=V; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            adj.get(u).add(new Pair(v, w));
        }

        distance[1] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b)-> Long.compare(a.dis, b.dis));
        pq.offer(new Pair(1,0));

        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int u = curr.node;
            long d = curr.dis;

            if(d>distance[u]) continue; // important line decrease key via lazy deletion

            for(Pair p : adj.get(u)){
                int v = p.node;
                long costu_v = p.dis;
                if(distance[v]>distance[u]+costu_v){
                    distance[v]=distance[u]+costu_v;
                    pq.offer(new Pair(v,distance[v]));
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=1; i< distance.length; i++){
            if(distance[i]!=Long.MAX_VALUE){
                bw.write(distance[i] + " ");
            }

        }
        bw.flush();
    }
}