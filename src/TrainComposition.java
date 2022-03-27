import java.util.ArrayDeque;
import java.util.LinkedList;

public class TrainComposition {
	LinkedList<Integer> wagons = new LinkedList<Integer>();
	//private ArrayDeque<Integer> wagons = new ArrayDeque<>();


	public void attachWagonFromLeft(int wagonId) {
		wagons.addFirst(wagonId);
	}

	public void attachWagonFromRight(int wagonId) {
		wagons.addLast(wagonId);
	}

	public int detachWagonFromLeft() {
		return wagons.pollFirst();
	}

	public int detachWagonFromRight() {
		return wagons.pollLast();
	}

	public static void main(String[] args) {
		TrainComposition train = new TrainComposition();
		train.attachWagonFromLeft(7);
		train.attachWagonFromLeft(13);
		System.out.println(train.detachWagonFromRight()); // 7
		System.out.println(train.detachWagonFromLeft()); // 13
	}
}