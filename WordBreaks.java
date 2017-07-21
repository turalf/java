public class WordBreaks{
	public static void main(String []){
		try(Scanner sc = new Scanner(System.in)){
            char[] str = sc.nextLine().toCharArray();

        }

	}

	public static List<String> breakWords(String word, Set<String> dict){
		List<Integer> breakPoints = new LinkedList<Integer>(Arrays.asList(0));
		List<Integer> concat = new LinkedList<Integer>(breakPoints);
		for(int i=0;i<word.length();i++){
				int currSize = breakPoints.size(), j = 0;
				while(j<currSize){
					//:TODO: check the end index
					if(dict.contains(word.substring(j,i))){
							breakPoints.add(j);
							int lastStart = concat.get(concat.length - 1);
							iklastStart
							break;
					}
				}
		}
	}
}
