import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_6782_현주가좋아하는제곱근놀이_D5 {
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc =1; tc<=TC; tc++) {
			cnt =0;
			
			Long N = Long.parseLong(br.readLine());
			
			
			
			while(N!=2) {
				// N 제곱근
				double sqrtN = (double)Math.sqrt(N);
				
				// N의 제곱근이 정수면
				if(sqrtN % 1 == 0) {
					cnt++;
					N = (long)sqrtN;
				}
				// 아니면 +1
				else {
					

					// 다음으로 큰 제곱수의 제곱근
					long nxtSqrtN = ((long)sqrtN) +1;
					
					long nxtN = nxtSqrtN*nxtSqrtN;
					
					long diff = nxtN-N;
					cnt += (diff);
					N += diff;
				}
			}
			
			System.out.println("#"+tc+" "+cnt);
		}
		
	}
}
