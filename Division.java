package JavaProject;

public class Division {
    private int numOfRows; // 2
    private int numOfColumns; // 4
    private Seat[][] detailOfDivision; // 2차원 배열

    public Division(int numOfRows, int numOfColumns) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.detailOfDivision = new Seat[numOfRows][numOfColumns]; // 분단을 2차원 배열로 생성
        
        for(int i=0; i<numOfRows; i++) {
        	for(int j=0; j<numOfColumns; j++) {
        		this.detailOfDivision[i][j] = new Seat();
        	}
        }
    }
    
    public Seat[][] getDetailOfDivision() {
        return this.detailOfDivision;
    }
}
