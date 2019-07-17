package linear;

public class RecursiveBinarySearch {
	public static int binarySearch(int[] A, int target, int low, int  high){
		if(low > high){
			return -1;
		}
		int middle = (low + high)/2;
		if(target == A[middle]){
			return middle;
		}
		else{
			if(target < A[middle]){
				return binarySearch(A, target, low, middle-1);
			}
			else{
				return binarySearch(A, target, middle+1, high);
			}
		}
	}
	public static int binarySearch(int[] A, int target){
		return binarySearch(A, target, 0, A.length-1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {3, 10, 20, 53, 70, 99};
		System.out.println(binarySearch(array, 53));
		System.out.println(binarySearch(array, 55));
	}

}
