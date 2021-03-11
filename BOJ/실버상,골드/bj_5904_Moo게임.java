import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long N = Long.parseLong(br.readLine());

		long moo = 3;
		long index = 0;
		while(moo < N) {
			index++;
			moo = 2*moo + (index+3);
		}

		while(true) {
			long preMoo = (moo-index-3)/2;
			if(N <= preMoo) {
				index--;
				moo = preMoo;
			} else if(N > preMoo+index+3) {
				N -= (preMoo+index+3);
				index--;
				moo = preMoo;
			} else {
				N -= preMoo;
				break;
			}
		}

		System.out.println((N==1)?"m":"o");

		br.close();
	}
}//end class

/* 재귀 형식
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int dp[];
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[28];
		dp[0] = 3;
		for(int i=1; i<28; i++) {
			dp[i] = 2*dp[i-1] + i + 3;
		}
		moo(N);
		br.close();
	}

	static void moo(int N) {
		if(N == 1) {
			System.out.println('m');
			return;
		}else if(N < 4) {
			System.out.println('o');
			return;
		}

		int idx = 0;
		while(dp[idx] < N) idx++;

		if(N - dp[idx-1] == 1) {
			System.out.println('m');
			return;
		}else if(N - dp[idx-1] <= idx+3) {
			System.out.println('o');
			return;
		}
		moo(N - dp[idx-1] - idx - 3);
	}
}//end class
*/