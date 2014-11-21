package ro.iasinschi.util.excel;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>ExcelReader</b> class
 * Reads an Excel file into a list of lists of Strings
 * @author Adrian Iasinschi (aiasinschi@gmail.com) on 11/18/2014.
 * @since 1.0
 */
public class ExcelReader {
    private static Workbook workbook;

    private List<List<String>> data;
    private List<String> headers;

    public ExcelReader(String fileName){
        File f = new File(fileName);
        try {
            workbook = WorkbookFactory.create(f);
        } catch (Exception ex) {
            System.err.println(String.format("Problem initializing workbook from file %s !", f.getAbsolutePath()));
        }
    }

    public List<List<String>> read(){
        Sheet sheet = workbook.getSheet("Sheet1");
        Row row0 = sheet.getRow(0);
        headers = new ArrayList<String>();
        int k = 0;
        for (int i = 0; i < row0.getLastCellNum(); i++){
            if (row0.getCell(i) != null) {
                k++;
                headers.add(row0.getCell(i).getStringCellValue());
            }
        }
        int numCols = headers.size();
        data = new ArrayList<List<String>>();
        for (Row row: sheet){
            if (row.getCell(0) != null) {
                List<String> rowList = new ArrayList<String>();
                for (int j = 0; j < numCols; j++) {
                    Cell cell = row.getCell(j);
                    rowList.add(cell !=null ? cell.toString() : "");
                }
                data.add(rowList);
            }
        }
        return data;
    }

    public void writeData() {
        for (int i = 0; i < data.size(); i++){
            for (int j = 0; j < data.get(i).size() ; j++){
                System.out.print(data.get(i).get(j) + " ; ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]){
        String fName = "plumberdb.xlsx";
        ExcelReader excelReader = new ExcelReader(fName);
        excelReader.read();
        excelReader.writeData();
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }


}
