public class SnakeSort {
	static void prin(int[] test){
		for (int i = 0; i < test.length; i++) {
			System.out.print(test[i] + ", ");
		}
	}
	public static void main(String[] args) {
//		//int[] test = {1,2,3,4,5,6,7,8};
//		int[] test = {9,8,7,6,5,4,3,2,1};
		int[] test = new int[80000];
		for (int i = 0; i < test.length; i++) {
			test[i] = (int)(Math.random()*10000);
		}
		
		
		int[] sorted;
		long start = System.currentTimeMillis();
//		sorted = snakeSort(test);
		long stop = System.currentTimeMillis();
		
//		prin(sorted);
		System.out.println();
		System.out.println((stop - start) + "ms");

		start = System.currentTimeMillis();
//		sorted = eliminationSort(test);
		stop = System.currentTimeMillis();
		
//		prin(sorted);
		System.out.println();
		System.out.println((stop - start) + "ms");

		start = System.currentTimeMillis();
		quickSort(test,0,test.length-1);
		stop = System.currentTimeMillis();
//		prin(test);
		System.out.println();
		System.out.println((stop - start) + "ms");
		System.out.printf("\nLength: %d",test.length);		
		System.out.println();
//		prin(sorted);
		System.out.println();
		prin(test);
	}
	
	static void quickSort(int in[],int lo, int hi){
		
		int left = lo;
		int right = hi-1;
		if(hi - lo <= 0 || lo >= in.length || hi >= in.length) return;
		int pivot = in[hi];
		if(hi - lo == 1){
			if(in[hi] < in[lo]){
				in[hi] = in[lo];
				in[lo] = pivot;
				return;
			} else return;
		}
		while(true){
			while(left <= right && in[right]>=pivot){
				right--;
			}
			while(left!=right && in[left]<=pivot){
				left++;
			}
			if(left<right){
				int t = in[left];
				in[left] = in[right];
				in[right] = t;
				left++;
				right--;
			}else break;
		}
		in[hi] = in[right+1];
		in[right+1] = pivot;
		quickSort(in, lo, right);
		quickSort(in, right+2, hi);
	}

	static int[] eliminationSort(int in[]){
		int result[] = new int[in.length];
		boolean sw[] = new boolean[in.length];
		for (int i = 0; i < sw.length; i++) {
			int min=999999999, minIndex=-1;
			for (int j = 0; j < in.length; j++) {
				if(!sw[j]){
					if(in[j]<min){
						min = in[j];
						minIndex = j;
					}
				}
			}
			result[i] = min;
			sw[minIndex] = true;
		}
		return result;
	}	
	static int[] snakeSort(int in[]){
		int result[] = new int[in.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = -999999999;
		}
		for (int i = 0; i < in.length; i++) {
			int breakIndex = 0;
			for (int j = result.length - 1; j >=0 ; j--) {
				if(result[j] <= in[i]){
					breakIndex = j;
					break;
				}
			}
			int lowBreak=0;
			for (int j = breakIndex - 1; j >0 ; j--) {
				if(result[j] == -9999){
					lowBreak = j;
					break;
				}
			}
//			for (int j = 0; j < breakIndex; j++) {
			for (int j = lowBreak; j < breakIndex; j++) {
				result[j] = result[j+1];
			}
			result[breakIndex] = in[i];
		}
		return result;
	}
}
