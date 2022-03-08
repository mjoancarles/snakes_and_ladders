import java.util.ArrayList;

public final class Board {
	private ArrayList<Square> squares = new ArrayList<>();
	private static final int MIN_NUM_SQUARES = 10;

	public Board(int numSquares, int[][] snakesOrLadders, int[] death_squares) {
		assert numSquares > MIN_NUM_SQUARES : "There must be at least " + MIN_NUM_SQUARES + " squares";
		makeSquares(numSquares);
		makeSnakesOrLadders(snakesOrLadders);
		makeDeaths(death_squares);
	}

	public Square firstSquare() {
		return squares.get(0);
	}

	public Square lastSquare() {
		return squares.get(squares.size()-1);
	}

	public Square findSquare(int position) {
		assert (position>0) && (position<numberOfSquares()) : "inexistent square";
		return squares.get(position);
	}

	public int numberOfSquares() {
		assert !squares.isEmpty();
		return squares.size();
	}

	private void makeSquares(int numSquares) {
		System.out.println("There are " + numSquares + " squares");
		squares.add(new FirstSquare(this));
		for (int position=1 ; position<numSquares ; position++) {
			Square square = new Square(position, this);
			squares.add(square);
		}
		assert squares.get(numSquares-1).isLastSquare();
	}

	private void makeSnakesOrLadders(int[][] snakesOrLadders) {
		for (int i=0; i<snakesOrLadders.length; i++) {
			assert snakesOrLadders[i].length == 2;

			int fromPosition = snakesOrLadders[i][0] - 1;
			int toPosition = snakesOrLadders[i][1] - 1;
			int transport = toPosition - fromPosition;
			String type= "";

			if (transport > 0) {
				type = "ladder";
				assert (toPosition < numberOfSquares()) && (toPosition > 0);
				assert (fromPosition > 0) && (fromPosition < numberOfSquares());
			} else if (transport < 0) {
				type = "snake";
				assert (toPosition > 0) && (toPosition<numberOfSquares()-1);
				assert (fromPosition < numberOfSquares()-1) && (fromPosition>0);
			}
			assert transport != 0 : "Ladders or snakes must change the player's position";

			System.out.println(type + " from " + (fromPosition + 1) + " to " + (toPosition + 1));
			squares.set(fromPosition, new SnakeOrLadder(fromPosition, this, transport, type));
		}
	}
	private void makeDeaths(int[] death_squares){
		for (int i=0;i<death_squares.length;i++) {
			int position= death_squares[i]-1;
			assert (position > 0) && (position<numberOfSquares()-1);
			System.out.println("Death in "+ (position+1));
			squares.set(position,new Death(position, this));
		}
	}
}
