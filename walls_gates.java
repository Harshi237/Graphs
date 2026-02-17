import java.util.LinkedList;
import java.util.Queue;

public class walls_gates {
    static int INF = 2147483647;
    static int rlen;
    static int clen;
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};

    public static void main(String[] args){
        int[][] grid = {
                {INF , -1 , 0  ,INF},
                {INF, INF ,INF,  -1},
                {INF , -1 ,INF , -1},
                {0  ,-1, INF ,INF}};

        rlen = grid.length;
        clen = grid[0].length;

        // insert all gates into queue

        Queue<int[] > q = new LinkedList<>();

        for(int i=0; i<rlen; i++){
            for(int j=0; j<clen; j++){
                if(grid[i][j]==0){
                    q.offer(new int[]{i,j,0});
                }

            }
        }
        //BFS
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];

            // movement
            for(int i=0;i<4;i++){
                int newrow=r+dr[i];
                int newcol=c+dc[i];

                //validity check

                if(newrow>=0 && newcol>=0 && newrow<rlen && newcol<clen && grid[newrow][newcol]==INF){
                    //update distance and push into queue
                    grid[newrow][newcol] = d + 1;
                    q.offer(new int[]{newrow,newcol,d+1});

                }
            }
        }
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
