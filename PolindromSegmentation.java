public class PolindromSegmentation{
	public static void main(String [] args){
			try(Scanner sc = new Scanner(System.in)){
				int n  = Integer.parseInt(sc.nextLine());
				while(n-->0) _dictionary.add(sc.nextLine());
				String input = sc.nextLine();
				String output = segmentIntoWords(input);
				System.out.println(output);
			}
	}

	public static String segmentIntoPolindroms(String input){
		int [] parentMap = new int[input.length];	
		int [] dp = new int[input.length];	
		for(int i=0;i<dp.length;i++) dp[i] = i;

		for(int i=0;i<input.length();i++){
			for(int j =0;j<i;j++){
				if(!isPolindrom(input, j, i))
						continue;

				if(dp[j] + 1 < dp[i]){
					dp[i] = dp[j] + 1;
					parent[i] = j;
				}

			}	
		}
	}

	private static boolean isPolindrom(String input, int start,int end){
		while(start <= end){
			if(input.charAt(start) != input.charAt(j))
					return false;
		}

		return true;
	
	}

}
