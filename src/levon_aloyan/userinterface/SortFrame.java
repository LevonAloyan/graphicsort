package levon_aloyan.userinterface;

import javax.swing.*;
import java.awt.*;

public class SortFrame extends JFrame {

    SortCanvas canvas;
    JPanel controlpanel;
    JButton startSorting;
    JEditorPane count;
    JEditorPane size;
    JLabel countLabel;
    JLabel sizeLabel;

    public SortFrame() {
        super("Sorting algorithms");
        canvas = new SortCanvas();
        controlpanel = new JPanel();
        startSorting = new JButton("Start sorting");
        count = new JEditorPane();
        size = new JEditorPane();

        countLabel = new JLabel("Sorting count");
        sizeLabel = new JLabel("Size count");
        controlpanel.add(countLabel);
        controlpanel.add(count);
        controlpanel.add(sizeLabel);
        controlpanel.add(size);
        controlpanel.add(startSorting);

        add(controlpanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        setSize(800, 600);
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        startSorting.addActionListener(e -> startSortingActionPerfored());

    }

    private int getSortingCount() {
        String c = count.getText();
        if (c.equals("")) {

            return -1;
        }
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) > '9' || c.charAt(i) < '0') {
                JOptionPane.showMessageDialog(this, "Please enter valid count number");
                return -1;
            }

        }
        return Integer.parseInt(count.getText());
    }

    private int getArraySize() {
        String s = size.getText();
        if (s.equals("")) {

            return -1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {
                JOptionPane.showMessageDialog(this, "Please enter valid size number");
                return -1;
            }

        }
        return Integer.parseInt(size.getText());

    }

    private void startSortingActionPerfored() {
        canvas.clean();

        canvas.start(getSortingCount(), getArraySize());
    }

    public static void main(String[] args) {
        new SortFrame();
    }
}
