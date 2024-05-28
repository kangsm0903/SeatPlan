package JavaProject;


public class Seat {
    private Student student;
    private boolean isOccupied;

    public Seat() {
    	this.isOccupied = false;
    }
    
    public Student getStudent() {
    	return student;
    }
    
    public void setStudent(Student student) {
    	this.student = student;
    }
    
    public void setIsOccupied(boolean bool) {
    	isOccupied = bool;
    }
    
    public boolean isOccupied() {
		return this.isOccupied;
	}
}
