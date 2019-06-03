package Tests.util;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ReadExcelFile {
    private static XSSFWorkbook wb = null;
    private static XSSFSheet ws = null;

    public static void readXLS(String file) {
        FileInputStream ipstr = null;
        try {
            ipstr = new FileInputStream(file);
            wb = new XSSFWorkbook(ipstr);
            ws = wb.getSheetAt(0);
            ipstr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int retrieveNoOfRows() {
        return ws.getLastRowNum();
    }

    public static int retrieveNoOfCols() {
        return ws.getRow(0).getLastCellNum();
    }

    public static Object[][] retrieveCellsMulti(String file, int startRw, int endRw) {
        readXLS(file);
        int colNum = retrieveNoOfCols();

        Object[][] data = new Object[endRw - startRw + 1][colNum];
        int element = 0;
        for (int i = startRw - 1; i < endRw; i++) {
            XSSFRow row = ws.getRow(i);

            //System.out.println(element);
            for (int j = 0; j < colNum; j++) {
                if (row.getCell(j) == null) {

                    data[element][j] = "";

                } else {
                    data[element][j] = row.getCell(j).getRawValue();

                    System.out.println(row.getCell(j).getRawValue());

                }

            }
            element++;
        }
        return data;
    }

    public static Object[][] getValueRow1ToRow2AndCol(String file, int sheetNum, int rowNum1, int rowNum2, int endColNum) {
        readXLS(file);
        int rowNum = rowNum2 - rowNum1 + 1;
        Object data[][] = new Object[rowNum][endColNum];
        int temp = 0;
        for (int i = rowNum1 - 1; i < rowNum2; i++) {
            XSSFRow row = wb.getSheetAt(sheetNum).getRow(i);
            for (int j = 0; j < endColNum; j++) {
                if (row.getCell(j) == null) {
                    data[temp][j] = "";
                } else {
                    data[temp][j] = row.getCell(j).getRawValue();
                    //System.out.println("\n\t1(" + row.getCell(j) + ")1\n");
                }
            }
            temp++;
        }
        return data;
    }

    public static Object[][] getValueRow1ToRow2AndCol1ToCol2(String file, int sheetNum, int rowNum1,
                                                             int rowNum2, int startColNum, int endColNum) {
        readXLS(file);
        int rowNum = rowNum2 - rowNum1 + 1;
        Object data[][] = new Object[rowNum][endColNum];
        int temp = 0;
        for (int i = rowNum1 - 1; i < rowNum2; i++) {
            XSSFRow row = wb.getSheetAt(sheetNum).getRow(i);
            for (int j = startColNum - 1; j < endColNum; j++) {
                if (row.getCell(j) == null) {
                    data[temp][j] = "";
                } else {
                    data[temp][j] = row.getCell(j).getRawValue();
                    //System.out.println("\n\t1(" + row.getCell(j).getRawValue() + ")1\n");
                }
            }
            temp++;
        }
        return data;
    }
}
