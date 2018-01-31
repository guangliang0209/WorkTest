package PrintSrc.com.szallcom.tools;

import PrintSrc.wf.common.SystemProperties;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Properties;
import java.awt.print.*;

import javax.print.*;
import javax.print.attribute.*;

import java.io.*;

public class PrintTest extends JFrame
        implements ActionListener, Printable {
    private JButton printTextButton = new JButton("Print Text");
    private JButton previewButton = new JButton("Print Preview");
    private JButton printText2Button = new JButton("Print Text2");
    private JButton printFileButton = new JButton("Print File");
    private JButton printFrameButton = new JButton("Print Frame");
    private JButton exitButton = new JButton("Exit");
    private JLabel tipLabel = new JLabel("");
    private JTextArea area = new JTextArea();
    private JScrollPane scroll = new JScrollPane(area);
    private JPanel buttonPanel = new JPanel();

    private int PAGES = 0;
    private String printStr;

    public PrintTest() {
        this.setTitle("Print Test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds((int) ((SystemProperties.SCREEN_WIDTH - 800) / 2), (int) ((SystemProperties.SCREEN_HEIGHT - 600) / 2), 800, 600);
        initLayout();
    }

    private void initLayout() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(scroll, BorderLayout.CENTER);
        printTextButton.setMnemonic('P');
        printTextButton.addActionListener(this);
        buttonPanel.add(printTextButton);
        previewButton.setMnemonic('v');
        previewButton.addActionListener(this);
        buttonPanel.add(previewButton);
        printText2Button.setMnemonic('e');
        printText2Button.addActionListener(this);
        buttonPanel.add(printText2Button);
        printFileButton.setMnemonic('i');
        printFileButton.addActionListener(this);
        buttonPanel.add(printFileButton);
        printFrameButton.setMnemonic('F');
        printFrameButton.addActionListener(this);
        buttonPanel.add(printFrameButton);
        exitButton.setMnemonic('x');
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == printTextButton)
            printTextAction();
        else if (src == previewButton)
            previewAction();
        else if (src == printText2Button)
            printText2Action();
        else if (src == printFileButton)
            printFileAction();
        else if (src == printFrameButton)
            printFrameAction();
        else if (src == exitButton)
            exitApp();
    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        /*Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(Color.black);
	    if (page >= PAGES)
	        return Printable.NO_SUCH_PAGE;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        drawCurrentPageText(g2, pf, page);
        return Printable.PAGE_EXISTS;*/

        // 转换成Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        // 设置打印颜色为黑色
        g2.setColor(Color.black);

        // 打印起点坐标
        double x = pf.getImageableX();// 返回与此 PageFormat 相关的 Paper
        // 对象的可成像区域左上方点的x 坐标。
        double y = pf.getImageableY();
        switch (page) {
            case 0:
                // 新建一个打印字体样式（1.字体名称，2.样式(加粗).3字号）
                Font font = new Font("微软雅黑", Font.PLAIN, 14);
                // 给g2设置字体
                g2.setFont(font);
                // 保存虚线的宽度
                float[] dash1 = {2.0f};
                // 设置打印线的属性。1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
                g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));
                float fontHeigth1 = font.getSize2D();
                // 标题
                String str1 = "江南大学2018年\n艺术类考试合格证";
                g2.drawString(str1, (int) x + 120, (int) y + fontHeigth1 + 60);
                // 新建一个字体样式
                Font font2 = new Font("微软雅黑", Font.PLAIN, 12);
                g2.setFont(font2);// 设置字体
                g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));
                float fontHeigth2 = (int) font.getSize2D();
                int tempHeight = (int) (fontHeigth1 * 2 + 50);
                String zkzh = "准考证号：sby001";
                g2.drawString(zkzh, (int) x + 80, (int) y + fontHeigth2 + 10 + tempHeight);
                String xm =   "姓    名：孙彬焱";
                g2.drawString(xm, (int) x + 80, (int) y + fontHeigth2 * 2 + 15 + tempHeight);
                String xb =   "性    别 ：男";
                g2.drawString(xb, (int) x + 80, (int) y + fontHeigth2 *3  + 20 + tempHeight);
                String syd =  "生 源 地：四川";
                g2.drawString(syd, (int) x + 80, (int) y + fontHeigth2 *4  + 25 + tempHeight);
                String sfzh = "身份证号：51110000101234";
                g2.drawString(sfzh, (int) x + 80, (int) y + fontHeigth2 * 5  + 30 + tempHeight);
                String ksh = "考 生 号：0001";
                g2.drawString(ksh, (int) x + 80, (int) y + fontHeigth2 * 6 + 35 + tempHeight);
                String bkzy = "报考专业： 美术";
                g2.drawString(bkzy, (int) x + 80, (int) y + fontHeigth2 * 7 + 40 + tempHeight);
                String zycj = "专业成绩：300";
                g2.drawString(zycj, (int) x + 80, (int) y + fontHeigth2 * 8 + 45 + tempHeight);
                String zymc = "专业名次：30";
                g2.drawString(zymc, (int) x + 80, (int) y + fontHeigth2 * 9 + 50 + tempHeight);
                String zyhgx = "专业合格线：260";
                g2.drawString(zyhgx, (int) x + 80, (int) y + fontHeigth2 * 10 + 55 + tempHeight);
                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;

        }
    }

    private void drawCurrentPageText(Graphics2D g2, PageFormat pf, int page) {
        Font f = area.getFont();
        String s = getDrawText(printStr)[page];
        String drawText;
        float ascent = 16;
        int k, i = f.getSize(), lines = 0;
        while (s.length() > 0 && lines < 54) {
            k = s.indexOf('\n');
            if (k != -1) {
                lines += 1;
                drawText = s.substring(0, k);
                g2.drawString(drawText, 0, ascent);
                if (s.substring(k + 1).length() > 0) {
                    s = s.substring(k + 1);
                    ascent += i;
                }
            } else {
                lines += 1;
                drawText = s;
                g2.drawString(drawText, 0, ascent);
                s = "";
            }
        }
    }

    public String[] getDrawText(String s) {
        String[] drawText = new String[PAGES];
        for (int i = 0; i < PAGES; i++)
            drawText[i] = "";

        int k, suffix = 0, lines = 0;
        while (s.length() > 0) {
            if (lines < 54) {
                k = s.indexOf('\n');
                if (k != -1) {
                    lines += 1;
                    drawText[suffix] = drawText[suffix] + s.substring(0, k + 1);
                    if (s.substring(k + 1).length() > 0)
                        s = s.substring(k + 1);
                } else {
                    lines += 1;
                    drawText[suffix] = drawText[suffix] + s;
                    s = "";
                }
            } else {
                lines = 0;
                suffix++;
            }
        }
        return drawText;
    }

    public int getPagesCount(String curStr) {
        int page = 0;
        int position, count = 0;
        String str = curStr;
        while (str.length() > 0) {
            position = str.indexOf('\n');
            count += 1;
            if (position != -1)
                str = str.substring(position + 1);
            else
                str = "";
        }

        if (count > 0)
            page = count / 54 + 1;

        return page;
    }

    private void printTextAction() {
        printStr = area.getText().trim();
        if (printStr != null && printStr.length() > 0) {
            PAGES = getPagesCount(printStr);
            PrinterJob myPrtJob = PrinterJob.getPrinterJob();
            PageFormat pageFormat = myPrtJob.defaultPage();
            myPrtJob.setPrintable(this, pageFormat);
            if (myPrtJob.printDialog()) {
                try {
                    myPrtJob.print();
                } catch (PrinterException pe) {
                    pe.printStackTrace();
                }
            }
        } else {
            JOptionPane.showConfirmDialog(null, "Sorry, Printer Job is Empty, Print Cancelled!", "Empty"
                    , JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
        }
    }

    private void previewAction() {
        printStr = area.getText().trim();
        PAGES = getPagesCount(printStr);
        (new PrintPreviewDialog(this, "Print Preview", true, this, printStr)).setVisible(true);
    }

    private void printText2Action() {
        printStr = area.getText().trim();
        if (printStr != null && printStr.length() > 0) {
            PAGES = getPagesCount(printStr);
            DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob job = printService.createPrintJob();
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            DocAttributeSet das = new HashDocAttributeSet();
            Doc doc = new SimpleDoc(this, flavor, das);

            try {
                job.print(doc, pras);
            } catch (PrintException pe) {
                pe.printStackTrace();
            }
        } else {
            JOptionPane.showConfirmDialog(null, "Sorry, Printer Job is Empty, Print Cancelled!", "Empty"
                    , JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
        }
    }

    private void printFileAction() {
        JFileChooser fileChooser = new JFileChooser(SystemProperties.USER_DIR);
        int state = fileChooser.showOpenDialog(this);
        if (state == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
            PrintService service = ServiceUI.printDialog(null, 200, 200, printService
                    , defaultService, flavor, pras);
            if (service != null) {
                try {
                    DocPrintJob job = service.createPrintJob();
                    FileInputStream fis = new FileInputStream(file);
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printFrameAction() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Properties props = new Properties();
        props.put("awt.print.printer", "durango");
        props.put("awt.print.numCopies", "2");
        if (kit != null) {
            PrintJob printJob = kit.getPrintJob(this, "Print Frame", props);
            if (printJob != null) {
                Graphics pg = printJob.getGraphics();
                if (pg != null) {
                    try {
                        this.printAll(pg);
                    } finally {
                        pg.dispose();
                    }
                }
                printJob.end();
            }
        }
    }

    private void exitApp() {
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        (new PrintTest()).setVisible(true);
    }
}

