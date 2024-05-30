package JavaProject;

import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Main {
    public static void main(String[] args) {

    	String filePath = "input.txt"; // 파일 경로를 여기에 입력하세요
        ArrayList<Student> students = new ArrayList<>();
        int numOfStudent=0; // 학생 수 
        
        // GUI에서 입력받을 값들 - 임의로 지정 
        // 분단 수, 분단 가로, 분단 세로  
        int numOfDivision = 5;
        int numOfRows = 2;
        int numOfColumns = 6;
        
        
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
        
        // GUI 생성
        JFrame frame = new JFrame("Classroom Seating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우 닫기 버튼 클릭 시 프로그램 종료
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS)); // 분단을 수평으로 배치 
        
        Font font = new Font("", Font.BOLD, 15);

        JPanel classroomPanel = new JPanel();
        
        
//       각 Division에 대한 패널 생성
        for (Division division : classroom.getDivisionArrayList()) { // 분단을 하나씩 받음
            JPanel divisionPanel = new JPanel();
            divisionPanel.setLayout(new BoxLayout(divisionPanel, BoxLayout.X_AXIS)); // Division을 수평으로 배치         
            
            // 각 행 내의 좌석에 대한 라벨 생성
            for (int i = 0; i < numOfRows; i++) {
                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
                
                for (int j = 0; j < numOfColumns; j++) {
                    Seat seat = division.getDetailOfDivision()[i][j];
                    JLabel seatLabel = new JLabel();
                    if (seat.isOccupied()) {
                        seatLabel.setText(seat.getStudent().getName()); // 학생이 앉아있으면 학생이름을 라벨에 설정
                    } else {
                        seatLabel.setText("Empty"); // 없으면 empty
                    }
                    seatLabel.setHorizontalAlignment(JLabel.CENTER);// label에 적힌 이름을 가운데 정렬 
                    
                    seatLabel.setMinimumSize(new Dimension(100, 50));
                    seatLabel.setPreferredSize(new Dimension(100, 50));
                    seatLabel.setMaximumSize(new Dimension(100, 50));
                    
                    
                    seatLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK)); // 제일 작은 label 테두리 검정 
                    seatLabel.setFont(font);                // 폰트 설정
                    
                    rowPanel.add(seatLabel);
                    rowPanel.setBorder((BorderFactory.createEmptyBorder(0,5,0,5)));
                }
                divisionPanel.add(rowPanel);
                divisionPanel.setBorder(BorderFactory.createEmptyBorder(50,10,50,10)); // division panel의 상좌하우 padding 설정
                
            }
            classroomPanel.add(divisionPanel);
            
        }
        frame.add(classroomPanel);
        frame.pack();
        frame.setVisible(true);
        
//        for (Student student : students) {
//            System.out.println("Name: " + student.getName() + ", Gender: " + (student.isGender() ? "Male" : "Female"));
//        }
    }
}