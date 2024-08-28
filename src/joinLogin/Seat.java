package joinLogin;

public class Seat {
	private int row;
    private int col;
    private boolean reserve;

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.reserve = false;
    }

    public boolean isBooked() {
        return reserve;
    }

    public boolean book() {
        if (!reserve) {
        	reserve = true;
            return true;
        }
        return false;
    }

}
