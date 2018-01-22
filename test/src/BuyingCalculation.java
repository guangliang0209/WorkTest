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
            int num = reader.getNumberOfPages();// 获得页数

            System.out.println("Total Page: " + num);

            String content = ""; // 存放读取出的文档内容

            for (int i = 1; i <= num; i++) {
                content += "\n";
                content += PdfTextExtractor.getTextFromPage(reader, i);
            }

            String[] arr = content.split("/n");

            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
