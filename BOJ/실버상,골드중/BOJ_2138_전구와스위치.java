import java.io.BufferedReader;
import java.io.InputStreamReader;

class BOJ_2138_전구와스위치 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		char[] target = br.readLine().toCharArray();
		char[] zeroOn = new char[input.length];
		char[] zeroOff = new char[input.length];

		for(int i=0; i<input.length; i++) {
			if(i == 0 || i ==1) {
				zeroOn[i] = (input[i] == '0')?'1':'0';
				zeroOff[i] = input[i];
			} else {
				zeroOff[i] = zeroOn[i] = input[i];
			}
		}
		
		int cntOn=1,cntOff=0;
		for(int i=1; i<N; i++) {
			if(zeroOn[i-1] != target[i-1]) {
				zeroOn[i-1] = (zeroOn[i-1] == '0')?'1':'0';
				zeroOn[i] = (zeroOn[i] == '0')?'1':'0';
				if(i < N-1) zeroOn[i+1] = (zeroOn[i+1] == '0')?'1':'0';
				cntOn++;
			}
			if(zeroOff[i-1] != target[i-1]) {
				zeroOff[i-1] = (zeroOff[i-1] == '0')?'1':'0';
				zeroOff[i] = (zeroOff[i] == '0')?'1':'0';
				if(i < N-1) zeroOff[i+1] = (zeroOff[i+1] == '0')?'1':'0';
				cntOff++;
			}
		}
		cntOff = (zeroOff[N-1] != target[N-1])?Integer.MAX_VALUE:cntOff;
		cntOn = (zeroOn[N-1] != target[N-1])?Integer.MAX_VALUE:cntOn;
		System.out.println((cntOff == Integer.MAX_VALUE && cntOn == Integer.MAX_VALUE)?-1:(cntOff<cntOn)?cntOff:cntOn);
		br.close();
	}// end main
	static char Convert(char c) {
		if(c == '0') return '1';
		return '0';
	}
}// end class