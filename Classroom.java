package JavaProject;

import java.util.ArrayList;
import java.util.Collections;

public class Classroom{
    private ArrayList<Division> divisions;
    private int rowNum, colNum;

    public ArrayList<Division> getDivisions() {return divisions;}

    public Classroom(int divisionNum, int rowNum, int colNum){
        //make divisions
        divisions = new ArrayList<>();
        this.rowNum = rowNum; this.colNum = colNum;
        for(int i = 0; i < divisionNum; i++){divisions.add(new Division(rowNum, colNum));}
    }

    void seatRandomly(ArrayList<Student> students){
        Collections.shuffle(students);
        int seatedStudents = 0;
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < divisions.size(); j++){
                for(int k = 0; k < colNum; k++){
                    if(seatedStudents < students.size()){
                        Student myStudent = students.get(seatedStudents);
                        divisions.get(j).getSeats()[i][k].setMyStudent(myStudent); seatedStudents++; divisions.get(j).getSeats()[i][k].setOccupied(true);
                        System.out.println(myStudent.getMyName() + ", 남아있는학생수: " + (students.size() - seatedStudents));
                    }
                }
            }
        }
    }

    void seatRandomlyMixedVer(ArrayList<Student> students){
        Collections.shuffle(students);
        int seatedStudents = 0;
        int seatedBoys = 0;
        int seatedGirls = 0;
        ArrayList<Student> boys = new ArrayList<>();
        ArrayList<Student> girls = new ArrayList<>();

        //init boys and girls info
        for(Student myStudent : students){
            if(myStudent.getMygender().equals(Main.gender.MALE)){boys.add(myStudent);}
            else if (myStudent.getMygender().equals(Main.gender.FEMALE)){girls.add(myStudent);}
        }
        //seat randomly mixed ver
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < divisions.size(); j++){
                for(int k = 0; k < colNum; k++){
                    if(seatedStudents < students.size()){
                        Student myStudent;
                        if(seatedStudents % 2 == 0){
                            if(seatedBoys < boys.size()){myStudent = boys.get(seatedBoys); seatedBoys++;}
                            else {myStudent = girls.get(seatedGirls); seatedGirls++;}
                        }
                        else{
                            if(seatedGirls < girls.size()){myStudent = girls.get(seatedGirls); seatedGirls++;}
                            else{myStudent = boys.get(seatedBoys); seatedBoys++;}
                        }
                        divisions.get(j).getSeats()[i][k].setMyStudent(myStudent); seatedStudents++; divisions.get(j).getSeats()[i][k].setOccupied(true);
                        System.out.println(myStudent.getMyName() + ", 남아있는학생수: " + (students.size() - seatedStudents));
                    }
                }
            }
        }

    }

}