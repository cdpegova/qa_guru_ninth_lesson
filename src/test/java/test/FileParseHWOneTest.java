package test;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileParseHWOneTest {

    ClassLoader classLoader = FileParseHWOneTest.class.getClassLoader();

    @Test
    void parseZipWithPdfTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("pdffile.zip");
        assert is != null;
        ZipInputStream zip = new ZipInputStream(is);
        ZipFile file = new ZipFile(new File("src/test/resources/" + "pdffile.zip"));
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            try (InputStream stream = file.getInputStream(entry)) {
                PDF pdf = new PDF(stream);
                assertThat(pdf.text).contains("PDF");
                System.out.println();
            }
        }
    }


    @Test
    void parseZipWithCsvTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("csvfile.zip");
        assert is != null;
        ZipInputStream zip = new ZipInputStream(is);
        ZipFile file = new ZipFile(new File("src/test/resources/" + "csvfile.zip"));
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            try (InputStream stream = file.getInputStream(entry);
                 CSVReader reader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                List<String[]> csv = reader.readAll();
                assertThat(csv).contains(
                        new String[]{"Number", "Description"},
                        new String[]{"123", "hello"}
                );
            }
        }
    }
/* @Test
    void parseZipWithXlsxTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("xlsxfile.zip");
        ZipInputStream zip = new ZipInputStream(is);
        ZipFile file = new ZipFile(new File("src/test/resources/" + "xlsxfile.zip"));
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            try (InputStream stream = file.getInputStream(entry)) {
                XLS xls = new XLS(stream);
                assertThat(
                        xls.excel.getSheetAt(0) //error: cannot access Workbook ???
                                .getRow(0)
                                .getCell(0)
                                .getStringCellValue()
                ).contains("test_pdf");
            }
        }
    }*/
}