package com.example.demo.html2pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

/**
 * @describe:
 * @aouthor 潘立欢
 * @create 2019-06-14 14:44
 *          添加依赖
 *         <dependency>
 *             <groupId>org.jsoup</groupId>
 *             <artifactId>jsoup</artifactId>
 *             <version>1.11.3</version>
 *         </dependency>
 *
 *         <dependency>
 *             <groupId>com.itextpdf</groupId>
 *             <artifactId>html2pdf</artifactId>
 *             <version>2.1.2</version>
 *         </dependency>
 *
 */
public class Html2Pdf {

    public void downPad(HttpServletRequest request, HttpServletResponse response){
        ReadPdfSingleton readPdf = ReadPdfSingleton.getRedPadString();

        OutputStream ous = null;
        ByteArrayOutputStream bout = null;
        try {
            Document doc = Jsoup.parse(readPdf.getPdfString(), "utf-8");
            // 进行拼装参数
            String str = doc.outerHtml();
            bout = new ByteArrayOutputStream();

            ConverterProperties props = new ConverterProperties();
            PdfWriter writer = new PdfWriter(bout);
            PdfDocument pdf = new PdfDocument(writer);
            com.itextpdf.layout.Document document = HtmlConverter.convertToDocument(str, pdf, props);
            /**
             * 添加两次文字 和pdf页脚
             */
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new IEventHandler(){
                @SuppressWarnings("resource")
                @Override
                public void handleEvent(Event paramEvent) {
                    System.out.println(pdf.getNumberOfPages());
                    PdfDocumentEvent docEvent = (PdfDocumentEvent) paramEvent;
                    PdfDocument pdfDoc = docEvent.getDocument();
                    PdfPage page = docEvent.getPage();
                    int pageNumber = pdfDoc.getPageNumber(page);
                    Rectangle pageSize = page.getPageSize();
                    PdfCanvas pdfCanvas = new PdfCanvas(
                            page.newContentStreamBefore(), page.getResources(), pdfDoc);
                    com.itextpdf.kernel.font.PdfFont font = null;
                    try {
                        font = com.itextpdf.kernel.font.PdfFontFactory.createFont();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pdfCanvas.beginText()
                            .setFontAndSize(font, 9)
                            .moveText(pageSize.getWidth() / 2, 30)
                            .showText(String.valueOf(pageNumber))
                            .endText();
                    /**
                     * 只在第一页添加两侧文字
                     */
                    if(pageNumber == 1){
                        Canvas canvas1 = new Canvas(pdfCanvas, pdfDoc, page.getPageSize()).setFontSize(10f);
                        canvas1.showTextAligned(new Paragraph("DO NOT WRITE IN THIS BINDING MARGIN"),
                                18, page.getPageSize().getHeight()/2, pageNumber,
                                TextAlignment.CENTER, VerticalAlignment.MIDDLE, 1.58f);
                        canvas1.close();
                        Canvas canvas2 = new Canvas(pdfCanvas, pdfDoc, page.getPageSize()).setFontSize(8f);
                        canvas2.showTextAligned(new Paragraph("All clinical form creation and amendments must be conducted through the Health Information Manager"),
                                30, page.getPageSize().getHeight()/2, pageNumber,
                                TextAlignment.CENTER, VerticalAlignment.MIDDLE, 1.58f);
                        canvas2.close();
                        Canvas canvas3 = new Canvas(pdfCanvas, pdfDoc, page.getPageSize()).setFontSize(10f);
                        canvas3.showTextAligned(new Paragraph("DO NOT WRITE IN THIS BINDING MARGIN"),
                                page.getPageSize().getWidth()-35, 230, pageNumber,
                                TextAlignment.CENTER, VerticalAlignment.MIDDLE, -1.58f);
                        canvas3.close();
                    }
                    pdfCanvas.release();

                }});
            document.close();
            pdf.close();
            byte[] bytes = bout.toByteArray();
            // 输出
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String("admission.pdf".getBytes(), "UTF-8"));
            response.addHeader("Content-Length", "" + bytes.length);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/pdf;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            ous = new BufferedOutputStream(
                    response.getOutputStream());
            ous.write(bytes);
//            ous.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ous != null) {
                    ous.close();
                }
                if (bout != null) {
                    bout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 读取本地html
 */
 class ReadPdfSingleton {

    private   String pdfString;

    public String getPdfString() {
        return pdfString;
    }
    private ReadPdfSingleton() {
        URL url = this.getClass().getClassLoader().getResource("pdfTemplates.html");
        BufferedReader in=null;
        String content = "";
        try {
            in = new BufferedReader(new FileReader(new File(url.getPath())));
            String str;
            while ((str = in.readLine()) != null) {
                content +=str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        pdfString=content;
    }
    private static ReadPdfSingleton redPadStr;

    public static ReadPdfSingleton getRedPadString(){
        if (redPadStr==null){
            synchronized (ReadPdfSingleton.class){
                if(redPadStr==null){
                    redPadStr=new ReadPdfSingleton();
                }
            }
        }
        return redPadStr;
    }

    public static ReadPdfSingleton getRedPad(){
        redPadStr=new ReadPdfSingleton();
        return redPadStr;
    }
}
