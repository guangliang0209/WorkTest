package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fengguangliang on 2017/6/5.
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);

        try {
            while (true) {
                ScheduleJob scheduleJob = new ScheduleJob();
                fixedThreadPool.execute(scheduleJob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
