import buy_house.BuyingCalculation;
import junit.framework.TestCase;
import mail.Mail;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

/**
 * Created by Administrator on 2017\11\28 0028.
 */
public class JobTest extends TestCase {
    private volatile static int count = 0;

    @Test
    public void testSql() throws Exception {
        int[] i = {1, 2, 3, 4, 5};
        int result = Arrays.binarySearch(i, 10);
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
    public void testBeiHu() throws Exception {
        String filePath = "C:\\Users\\Administrator\\Desktop\\摇号结果\\北湖国际城普通选房摇号结果.pdf";
        BuyingCalculation buyingCalculation = new BuyingCalculation();
        buyingCalculation.setPdfFilePath(filePath);
        buyingCalculation.setStartNum_1("B0001");
        buyingCalculation.setEndNum_1("B1671");
        buyingCalculation.setStartNum_2("B1672");
        buyingCalculation.setEndNum_2("B2521");
        buyingCalculation.setStartNum_3("B2522");
        buyingCalculation.setEndNum_3("B3621");

        buyingCalculation.setEndNum1(1671);
        buyingCalculation.setEndNum2(2521);
        buyingCalculation.setEndNum3(3621);

        buyingCalculation.setTotalHouseNumber(404);
        buyingCalculation.readPdfFile();

        /**
         * 北湖摇号购房结果
         */
        buyingCalculation.calculateProbability();
    }

    @Test
    public void testLvDiXinLiCheng() throws Exception {
        String filePath = "C:\\Users\\Administrator\\Desktop\\摇号结果\\18年1月19日绿地新里城项目普通登记购房人摇号结果.pdf";
        BuyingCalculation buyingCalculation = new BuyingCalculation();
        buyingCalculation.setPdfFilePath(filePath);
        buyingCalculation.setStartNum_1("B0001");
        buyingCalculation.setEndNum_1("B5681");
        buyingCalculation.setStartNum_2("B5682");
        buyingCalculation.setEndNum_2("B7535");
        buyingCalculation.setStartNum_3("B7536");
        buyingCalculation.setEndNum_3("B8995");

        buyingCalculation.setEndNum1(5681);
        buyingCalculation.setEndNum2(7535);
        buyingCalculation.setEndNum3(8995);

        buyingCalculation.setTotalHouseNumber(724);

        buyingCalculation.readPdfFile();
        /**
         * 绿地新里城摇号购房结果
         */
        buyingCalculation.calculateProbability();
    }
}
