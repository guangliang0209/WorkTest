package buy_house;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.exceptions.UnsupportedPdfException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Created by Administrator on 2018\1\22 0022.
 */
public class ExtractPicFromPdf {
    public static void main(String[] args) {
        String path = "C:\\Users\\Administrator\\Desktop\\绿地新里城选房顺序摇号结果.pdf";
        extractImage(path);
    }

    /**
     * @param pdf PDF文件路径
     * @param txt 输出文本文件路径
     * @throws IOException
     */
    public static void parsePdf(String pdf, String txt) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        Rectangle rect = new Rectangle(100, 100, 1000, 10000);
        RenderFilter filter = new RegionTextRenderFilter(rect);
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
            out.println(PdfTextExtractor.getTextFromPage(reader, i, strategy));
        }
        out.flush();
        out.close();
        reader.close();
    }

    /**
     * @param filePath: 文件路径
     *                  读取PDF中的图片，方法有问题，导出的图片有些打不开
     * @date: 2018\1\22 0022 14:50
     */
    public static void extractImage(String filePath) {

        PdfReader reader = null;
        try {
            //读取pdf文件
            reader = new PdfReader(filePath);
            //获得pdf文件的页数
            int sumPage = reader.getNumberOfPages();
            //读取pdf文件中的每一页
            for (int i = 1; i <= sumPage; i++) {
                //得到pdf每一页的字典对象
                PdfDictionary dictionary = reader.getPageN(i);
                //通过RESOURCES得到对应的字典对象
                PdfDictionary res = (PdfDictionary) PdfReader.getPdfObject(dictionary.get(PdfName.RESOURCES));
                //得到XOBJECT图片对象
                PdfDictionary xobj = (PdfDictionary) PdfReader.getPdfObject(res.get(PdfName.XOBJECT));
                if (xobj != null) {
                    Iterator it = xobj.getKeys().iterator();
                    while (it.hasNext()) {
                        PdfObject obj = xobj.get((PdfName) it.next());
                        if (obj.isIndirect()) {
                            PdfDictionary tg = (PdfDictionary) PdfReader.getPdfObject(obj);
                            PdfName type = (PdfName) PdfReader.getPdfObject(tg.get(PdfName.SUBTYPE));
                            if (PdfName.IMAGE.equals(type)) {
                                PdfObject object = reader.getPdfObject(obj);
                                if (object.isStream()) {
                                    PRStream prstream = (PRStream) object;
                                    byte[] b;
                                    try {
                                        b = reader.getStreamBytes(prstream);
                                    } catch (UnsupportedPdfException e) {
                                        b = reader.getStreamBytesRaw(prstream);
                                    }

                                    String outputPath = String.format("C:\\Users\\Administrator\\Desktop\\pdf\\pic%d.jpg", i);
                                    FileOutputStream output = new FileOutputStream(outputPath);
                                    output.write(b);
                                    output.flush();
                                    output.close();
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}