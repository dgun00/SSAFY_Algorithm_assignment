import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_7206_숫자게임_D5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			String num = br.readLine();

			func(numAry);

		} // end of tc
	}// end of main

	public static int func(String num) {
		if (num.length() == 1) {
			return 1;
		}

		String n1 = null;
		String n2 = null;

		// 수 나누기
		for (int i = 0; i < num.length() - 1; i++) {
			
			n1 = num.substring(0,i+1);
			
			for (int j = i + 1; j < num.length(); j++) {
				n2 = num.substring(j,num.length());
				
				
				int n1Int = Integer.parseInt(n1);
				int n2Int = Integer.parseInt(n2);
				
				return 1+func(Integer.toString(n1Int*n2Int));
				
			}
			
		}
		
		

	}

}// end of class
