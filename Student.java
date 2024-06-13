package JavaProject;

public class Student{
    private String myName;
    private Main.gender mygender;
    private boolean isSeated = false;
    public Student(String name, Main.gender myGender){
        this.myName = name; this.mygender = myGender;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public Main.gender getMygender() {
        return mygender;
    }

    public void setMygender(Main.gender mygender) {
        this.mygender = mygender;
    }

    public boolean isSeated() {
        return isSeated;
    }

    public void setSeated(boolean seated) {
        isSeated = seated;
    }
}