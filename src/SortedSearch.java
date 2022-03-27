import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SortedSearch {

	/*Implement function countNumbers that accepts a sorted array of unique integers and,
	efficiently with respect to time used, counts the number of array elements that are less than the parameter lessThan.
		For example, SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4)
		should return 2 because there are two array elements less than 4.*/
	public static int countNumbers_old(int[] sortedArray, int lessThan) {
		List<Integer> sorted1 = Arrays.stream(sortedArray).boxed().collect(Collectors.toList());
		List<Integer> sorted2 = Arrays.stream(sortedArray).boxed().collect(Collectors.toList());
		sorted1.sort(Integer::compareTo);
		if(sorted1.equals(sorted2)){
			return  (int) sorted1.stream().filter(num -> num.intValue() < lessThan).count();
			
		}else throw new UnsupportedOperationException("array is not sorted.");
	}
	public static int countNumbers_old2(int[] sortedArray, int lessThan) {
		int count = 0;
		for(int num:sortedArray){
			if(num>=lessThan) continue;
			else{
				count++;
			}
		}
		return count;
	}
	public static int countNumbers(int[] sortedArray, int lessThan) {
		int b = 0;
		int e = sortedArray.length - 1;

		int result = -1;
		while(b <= e) {
			int mid = b + (e - b) / 2;

			if (sortedArray[mid] < lessThan) {
				result = mid;
				b = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return result + 1;
	}

	public static void main(String[] args) {
		System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
	}
}