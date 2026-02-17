import java.util.*;

public class Lakes {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            boolean[][] visited = new boolean[n][m];
            long maxVolume = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    if (grid[i][j] > 0 && !visited[i][j]) {
                        long volume = bfs(i, j, grid, visited, n, m);
                        maxVolume = Math.max(maxVolume, volume);
                    }
                }
            }

            System.out.println(maxVolume);
        }
    }

    static long bfs(int sr, int sc, int[][] grid, boolean[][] visited, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        long sum = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            sum += grid[r][c];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                        grid[nr][nc] > 0 && !visited[nr][nc]) {

                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return sum;
    }
}
