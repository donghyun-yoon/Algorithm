import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_15649_N과M1 {
	static int[] sequence;
	static boolean[] isCheck;
	static int R;
	static int N;

	static void permutation(int cnt) {
		if(cnt == R) {
			for(int val : sequence) System.out.print(val+" ");
			System.out.println();
			return;
		}
		for(int i = 0; i < N; i++) {
			if(isCheck[i]) continue;
			sequence[cnt] = i+1;
			isCheck[i] = true;
			permutation(cnt+1);
			isCheck[i] = false;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		sequence = new int[R];
		isCheck = new boolean[N];
		permutation(0);
		br.close();
	}// end main
}// end class