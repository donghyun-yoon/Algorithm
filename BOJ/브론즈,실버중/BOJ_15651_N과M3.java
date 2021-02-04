import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_15651_N과M3{
	static int N,M;
	static int[] input;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[M];
		combination(0);
		System.out.println(sb);
	}

	static void combination(int cnt) {
		if(cnt == M) {
			for(int i:input) sb.append(i+" ");
			sb.append("\n");
			return;
		}

		for(int i=1; i<=N; i++) {
			input[cnt] = i;
			combination(cnt+1);
		}
	}
}