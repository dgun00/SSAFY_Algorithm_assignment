import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5643_키순서_D4 {

	static List<Integer>[] inList;
	static List<Integer>[] outList;
	static Set<Integer> s;
	static int N;
	static int res;

	static int cnt1;
	static int cnt2;


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());

//			// 0번 행,열 버리기
//			boolean[][] matrix = new boolean[N + 1][N + 1];

			inList = new ArrayList[N + 1];
			outList = new ArrayList[N + 1];

			for (int i = 0; i < N + 1; i++) {
				inList[i] = new ArrayList<>();
				outList[i] = new ArrayList<>();

			}

			// in,out list 초기화
			for (int i = 0; i < M; i++) {
				String[] cmp = br.readLine().split(" ");
				int a = Integer.parseInt(cmp[0]);
				int b = Integer.parseInt(cmp[1]);

				outList[a].add(b);
				inList[b].add(a);

			}

			// 비교가 가능한 노드들 모음, 중복 피하기위해 Set 사용
			s = new HashSet<>();
			res = 0;
			cnt1 = 0;
			cnt2 =0;
			for (int i = 1; i <= N; i++) {
				cntOut(i);
				cntIn(i);
				s.add(i);
//				System.out.println("size"+":"+s.size());
				if (s.size() == N)
					res++;
				s.clear();
			}

			System.out.println("#" + tc + " " + res);


		} // end of testcase
	}// end of main

	static public void cntOut(int node) {


//		System.out.println("out"+":"+node);
		for (int i = 0; i < outList[node].size(); i++) {
			// set에 없으면 타고 들어가기
			if(s.add(outList[node].get(i))) {
				cntOut(outList[node].get(i));
			}
//			System.out.println("size"+":"+s.size());
			
		}
	}

	static public void cntIn(int node) {


//		System.out.println("in"+":"+node);
		for (int i = 0; i < inList[node].size(); i++) {
			
			// set에 없으면 타고 들어가기
			if(s.add(inList[node].get(i))) {
				cntIn(inList[node].get(i));
			}
			
//			System.out.println("size"+":"+s.size());

			
		}
	}
}// end of class
