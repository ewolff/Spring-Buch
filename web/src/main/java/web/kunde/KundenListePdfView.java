package web.kunde;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import businessobjects.Kunde;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class KundenListePdfView extends AbstractPdfView {

    private static final Font BOLD_FONT = new Font(Font.HELVETICA, 12,
            Font.BOLD, Color.black);

    private static final Font NORMAL_FONT = new Font(Font.HELVETICA, 12,
            Font.NORMAL, Color.black);

    protected void buildPdfDocument(Map model, Document document,
            PdfWriter writer, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(50);
        table.getDefaultCell().setBorderWidth(1);
        table.getDefaultCell().setBorderColor(Color.black);
        table.getDefaultCell().setPadding(4);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        createCell(table, "ID", BOLD_FONT);
        createCell(table, "Vorname", BOLD_FONT);
        createCell(table, "Name", BOLD_FONT);

        List kunden = (List) model.get("kundenListe");
        for (int i = 0; i < kunden.size(); i++) {
            Kunde kunde = (Kunde) kunden.get(i);
            createCell(table, Integer.toString(kunde.getId()), NORMAL_FONT);
            createCell(table, kunde.getVorname(), NORMAL_FONT);
            createCell(table, kunde.getName(), NORMAL_FONT);
        }

        document.add(table);
    }

    private void createCell(PdfPTable table, String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        table.addCell(cell);
    }

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        super.buildPdfMetadata(model, document, request);
        document.addTitle("Kunden Liste");
        document.addCreator("Spring Buch Demo Anwendung ");
    }

}
