
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class SWEA_3260_두수의덧셈_D3 {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			String[] nums = br.readLine().split(" ");

			char[] A = nums[0].toCharArray();
			char[] B = nums[1].toCharArray();

			// 길이 긴 놈이 A로 설정
			if (A.length < B.length) {
				char[] tmp = A;
				A = B;
				B = tmp;
			}

			// A와 B 끝 인덱스
			int idxA = A.length - 1;
			int idxB = B.length - 1;

			boolean carry = false;
			while (idxB >= 0) {

				int plusRearVal = A[idxA] - '0' + B[idxB] - '0';

				if (carry) {
					plusRearVal++;
					carry = false;
				}

				if (plusRearVal >= 10) {
					carry = true;
				}

				A[idxA] = (char) (plusRearVal % 10 + '0');

				idxA--;
				idxB--;

			}

			while (idxA >= 0 && carry) {
				int plusRearVal = A[idxA] - '0';

				if (carry) {
					plusRearVal++;
					carry = false;
				}

				if (plusRearVal >= 10) {
					carry = true;
				}

				A[idxA] = (char) (plusRearVal % 10 + '0');

				idxA--;
			}

			String res = new String(A);
			if (carry) res = "1"+res;
			System.out.println("#" + tc + " " + res);
		}

	}// end of main
}// end of class
