import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bj_s1_16948_데스나이트 {

	static int[][] d = {{-2,-1},{-2,1},{0,-2},{0,2},{2,-1},{2,1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st =  new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());

		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		queue.offer(new int[] {r1,c1,0});
		visited[r1][c1] = true;

		int res = -1;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();

			if(cur[0] == r2 && cur[1] == c2) {
				res = cur[2];
				break;
			}

			for(int dir=0; dir<6; dir++) {
				int nx = cur[0] + d[dir][0];
				int ny = cur[1] + d[dir][1];

				if(nx < 0 || nx >=N || ny < 0 || ny >= N || visited[nx][ny]) continue;

				visited[nx][ny] = true;
				queue.offer(new int[] {nx,ny,cur[2]+1});
			}
		}

		System.out.println(res);
        br.close();
	}

}