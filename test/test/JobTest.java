import junit.framework.TestCase;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Administrator on 2017\11\28 0028.
 */
public class JobTest extends TestCase{
    private volatile static int count = 0;

    @Test
    public void testSql() throws Exception {
        int[] i = {1,2,3,4,5};
        int result =   Arrays.binarySearch(i, 10);
        System.out.println(result);
    }

    @Test
    public void testThread() throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            for (int j = 0; j < 100; j++) {
                                count++;
                            }
                        }
                    }
            ).start();
        }

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count = " + count);
    }
}
