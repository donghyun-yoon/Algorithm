import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main_17471_개리맨더링 {
	
	static int[][] adjMatrix;
	static int[] population, district;
	static int N,res=Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		N = Integer.parseInt(br.readLine());

		population = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) population[i] = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int j=1; j<=n; j++) {
				int k = Integer.parseInt(st.nextToken());
				adjMatrix[i][k] = adjMatrix[k][i] = 1;
			}
		}
		district = new int[N+1];
		dfs(1, district);
		System.out.println((res==Integer.MAX_VALUE)?-1:res);
		br.close();
	}

	private static void dfs(int depth, int[] district) {
		if(depth == N+1) {
			int d1 = 0, d2 = 0;
			for(int i=1; i<=N; i++) {
				if(district[i] == 1) d1 += population[i];
				else d2 += population[i];
			}

			boolean[] visited = new boolean[N+1];
			int cnt=0;
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				divideDistrict(i, district, visited);
				cnt++;
			}

			if(cnt == 2) res = Math.min(res, Math.abs(d1-d2));

			return;
		}
		district[depth]=1;
		dfs(depth+1, district);
		district[depth]=2;
		dfs(depth+1, district);
	}

	private static void divideDistrict(int i, int[] district, boolean[] visited) {
		visited[i] = true;
		for(int k=1; k<=N; k++) {
			if(adjMatrix[i][k] != 1 || visited[k] || district[k] != district[i]) continue;
			divideDistrict(k, district, visited);
		}
	}
}
/* 조합 부분집합 속도차이 없음!!
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static int N,people[],adjMatrix[][],res=Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		adjMatrix = new int[N+1][N+1];

		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		for(int from=1; from<=N; from++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			for(int j=0; j<k; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjMatrix[from][to] = adjMatrix[to][from] = 1;
			}
		}

		for(int i=1; i<= N/2; i++) {
			boolean[] combi = new boolean[N+1];
			combination(0, 1, i, combi);
		}
		System.out.println(res==Integer.MAX_VALUE?-1:res);
        br.close();
	}


	static void combination(int cnt, int start, int end, boolean[] combi) {
		if(cnt == end) {
			int a=0,b=0;
			for(int i=1; i<=N; i++) {
				if(combi[i]) {
					a += people[i];
				}	
				else {
					b += people[i];
				}
			}

			visited = new boolean[N+1];
			int count = 0;
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				divideArea(i, combi, combi[i]);
				count++;
			}

			if(count==2) res = Math.min(res, Math.abs(a-b));

			return;
		}

		for(int i = start; i<=N; i++) {
			combi[i] = true;
			combination(cnt+1, i+1, end, combi);
			combi[i] = false;
		}
	}

	static void divideArea(int i, boolean[] combi, boolean flag) {
		visited[i] = true;
		for(int j=1; j<=N; j++) {
			if(visited[j] || adjMatrix[i][j] != 1 || combi[j] != flag) continue;
			divideArea(j, combi, flag);
		}
	}
}
*/