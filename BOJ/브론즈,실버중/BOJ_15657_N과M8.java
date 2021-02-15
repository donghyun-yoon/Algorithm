import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class BOJ_15657_N과M8 {
	static StringBuilder sb = new StringBuilder();
	static int[] num;
	static int[] length;
	static boolean[] isChecked;
	static int n, m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		num = new int[n];
		isChecked = new boolean[n];
		length = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		sequence(0, 0);
		System.out.println(sb);
		br.close();
	}

	static void sequence(int cnt, int start) {
		if (cnt == m) {
			for (int v : length)
				sb.append(v + " ");
			sb.append("\n");
			return;
		}
		for (int i = start; i < n; i++) {
			length[cnt] = num[i];
			sequence(cnt + 1, i);
		}
	}
}