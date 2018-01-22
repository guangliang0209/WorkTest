import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;


/**
 * 摇号买房概率计算
 *
 * @date: 2018\1\22 0022 9:52
 */
public class BuyingCalculation {

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
                if (tempArr.length > 2) {
                    System.out.println(tempArr[1] + " " + tempArr[2]);
                } else {
                    System.out.println(arr[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
