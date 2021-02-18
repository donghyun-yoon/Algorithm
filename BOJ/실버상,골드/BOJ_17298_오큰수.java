import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class BOJ_17298_오큰수 {
	static class Idx {
		int index;
		int value;
		Idx(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
	static int[] NGE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		NGE = new int[N];

		Stack<Idx> s = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			while(true) {
				if(s.isEmpty() || num <= s.peek().value) {
					s.push(new Idx(i, num));
					break;
				}
				if(num > s.peek().value) {
					NGE[s.peek().index] = num;
				}
				s.pop();
			}
		}
		while(!s.isEmpty()) {
			NGE[s.pop().index] = -1;
		}

		for(int i:NGE) sb.append(i + " ");

		System.out.println(sb);
		br.close();
	}
}