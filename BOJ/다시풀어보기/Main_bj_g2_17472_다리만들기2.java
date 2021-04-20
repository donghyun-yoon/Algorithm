import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_bj_g2_17472_다리만들기2 {

	static final int INF = 987654321;
	static int[][] map, adjMatrix;
	static boolean[][] visited;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int N,M,cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//각 섬마다 번호 붙이기
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0 || visited[i][j]) continue;
				dfs(i,j,++cnt);
			}
		}

		adjMatrix = new int[cnt+1][cnt+1]; // 1부터 시작하므로

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) continue;
				makeBridge(i, j);
			}
		}	

		System.out.println(prim());
		br.close();
	}
	
	//번호에 맞게 군집화하기!!!
	static void dfs(int x, int y, int cnt) {
		map[x][y] = cnt;
		visited[x][y] = true;
		
		int nx,ny;
		for(int dir=0; dir<4; dir++) {
			nx = x + dx[dir];
			ny = y + dy[dir];
			
			if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
			if(visited[nx][ny] || map[nx][ny] != 1) continue;
			dfs(nx, ny, cnt);
		}
	}
	
	static void makeBridge(int x, int y) {
		int from = map[x][y];
		int to = -1;
		
		for(int dir=0; dir<4; dir++) {
			int nx = x;
			int ny = y;
			int d = -1;
			while(true) {
				
				nx = nx + dx[dir];
				ny = ny + dy[dir];
				d++;
				
				if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] == from) break;
				if(map[nx][ny] != 0 && map[nx][ny] != from) {
					if(d>1) {
						to = map[nx][ny];
						if(adjMatrix[from][to] == 0) {
							adjMatrix[from][to] = d;
						}else {
							adjMatrix[from][to] = Math.min(adjMatrix[from][to], d);
						}
					}
					break;
				}
				
			}
		}
	}
	
	static int prim() {
		int[] minEdge = new int[cnt+1];
		boolean[] visited = new boolean[cnt+1];
		
		Arrays.fill(minEdge, INF);
		minEdge[1] = 0;
		int res = 0;

		for(int i=0; i<=cnt; i++) {
			for(int j=0; j<=cnt; j++) {
				if(adjMatrix[i][j] == INF) adjMatrix[i][j] = 0;
			}
		}
		
		for(int i=1; i<=cnt; i++) {
			int min = INF;
			int minVertex = 0;
			for(int j=1; j<=cnt; j++) {
				if(!visited[j] && min > minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
				}
			}

			res += min;
			visited[minVertex] = true;

			for(int j=1; j<=cnt; j++) {
				if(!visited[j] && adjMatrix[minVertex][j] != 0 && minEdge[j] > adjMatrix[minVertex][j]) {
					minEdge[j] = adjMatrix[minVertex][j];
				}
			}
		}

		for(int i=1; i<=cnt; i++) {
			if(minEdge[i] == INF) return -1;
		}
		return res;
	}
}
// 각 번호별로 번호를 부여해서 군집화를 이룬다 -> dfs
// 각 번호ㅂㄹ로 인접행렬로 가장 짧은 거리를 계산

/*
7 8
0 0 0 0 0 0 1 1
1 1 0 0 0 0 1 1
1 1 0 0 0 0 0 0
1 1 0 0 0 1 1 0
0 0 0 0 0 1 1 0
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1

7 8
0 0 0 0 0 0 1 1
1 1 0 0 0 0 1 1
1 1 0 0 0 0 0 0
1 1 0 0 0 1 1 0
0 0 0 0 0 1 1 0
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
*/