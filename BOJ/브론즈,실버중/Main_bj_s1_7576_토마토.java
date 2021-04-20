import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_bj_s1_7576_토마토 {

	static Queue<int[]> queue;
    static int[][] map;
	static boolean[][] visited;
	static int[][] d = {{1,0},{-1,0},{0,1},{0,-1}};
	static int N,M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];
		queue = new ArrayDeque<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					queue.offer(new int[] {i,j,0});
					visited[i][j] = true;
				}
			}
		}

		int[] cur;
		int res = 0;
		while(!queue.isEmpty()){
			cur = queue.poll();

			res = Math.max(res, cur[2]);

			for(int k=0; k<4; k++) {
				int nx = cur[0] + d[k][0];
				int ny = cur[1] + d[k][1];

				if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
				if(visited[nx][ny] || map[nx][ny] == -1) continue;

				visited[nx][ny] = true;
				map[nx][ny] = 1;
				queue.offer(new int[] {nx,ny,cur[2]+1});
			}
		}

		boolean isCheck = false;
		for(int[] vv : map) {
			for(int v : vv) {
				if(v == 0) {
					isCheck = true;
					break;
				}
			}
			if(isCheck) break;
		}

		System.out.println((isCheck)?-1:res);

        br.close();
	}
}