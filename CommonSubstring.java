import java.util.*;

public class Main{

	static int M = 10;

	public static void main(String ... args){
		try(Scanner sc = new Scanner(System.in)){
			String s1 = sc.nextLine(),
				   s2 = sc.nextLine();

			int left = 1,
				right = Math.min(s1.length(), s2.length());

			String result = "";

			while(left <= right){
				int mid = (left + right) >> 1;

				String commonString = getCommonSubstringOfLength(mid, s1,s2);

				if(commonString.equals("")){
					right = mid-1;
				}
				else {
					result = commonString;
					left = mid+1;
				}
			}

			if(result.equals("")){
				System.out.println(0);
			}
			else {
				System.out.println(result);
				System.out.println(result.length());
			}
		}
	}

	private static List<Long> getPrefixHashes(String s){
		if(s.length() == 0) 
			return null;

		List<Long> hashValues = new ArrayList<Long>();
		hashValues.add((long)s.charAt(0)); 

		for(int i = 1;i < s.length();i++){
			hashValues.add(M * hashValues.get(i-1) + s.charAt(i));
		}

		return hashValues;
	}

	private static HashMap<Long,List<Integer>> getHashesOfLength(int length, String s){
		HashMap<Long,List<Integer>> hashValues = new HashMap<Long,List<Integer>>();

		if(length > s.length())
			return hashValues;

		long currentHash = getSingleHash(s,0,length);

		long power = (long) Math.pow(M, length);
		hashValues.put(currentHash, new ArrayList<Integer>(){{add(0);}});

		for(int i =1;i<s.length() - length+1;i++){
			final long hash = M * currentHash + (s.charAt(i+length - 1)) - (s.charAt(i-1)) * power;

			if(!hashValues.containsKey(hash)){
				hashValues.put(hash, new ArrayList<Integer>());
			}

			hashValues.get(hash).add(i);

			currentHash = hash;
		}

		return hashValues;
	}

	public static String getCommonSubstringOfLength(int length, String s1, String s2){
		HashMap<Long,List<Integer>> s1Hashes = getHashesOfLength(length,s1);
		HashMap<Long,List<Integer>> s2Hashes = getHashesOfLength(length,s2);
		
		s1Hashes.keySet().retainAll(s2Hashes.keySet());


		for(long hashVal : s1Hashes.keySet()){
			List<Integer> s1List = s1Hashes.get(hashVal);
			List<Integer> s2List = s2Hashes.get(hashVal);
			

			for(int posS1 : s1List){
				for(int posS2 : s2List){
					if(isMatch(s1,s2,posS1,posS2,length)){
						return s1.substring(posS1,posS1 + length);
					}
				}	
			}
		}

		return "";
	}

	private static long getSingleHash(String s, int start, int length){
		long val = 0l;

		for(int i=start;i<length;i++){
			val = val * M + (s.charAt(i));
		}

		return val;
	}

	private static boolean isMatch(String s1, String s2, int posS1, int posS2, int length){
		for(int i=0;i<length;i++){
			if(s1.charAt(i + posS1) != s2.charAt(i + posS2))
				return false;
		}

		return true;
	}
}