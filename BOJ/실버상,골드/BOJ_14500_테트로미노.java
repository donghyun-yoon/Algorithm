import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_14500_테트로미노 {

	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int[][][] d = {
	{{0,0,0},{1,1,1}}, //ㅡ
	{{1,1,1},{0,0,0}}, //ㅣ

	{{0,1,0},{1,0,-1}}, //ㅁ

	{{1,1,0},{0,0,1}}, //ㄴ
	{{-1,0,0},{0,1,1}}, //ㄴ 90
	{{0,1,1},{1,0,0}}, //ㄴ 180
	{{1,0,0},{0,-1,-1}}, //ㄴ 270

	{{0,-1,-1},{1,0,0}}, //ㄴ대칭
	{{1,0,0},{0,1,1}}, //ㄴ대칭 90
	{{0,1,1},{-1,0,0}}, //ㄴ대칭 180
	{{-1,0,0},{0,-1,-1}}, //ㄴ대칭 270

	{{1,0,1},{0,1,0}},
	{{0,1,0},{-1,0,-1}}, //90
	{{1,0,1},{0,-1,0}}, //대칭
	{{0,1,0},{1,0,1}}, //대칭 90

	{{-1,0,0},{-1,1,1}}, //ㅜ
	{{-1,1,1},{1,0,0}}, //ㅜ 90
	{{1,0,0},{-1,1,1}}, //ㅜ 180
	{{-1,1,1},{-1,0,0}} //ㅜ 270
	};
	static int N,M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}

		int sumMax = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sumMax = Math.max(sumMax, tetromino(i, j));
			}
		}

		System.out.println(sumMax);

		br.close();
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int tetromino(int x, int y) {
		int sum=0;
		for(int i=0; i<19; i++) {
			int temp = map[x][y];
			int dx = x, dy = y;
			for(int dir=0; dir<3; dir++) {
				int nx = dx + d[i][0][dir];
				int ny = dy + d[i][1][dir];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M) {
					temp = 0;
					break;
				}
				temp += map[nx][ny];
				dx = nx; dy = ny;
			}
			sum = Math.max(sum, temp);
		}
		return sum;
	}
}//end class