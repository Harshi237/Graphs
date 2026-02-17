import java.io.*;
import java.util.*;

public class foxAndNAmes {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[26];


        for (int i = 0; i < n - 1; i++) {
            String a = words[i];
            String b = words[i + 1];

            int len = Math.min(a.length(), b.length());
            boolean found = false;

            for (int j = 0; j < len; j++) {
                if (a.charAt(j) != b.charAt(j)) {
                    int u = a.charAt(j) - 'a';
                    int v = b.charAt(j) - 'a';
                    adj.get(u).add(v);
                    indegree[v]++;
                    found = true;
                    break;
                }
            }


            if (!found && a.length() > b.length()) {
                System.out.println("Impossible");
                return;
            }
        }


        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        StringBuilder ans = new StringBuilder();

        while (!q.isEmpty()) {
            int u = q.poll();
            ans.append((char) (u + 'a'));

            for (int v : adj.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.add(v);
                }
            }
        }

        if (ans.length() != 26) {
            System.out.println("Impossible");
        } else {
            System.out.println(ans.toString());
        }
    }
}