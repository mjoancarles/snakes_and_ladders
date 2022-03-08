public class Death extends Square {
    public Death(int position,Board b) {
        super(position, b);
    }
    @Override
    public void enter(Player player) {
        player.setDead(true);
    }
}
