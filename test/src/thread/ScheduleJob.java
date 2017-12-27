package thread;

import java.util.Date;
import java.util.Random;

/**
 * Created by fengguangliang on 2017/6/5.
 */
public class ScheduleJob implements Runnable {
    @Override
    public void run() {
        try {
            Integer number = getRandomNumber(1, 3);
            Long millis = Long.valueOf(number * 1000);
            printWord(number);
            Thread.sleep(millis);
            number = null;
            millis = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void printWord(int number) {
        System.out.println("second = " + number);
    }

    /**
     * @param min:最小值
     * @param max:最大值
     * @return 随机数
     * @author : fengguangliang
     * @description : 生成min到max之间随机数
     * @date : 2017/6/5
     */
    private static Integer getRandomNumber(int min, int max) {
        Random random = new Random();
        Integer r = random.nextInt(max - min + 1) + min;
        return r;
    }


    public static void main(String[] args) {
        while (true) {
            int i = getRandomNumber(2, 5);
            System.out.println(i);
        }
    }

}
