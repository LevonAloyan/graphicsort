package levon_aloyan.userinterface;

import figure.Rectangle;
import graphicsort.stopWatch.PerformanceTester;

import javax.swing.*;
import java.awt.*;

public class SortCanvas extends JPanel implements Runnable {
    private boolean isFirst = true;

    private double conditionalUnit;
    private Rectangle quick;
    private Rectangle merge;
    private Rectangle bubble;
    private Rectangle selection;
    private double mergeSum = 0;
    private double selectionSum = 0;
    private double bubbleSum = 0;
    private double quickSum = 0;
    Thread t;
    private int fontSize = PerformanceTester.SortMode.BUBBLE.getFontSize();

    public SortCanvas() {
        setBackground(Color.LIGHT_GRAY);
    }

    public void start(int count, int size) {
        if (count < 0 && size < 0) {
            JOptionPane.showMessageDialog(this, "Please fill required fields ");
            return;
        }
        if (count < 0) {
            JOptionPane.showMessageDialog(this, "Please input sorting count");
            return;
        } else if (size < 0) {
            JOptionPane.showMessageDialog(this, "Please input array size");
        }

        PerformanceTester.calculateAverages(count, size);
        calculateConditionalunit();
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            increaseDiagrams(bubble, getStopPoint(PerformanceTester.SortMode.BUBBLE));
            increaseDiagrams(selection, getStopPoint(PerformanceTester.SortMode.SELECTION));
            increaseDiagrams(quick, getStopPoint(PerformanceTester.SortMode.QUICK));
            increaseDiagrams(merge, getStopPoint(PerformanceTester.SortMode.MERGE));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double getStopPoint(PerformanceTester.SortMode mode) {
        switch (mode) {
            case BUBBLE:
                bubbleSum = PerformanceTester.getBubbleSum();
                return bubble.getY() - (conditionalUnit * bubbleSum);
            case SELECTION:
                selectionSum = PerformanceTester.getSelectionSum();
                return selection.getY() - (conditionalUnit * selectionSum);
            case QUICK:
                quickSum = PerformanceTester.getQuickSum();
                return quick.getY() - (conditionalUnit * quickSum);
            case MERGE:
                mergeSum = PerformanceTester.getMergeSum();
                return merge.getY() - (conditionalUnit * mergeSum);

            default:
                return 0;
        }
    }

    private void calculateConditionalunit() {
        double bubbleSum = PerformanceTester.getBubbleSum();
        double quickSum = PerformanceTester.getQuickSum();
        double max = Math.max(bubbleSum, quickSum);
        conditionalUnit = (getHeight() - 200) / max;
    }

    private void increaseDiagrams(Rectangle rectangle, double stopPoint) throws InterruptedException {
        while (rectangle.getY() > stopPoint) {

            update(getGraphics());
            rectangle.setY(rectangle.getY() - 1);
            rectangle.setHeight(rectangle.getHeight() + 1);
            Thread.sleep(5);

        }
        Thread.sleep(20);
    }

    public void clean() {
        PerformanceTester.resetSums();
        quick.setHeight(2);
        quick.setY(getHeight() - fontSize - 5);
        quickSum = 0;
        selection.setHeight(2);
        selection.setY(getHeight() - fontSize - 5);
        selectionSum = 0;
        bubble.setHeight(2);
        bubble.setY(getHeight() - fontSize - 5);
        bubbleSum = 0;
        merge.setHeight(2);
        merge.setY(getHeight() - fontSize - 5);
        mergeSum = 0;
    }

    private void initFigures() {
        int width = 60;
        int height = 2;
        int interval;
        interval = (getWidth() - 4 * width) / 5;

        bubble = new Rectangle(interval, getHeight() - fontSize - 5, width, height, this, new Color(0x5C88C4));
        setModeXAndY(bubble, PerformanceTester.SortMode.BUBBLE);

        selection = new Rectangle(bubble.getX() + width + interval, getHeight() - fontSize - 5, width, height, this, new Color(0x5C88C4));
        setModeXAndY(selection, PerformanceTester.SortMode.SELECTION);

        quick = new Rectangle(selection.getX() + width + interval, getHeight() - fontSize - 5, width, height, this, new Color(0x5C88C4));
        setModeXAndY(quick, PerformanceTester.SortMode.QUICK);

        merge = new Rectangle(quick.getX() + width + interval, getHeight() - fontSize - 5, width, height, this, new Color(0x5C88C4));
        setModeXAndY(merge, PerformanceTester.SortMode.MERGE);
    }

    private void setModeXAndY(Rectangle rectangle, PerformanceTester.SortMode currentMode) {
        currentMode.setY(rectangle.getY() + rectangle.getHeight());
        currentMode.setX(rectangle.getX() + rectangle.getWidth() + 2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isFirst) {
            initFigures();
            isFirst = false;
        }
        g.setFont(new Font("Italic", Font.ROMAN_BASELINE, PerformanceTester.SortMode.QUICK.getFontSize()));
        bubble.draw(g);
        g.drawString(Double.toString(bubbleSum), bubble.getX(), bubble.getY());
        PerformanceTester.SortMode.BUBBLE.draw(g);
        g.drawString(Double.toString(quickSum), quick.getX(), quick.getY());
        quick.draw(g);
        PerformanceTester.SortMode.QUICK.draw(g);
        g.drawString(Double.toString(selectionSum), selection.getX(), selection.getY());
        selection.draw(g);
        PerformanceTester.SortMode.SELECTION.draw(g);
        g.drawString(Double.toString(mergeSum), merge.getX(), merge.getY());
        merge.draw(g);
        PerformanceTester.SortMode.MERGE.draw(g);
    }
}
