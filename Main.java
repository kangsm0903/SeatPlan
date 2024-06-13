package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Main {
    enum gender{MALE, FEMALE};
    enum seatVer{MIXED, NORMAL};
    static JTextField divisionField;
    static JTextField rowsField;
    static JTextField columnsField;
    static JCheckBox mixedVerCheckbox;
    static JCheckBox normalVerCheckbox;
    static JPanel inputPanel;
    static int result;
    static Classroom classroom;
    static GUI gui;
    static ArrayList<Student> students;
    static String filePath;
    static boolean isReadyToStart;

    static void getInputFromGUI(){
        divisionField = new JTextField(5);
        rowsField = new JTextField(5);
        columnsField = new JTextField(5);

        mixedVerCheckbox = new JCheckBox("Mixed Version");
        normalVerCheckbox = new JCheckBox("Normal Version");

        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(new JLabel("분단 수를 입력하세요."));
        inputPanel.add(divisionField);
        inputPanel.add(new JLabel("분단의 가로 칸 수를 입력하세요."));
        inputPanel.add(columnsField);
        inputPanel.add(new JLabel("분단의 세로 칸 수를 입력하세요."));
        inputPanel.add(rowsField);

        inputPanel.add(new JLabel("체크박스를 선택하세요."));
        inputPanel.add(mixedVerCheckbox);
        inputPanel.add(normalVerCheckbox);

        result = JOptionPane.showConfirmDialog(null, inputPanel, "분단 정보 입력", JOptionPane.OK_CANCEL_OPTION); // 사용자에게 팝업창을 띄움
        if (result == JOptionPane.OK_OPTION) { // 사용자가 OK를 누른 경우 입력된 값을 읽어
            try{
                doWhatUserWants();
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a valid number."); students = new ArrayList<>(); getInputFromGUI();
            } catch (WrongFormatInputFile | IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Please match the format of input file and restart the program."); exit(0);
            } catch(OverAcceptance e){
                JOptionPane.showMessageDialog(null, "Please allocate sufficient seats for your classroom."); students = new ArrayList<>(); getInputFromGUI();
            } catch(NotSelectedOption e){
                JOptionPane.showMessageDialog(null, "Please select a valid option.");  students = new ArrayList<>(); getInputFromGUI();
            } catch(OverSelectedOption e){
                JOptionPane.showMessageDialog(null, "Please select only one option.");  students = new ArrayList<>(); getInputFromGUI();
            } catch (FileNotFoundException e){
                JOptionPane.showMessageDialog(null, "'input.txt' file is not found. Change the file name or move it to specified folder"); exit(0);
            }  catch (IOException e) { e.printStackTrace(); }
        }
    }

    static void doWhatUserWants() throws OverAcceptance, NotSelectedOption, OverSelectedOption, IOException, WrongFormatInputFile, IllegalArgumentException {
        int divisionNum, rowNum, colNum;
        try {
            divisionNum = Integer.parseInt(divisionField.getText());
            rowNum = Integer.parseInt(rowsField.getText());
            colNum = Integer.parseInt(columnsField.getText());
        } finally {}

        //get students info from input.txt
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");    // 띄어쓰기 기준으로 분할
                if (parts.length == 2) {
                    String name = parts[0];
                    gender myGender;
                    switch (parts[1]) {
                        case "남": myGender = gender.MALE; break;
                        case "여": myGender = gender.FEMALE; break;
                        default: throw new WrongFormatInputFile();
                    }
                    students.add(new Student(name, myGender));
                }  else {throw new WrongFormatInputFile();}
            }
        }
        // 입력한 자릿수보다 학생들의 수가 더 많을 때 사용자 정의 에러 반환
        if (students.size() > divisionNum * colNum * rowNum) { throw new OverAcceptance(); }

        classroom = new Classroom(divisionNum, rowNum, colNum);
        gui = new GUI(classroom, students, rowNum, colNum);
        while(!isReadyToStart){
            if(!mixedVerCheckbox.isSelected() && !normalVerCheckbox.isSelected()) { throw new NotSelectedOption(); }
            else if(mixedVerCheckbox.isSelected() && normalVerCheckbox.isSelected()) { throw new OverSelectedOption(); }
            else if(mixedVerCheckbox.isSelected()) { isReadyToStart = true; gui.start(seatVer.MIXED); }
            else { isReadyToStart = true; gui.start(seatVer.NORMAL);}
        }
    }

    public static void main(String[] args) {

        filePath = "input.txt"; // 파일 경로를 여기에 입력하세요
        students = new ArrayList<>();
        isReadyToStart = false;

        // GUI에서 값을 입력받음
        getInputFromGUI();


    }
    static class WrongFormatInputFile extends Exception{}
    static class OverAcceptance extends Exception{}
    static class NotSelectedOption extends Exception{}
    static class OverSelectedOption extends Exception{}
}
