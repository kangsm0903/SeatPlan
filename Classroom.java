package JavaProject;

import java.util.ArrayList;

public class Classroom {
    //분단 배열(Division이 들어갈 배열)
    private ArrayList<Division> divisionArrayList;

    // 전체 학생 수, 분단의 개수, 분단 가로개수, 분단 세로개수
    public Classroom(int numOfStudent, int numOfDivision, int numOfRows, int numOfColumns) {
        this.divisionArrayList = new ArrayList<>();
        
        for(int i=0; i < numOfDivision; i++) {
        	Division division = new Division(numOfRows, numOfColumns);
        	this.divisionArrayList.add(division);
        }
    }
    
    // 반환
    public ArrayList<Division> getDivisionArrayList(){
    	return this.divisionArrayList;
    }
}
