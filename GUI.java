package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GUI {
	private JFrame frame;
    private JPanel classroomPanel;
    private Font font;
    private Map<Seat, JLabel> seatLabelMap;
    private int numOfRows;
    private int numOfColumns;
    private ArrayList<Division> divisions;
    
    public GUI(ArrayList<Division> divisions, int numOfRows, int numOfColumns) {
    	this.divisions = divisions;
    	this.numOfColumns = numOfColumns;
    	this.numOfRows = numOfRows;
    	seatLabelMap = new HashMap<>();
    	 
    	
        // JFrame 생성
        frame = new JFrame("Classroom Seating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우 닫기 버튼 클릭 시 프로그램 종료
        frame.setLayout(new BorderLayout()); // BorderLayout으로 변경

        font = new Font("", Font.BOLD, 15);
        classroomPanel = new JPanel();

        // 각 Division에 대한 패널 생성
        for (Division division : divisions) { // 분단을 하나씩 받음
            JPanel divisionPanel = new JPanel();
            divisionPanel.setLayout(new BoxLayout(divisionPanel, BoxLayout.X_AXIS)); // Division을 수평으로 배치

            // 각 행 내의 좌석에 대한 라벨 생성
            for (int i = 0; i < numOfRows; i++) {
                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));

                for (int j = 0; j < numOfColumns; j++) {
                    Seat seat = division.getDetailOfDivision()[i][j];
                    JLabel seatLabel = new JLabel();
                    seatLabel.setHorizontalAlignment(JLabel.CENTER); // label에 적힌 이름을 가운데 정렬

                    seatLabel.setMinimumSize(new Dimension(100, 50));
                    seatLabel.setPreferredSize(new Dimension(100, 50));
                    seatLabel.setMaximumSize(new Dimension(100, 50));

                    seatLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 제일 작은 label 테두리 검정
                    seatLabel.setFont(font); // 폰트 설정
                    
                    
                    seatLabelMap.put(seat, seatLabel); // seat, seatLabel을 Map에 저장 
                    

                    rowPanel.add(seatLabel);
                    rowPanel.setBorder((BorderFactory.createEmptyBorder(0, 5, 0, 5)));
                }
                divisionPanel.add(rowPanel);
                divisionPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10)); // division panel의 상좌하우 padding 설정
            }
            classroomPanel.add(divisionPanel);
        }
        
        // 버튼 생성
        JButton updateButton = new JButton("자리 배치");
        updateButton.setMinimumSize(new Dimension(50,50));
        updateButton.setPreferredSize(new Dimension(50, 50));
        updateButton.setMaximumSize(new Dimension(50, 50));
        updateButton.setFont(font);

        // 버튼 클릭 이벤트 처리
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAllSeatLabels();
            }
        });
        
        frame.add(classroomPanel, BorderLayout.CENTER);
        frame.add(updateButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public void updateSeatLabel(Seat seat) { // 넘겨받은 seat에 하나씩 배치해놓음 
        JLabel seatLabel = seatLabelMap.get(seat);
        if (seatLabel != null) {
            if (seat.isOccupied()) {
                seatLabel.setText(seat.getStudent().getName()); // 학생이 앉아있으면 학생이름을 라벨에 설정
            } else {
                seatLabel.setText("Empty"); // 없으면 empty
            }
        }
    }
    
    public void updateAllSeatLabels() { // division에서 seat를 하나씩 받아서 updateSeatLabel에 넘겨줌 
        for (Division division : divisions) {
            for (int i = 0; i < numOfRows; i++) {
                for (int j = 0; j < numOfColumns; j++) {
                    updateSeatLabel(division.getDetailOfDivision()[i][j]);
                }
            }
        }
    }
}