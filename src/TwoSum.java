import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoSum {

	public static int[] findTwoSum(int[] list, int sum) {

		int len = list.length;
		if(len == 0 || list == null) return null;
		Hashtable mapping = new Hashtable();
		for (int i=0; i < len; i++)
		{
			mapping.put(list[i], i);
		}
		for (int i=0; i < len; i++)
		{
			if (mapping.containsKey(sum - list[i]) && (Integer)mapping.get(sum-list[i]) != i)
			{
				return new int[]{(Integer)mapping.get(sum - list[i]), i};
			}
		}
		return null;
	}
	public static void main(String[] args) {
		int[] indices = findTwoSum(new int[] { 3, 1, 5, 7, 5, 9 }, 10);
		if(indices != null) {
			System.out.println(indices[0] + " " + indices[1]);
		}
	}


}
