package com.mett.writeMe.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.mett.writeMe.ejb.Writting;
import com.mett.writeMe.utils.Utils;
import ch.qos.logback.classic.pattern.Util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Mildred Guerra
 *	Generate PDF 
 */
public class GeneratePDFService {	
	private static String PDF_PATH = "resources/pdfWrittings/";
	private String databaseFileName;   
	/**
	    * Constructor
	    */
    public GeneratePDFService(){
       
    }
    public String pdf(Writting w,ServletContext servletContext){
    	String contenido = w.getContent();
        OutputStream file;
		try {
		String uploadedFileLocation =  servletContext.getRealPath("") + PDF_PATH + w.getName()+ ".pdf";
		file = new FileOutputStream(new File(uploadedFileLocation));
		System.out.println(uploadedFileLocation+"file location");
		Image portda = Image.getInstance(w.getImage());
		//portda.setAbsolutePosition(0, 0);
		portda.scaleToFit(726, 900);

//		portda.setAlignment(Image.ALIGN_CENTER);
//		portda.scalePercent(100f);
        Document document = new Document();
        PdfWriter.getInstance(document, file);
        document.open();
		document.add(portda);
//		  document.addAuthor("Real Gagnon");
//	      document.addCreator("Real's HowTo");
        document.addTitle(w.getName());
        document.add(new Paragraph(w.getName()));
        document.add(new Paragraph("SIPNOSIS"));
		document.add(new Paragraph(w.getDescription()));
        document.add(new Paragraph("CONTENIDO"));
        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new StringReader(contenido));
        document.close();
        file.close();
        databaseFileName= Utils.pdfFile(servletContext, w, PDF_PATH);
      System.out.println("direccion "+ databaseFileName);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return databaseFileName;

    }
    

}
