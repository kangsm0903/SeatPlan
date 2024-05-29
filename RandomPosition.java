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
				int first = rand.nextInt(numOfDivision); 
				int second = rand.nextInt(numOfRows); 
				int third = rand.nextInt(numOfColumns); 
				
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
