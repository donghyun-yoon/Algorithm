import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_g5_14503_로봇청소기 {

	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
	static int N,M,R,C,D;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st =  new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int res = 0, dirCnt = 0;
		int nx,ny;
		boolean flag = true;

		while(flag) {
			if(map[R][C] == 0) {
				map[R][C] = 2;
				res++;
			}

			while(true) {
				if(dirCnt == 4) {
					nx = R - dx[D];
					ny = C - dy[D];

					if(map[nx][ny] == 1) {
						flag = false;
						break;
					}else {
						R = nx;
						C = ny;
						dirCnt = 0;
					}
				}

				D = (D+3) % 4;
				nx = R + dx[D];
				ny = C + dy[D];

				if(map[nx][ny] == 0) {
					dirCnt = 0;
					R = nx;
					C = ny;
					break;
				}else {
					dirCnt++;
					continue;
				}
			}
		}

		System.out.println(res);

        br.close();
	}
}