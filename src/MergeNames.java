import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeNames {

	public static String[] uniqueNames_old(String[] names1, String[] names2) {
		String[] res = null;
		List<String> totalList = new ArrayList<>(Arrays.asList(names1));
		List<String> totalList2 =  new ArrayList<>(Arrays.asList(names2));

		List<String> delrepeated1 =  new ArrayList<>();
		List<String> delrepeated2 =  new ArrayList<>();

		totalList.forEach(name -> {
			if(!delrepeated1.contains(name)) delrepeated1.add(name);
		});

		totalList2.forEach(name -> {
			if(!delrepeated2.contains(name)) delrepeated2.add(name);
		});

		List<String> aa2 = delrepeated2.stream().filter(name -> !delrepeated1.contains(name)).collect(Collectors.toList());

		totalList.addAll(aa2);

		String[] aaa = totalList.stream()
				.map(String::new)
				.toArray(String[]::new);

		return aaa;

	}

	public static String[] uniqueNames(String[] names1, String[] names2) {
		String[] res = null;
		List<String> totalList = new ArrayList<>();
		List<String> totalList2 =  new ArrayList<>();

		for(String name:names1){
			if(!totalList.contains(name)) totalList.add(name);
		}
		for(String name:names2){
			if(!totalList2.contains(name)) totalList2.add(name);
		}

		List<String> aa2 = totalList2.stream().filter(name -> !totalList.contains(name)).collect(Collectors.toList());

		totalList.addAll(aa2);

		String[] aaa = totalList.stream()
				.map(String::new)
				.toArray(String[]::new);

		return aaa;

	}

	public static void main(String[] args) {
		String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
		String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};
		System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
	}
}