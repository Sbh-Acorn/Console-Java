package joinLogin;

class Cinema {
    private String name;
    private Seat[][] seats;

    public Cinema(String name, int rows, int cols) {
        this.name = name;
        this.seats = new Seat[rows][cols];

        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

    public void displaySeats() {
        System.out.println("Available seats in " + name + ":");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isBooked()) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[ ] ");
                }
            }
            System.out.println();
        }
    }

    public boolean bookSeat(int row, int col) {
        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length) {
            return false; 
        }
        return seats[row][col].book();
    }
}
