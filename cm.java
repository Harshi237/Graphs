import java.io.*;
import java.util.*;

public class cm {
    static int n, m;
    static char[][] grid;
    static boolean[][] vis;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        n = fs.nextInt();
        m = fs.nextInt();

        grid = new char[n][m];
        vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            grid[i] = fs.next().toCharArray();
        }

        int rooms = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.' && !vis[i][j]) {
                    dfs(i, j);
                    rooms++;
                }
            }
        }

        System.out.println(rooms);
    }

    static void dfs(int r, int c) {
        vis[r][c] = true;

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if (nr >= 0 && nc >= 0 && nr < n && nc < m &&
                    grid[nr][nc] == '.' && !vis[nr][nc]) {
                dfs(nr, nc);
            }
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
