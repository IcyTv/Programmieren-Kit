public class tmp {
	public static MagicSquare fromString(String sq) {
		// args[0] == "1,2,3;4,5,6;7,8,9"
		String[] square = sq.split(";"); // ["1,2,3", "4,5,6", "7,8,9"]
		String[][] cells = new String[square.length][square.length];

		for (int i = 0; i < square.length; i++) {
			cells[i] = square[i].split(",");
		}

		// cells == [["1", "2", "3"],
		// ["4", "5", "6"],
		// ["7", "8", "9"]]

		int[][] intCells = new int[cells.length][cells.length];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; i++) {
				intCells[i][j] = Integer.parseInt(cells[i][j]);
			}
		}

		// intCells == [[1,2,3],
		// [4, 5,6],
		// [7,8,9]];

		return new MagicSquare(intCells);

	}
}
