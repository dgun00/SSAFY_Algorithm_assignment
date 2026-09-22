import java.util.Arrays;
import java.util.Scanner;

public class CODETREE_4_2_아름다운수_E {

	static int[] beautifulNum;
	static int N;
	static int res;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		res = 0;
		beautifulNum = new int[N];

		multiPerm(0);
		System.out.println(res);

	}

	public static void multiPerm(int cnt) {

		if (cnt == N) {
			if (isBeautiful(beautifulNum)){
				res++;
//				System.out.println(Arrays.toString(beautifulNum));

			}

			return;

		}

		for (int i = 1; i <= 4; i++) {

			beautifulNum[cnt] = i;
			multiPerm(cnt + 1);

		}

	}

	public static boolean isBeautiful(int[] num) {

		int prevNum = num[0];

		int cnt = 1;

		for (int i = 0; i < N - 1; i++) {
			if (prevNum == num[i+1]) {
				cnt++;
				continue;
			} else {
				if (cnt % prevNum != 0) {
					return false;
				}
			}
			// 이전수가 아름다울때 초기화
			prevNum = num[i+1];
			cnt = 1;

		}
		
		if(prevNum == num[N-1]) {
			if (cnt % prevNum != 0) {
				return false;
			}
		}
		

		return true;
	}
}
