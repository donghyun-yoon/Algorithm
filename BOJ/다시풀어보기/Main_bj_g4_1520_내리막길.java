import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_g4_1520_내리막길 {

	static int[][] map,dp;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int N,M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		System.out.println(dfs(0,0));

		br.close();
	}

	static int dfs(int x, int y) {
		if(x == N-1 && y == M-1) {
			return 1;
		}

		//다른 경로에서 갔다왔으므로 갈필요없다.
		if(dp[x][y] != -1) {
			return dp[x][y];
		}

		//현재 위치에서 끝점까지 도달하는 경로의 개수를 일단 0으로 생각
		dp[x][y] = 0;

		for(int dir=0; dir<4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
			if(map[x][y] > map[nx][ny]) dp[x][y] += dfs(nx, ny);
		}

		return dp[x][y];
	}
	
}