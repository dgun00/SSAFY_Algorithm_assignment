
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 접근:
 * 
 * 걍 조합 다 구하고 parseInt 해서 최대값 구하셈
 * 
 */
xxxx
public class SWEA_1244_최대상금_D3 {
	
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc =1; tc<=TC; tc++) {
			String[] line = br.readLine().split(" ");
			
			String num = line[0];
			
			N = Integer.parseInt(line[1]);
			
			
			getCombi()
			
			
			
		}
		
	}

	public static void getCombi(int cnt) {
		
		if(cnt == N) {
			return;
		}
		
		for(int i = 0; i<N; i++) {
			getCombi
		}
		
	}
}
