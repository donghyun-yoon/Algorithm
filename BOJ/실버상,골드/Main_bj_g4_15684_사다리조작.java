import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_g4_15684_사다리조작 {

	static int[][] map;
	static int N,M,H,res;
	static boolean rigging;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st =  new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로선
		M = Integer.parseInt(st.nextToken()); // 가로선
		H = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 위치의 개수

		map = new int[H+1][N+1];

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
		}

		rigging = false;
		for(int i=0; i<=3; i++) {
			res = i;
			dfs(0, 1);
			if(rigging) break;
		}

		System.out.println((rigging)?res:-1);

        br.close();
	}

	static boolean isCheck() {
		for(int i=1; i<=N; i++) {
			int nh = 1, n = i;
			while(nh <= H && n > 0 && n <=N) {
				if(map[nh][n] == 1) n++;
				else if(n-1 > 0 && map[nh][n-1] == 1) n--;
				nh++;
			}
			if(i != n) return false;
		}
		return true;
	}

	static void dfs(int cnt, int row) {
		if(rigging) return;

		if(cnt == res) {
			if(isCheck()) rigging = true;
			return;
		}

		for(int i=row; i<=H; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] == 1) continue;
				if(j-1>0 && map[i][j-1]==1) continue;
				if(j+1<=N && map[i][j+1]==1) continue;
				map[i][j] = 1;
				dfs(cnt+1, i);
				map[i][j] = 0;
			}
		}
	}
}