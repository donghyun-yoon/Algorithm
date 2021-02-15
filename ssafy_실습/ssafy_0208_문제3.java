import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class ssafy_0208_문제3 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] password = new String[N+1];
		password[N] = Integer.toBinaryString(N);
		for(int i=0; i<N; i++) {
			sb.append(Integer.toBinaryString(i));
			while(sb.length() != password[N].length()) {
				sb.insert(0, "0");
			}
			password[i] = sb.toString();
			sb.setLength(0);
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] hacker = new int[M];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<M; i++) {
			hacker[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<=N; i++) {
			int min = Integer.MAX_VALUE;
			for(int j=0; j<M; j++) {
				//i와 해커가 사용한 숫자와 같을떈 수행하지 않게 하기 위해서.
				if(CheckHacker(hacker,i)) continue;
				int cnt=0;
				for(int k=0; k<password[i].length(); k++) {
					if(password[i].charAt(k) == password[hacker[j]].charAt(k)) continue;
					cnt++;
				}
				min = Math.min(min, cnt);
			}
			max = Math.max(max, (min==Integer.MAX_VALUE)?0:min);
		}
		System.out.println(max);
		br.close();
	}

	static boolean CheckHacker(int[] hacker, int i) {
		for(int j=0; j<hacker.length; j++) {
			if(i == hacker[j]) return true;
		}
		return false;
	}
}