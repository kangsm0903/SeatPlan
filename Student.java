package JavaProject;


public class Student {
    private boolean gender;
    private String name;

    public Student(boolean gender, String name) {
        this.gender = gender;
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
