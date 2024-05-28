package JavaProject;

import java.util.ArrayList;
import java.util.Random;


public class RandomPosition {
	private int numOfStudent;
	
	public RandomPosition(ArrayList<Division> divisionArrayList ,ArrayList<Student> students,int numOfDivision, int numOfRows, int numOfColumns) {
		numOfStudent = students.size();
		
		long seed = System.currentTimeMillis();
		Random rand = new Random(seed);
		
		for(Student student : students) {
			while(true) {
				int first = rand.nextInt(numOfDivision); 4
				int second = rand.nextInt(numOfRows); 2
				int third = rand.nextInt(numOfColumns); 5
				
				Seat temp = divisionArrayList.get(first).getDetailOfDivision()[second][third];
				
				if(temp.isOccupied()==false) {
					temp.setStudent(student);
					temp.setIsOccupied(true);
					break;
				} else {
					continue;
				}	
			}
			
		}
	}
}
