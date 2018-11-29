package graphicsort.stopWatch;

/**
 * Created by levon on 6/11/16.
 */
public class StopWatch {
    boolean isRunning;
    private long startTime;
    private long elapsedTime;


    public StopWatch() {
        reset();
    }

    public void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        if(isRunning){
            elapsedTime=elapsedTime+System.currentTimeMillis()-startTime;
            isRunning=false;
        }
    }

    public long getElapsedTime() {
        if(!isRunning){
            return elapsedTime;
        }
        return elapsedTime+System.currentTimeMillis()-startTime;
    }

    public void reset() {
        this.elapsedTime=0;
        isRunning=false;
    }

}
