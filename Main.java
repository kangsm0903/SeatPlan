package JavaProject;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

    	String filePath = "input.txt"; // 파일 경로를 여기에 입력하세요
        ArrayList<Student> students = new ArrayList<>();
        int numOfStudent=0; // 학생 수 
        
        // GUI에서 값을 입력받음 
        
        JTextField divisionField = new JTextField(5);
        JTextField rowsField = new JTextField(5);
        JTextField columnsField = new JTextField(5);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(new JLabel("분단 수를 입력하세요:"));
        inputPanel.add(divisionField);
        inputPanel.add(new JLabel("분단의 가로 칸 수를 입력하세요:"));
        inputPanel.add(rowsField);
        inputPanel.add(new JLabel("분단의 세로 칸 수를 입력하세요:"));
        inputPanel.add(columnsField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "분단 정보 입력", JOptionPane.OK_CANCEL_OPTION); // 사용자에게 팝업창을 띄움
        
        if (result == JOptionPane.OK_OPTION) { // 사용자가 OK를 누른 경우 입력된 값을 읽어
            int numOfDivision = Integer.parseInt(divisionField.getText());
            int numOfRows = Integer.parseInt(rowsField.getText());
            int numOfColumns = Integer.parseInt(columnsField.getText());
            
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(" "); 	// 띄어쓰기 기준으로 분할 
	                if (parts.length == 2) {
	                    String name = parts[0];			
	                    boolean gender = parts[1].equals("남");	// 1 == 남, 0 == 여
	                    students.add(new Student(gender, name));// 학생 배열에 학생들 추가 
	                    numOfStudent++;
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        
	        if(numOfStudent > numOfDivision * numOfRows * numOfColumns) {
	        	// 입력한 자릿수보다 학생들의 수가 더 많을 때 사용자 정의 에러 반환 및 종료
	        	System.exit(0);
	        } 
	        
	        // classroom(총 학생, 분단 수, 분단 가로, 분단 세로, 학생정보)
	        // 자리 설정 
	        Classroom classroom = new Classroom(numOfStudent, numOfDivision, numOfRows, numOfColumns);
	        
	        // 시작 버튼
	        
	        // 랜덤 배치 
	        RandomPosition random = new RandomPosition(classroom.getDivisionArrayList(), students, numOfDivision, numOfRows, numOfColumns);
	        
	        // GUI 
	        GUI gui = new GUI(classroom.getDivisionArrayList(), numOfRows, numOfColumns);
	        
	    }
	}
}