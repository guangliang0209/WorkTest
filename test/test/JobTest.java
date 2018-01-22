import junit.framework.TestCase;
import mail.Mail;

import java.util.Arrays;
import java.util.Date;

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

    @Test
    public void testSendMail() throws Exception {
        String email = "fengguangliang@comsys.net.cn";
        Mail mail = new Mail();
        mail.setRecipient(email);
        mail.setSubject("title");
        mail.setDate(new Date());
        mail.setFrom("rabbit");
        mail.setContent("sdbsdgbdsfgbsfgbsdfb", "text/html; charset=utf-8");
        mail.sendMessage();
    }

    @Test
    public void testBuyingCalculation() throws Exception {
        String filePath = "C:\\Users\\Administrator\\Desktop\\北湖国际城普通选房摇号结果.pdf";
        BuyingCalculation buyingCalculation = new BuyingCalculation();
        buyingCalculation.readPdfFile(filePath);
    }
}
