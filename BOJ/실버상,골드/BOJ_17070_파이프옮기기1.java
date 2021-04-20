import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_17070_파이프옮기기1 {
	
	static int[][] map;
	static int[] dx = {0,1,1};
	static int[] dy = {1,0,1};
	static int N,cnt=0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,1,0);
		System.out.println(cnt);
		br.close();
	}

	static void dfs(int x, int y, int dir) {
		if(x == N-1 && y == N-1) {
			cnt++;
			return;
		}

		int nx,ny;
		for(int d=0; d<3; d++) {
			if(dir == 0 && d == 1) continue;
			if(dir == 1 && d == 0) continue;
			nx = x + dx[d];
			ny = y + dy[d];
			if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny] == 1) continue;
			if(d==2 && (map[nx][ny-1] == 1 || map[nx-1][ny] == 1)) continue;
			dfs(nx,ny,d);
		}
	}
}//end class

/* dp 버전
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int[][][] dp;
    static int[][] map;
    static int[] dx = {0,1,1};
    static int[] dy = {1,0,1};
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        dp = new int[N][N][3];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                for(int k=0; k<3; k++) dp[i][j][k] = -1;
            }
        }

        System.out.println(dfs(0, 1, 0));

        br.close();
    }

    static int dfs(int x, int y, int d) {
        if(x == N-1 && y == N-1) return 1;

        if(dp[x][y][d] != -1) return dp[x][y][d];

        dp[x][y][d] = 0;

        for(int dir=0; dir<3; dir++) {
            if(d == 0 && dir == 1) continue;
            if(d == 1 && dir == 0) continue;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1) continue;
            if(dir == 2 && (map[x][ny] == 1 || map[nx][y] == 1)) continue;

            dp[x][y][d] += dfs(nx, ny, dir);
        }

        return dp[x][y][d];
    }
}
*/