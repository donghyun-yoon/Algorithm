import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_bj_g4_1043_거짓말 {

	static ArrayList<Integer>[] party;
	static ArrayList<Integer> know;
	static int[] parent;
	static int N,M,T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st =  new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 파티의 수

		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수

		know = new ArrayList<>();
		
		for(int i=0; i<T; i++) {
			int t = Integer.parseInt(st.nextToken());
			know.add(t);
		}
		
		makeSet();
		party = new ArrayList[M];
		for(int i=0; i<M; i++) {
			party[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int people = Integer.parseInt(st.nextToken()); // 파티에 오는 사람의 수
			for(int j=0; j<people; j++) {
				int p = Integer.parseInt(st.nextToken());
				party[i].add(p);
			}
		}
		
		//각각 파티별의 최초 사람으로 묶어준다.
		for(int i=0; i<M; i++) {
			int p = party[i].get(0); // 각 파티의 첫번째 사람을 기준으로 union
			for(int j=1; j<party[i].size(); j++) {
				union(p, party[i].get(j));
			}
		}

		int res = 0;
		for(int i=0; i<M; i++) {
			boolean isCheck = true;
			int p = party[i].get(0); // 각 파티의 첫번째 사람을 기준으로 알고있는사람과 비교
			for(int v : know) {
				if(find(p) == find(v)) {
					isCheck = false;
					break;
				}
			}
			if(isCheck) res++;
		}
		System.out.println(res);

        br.close();
	}

	static void makeSet() {
		parent = new int[N+1];
		for(int i=0; i<N+1; i++) {
			parent[i] = i;
		}
	}

	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if(x != y) parent[y] = x;
	}
}