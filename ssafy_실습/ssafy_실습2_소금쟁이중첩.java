import java.io.*;
import java.util.StringTokenizer;

class ssafy_실습2_소금쟁이중첩 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int bugNum = Integer.parseInt(st.nextToken());

            int[][] pond = new int[N][N];
            int[] dx = {1,0};
            int[] dy = {0,1};

            int checkDouble=0;
            for(int bug=1; bug<=bugNum; bug++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1;
                System.out.println("input data "+ x + " "+y+" " +dir);
                pond[x][y] = bug;

                for(int k=3; k>0; k--) {
                    int nx = x + dx[dir]*k;
                    int ny = y + dy[dir]*k;

                    if(nx<0 || ny<0 || nx>=N || ny >=N) break;
                    if(pond[nx][ny] != 0) {
                        checkDouble = bug;
                    }

                    pond[nx][ny] = bug;
                    x = nx;
                    y = ny;
                }
            }
            System.out.println("#"+tc+" "+checkDouble);
        }//end tc
    }//end main
}//end class