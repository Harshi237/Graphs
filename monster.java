import java.io.*;
import java.util.*;

public class monster{

    static int [] dr={-1,1,0,0};
    static int [] dc = {0,0,-1,1};
    static char[] dir={'U','D','L','R'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        char[][] grid = new char [n][m];
        boolean[][] visM = new boolean[n][m];
        boolean[][] visP = new boolean[n][m];
        char [][] parent = new char [n][m];
        int [][] disM = new int [n][m];
        int [][] disP = new int [n][m];

        for(int [] row : disM){
            Arrays.fill(row,Integer.MAX_VALUE);

        }
        for(int [] row : disP){
            Arrays.fill(row,Integer.MAX_VALUE);

        }

        Queue<int []> mQ = new LinkedList<>();
        Queue<int []> mP = new LinkedList<>();
        int pr = -1, pc=-1;

        for(int i =0 ;i<n ;i++){
            String s = br.readLine();
            for(int j=0;j<m;j++){
                grid[i][j]=s.charAt(j);
                if(grid[i][j]=='M'){
                    mQ.offer(new int [] {i,j});
                    disM[i][j]=0;
                    visM[i][j]=true;
                }
                if(grid[i][j]=='A'){
                    pr=i;
                    pc=j;
                    disP[i][j]=0;
                    visP[i][j]=true;
                    mP.offer(new int [] {i,j});
                }
            }
        }

        //update monster distance to neighbor cells
        while(!mQ.isEmpty()){
            int[] curr= mQ.poll();
            int r = curr[0];
            int c =curr[1];
            int dis = disM[r][c];

            //is valid
            for(int i =0;i<4;i++){
                int nr = r+dr[i];
                int nc = c+dc[i];

                if(nr>= 0 && nc>=0 && nr<n && nc<m && !visM[nr][nc] && grid[nr][nc]=='.' && 1 + dis < disM[nr][nc]){
                    disM[nr][nc]= 1+dis;
                    visM[nr][nc]=true;
                    mQ.offer(new int [] {nr,nc});

                }
            }

        }
//         for(int i =0;i<n ;i++){
//             for(int j =0;j<m;j++){
//                 System.out.print(disM[i][j] + " ");
//             }
//             System.out.println("\n");
//         }

        //update player distance

        int per = -1 , pec=-1;
        while(!mP.isEmpty()){
            int [] curr = mP.poll();
            int r = curr[0];
            int c = curr[1];
            int dis = disP[r][c];
            if(r==0 || c==0 || r==n-1 || c==m-1){ //boundary check
                per=r;
                pec=c;
                break;
            }
            for(int i =0;i<4;i++){
                int nr= r+dr[i];
                int nc = c+dc[i];
                char direction = dir[i];
                //validity

                if(nr>= 0 && nc>=0 && nr<n && nc<m && !visP[nr][nc] && grid[nr][nc]=='.' && 1 + dis < disM[nr][nc] ){
                    visP[nr][nc]=true;
                    disP[nr][nc]=1+dis;
                    mP.offer(new int [] {nr,nc});
                    parent[nr][nc]=direction;
                }
            }

        }
           for(int i =0;i<n ;i++){
                 for(int j =0;j<m;j++){
                     System.out.print(disP[i][j] + " ");
                 }
                 System.out.println("\n");
             }
              if(visM[per][pec]==false){
                 System.out.println("NO");
                 return;
         }

        //update player distance

        if(per==-1 && pec==-1){
            System.out.println("NO");
            return;
        }
        ArrayList<Character > path = new ArrayList<>();
        int i =per , j=pec;

        while(i!=pr || j!=pc){
            path.add(parent[i][j]);
            if(parent[i][j]=='U') i++;
            else if (parent[i][j]=='D') i--;
            else if (parent[i][j]=='L') j++;
            else if (parent[i][j]=='R') j--;
        }

        Collections.reverse(path);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int pathL= path.size();
        bw.write("YES" + "\n");
        bw.write(pathL+ "\n");

        for(int i1 =0;i1<pathL ;i1++){
            bw.write(path.get(i1));

        }
        bw.flush();
        return;

    }
}