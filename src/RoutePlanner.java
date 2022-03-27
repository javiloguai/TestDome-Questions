import java.util.*;

public class RoutePlanner {

	static HashMap<String, ArrayList<String>> graph;

	public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn, boolean[][] mapMatrix) {
		//validating input into edges
		if(fromRow < 0 || fromColumn < 0 || toRow < 0 || toColumn < 0) {
			return false;
		}
		if(fromRow >= mapMatrix.length || fromColumn >= mapMatrix[0].length || toRow >= mapMatrix.length || toColumn >= mapMatrix[0].length) {
			return false;
		}
		//Validating if start or end are roads or lava
		if(!mapMatrix[fromRow][fromColumn] || !mapMatrix[toRow][toColumn]) {
			return false;
		}
		//Validating if start and end are the same coords
		if(fromRow == toRow && fromColumn == toColumn) {
			return true;
		}

		mapToGraph(mapMatrix);
		return doBFSearch(fromRow + "-" + fromColumn, toRow + "-" + toColumn);
	}


	private static void mapToGraph(boolean[][] matrix) {
		graph = new HashMap<String, ArrayList<String>>();

		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j]) {
					String arc = i + "-" + j;
					//above
					if(i+1 < matrix.length) {
						if(matrix[i+1][j]) {
							confirmWay(arc, (i+1) + "-" + j);
						}
					}
					//under
					if(i-1 >= 0) {
						if(matrix[i-1][j]) {
							confirmWay(arc, (i-1) + "-" + j);
						}
					}
					//left
					if(j-1 >= 0) {
						if(matrix[i][j-1]) {
							confirmWay(arc, i + "-" + (j-1));
						}
					}
					//right
					if(j+1 < matrix[i].length) {
						if(matrix[i][j+1]) {
							confirmWay(arc, i + "-" + (j+1));

						}
					}
				}else continue;

			}
		}
	}

	private static void confirmWay(String from, String to) {
		if(graph.containsKey(from)) {
			graph.get(from).add(to);
		} else {
			ArrayList<String> neighbour = new ArrayList<String>();
			neighbour.add(to);
			graph.put(from, neighbour);
		}
	}

	private static boolean doBFSearch(String start, String end) {
		LinkedList<String> fifoQueue = new LinkedList<String>();
		HashSet<String> visitedNodes = new HashSet<String>();

		fifoQueue.add(start);
		visitedNodes.add(start);

		String currNode;
		while(!fifoQueue.isEmpty()) {
			currNode = fifoQueue.poll();

			if(currNode.equals(end)) {
				//Arrived at the end
				return true;
			}

			if(!graph.containsKey(currNode)) {
				return false;
			}

			Iterator<String> it = graph.get(currNode).iterator();

			while (it.hasNext()){
				String next = it.next();
				if(!visitedNodes.contains(next)) {
					visitedNodes.add(next);
					fifoQueue.add(next);
				}
			}

		}

		return false;
	}


	public static void main(String[] args) {
		boolean[][] mapMatrix = {
				{true,  false, false},
				{true,  true,  false},
				{false, true,  true}
		};
		System.out.println(routeExists(0, 0, 2, 2, mapMatrix));

	}


}
