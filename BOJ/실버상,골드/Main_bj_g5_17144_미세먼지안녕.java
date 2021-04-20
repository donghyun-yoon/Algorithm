import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_bj_g5_17144_미세먼지안녕 {

	static int[][] tmap, airCleaner;
	static int[][] speard = {{1,0},{-1,0},{0,1},{0,-1}};
	static int[][] top = {{0,1},{-1,0},{0,-1},{1,0}};
	static int[][] bottom = {{0,1},{1,0},{0,-1},{-1,0}};
	static int R,C,T,res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int[][] map = new int[R][C];
		airCleaner = new int[2][2];
		int aidx = 0;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					airCleaner[aidx][0] = i;
					airCleaner[aidx++][1] = j;
				}
			}
		}

		while(T-- > 0) {
			map = speardDust(map);
			map = move(map,false);
			map = move(map,true);
		}

		for(int[] vv:map) {
			for(int v:vv) if(v>0) res += v;
		}
		System.out.println(res);

        br.close();
	}

	static boolean isCheck(int x, int y) {
		return (x < 0 || x >=R || y < 0 || y >=C);
	}

	static int[][] speardDust(int[][] map) {
		tmap = new int[R][C];
		tmap[airCleaner[0][0]][airCleaner[0][1]] = -1;
		tmap[airCleaner[1][0]][airCleaner[1][1]] = -1;

		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 0 || map[i][j] == -1) continue;

				int A = map[i][j]/5; //확산되는 양
				int cnt = 0; //몇개나 확산되었는지
				for(int d=0; d<4; d++) {
					int nx = i + speard[d][0];
					int ny = j + speard[d][1];

					if(isCheck(nx, ny) || tmap[nx][ny] == -1) continue;

					tmap[nx][ny] += A;
					cnt++;
				}
				tmap[i][j] += map[i][j] - (A*cnt);
			}
		}
		return tmap;
	}

	static boolean moveCheck(int x, int y, boolean flag) {
		if(!flag) {
			return (x < 0 || x > airCleaner[0][0] || y < 0 || y >= C);
		}
		return (x < airCleaner[1][0] || x >= R || y < 0 || y >= C);
	}

	static int[][] move(int[][] map, boolean flag) {
		tmap = new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				tmap[i][j] = map[i][j];
			}
		}

		int x=0,y=0;
		if(!flag) {
			x=airCleaner[0][0];
			y=airCleaner[0][1]+1;
		}else {
			x=airCleaner[1][0];
			y=airCleaner[1][1]+1;
		}
		tmap[x][y] = 0;
		for(int d=0; d<4; d++) {
			int nx=0,ny=0;
			while(true) {
				if(!flag) {
					nx = x + top[d][0];
					ny = y + top[d][1];
				}else {
					nx = x + bottom[d][0];
					ny = y + bottom[d][1];
				}
				if(moveCheck(nx, ny, flag)) break;
				if(map[nx][ny] == -1) {
					break;
				}
				tmap[nx][ny] = map[x][y];
				x = nx; y = ny;
			}
		}
		return tmap;
	}
}