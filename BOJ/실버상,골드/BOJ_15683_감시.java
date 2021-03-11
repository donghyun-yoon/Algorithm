import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class BOJ_15683_감시 {

	static class Node {
		int x;
		int y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static ArrayList<Node> cctv;
	static int[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int N,M,blindSpot=Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cctv = new ArrayList<>();
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >=1 && map[i][j] <= 5) cctv.add(new Node(i,j));
			}
		}
		perm(0, map);
		System.out.println(blindSpot);
		br.close();
	}

	static void copyArr(int[][] origin, int[][] temp) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][j] = origin[i][j];
			}
		}
	}

	static void perm(int cnt, int[][] pArr) {
		if(cnt == cctv.size()) {
			int zero = 0;
			for(int[] aa: pArr) {
				for(int a: aa) {
					if(a == 0) zero++;
				}
			}
			if(zero < blindSpot) blindSpot = zero;
			return;
		} else {
			switch(map[cctv.get(cnt).x][cctv.get(cnt).y]) {
				case 1:
					for (int dir = 0; dir < 4; dir++) {
						int[][] visited = new int[N][M];
						copyArr(pArr, visited);
						int nx = cctv.get(cnt).x + dx[dir];
						int ny = cctv.get(cnt).y + dy[dir];
						while (true) {
							if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 6) break;
							visited[nx][ny] = 7;
							nx = nx + dx[dir];
							ny = ny + dy[dir];
						}
						perm(cnt+1, visited);
					}
					break;
				case 2:
					for(int dir=0; dir<2; dir++) {
						int[][] visited = new int[N][M];
						copyArr(pArr, visited);
						int nx = cctv.get(cnt).x + dx[dir];
						int ny = cctv.get(cnt).y + dy[dir];
						while (true) {
							if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 6) break;
							visited[nx][ny] = 7;
							nx = nx + dx[dir];
							ny = ny + dy[dir];
						}
						nx = cctv.get(cnt).x + dx[dir+2];
						ny = cctv.get(cnt).y + dy[dir+2];
						while (true) {
							if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 6) break;
							visited[nx][ny] = 7;
							nx = nx + dx[dir+2];
							ny = ny + dy[dir+2];
						}
						perm(cnt+1, visited);
					}
					break;
				case 3:
					for(int dir=0; dir<4; dir++) {
						int nx = cctv.get(cnt).x + dx[dir];
						int ny = cctv.get(cnt).y + dy[dir];
						int[][] visited = new int[N][M];
						copyArr(pArr, visited);
						while (true) {
							if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 6) break;
							visited[nx][ny] = 7;
							nx = nx + dx[dir];
							ny = ny + dy[dir];
						}
						nx = cctv.get(cnt).x + dx[(dir+1)%4];
						ny = cctv.get(cnt).y + dy[(dir+1)%4];
						while (true) {
							if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 6) break;
							visited[nx][ny] = 7;
							nx = nx + dx[(dir+1)%4];
							ny = ny + dy[(dir+1)%4];
						}
						perm(cnt+1, visited);
					}
					break;
				case 4:
					for (int dir = 0; dir < 4; dir++) {
						int[][] visited = new int[N][M];
						copyArr(pArr, visited);
						int nx = cctv.get(cnt).x + dx[dir];
						int ny = cctv.get(cnt).y + dy[dir];
						while (true) {
							if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 6) break;
							visited[nx][ny] = 7;
							nx = nx + dx[dir];
							ny = ny + dy[dir];
						}
						nx = cctv.get(cnt).x + dx[(dir+1)%4];
						ny = cctv.get(cnt).y + dy[(dir+1)%4];
						while (true) {
							if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 6) break;
							visited[nx][ny] = 7;
							nx = nx + dx[(dir+1)%4];
							ny = ny + dy[(dir+1)%4];
						}
						nx = cctv.get(cnt).x + dx[(dir+2)%4];
						ny = cctv.get(cnt).y + dy[(dir+2)%4];
						while (true) {
							if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 6) break;
							visited[nx][ny] = 7;
							nx = nx + dx[(dir+2)%4];
							ny = ny + dy[(dir+2)%4];
						}
						perm(cnt + 1, visited);
					}
					break;
				case 5:
					int[][] visited = new int[N][M];
					copyArr(pArr, visited);
					for (int dir = 0; dir < 4; dir++) {
						int nx = cctv.get(cnt).x + dx[dir];
						int ny = cctv.get(cnt).y + dy[dir];
						while (true) {
							if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 6) break;
							visited[nx][ny] = 7;
							nx = nx + dx[dir];
							ny = ny + dy[dir];
						}
					}
					perm(cnt + 1, visited);
					break;
				default: break;
			}
		}
	}
}