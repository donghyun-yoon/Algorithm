import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ssafy_실습1_로봇이동거리 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(br.readLine());
            char[][] map = new char[N][N];

            for(int i=0; i<N; i++) {
                StringTokenizer str = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = str.nextToken().charAt(0);
                }
            }
            
            int cnt = 0;
            int[] dx = {0,0,1,-1};
            int[] dy = {1,-1,0,0};
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] == 'W' || map[i][j] == 'S') continue;

                    int dir = (map[i][j] == 'A')?1:(map[i][j] == 'B')?2:4;

                    for(int k=0; k<dir; k++) {
                        int nx = i+dx[k];
                        int ny = j+dy[k];
                        while(nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny]=='S') {
                            nx += (k==3)?-1:(k==2)?1:0;
                            ny += (k==0)?1:(k==1)?-1:0;
                            cnt++;
                        }
                    }
                }
            }
            System.out.println("#"+tc+" "+cnt);
        }
    }//end main
}//end class