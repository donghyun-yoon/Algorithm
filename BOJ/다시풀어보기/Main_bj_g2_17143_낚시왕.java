import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
낚시왕은 처음에 1번 열의 한 칸 왼쪽에 있다. 다음은 1초 동안 일어나는 일이며, 아래 적힌 순서대로 일어난다. 낚시왕은 가장 오른쪽 열의 오른쪽 칸에 이동하면 이동을 멈춘다.

낚시왕이 오른쪽으로 한 칸 이동한다.
낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
상어가 이동한다.
*/

public class Main_bj_g2_17143_낚시왕 {

	static class Shark{
		int r,c,s,d,z;
		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		void convertDir() {
			if(this.d == 1) this.d = 2;
			else if(this.d == 2) this.d = 1;
			else if(this.d == 3) this.d = 4;
			else if(this.d == 4) this.d = 3;
		}
	}

	static Shark[] sharks = new Shark[10001];
	static int[][] map;
	static int[][] d = {{},{-1,0},{1,0},{0,1},{0,-1}};
	static int R,C,M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st =  new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];

		for(int i=0; i<M; i++) {
			st =  new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks[z] = new Shark(r, c, s, d, z);
			map[r][c] = z;
		}


		int res = 0;
		for(int j=1; j<=C; j++) {

			for(int i=1; i<=R; i++) {
				if(map[i][j] != 0) {
					sharks[map[i][j]] = null;
					res += map[i][j];
					map[i][j] = 0;
					break;
				}
			}

			for(int i=1; i<=10000; i++) {
				if(sharks[i] == null) continue;

				Shark shark = sharks[i];

				int size = shark.s;
				if(shark.d == 0 || shark.d == 2) {
					size %= ((R-1)*2);
				}else size %= ((C-1)*2);

				int nx = shark.r;
				int ny = shark.c;
				map[nx][ny] = 0;

				for(int k=0; k<size; k++) {
					if(isCheck(nx + d[shark.d][0], ny + d[shark.d][1])) {
						shark.convertDir();
					}

					nx += d[shark.d][0];
					ny += d[shark.d][1];
				}
				sharks[shark.z] = new Shark(nx, ny, size, shark.d, shark.z);
			}

			for(int i=1; i<=10000; i++) {
				if(sharks[i] == null) continue;
				
				Shark shark = sharks[i];
				if(map[shark.r][shark.c] < shark.z) {
					sharks[map[shark.r][shark.c]] = null;
					map[shark.r][shark.c] = shark.z;
				}
			}
		}
	
		System.out.println(res);
        br.close();
	}

	static boolean isCheck(int x, int y) {
		return (x <= 0 || x > R || y <= 0 || y > C) ? true : false;
	}
}