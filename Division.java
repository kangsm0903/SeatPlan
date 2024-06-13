package JavaProject;

public class Division{
    private Seat[][] seats;

    public Division(int rowNum, int colNum){
        this.seats = new Seat[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                seats[i][j] = new Seat();
            }
        }
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }
}