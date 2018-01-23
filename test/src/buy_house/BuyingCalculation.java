package buy_house;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.xiaoleilu.hutool.util.StrUtil;

import java.io.File;
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
     * pdf文件路径
     */
    private String pdfFilePath;

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
     * 第一天资料复核通过总人数
     */
    private int endNum1;

    /**
     * 第二天资料复核通过总人数
     */
    private int endNum2;

    /**
     * 第三天资料复核通过总人数
     */
    private int endNum3;

    /**
     * 除去去棚改，普通选房共有多少套
     */
    private int totalHouseNumber;


    public String getPdfFilePath() {
        return pdfFilePath;
    }

    public void setPdfFilePath(String pdfFilePath) {
        this.pdfFilePath = pdfFilePath;
    }

    public int getTotalHouseNumber() {
        return totalHouseNumber;
    }

    public void setTotalHouseNumber(int totalHouseNumber) {
        this.totalHouseNumber = totalHouseNumber;
    }

    private String startNum_1;

    private String endNum_1;

    private String startNum_2;

    private String endNum_2;

    private String startNum_3;

    private String endNum_3;

    public int getEndNum1() {
        return endNum1;
    }

    public void setEndNum1(int endNum1) {
        this.endNum1 = endNum1;
    }

    public int getEndNum2() {
        return endNum2;
    }

    public void setEndNum2(int endNum2) {
        this.endNum2 = endNum2;
    }

    public int getEndNum3() {
        return endNum3;
    }

    public void setEndNum3(int endNum3) {
        this.endNum3 = endNum3;
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

    /**
     * 文件名
     */
    private String fileName;


    /**
     * 读取摇号结果pdf文件
     *
     * @date: 2018\1\22 0022 16:41
     */
    public void readPdfFile() {
        PdfReader reader = null;
        File file = null;

        try {
            reader = new PdfReader(pdfFilePath);
            file = new File(pdfFilePath);
            this.fileName = file.getName().replaceAll(".pdf", "");

            // 获得页数
            int num = reader.getNumberOfPages();
            System.out.println("文件一共: " + num + "页");

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
                int flag = Integer.valueOf(arr[0]); // 选房顺序号
                int num = Integer.valueOf(arr[1].replace("B", "")); // 公正摇号编号
                if (flag <= totalHouseNumber) {
                    if (num >= 0001 && num <= endNum1) {
                        i += 1;
                    } else if (num > endNum1 && num <= endNum2) {
                        j += 1;
                    } else if (num > endNum2 && num <= endNum3) {
                        k += 1;
                    } else {

                    }
                }
            }
        }
    }

    public void calculateProbability() {
        System.out.println(this.fileName + "楼盘共" + this.totalHouseNumber + "套房");
        System.out.println("第一天报名资料复核通过共" + (this.endNum1) + "人" + "，被选中的有" + i + "人");
        System.out.println("第二天报名资料复核通过共" + (this.endNum2 - this.endNum1) + "人" + "，被选中的有" + j + "人");
        System.out.println("第三天报名资料复核通过共" + (this.endNum3 - this.endNum2) + "人" + "，被选中的有" + k + "人");
        System.out.println("第一天概率为：" + ((double) i / this.endNum1));
        System.out.println("第二天概率为：" + ((double) j / (this.endNum2 - this.endNum1)));
        System.out.println("第三天概率为：" + ((double) k / (this.endNum3 - this.endNum2)));
    }

}
