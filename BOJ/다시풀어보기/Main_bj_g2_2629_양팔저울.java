import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_bj_g2_2629_양팔저울 {

	final static int MAX = 15001; //30 * 500
	static int N,weight[];
	static boolean dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		weight = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		dp = new boolean[N+1][MAX];
		dfs(0,0);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int marble = Integer.parseInt(st.nextToken());
			if(marble >= MAX) {
				sb.append("N").append(" ");
			}else if(dp[N][marble]) {
				sb.append("Y").append(" ");
			}else {
				sb.append("N").append(" ");
			}
		}
		System.out.println(sb);
        br.close();
	}

	static void dfs(int cnt, int w) {
		if(dp[cnt][w]) return;
		dp[cnt][w] = true;
		if(cnt == N) return;

		dfs(cnt+1, w+weight[cnt]);
		dfs(cnt+1, w);
		dfs(cnt+1, Math.abs(w-weight[cnt]));
	}
}