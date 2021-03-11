import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_10819_차이를최대로 {

	static int[] perm;
	static boolean[] visited;
	static int[] arr;

	static int N,max=Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		perm = new int[N];
		visited = new boolean[N];
		dfs(0);

		System.out.println(max);
		br.close();
	}

	static void dfs(int cnt) {
		if(cnt == N) {
			int sum=0;
			for(int i=1; i<N; i++) {
				sum += Math.abs(perm[i-1]-perm[i]);
			}
			max = Math.max(max, sum);
			return;
		}
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			perm[cnt] = arr[i];
			dfs(cnt+1);
			visited[i] = false;
		}
	}
}