package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUI {
    private JFrame frame;
    private CustomPanel classroomPanel;
    
//    private JPanel classroomPanel;
    private Font font;
    private Map<Seat, JLabel> seatLabelMap;
    private int numOfRows;
    private int numOfColumns;
    private ArrayList<Division> divisions;
    private ArrayList<Student> students;
    private Classroom myClassroom;

    public GUI(Classroom myClassroom, ArrayList<Student> students, int numOfRows, int numOfColumns) {
        this.divisions = myClassroom.getDivisions();
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.myClassroom = myClassroom;
        this.students = students;
        seatLabelMap = new HashMap<>();

    }
    public void start(Main.seatVer seatVer){
        // JFrame 생성
        frame = new JFrame("Classroom Seating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우 닫기 버튼 클릭 시 프로그램 종료
        frame.setLayout(new BorderLayout()); // BorderLayout으로 변경

        font = new Font("", Font.BOLD, 15);
        classroomPanel = new CustomPanel("classroom.jpg"); // 배경 이미지 파일 경로

        // 각 Division에 대한 패널 생성
        for (Division division : divisions) { // 분단을 하나씩 받음
            JPanel divisionPanel = new JPanel();
            divisionPanel.setLayout(new BoxLayout(divisionPanel, BoxLayout.X_AXIS)); // Division을 수평으로 배치
            divisionPanel.setOpaque(false);
            divisionPanel.setBorder(BorderFactory.createLineBorder(null, 2, true));

            // 각 행 내의 좌석에 대한 라벨 생성
            for (int i = 0; i < numOfColumns; i++) {
                BlurPanel rowPanel = new BlurPanel();
                rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
                rowPanel.setOpaque(false);

                rowPanel.setBackground(new Color(255, 255, 255, 255));

                for (int j = 0; j < numOfRows; j++) {
                    Seat seat = division.getSeats()[j][i];
                    JLabel seatLabel = new JLabel();
                    seatLabel.setHorizontalAlignment(JLabel.CENTER); // label에 적힌 이름을 가운데 정렬

                    seatLabel.setMinimumSize(new Dimension(100, 50));
                    seatLabel.setPreferredSize(new Dimension(100, 50));
                    seatLabel.setMaximumSize(new Dimension(100, 50));

                    seatLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 제일 작은 label 테두리 검정
                    seatLabel.setFont(font); // 폰트 설정


                    seatLabelMap.put(seat, seatLabel); // seat, seatLabel을 Map에 저장


                    rowPanel.add(seatLabel);
//                    rowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, null, true));
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
                updateAllSeatLabels(seatVer);
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
                seatLabel.setText(seat.getMyStudent().getMyName()); // 학생이 앉아있으면 학생이름을 라벨에 설정
            } else {
                seatLabel.setText("Empty"); // 없으면 empty
            }
        }
    }

    public void updateAllSeatLabels(Main.seatVer seatVer) { // division에서 seat를 하나씩 받아서 updateSeatLabel에 넘겨줌
        switch (seatVer) {
            case MIXED: myClassroom.seatRandomlyMixedVer(students); break;
            case NORMAL: myClassroom.seatRandomly(students); break;
            default:
        }
        divisions = myClassroom.getDivisions();
        for (Division division : divisions) {
            for (int i = 0; i < numOfRows; i++) {
                for (int j = 0; j < numOfColumns; j++) {
                    updateSeatLabel(division.getSeats()[i][j]);
                }
            }
        }
    }
    
    // Custom JPanel to display background image
    class CustomPanel extends JPanel {
        private Image backgroundImage;

        public CustomPanel(String fileName) {
            try {
                backgroundImage = new ImageIcon(fileName).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }   
    
 // Custom JPanel to create blur effect
    class BlurPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 투명한 블러 이미지 그리기
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            float opacity = 0.5f; // 블러 투명도 설정 (0.0f에서 1.0f까지)
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
    }
    
}