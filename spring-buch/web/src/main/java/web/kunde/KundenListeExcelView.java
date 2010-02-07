package web.kunde;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import businessobjects.Kunde;

public class KundenListeExcelView extends AbstractExcelView {

    private HSSFFont createFont(HSSFWorkbook workbook, boolean bold) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        if (bold) {
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }
        return font;
    }

    private HSSFCellStyle createHeaderCellStyle(HSSFWorkbook workbook) {
        HSSFFont headerFont = createFont(workbook, true);

        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        return headerCellStyle;
    }

    protected void createCell(short x, String content, HSSFRow row,
            HSSFCellStyle cellStyle) {
        HSSFCell cell = row.createCell(x);
        cell.setCellValue(content);
        cell.setCellStyle(cellStyle);
    }

    protected void createCell(short x, int content, HSSFRow row,
            HSSFCellStyle cellStyle) {
        HSSFCell cell = row.createCell(x);
        cell.setCellValue(content);
        cell.setCellStyle(cellStyle);
    }

    private HSSFCellStyle createBodyCellStyle(HSSFWorkbook workbook) {
        HSSFFont bodyCellFont = createFont(workbook, false);

        HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
        bodyCellStyle.setFont(bodyCellFont);
        bodyCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyCellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        bodyCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyCellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        return bodyCellStyle;
    }

    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HSSFSheet sheet = workbook.createSheet("Kunden");
        sheet.setDefaultColumnWidth((short) 12);

        sheet.createRow(0);
        HSSFRow headerRow = sheet.createRow(1);

        HSSFCellStyle headerCellStyle = createHeaderCellStyle(workbook);

        createCell((short) 1, "ID", headerRow, headerCellStyle);
        createCell((short) 2, "Vorname", headerRow, headerCellStyle);
        createCell((short) 3, "Name", headerRow, headerCellStyle);

        HSSFCellStyle bodyCellStyle = createBodyCellStyle(workbook);

        List kunden = (List) model.get("kundenListe");
        for (int i = 0; i < kunden.size(); i++) {
            Kunde kunde = (Kunde) kunden.get(i);
            HSSFRow bodyRow = sheet.createRow(i + 2);
            createCell((short) 1, kunde.getId(), bodyRow, bodyCellStyle);
            createCell((short) 2, kunde.getVorname(), bodyRow, bodyCellStyle);
            createCell((short) 3, kunde.getName(), bodyRow, bodyCellStyle);
        }

    }

}