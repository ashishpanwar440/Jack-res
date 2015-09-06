public class Euler24 {
	static int nth = 0;
	static java.util.LinkedList <Long>listy = new java.util.LinkedList<Long>();
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		int[] nums = {0,1,2,3,4,5,6,7,8,9};
		perm(nums, 0);
		listy.sort(null);
		System.out.println(listy.get(1000000-1));
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
	}

	public static void perm(int[] list, int pos){
		if(pos == list.length-1){
			nth++;
			long input = 0;
			for (int i = 0; i < list.length; i++) {
				input = input*10+list[i];
			}
			listy.add(input);
			return;
		}
		for(int i = pos; i < list.length;i++){
			swap(list,pos,i);
			perm(list,pos+1);
			swap(list,pos,i);
		}
	}
	
	public static void swap(int[] list,int posA, int posB){
		int temp = list[posA];
		list[posA] = list[posB];
		list[posB] = temp;
	}

}
