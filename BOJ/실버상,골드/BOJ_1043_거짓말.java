import java.io.*;
import java.util.*;


class BOJ_1043_거짓말 {
	static ArrayList<Integer>[] people;
	static ArrayList<Integer>[] party;
	static ArrayList<Integer> know; // 진실을 알고있는사람
	static boolean visit[];
	static int N,M,T; //N:사람의수 M:파티의수 T:진실을 아는 사람의 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		know = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int i=0; i<T; i++) know.add(Integer.parseInt(st.nextToken()));
		
		people = new ArrayList[N+1];
		for(int i=0; i<=N; i++) people[i] = new ArrayList<>();
		party = new ArrayList[M];
		for(int i=0; i<M; i++) party[i] = new ArrayList<>();//party 초기화
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visit = new boolean[M];//1~M까지를 사용하기 위해서

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			while(k-->0) {
				int p = Integer.parseInt(st.nextToken());
				party[i].add(p); // 파티에 어떤 사람이 오는지 저장
				people[p].add(i); // 파티참석자가 어떤 파티에 가는지 저장
				if(know.contains(p)) { // 그사람이 진실을 알고있는 사람이라면 파티번호 큐에저장
					q.offer(i);
				}
			}
		}

		while(!q.isEmpty()) {
			//진실을 알고있는 사람이 참여한 파티를 바탕으로 파티에 속한 사람들도 진실을 알아야한다.
			int p = q.poll();
			visit[p] = true; //진실을 알고있는 사람이 있으므로 해당 파티는 진실로 처리
			//현재 큐의 파티번호로 파티에 있는 사람들을 체크
			for(int i=0; i<party[p].size(); i++) {
				int member = party[p].get(i); // 해당 파티의 참석자
				for(int j=0; j<people[member].size(); j++) {
					int curP = people[member].get(j);
					if(!visit[curP]) {
						q.offer(curP);
					}
				}
			}
		}

		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if (!visit[i]) cnt++;
		}
		System.out.println(cnt);
		
		br.close();
	}
}

/* union find 코드
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	static int n,m,k;
	static int[] parents = new int[51];
	static ArrayList<Integer> know;
	static ArrayList<Integer>[] party = new ArrayList[51];

	static int Find(int x) {
		if(parents[x] == x) return x;
		return x = Find(parents[x]);
	}
	// 1 2 3 4 5 6
	// 1 2 2 3 4 4

	static void Union(int u, int v) {
		u = Find(u);
		v = Find(v);
		System.out.println(u+" "+v);
		parents[v] = u;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		know = new ArrayList<>();
		for(int i=0; i<k; i++) know.add(Integer.parseInt(st.nextToken())); // 진실을 아는 사람 저장

		for(int i=1; i<=m; i++) {
			party[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			for(int tmp,j=1; j<=p; j++) {
				tmp = Integer.parseInt(st.nextToken());
				party[i].add(tmp); // 파티 입력
			}
		}

		for(int i=1; i<=n; i++) parents[i] = i; //유니온파인드 하기전 각자 자기 자신의 값으로 초기화
		
		System.out.println();
		for(int i=1; i<=n; i++) System.out.print(parents[i]+" ");
		System.out.println();
		
		//각 파티에 참여한 사람끼리 묶는다.
		for(int i=1; i<=m; i++) {
			int cur = party[i].get(0);
			for(int j=1; j<party[i].size(); j++) {
				Union(cur, party[i].get(j));
			}
		}

		System.out.println();
		for(int i=1; i<=n; i++) System.out.print(parents[i]+" ");
		
		int res = 0;
		//묶은 후 진실을 아는 사람과 묶여있다면 과장할수없다!
		for(int i=1; i<=m; i++) {
			boolean isPossible = true;
			int cur = party[i].get(0);
			for(int j=0; j<know.size(); j++) {
				if(Find(cur) == Find(know.get(j))) {
					isPossible = false;
					break;
				}
			}
			if(isPossible) res++;
		}

		System.out.println(res);
		br.close();
	}
}
*/