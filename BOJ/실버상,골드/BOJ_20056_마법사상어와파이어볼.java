import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class BOJ_20056_마법사상어와파이어볼 {
	
	static class Fire {
		int m,s,d;

		Fire(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static ArrayList<Fire>[][] fire;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int N,M,K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		fire = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				fire[i][j] = new ArrayList<>();
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			fire[r-1][c-1].add(new Fire(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		//dfs(0, fire);
		for(int i=0; i<K; i++) move();

		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(Fire f:fire[i][j]) sum += f.m;
			}
		}
		System.out.println(sum);
		br.close();
	}

	static void move() {
		ArrayList<Fire>[][] temp = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temp[i][j] = new ArrayList<>();
			}
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(fire[i][j].size() == 0) continue;
				for(int k=0; k<fire[i][j].size(); k++) {
					int s = fire[i][j].get(k).s;
					int d = fire[i][j].get(k).d;
					int nx = i + dr[d]*s%N;
					int ny = j + dc[d]*s%N;

					if(nx >= N) nx -= N;
					else if(nx < 0) nx += N;
					if(ny >= N) ny -= N;
					else if(ny < 0) ny += N;

					temp[nx][ny].add(new Fire(fire[i][j].get(k).m, s, d));
				}
			}
		}

		fire = temp;

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(fire[i][j].size() <= 1) continue;
				int mass = 0;
				int speed = 0;
				boolean odd = true,even = true; //odd:홀수 even:짝수
				for(int k=0; k<fire[i][j].size(); k++) {
					mass += fire[i][j].get(k).m;
					speed += fire[i][j].get(k).s;
					if(fire[i][j].get(k).d % 2 == 0) odd = false;
					else even = false;
				}
				mass = mass/5;
				speed /= fire[i][j].size();
				fire[i][j].clear();

				if(mass > 0) {
					for(int k=0; k<4; k++) {
						if(odd || even) fire[i][j].add(new Fire(mass, speed ,0 + 2*k));
						else fire[i][j].add(new Fire(mass, speed, 1 + 2*k));
					}
				}
			}
		}
	}
/*
	static void dfs(int depth, ArrayList<Fire>[][] fire) {
		if(depth == K+1) {
			int massSum = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(fire[i][j].size() == 0) continue;
					for(int k=0; k<fire[i][j].size(); k++) {
						System.out.println(i+" "+j+fire[i][j].get(k).toString());
						massSum += fire[i][j].get(k).m;
					}
				}
			}
			System.out.println(massSum);
			return;
		}

		ArrayList<Fire>[][] temp = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temp[i][j] = new ArrayList<>();
			}
		}
		Move(fire, temp);
		Merge(temp);

		// for(int i=0; i<N; i++) {
		// 	for(int j=0; j<N; j++) {
		// 		if(temp[i][j].size() == 0) continue;
		// 		for(int k=0; k<temp[i][j].size(); k++) {
		// 			System.out.println(i+" "+j+temp[i][j].get(k).toString());
		// 		}
		// 	}
		// }
		System.out.println();

		dfs(depth+1, temp);
	}//end dfs

	static void Move(ArrayList<Fire>[][] fire, ArrayList<Fire>[][] temp) {
		//모든 파이어볼이 자신의 방향으로 속력만큼 이동
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(fire[i][j].size() == 0) continue;
				for(int k=0; k<fire[i][j].size(); k++) {
					int s = fire[i][j].get(k).s;
					int d = fire[i][j].get(k).d;
					int nx = i + dr[d]*s%N;
					int ny = j + dc[d]*s%N;

					if(nx >= N) nx -= N;
					else if(nx < 0) nx += N;
					if(ny >= N) ny -= N;
					else if(ny < 0) ny += N;

					temp[nx][ny].add(new Fire(fire[i][j].get(k).m, s, d));
					fire[i][j].remove(k--);
				}
			}
		}
	}

	//이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
			//1.같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
			//2.파이어볼은 4개의 파이어볼로 나누어진다.
			//3.나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
				//3-1.질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
				//3-2.속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
				//3-3.합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
			//4.질량이 0인 파이어볼은 소멸되어 없어진다.
	static void Merge(ArrayList<Fire>[][] temp) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(temp[i][j].size() <= 1) continue;
				int mass = 0;
				int speed = 0;
				int odd = 0,even = 0; //odd:홀수 even:짝수
				for(int k=0; k<temp[i][j].size(); k++) {
					mass += temp[i][j].get(k).m;
					speed += temp[i][j].get(k).s;
					if(temp[i][j].get(k).d % 2 == 0) even++;
					else odd++;
				}
				mass = mass/5;
				speed /= temp[i][j].size();
				boolean isDiretion = (odd == 4 || even == 4)?true:false;

				temp[i][j].clear();

				if(mass > 0) {
					if(isDiretion) {
						temp[i][j].add(new Fire(mass, speed, 0));
						temp[i][j].add(new Fire(mass, speed, 2));
						temp[i][j].add(new Fire(mass, speed, 4));
						temp[i][j].add(new Fire(mass, speed, 6));
					} else {
						temp[i][j].add(new Fire(mass, speed, 1));
						temp[i][j].add(new Fire(mass, speed, 3));
						temp[i][j].add(new Fire(mass, speed, 5));
						temp[i][j].add(new Fire(mass, speed, 7));
					}
				}
			}
		}
	}
	*/
}//end class