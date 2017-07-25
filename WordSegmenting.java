import java.util.*;


public class WordSegmenting{
	private static	HashSet<String> _dictionary  = new HashSet<String>();

	public static void main(String ... args){
		try(Scanner sc = new Scanner(System.in)){
			int n  = Integer.parseInt(sc.nextLine());
			while(n-->0) _dictionary.add(sc.nextLine());
			String input = sc.nextLine();
			String output = segmentIntoWords(input);

			System.out.println(output);

		}
	}

	public static String segmentIntoWords(String input){
		List<Integer> breakPoints = new LinkedList<Integer>(Arrays.asList(0));
		HashMap<Integer,Integer> parentMap = new HashMap<Integer,Integer>();

		for(int i=1;i<=input.length();i++){
			int listSize = breakPoints.size();
			Iterator iterator = breakPoints.iterator();

			while(listSize -- > 0){
				int startIndex = (Integer) iterator.next();		
				if(_dictionary.contains(input.substring(startIndex,i))){
					breakPoints.add(i);
					parentMap.put(i,startIndex);
					break;
				}
			}
		}

		//generating a segmented string
		int endIndex = parentMap.containsKey(input.length()) ? (input.length()) : -1;
		int startIndex = -1;

		System.out.println(breakPoints);
		System.out.println(parentMap);

		if(endIndex == -1) return null;

		StringBuilder sb = new StringBuilder();
		
		while(endIndex != 0){
			startIndex = parentMap.get(endIndex);
			sb.insert(0, input.substring(startIndex, endIndex) + " ");
			endIndex = startIndex;
		}	

		return sb.toString();

	}

}
