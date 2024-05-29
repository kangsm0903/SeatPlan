package JavaProject;

import java.util.ArrayList;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {

    	String filePath = "input.txt"; // 파일 경로를 여기에 입력하세요
        ArrayList<Student> students = new ArrayList<>();
        int numOfStudent=0; // 학생 수 
        
        // GUI에서 입력받을 값들 - 임의로 지정 
        // 분단 수, 분단 가로, 분단 세로  
        int numOfDivision = 3;
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
        frame.setSize(800, 600);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS)); 
        
        frame.add(Box.createVerticalGlue());

        
        
//       각 Division에 대한 패널 생성
        for (Division division : classroom.getDivisionArrayList()) { // 분단을 하나씩 받음
            JPanel divisionPanel = new JPanel();
            divisionPanel.setLayout(new BoxLayout(divisionPanel, BoxLayout.X_AXIS)); // Division을 수평으로 배치         
            
            // 각 행 내의 좌석에 대한 라벨 생성
            for (int i = 0; i < numOfRows; i++) {
                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
                
                rowPanel.add(Box.createHorizontalGlue()); // 좌우 공간 추
                
                for (int j = 0; j < numOfColumns; j++) {
                    Seat seat = division.getDetailOfDivision()[i][j];
                    JLabel seatLabel = new JLabel();
                    if (seat.isOccupied()) {
                        seatLabel.setText(seat.getStudent().getName()); // 학생이 앉아있으면 학생이름을 라벨에 설정
                        seatLabel.setHorizontalAlignment(JLabel.CENTER);// text 가운데 정
                    } else {
                        seatLabel.setText("Empty"); // 없으면 empty
                    }
                    seatLabel.setHorizontalAlignment(JLabel.CENTER);// text 가운데 정
                    seatLabel.setMinimumSize(new Dimension(75, 75));
                    seatLabel.setPreferredSize(new Dimension(75, 75));
                    seatLabel.setMaximumSize(new Dimension(75, 75));
                    seatLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    seatLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
                                       
                    rowPanel.add(seatLabel);
                }
                divisionPanel.add(rowPanel);
                divisionPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                
            }
            frame.add(divisionPanel);
            frame.add(Box.createVerticalGlue());
            
        }

        frame.setVisible(true);
        
//        for (Student student : students) {
//            System.out.println("Name: " + student.getName() + ", Gender: " + (student.isGender() ? "Male" : "Female"));
//        }
    }
}



//package JavaProject;
//
//import java.util.ArrayList;
//import java.util.Random;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import javax.swing.*;
//import java.awt.*;
//
//public class Main {
//    public static void main(String[] args) {
//
//        String filePath = "input.txt"; // 파일 경로를 여기에 입력하세요
//        ArrayList<Student> students = new ArrayList<>();
//        int numOfStudent = 0; // 학생 수 
//
//        // GUI에서 입력받을 값들 - 임의로 지정 
//        // 분단 수, 분단 가로, 분단 세로  
//        int numOfDivision = 3;
//        int numOfRows = 2;
//        int numOfColumns = 6;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(" ");  // 띄어쓰기 기준으로 분할 
//                if (parts.length == 2) {
//                    String name = parts[0];
//                    boolean gender = parts[1].equals("남"); // 1 == 남, 0 == 여
//                    students.add(new Student(gender, name)); // 학생 배열에 학생들 추가 
//                    numOfStudent++;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (numOfStudent > numOfDivision * numOfRows * numOfColumns) {
//            // 입력한 자릿수보다 학생들의 수가 더 많을 때 사용자 정의 에러 반환 및 종료
//            System.exit(0);
//        } 
//
//        // classroom(총 학생, 분단 수, 분단 가로, 분단 세로, 학생정보)
//        // 자리 설정 
//        Classroom classroom = new Classroom(numOfStudent, numOfDivision, numOfRows, numOfColumns);
//
//        // 랜덤 배치 
//        RandomPosition random = new RandomPosition(classroom.getDivisionArrayList(), students, numOfDivision, numOfRows, numOfColumns);
//
//        // GUI 생성
//        JFrame frame = new JFrame("Classroom Seating");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//        frame.setLayout(new GridBagLayout()); // 프레임 레이아웃을 grid로 설정
//        GridBagConstraints gbc = new GridBagConstraints(); // grid 객체 생성
//        gbc.insets = new Insets(20, 20, 20, 20); 	// 컴포넌트 간의 간격 설정
//        gbc.anchor = GridBagConstraints.CENTER; 	// 컴포넌트 중앙에 정
//
//        for (Division division : classroom.getDivisionArrayList()) {
//            JPanel divisionPanel = new JPanel();
//            divisionPanel.setLayout(new GridBagLayout());
//            GridBagConstraints divGbc = new GridBagConstraints();
//            divGbc.insets = new Insets(2, 2, 2, 2);
//            divGbc.fill = GridBagConstraints.BOTH;
//
//            for (int i = 0; i < numOfRows; i++) {
//                for (int j = 0; j < numOfColumns; j++) {
//                    Seat seat = division.getDetailOfDivision()[i][j];
//                    JLabel seatLabel = new JLabel();
//                    if (seat.isOccupied()) {
//                        seatLabel.setText(seat.getStudent().getName());
//                    } else {
//                        seatLabel.setText("Empty");
//                    }
//                    seatLabel.setHorizontalAlignment(SwingConstants.CENTER);
//                    seatLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
//                    divGbc.gridx = i;
//                    divGbc.gridy = j;
//                    divisionPanel.add(seatLabel, divGbc);
//                }
//            }
//
//            gbc.gridx = 0;
//            gbc.gridy = GridBagConstraints.RELATIVE;
//            frame.add(divisionPanel, gbc);
//        }
//
//        frame.setVisible(true);
//    }
//}
//
//
//
//
//
//
//
