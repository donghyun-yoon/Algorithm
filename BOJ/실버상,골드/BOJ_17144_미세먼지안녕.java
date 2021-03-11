import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class BOJ_17144_미세먼지안녕 {

	static class Node {
		int x;
		int y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static ArrayList<Node> airCleaner;
	static int[][] map;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	static int R,C,T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		airCleaner = new ArrayList<>();
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) airCleaner.add(new Node(i,j));
			}
		}

		for(int i=0; i<T; i++) {
			move();
		}

		int cnt = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] <= 0) continue;
				cnt += map[i][j];
			}
		}
		System.out.println(cnt);

		br.close();
	}

	static void move() {
		int[][] temp = new int[R][C];
		temp[airCleaner.get(0).x][airCleaner.get(0).y] = temp[airCleaner.get(1).x][airCleaner.get(1).y] = -1;

		int nr,nc;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] <= 0) continue;
				int cnt=0;
				for(int dir=0; dir<4; dir++) {
					nr = i + dr[dir];
					nc = j + dc[dir];
					if(nr<0 || nr>=R || nc<0 || nc>=C || map[nr][nc] == -1) continue;
					temp[nr][nc] += map[i][j]/5;
					cnt++;
				}
				temp[i][j] += map[i][j] - (map[i][j]/5 * cnt);
			}
		}

		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = temp[i][j];
			}
		}

		//반시계방향
		int r = airCleaner.get(0).x;
		int c = airCleaner.get(0).y+1;
		temp[r][c] = 0;
		while(c+1<C) temp[r][c+1] = map[r][c++];
		while(r-1>=0) temp[r-1][c] = map[r--][c];
		while(c-1>=0) temp[r][c-1] = map[r][c--];
		while(r+1<airCleaner.get(0).x) temp[r+1][c] = map[r++][c];

		//시계방향
		r = airCleaner.get(1).x;
		c = airCleaner.get(1).y+1;
		temp[r][c] = 0;
		while(c+1<C) temp[r][c+1] = map[r][c++];
		while(r+1<R) temp[r+1][c] = map[r++][c];
		while(c-1>=0) temp[r][c-1] = map[r][c--];
		while(r-1>airCleaner.get(1).x) temp[r-1][c] = map[r--][c];

		map = temp;
	}
}//end class