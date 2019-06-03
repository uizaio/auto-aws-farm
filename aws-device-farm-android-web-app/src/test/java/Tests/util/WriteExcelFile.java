package Tests.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteExcelFile {
    public static void writeResultTestLinks(String fileLocation, List resultCheckLinks,
                                            int sheetNumber, int column) {
        try {
            FileInputStream inputStream = new FileInputStream(fileLocation);
            Workbook workbook = WorkbookFactory.create(inputStream);

            // Get Sheet at index sheetNumber
            Sheet sheet = workbook.getSheetAt(sheetNumber);

            for (int i = 0; i < resultCheckLinks.size(); i++) {

                // Get Row at index 1
                Row row = sheet.getRow(i +1 );

                // Get the Cell at index 2 from the above row
                Cell cell = row.getCell(column);

                // Update the cell's value
                cell.setCellType(CellType.STRING);

                if (resultCheckLinks.get(i).toString().equals("true")) {
                    cell.setCellValue("OK");
                } else {
                    cell.setCellValue("FAIL");
                }
            }

            inputStream.close();

            // Write the output to the file
            FileOutputStream fileOut = new FileOutputStream(fileLocation);
            workbook.write(fileOut);

            // Closing the workbook
            workbook.close();
            fileOut.close();
        } catch (IOException ex) {
        } catch (InvalidFormatException e) {
        }
    }
}
