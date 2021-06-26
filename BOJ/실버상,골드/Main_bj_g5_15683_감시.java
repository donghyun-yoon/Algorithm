import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main_bj_g5_15683_감시 {

	static ArrayList<int[]> cctv;
	static int[][][] d = { {}, //상 하 좌 우 4개로 표현
		{{0,0,0,1},{0,1,0,0},{0,0,-1,0},{-1,0,0,0}}, // 1번 cctv
		{{0,0,-1,1},{-1,1,0,0}}, // 2번 cctv
		{{-1,0,0,1},{0,1,0,1},{0,1,-1,0},{-1,0,-1,0}}, // 3번 cctv
		{{-1,0,-1,1},{-1,1,0,1},{0,1,-1,1},{-1,1,-1,0}}, //4번 cctv
		{{-1,1,-1,1}} // 5번 cctv
	};
	static int N,M,res=Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 점의 개수
		M = Integer.parseInt(st.nextToken()); // 진행된 차례의 수

		cctv = new ArrayList<>();
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0 && map[i][j] < 6) cctv.add(new int[] {i,j,map[i][j]});
			}
		}

		dfs(0, map);
		System.out.println(res);

        br.close();
	}

	static void dfs(int cnt, int[][] map) {
		if(cnt == cctv.size()) {
			int count = 0;
			for(int[] mm:map){
				for(int m:mm) if(m == 0) count++;
			}
			res = Math.min(res, count);
			return;
		}

		int[] cur = cctv.get(cnt);
		int x = cur[0];
		int y = cur[1];
		int dir = cur[2];

		int[][] tmp = new int[N][M];
		
		for(int i=0; i<d[dir].length; i++) {

			copyArr(map, tmp);
			int up = d[dir][i][0];
			int down = d[dir][i][1];
			int left = d[dir][i][2];
			int right = d[dir][i][3];

			move(tmp, x, y, up, down, left, right, 7);
			dfs(cnt+1, tmp);
		}
	}

	static void copyArr(int[][] map, int[][] tmp) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tmp[i][j] = map[i][j];
			}
		}
	}

	static void move(int[][] map, int x, int y, int up, int down, int left, int right, int check) {
		int nx = x + up;
		while(nx >= 0 && up != 0) {
			if(map[nx][y] == 6) break;
			map[nx][y] = check;
			nx += up;
		}

		nx = x + down;
		while(nx < N && down != 0) {
			if(map[nx][y] == 6) break;
			map[nx][y] = check;
			nx += down;
		}

		int ny = y + left;
		while(ny >= 0 && left != 0) {
			if(map[x][ny] == 6) break;
			map[x][ny] = check;
			ny += left;
		}

		ny = y + right;
		while(ny < M && right != 0) {
			if(map[x][ny] == 6) break;
			map[x][ny] = check;
			ny += right;
		}
	}
}