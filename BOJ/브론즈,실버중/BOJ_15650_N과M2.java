import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_15650_N과M2{
	static int N,M;
	static int[] input;
	static boolean[] isChecked;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[M];
		isChecked = new boolean[N+1];
		combination(0, 1);
	}

	static void combination(int cnt, int start) {
		if(cnt == M) {
			for(int i:input) System.out.print(i+" ");
			System.out.println();
			return;
		}

		for(int i=start; i<=N; i++) {
			if(isChecked[i]) continue;

			input[cnt] = i;
			isChecked[i] = true;
			combination(cnt+1, i);
			isChecked[i] = false;
		}
	}
}