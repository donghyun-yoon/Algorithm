import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bj_g1_1194_달이차오른다 {

	static char[][] map;
	static int[][] d = {{1,0},{-1,0},{0,1},{0,-1}};
	static int N,M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로크기
		M = Integer.parseInt(st.nextToken()); // 가로크기

		map = new char[N][M];
		int x = 0, y = 0;
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) if(map[i][j] == '0') { x = i; y = j;}
		}

		System.out.println(bfs(x,y));

        br.close();
	}

	// 열쇠 f e d c b a 를 각각 1비트라고 생각한다.
	// 000000 -> 아무 열쇠도 가지지 못함.
	// 100001 -> a,f열쇠를 가지고 있음 -> 'A','F' 통과 가능
	// 000000...111111 까지의 경우의 수가 있다 -> 2^6 -> [][][2^6]

	static int bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][1<<6];

		visited[x][y][0] = true; //시작할때 열쇠를 가지고 있지않으므로 0
		queue.offer(new int[] {x,y,0,0}); //좌표값,열쇠,이동한거리

		while(!queue.isEmpty()) {
			int[] cur = queue.poll();

			if(map[cur[0]][cur[1]] == '1') return cur[3];

			for(int dir=0; dir<4; dir++) {
				int nx = cur[0] + d[dir][0];
				int ny = cur[1] + d[dir][1];
				int key = cur[2];

				// 범위 체크, 해당 키를 가지고 방문했던 적이 있거나 벽이라면 x
				if(rangeCheck(nx, ny) || visited[nx][ny][key] || map[nx][ny] == '#') continue;

				//문을 만났다면 해당 열쇠를 가지고 있어야한다. -> key의 비트마스킹을 통해 해당 문에 대한 키가 있는지 확인
				if(doorCheck(map[nx][ny])) {
					// 해당 키를 가지고 있지 않다! -> 못간다!!
					if((key & (1<<map[nx][ny]-'A')) == 0) continue;
				}

				if(keyCheck(map[nx][ny])) {
					// or 연산이니깐 해당 키가 원래 존재하든 안하든 그냥 넣어준다.
					key |= (1<<map[nx][ny]-'a');
				}

				visited[nx][ny][key] = true;
				queue.offer(new int[] {nx,ny,key,cur[3]+1});
			}
		}
		return -1;
	}

	static boolean rangeCheck(int x, int y) {
		return (x < 0 || x >= N || y < 0 || y >= M) ? true : false;
	}

	static boolean doorCheck(char c) {
		return (c >= 'A' && c <= 'F') ? true : false;
	}

	static boolean keyCheck(char c) {
		return (c >= 'a' && c <= 'f') ? true : false;
	}
}