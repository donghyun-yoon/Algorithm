import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class ssafy_실습3_미로도착지점 {
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int jumper = Integer.parseInt(st.nextToken());

            int[][] map = new int[N+1][N+1];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<jumper; i++) {
                int jx = Integer.parseInt(st.nextToken());
                int jy = Integer.parseInt(st.nextToken());
                map[jx][jy] = 1;
            }

            boolean isCheck = false;
            int dirCnt = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<dirCnt; i++) {
                int dir = Integer.parseInt(st.nextToken())-1;
                int move = Integer.parseInt(st.nextToken());

                for(int j=0; j<move; j++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if(nx<1 || nx>N || ny<1 || ny>N || map[nx][ny] == 1) {
                        x = y = 0;
                        isCheck = true;
                        break;
                    }
                    x = nx;
                    y = ny;
                }
                if(isCheck) break;
            }
            System.out.println("#"+tc+" "+x+" "+y);
        }//end tc
    }//end main
}//end class