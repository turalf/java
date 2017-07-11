public class RemoveDuplicates{
	public static void main(String ... args){
		int [] arr = new int[]{1,1,1,2,2,3,3};
		removeDuplicatesM(arr,2);

		//removeKey(arr,1);

		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i] +" ");
		}
	}

	private static void removeDuplicates(int [] array){
		int j =0, i =0;
		for(i=0;i+1<array.length;i++){
			if(array[i] != array[i+1]){
				array[j++] = array[i];
			}
		}

		array[j] = array[i];
	}

	private static void removeDuplicatesM(int [] array, int m){
		if(array.length < 3 || m < 2)
			return;

		int j =2;
		int count = 1;
		for(int i = 1;i<array.length;i++){
			if(array[i] == array[i-1])
				count ++;
			else 
				count =0;
			

			if(count < m && j<i)
				array[j++] = array[i];
			
		}
	}

	private static void removeKey(int [] array, int k){
		int j = 0;
		for(int i=0;i<array.length;i++){
			if(array[i] != k){
				array[j++] = array[i];
			}
		}
	}
}