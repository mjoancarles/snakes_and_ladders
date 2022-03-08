public class SnakeOrLadder extends Square {
    private final int transport;
    private final String subtype;

    public SnakeOrLadder(int pos, Board b, int trans, String type) {
        super(pos, b);
        assert ((type == "snake") || (type == "ladder"));
        if (type == "snake"){
            assert trans < 0;
        } else if (type == "ladder") {
            assert trans > 0;
        }
        transport = trans;
        subtype = type;
    }

    @Override
    protected Square landHereOrGoHome() {

        System.out.println(subtype + " from " + (position+1) + " to " + (destination().getPosition()+1));
        return destination().landHereOrGoHome();
    }

    private Square destination() {
        return findRelativeSquare(transport);
    }
}
