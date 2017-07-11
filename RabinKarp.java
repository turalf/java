
public class RabinKarp{
	static int M = 10;

	public static void main(String [] args){
		System.out.println(matchPattern("abcd","c7"));
	}

	public static boolean matchPattern(String text, String pattern){
		if(text.length() < pattern.length())
			return false;

		long patternHash = calcHash(pattern);
		long txtHash = calcHash(text.substring(0,pattern.length()));
		long power = (long)Math.pow(M,pattern.length() - 1);

		for(int i=0;i<=text.length() - pattern.length();i++){
			System.out.println(patternHash);
			System.out.println(txtHash);
			System.out.println(i+"-----");
			if(patternHash == txtHash && isSubstringPattern(text, pattern, i))
				return true;
			if(i + pattern.length() < text.length())
				txtHash =  (txtHash - text.charAt(i) * power) * power + text.charAt(i+pattern.length());
		}

		return false;
	}

	private static long calcHash(String str){
		long hashValue =0;
		long power = 1;
		for(int i=str.length()-1;i>=0;i--){
			hashValue += (str.charAt(i)) * power;
			power *= M;
		}

		return hashValue;
	}

	private static boolean isSubstringPattern(String text, String pattern, int start){
		for(int i=0;i<pattern.length();i++){
			if(pattern.charAt(i) != text.charAt(start +i))
				return false;
		}

		return true;
	}
}