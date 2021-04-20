import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class BOJ_1759_암호만들기 {
	static boolean[] visited;
	static String[] code;
	static int L,C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		code = br.readLine().split(" ");
		Arrays.sort(code);

		visited = new boolean[C];
		combination(0,0);

		br.close();
	}

	static void combination(int start, int depth) {
		if(depth == L) {
			int vow = 0, cons = 0;
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < C; i++) {
				if(visited[i]) {
					sb.append(code[i]);
					if(code[i].equals("a") || code[i].equals("e") || code[i].equals("i") || code[i].equals("o") || code[i].equals("u")) vow++;
					else cons++;
				}
			}
			if(vow >= 1 && cons >= 2) System.out.println(sb);
			return;
		}

		for(int i = start; i < C; i++) {
			visited[i] = true;
			combination(i+1, depth+1);
			visited[i] = false; 
		}
	}

}

//다시 풀었을때
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static char[] crypto;
	static char[] text;
	static int L,C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		crypto = new char[L];
		text = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			text[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(text);
		combinatoin(0,0);
		System.out.print(sb);
		
		br.close();
	}

	private static void combinatoin(int cnt, int start) {
		if(cnt == L) {
			//vowel 모음의 갯수, consonant 자음의 갯수
			int vowel=0,consonant=0;
			for(char c:crypto) {
				if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') vowel++;
				else consonant++;
			}
			if(vowel>=1 && consonant>=2) sb.append(crypto).append("\n");
			return;
		}

		for(int i=start; i<C; i++) {
			crypto[cnt] = text[i];
			combinatoin(cnt+1, i+1);
		}
	}
}
*/