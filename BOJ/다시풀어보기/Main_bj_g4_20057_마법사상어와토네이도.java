import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_g4_20057_마법사상어와토네이도 {

	static int[][] map;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int[] percent = {1,1,2,2,5,7,7,10,10};
	static int[][] blowRow = {
		{-1,1,-2,2,0,-1,1,-1,1},//좌
		{-1,-1,0,0,2,0,0,1,1},//하
		{-1,1,-2,2,0,-1,1,-1,1},//우
		{1,1,0,0,-2,0,0,-1,-1}//상
	};
	static int[][] blowCol = {
		{1,1,0,0,-2,0,0,-1,-1},//좌
		{-1,1,-2,2,0,-1,1,-1,1},//하
		{-1,-1,0,0,2,0,0,1,1},//우
		{-1,1,-2,2,0,-1,1,-1,1}//상
	};
	static int N,res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int x = N/2;
		int y = N/2;
		int dir = 0;
		int moveCnt = 0;
		int cnt = 1;
		int size = 1;

		int nx,ny;
		boolean isCheck = false;
		while(true) {
			if(moveCnt == 2) {
				size++; moveCnt = 0;
			}
			for(int i=0; i<size; i++) {
				nx = x + dx[dir%4];
				ny = y + dy[dir%4];
				blow(nx, ny, dir);
				if(nx == 0 && ny == 0) {
					isCheck = true;
					break;
				}
				x = nx;
				y = ny;
			}
			if(isCheck) break;
			dir++; moveCnt++;
		}
		System.out.println(res);
		br.close();
	}

	static void blow(int x, int y, int d) {
		int sand = map[x][y];
		int a = sand;

		for(int i=0; i<9; i++) {
			int nx = x + blowRow[d%4][i];
			int ny = y + blowCol[d%4][i];
			int blowRate = (sand * percent[i]) / 100; // 각 퍼센트에 맞게 계산
			a -= blowRate; // a값은 흩날리고 남은 값과 동일하므로
			if(nx<0 || nx>=N || ny<0 || ny>=N) { // 맵 밖으로 나갔다는 소리이므로
				res += blowRate;
				continue;
			}
			map[nx][ny] += blowRate;
		}

		int ax = x + dx[d%4];
		int ay = y + dy[d%4];
		if(ax<0 || ax>=N || ay<0 || ay>=N) {
			res += a;
		}else {
			map[ax][ay] += a;
		}

		map[x][y] = 0;
	}
}