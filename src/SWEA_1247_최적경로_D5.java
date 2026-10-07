import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1247_최적경로_D5 {

	static int[][] posAry;
	static int N;
	static int[] permRes;
	static boolean[] used;

	static int[] st;
	static int[] fi;
	
	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			posAry = new int[N][2];

			String[] posInfo = br.readLine().split(" ");

			st = new int[] { Integer.parseInt(posInfo[0]), Integer.parseInt(posInfo[1]) };
			fi = new int[] { Integer.parseInt(posInfo[2]), Integer.parseInt(posInfo[3]) };

			int idx = 0;
			for (int i = 4; i < posInfo.length; i += 2) {
				posAry[idx][0] = Integer.parseInt(posInfo[i]);
				posAry[idx][1] = Integer.parseInt(posInfo[i + 1]);
				idx++;
			}

			permRes = new int[N];
			used = new boolean[N];
			min = Integer.MAX_VALUE;
			perm(0);
			System.out.println("#"+tc+" "+min);
		} // end of tc
	}// end of main
	
	static int getTotalDist(int[] permAry) {
		int totalDist =0;
		
		for(int i=0; i<	permAry.length-1;i++) {
			totalDist += getDist(permAry[i],permAry[i+1]);
		}
		totalDist += Math.abs(st[0]-posAry[permAry[0]][0]) + Math.abs(st[1]-posAry[permAry[0]][1]);
		totalDist += Math.abs(fi[0]-posAry[permAry[permAry.length-1]][0]) + Math.abs(fi[1]-posAry[permAry[permAry.length-1]][1]);
		
		return totalDist;
	}
	
	static int getDist(int a, int b) {
		return Math.abs(posAry[a][0]-posAry[b][0]) + Math.abs(posAry[a][1]-posAry[b][1]);
	}
	
	
	static void perm(int depth) {
		if (depth == N) {
			min = Math.min(getTotalDist(permRes),min);
			return;
		}

		for (int i = 0; i < N; i++) {
			if(used[i]) continue;
			
			used[i] = true;
			permRes[depth] = i;
			perm(depth+1);
			used[i]=false;
		}
	}

}// end of class
