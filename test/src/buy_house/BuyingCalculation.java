package buy_house;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.xiaoleilu.hutool.util.StrUtil;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 摇号买房概率计算
 *
 * @date: 2018\1\22 0022 9:52
 */
public class BuyingCalculation {

    /**
     * 第一天摇中选房总人数
     */
    private int i = 0;
    /**
     * 第二天摇中选房总人数
     */
    private int j = 0;
    /**
     * 第三天摇中选房总人数
     */
    private int k = 0;

    /**
     * 第一天资料复核过总人数
     */
    private int totalNum1;

    /**
     * 第二天资料复核过总人数
     */
    private int totalNum2;

    /**
     * 第三天资料复核过总人数
     */
    private int totalNum3;

    private String startNum_1;

    private String endNum_1;

    private String startNum_2;

    private String endNum_2;

    private String startNum_3;

    private String endNum_3;

    public int getTotalNum1() {
        return totalNum1;
    }

    public void setTotalNum1(int totalNum1) {
        this.totalNum1 = totalNum1;
    }

    public int getTotalNum2() {
        return totalNum2;
    }

    public void setTotalNum2(int totalNum2) {
        this.totalNum2 = totalNum2;
    }

    public int getTotalNum3() {
        return totalNum3;
    }

    public void setTotalNum3(int totalNum3) {
        this.totalNum3 = totalNum3;
    }

    public String getStartNum_1() {
        return startNum_1;
    }

    public void setStartNum_1(String startNum_1) {
        this.startNum_1 = startNum_1;
    }

    public String getEndNum_1() {
        return endNum_1;
    }

    public void setEndNum_1(String endNum_1) {
        this.endNum_1 = endNum_1;
    }

    public String getStartNum_2() {
        return startNum_2;
    }

    public void setStartNum_2(String startNum_2) {
        this.startNum_2 = startNum_2;
    }

    public String getEndNum_2() {
        return endNum_2;
    }

    public void setEndNum_2(String endNum_2) {
        this.endNum_2 = endNum_2;
    }

    public String getStartNum_3() {
        return startNum_3;
    }

    public void setStartNum_3(String startNum_3) {
        this.startNum_3 = startNum_3;
    }

    public String getEndNum_3() {
        return endNum_3;
    }

    public void setEndNum_3(String endNum_3) {
        this.endNum_3 = endNum_3;
    }

    public void readPdfFile(String pdfPath) {
        PdfReader reader = null;

        try {
            reader = new PdfReader(pdfPath);
            // 获得页数
            int num = reader.getNumberOfPages();
            System.out.println("Total Page: " + num);

            // 存放读取出的文档内容
            String content = "";

            for (int i = 1; i <= num; i++) {
                content += "\n";
                content += PdfTextExtractor.getTextFromPage(reader, i);
            }

            String[] arr = content.split("\n");

            for (int i = 0; i < arr.length; i++) {
                String temp = arr[i].trim();
                String[] tempArr = temp.split(" ");
                String s = "";
                if (tempArr.length > 2) {
                    s = tempArr[1] + " " + tempArr[2];
                } else {
                    s = arr[i];
                }
                System.out.println(s);
                countNumber(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param str: 选房号 + 摇号编号组成的字符串
     *             计算选房结果出现在报名三天时间内的个数
     * @date: 2018\1\22 0022 11:48
     */
    private void countNumber(String str) {
        if (StrUtil.isNotEmpty(str)) {
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(str);
            if (!m.find()) {
                String[] arr = str.split(" ");
                int flag = Integer.valueOf(arr[0]);
                int num = Integer.valueOf(arr[1].replace("B", ""));
                if (flag <= 404) {
                    if (num >= 0001 && num <= totalNum1) {
                        i += 1;
                    } else if (num >= totalNum1 && num <= totalNum2) {
                        j += 1;
                    } else if (num >= totalNum2 && num <= totalNum3) {
                        k += 1;
                    } else {

                    }
                }
            }
        }
    }

    public void calculateProbability(int num) {
        System.out.println("第一天报名被选中的有" + i + "人，选中概率为" + ((double) i / num));
        System.out.println("第二天报名被选中的有" + j + "人，选中概率为" + ((double) j / num));
        System.out.println("第三天报名被选中的有" + k + "人，选中概率为" + ((double) k / num));
    }

}
