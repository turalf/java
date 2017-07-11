import java.util.*;
 
public class Test{
 
	static int M = 10;
	static long P = 3119454983l;
	static List<Long> _prefixHashesS1 = new ArrayList();
	static List<Long> _prefixHashesS2 = new ArrayList();
 
	public static void main (String[] args){
		try(Scanner sc = new Scanner(System.in)){
			String s1 = sc.nextLine(),
				   s2 = sc.nextLine();
 
			int left = 1,
				right = Math.min(s1.length(), s2.length());

			_prefixHashesS1 = getPrefixHashes(s1);
			_prefixHashesS2 = getPrefixHashes(s2);

			System.out.println(getCommonSubstringOfLength(2,s1,s2));
			// System.out.println(getPrefixHashes(s1));
		}
	}
 
	private static List<Long> getPrefixHashes(String s){
		if(s.length() == 0) 
			return null;
 
		List<Long> hashValues = new ArrayList<Long>();
		hashValues.add((long)s.charAt(0)-'0'); 
 
		for(int i = 1;i < s.length();i++){
			hashValues.add((M * hashValues.get(i-1) + (s.charAt(i)-'0') % P));
		}
 
		return hashValues;
	}
 
	private static HashMap<Long,List<Integer>> getHashesOfLength(int length, String s, boolean isFirst){
		HashMap<Long,List<Integer>> hashValues = new HashMap<Long,List<Integer>>();
 
		if(length > s.length())
			return hashValues;

		long pow = (long) Math.pow(M, length);

		List<Long> _prefixHashes = isFirst ? _prefixHashesS1 : _prefixHashesS2;
 
		for(int i =0;i<s.length() - length + 1;i++){
			long prefixHash = _prefixHashes.get(i + length - 1);
 			
 			final long hash = i > 0 ? prefixHash -  pow * _prefixHashes.get(i -1) : prefixHash;

			if(!hashValues.containsKey(hash)){
				hashValues.put(hash, new ArrayList<Integer>());
			}
 
			List<Integer> prevOccurrences = hashValues.get(hash);

			final int currentIndex = i; 
			if(!prevOccurrences.stream().anyMatch(oc -> isMatch(s,s,oc,currentIndex,length))){
				hashValues.get(hash).add(i);
			}
		}
 
		return hashValues;
	}
 
	public static int getCommonSubstringOfLength(int length, String s1, String s2){
		HashMap<Long,List<Integer>> s1Hashes = getHashesOfLength(length,s1,true);
		HashMap<Long,List<Integer>> s2Hashes = getHashesOfLength(length,s2,false);
		

		System.out.println(s1Hashes);
 		System.out.println(s2Hashes);

		s1Hashes.keySet().retainAll(s2Hashes.keySet());
 		

 
		for(long hashVal : s1Hashes.keySet()){
			List<Integer> s1List = s1Hashes.get(hashVal);
			List<Integer> s2List = s2Hashes.get(hashVal);
			
 
			for(int posS1 : s1List){
				for(int posS2 : s2List){
					if(isMatch(s1,s2,posS1,posS2,length)){
						return posS1;
					}
				}	
			}
		}
 
		return -1;
	}
 
	private static long getSingleHash(String s, int start, int length){
		long val = 0l;
 
		for(int i=start;i<length;i++){
			val = (val * M + (s.charAt(i))) % P;
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