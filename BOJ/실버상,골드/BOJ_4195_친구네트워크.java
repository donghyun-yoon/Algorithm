import java.io.*;
import java.util.*;
/*
1
3
Fred Barney
Barney Betty
Betty Wilma

1
3
Fred Barney
Betty Wilma
Barney Betty
*/

class BOJ_4195_친구네트워크 {

	static int[] parent; // 루트 노드 값
	static int[] level; // 각 노드마다 층의 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			int N = Integer.parseInt(br.readLine());

			parent = new int[N*2]; // 서로 연관이 하나도 없다면 최대 N*2개이므로
			level = new int[N*2];
			for(int i=0; i<N*2; i++) {
				parent[i] = i;
				level[i] = 1;
			}

			int index = 0;
			Map<String, Integer> network = new HashMap<>();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();

				if(!network.containsKey(f1)) network.put(f1, index++);
				if(!network.containsKey(f2)) network.put(f2, index++);

				sb.append(union(network.get(f1), network.get(f2))).append("\n");
			}
		}
		System.out.println(sb);
		br.close();
	}

	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]); //어떤 인덱스가 바로 루트로 가게 하기 위해
	}

	static int union(int u, int v) {
		u = find(u);
		v = find(v);

		if(u != v) {
			parent[v] = u; // v에 루트의 값을 넣는다.
			level[u] += level[v]; // 항상 참조를 루트로 하기때문에 루트에 v의 개수를 더해 준다.
		}
		return level[u];
	}
}

/*
class Main {
	static class Friends {
		String name;
		int index;
		Friends(String name, int index) {
			this.name = name;
			this.index = index;
		}
	}

	static ArrayList<Friends>[] friend;
	static ArrayList<Integer> parent;
	static int T, N, index;

	static int f(int x) {
		if(parent.get(x) == x) return x;
		return x = f(parent.get(x));
	}

	static void u(int u, int v) {
		u = f(u);
		v = f(v);
		parent.set(v,u);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			index=0;
			friend = new ArrayList[N];
			for(int i=0; i<N; i++) friend[i] = new ArrayList<>();
			parent = new ArrayList<>();

			for(int i=0; i<N; i++) {
				//이름 입력받기
				st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();
				friend[i].add(new Friends(f1, isCheck(f1)));
				if(!parent.contains(friend[i].get(0).index)) parent.add(friend[i].get(0).index);

				friend[i].add(new Friends(f2, isCheck(f2)));
				if(!parent.contains(friend[i].get(1).index)) parent.add(friend[i].get(1).index);
				
				int cnt = 2;
				if(i!=0) {
					//find로 묶여있는 갯수 찾기
					Friends friends1 = friend[i].get(0);
					Friends friends2 = friend[i].get(1);
					for(int k=0; k<i; k++) {
						Friends prev = friend[k].get(0);
						if(f(parent.get(friends1.index)) == f(parent.get(prev.index))) cnt++;
						if(f(parent.get(friends2.index)) == f(parent.get(prev.index))) cnt++;
					}
				}
				//union으로 묶기
				u(friend[i].get(0).index, friend[i].get(1).index);
				sb.append(cnt).append("\n");
			}

		}//end tc
		
		System.out.println(sb);
		br.close();
	}

	static int isCheck(String name) {
		for(int i=0; i<friend.length; i++) {
			for(int j=0; j<friend[i].size(); j++) {
				if(friend[i].get(j).name.equals(name)) return parent.get(friend[i].get(j).index);
			}
		}
		return index++;
	}
}
*/