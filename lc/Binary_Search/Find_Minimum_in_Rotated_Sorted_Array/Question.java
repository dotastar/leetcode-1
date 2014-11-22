package Find_Minimum_in_Rotated_Sorted_Array;

public class Question {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//Using Binary Search template
    public int findMin(int[] num) {
        if(num == null || num.length == 0){
        	return Integer.MAX_VALUE;
        }
        int start = 0;
        int end = num.length - 1;
        while (start + 1 < end) {
        	int mid = start + (end - start) / 2;
        	if (num[mid] < num[end]) { //右边有序，所以min一定在左边
        		end = mid;
        	} else {
        		start = mid;
        	}
        }
        return Math.min(num[start], num[end]);
    }

}