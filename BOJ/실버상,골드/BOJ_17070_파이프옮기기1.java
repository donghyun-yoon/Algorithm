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