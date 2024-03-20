package workwithzip;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileExtractorTest {
    private final ClassLoader cl = FileExtractorTest.class.getClassLoader();

    public InputStream parseZipFile(String fileName) throws IOException {
        ZipInputStream is = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("zip4test.zip"))
        );
        ZipEntry zipEntry;
        while ((zipEntry = is.getNextEntry()) != null) {
            if (zipEntry.getName().equals(fileName)) {

                return is;
            }
        }
        return null;
    }

    @ParameterizedTest(name = "check PDF file {0}")
    @ValueSource(strings = {"pdf4test.pdf"})
    void checkPDFContentInZip(String fileName) throws IOException {
        try (InputStream is = parseZipFile(fileName)) {
            if (is != null) {
                PDF pdf = new PDF(is);
                assertTrue(pdf.numberOfPages > 0);
                Assertions.assertEquals("NEW PROCEDURES FIREARMS February 2019.docx", pdf.title);
                assertThat(pdf.text, containsString("NJ0111000"));

            } else throw new IOException("No such PDF file in archive (" + fileName + ")");

        }

    }

    @ParameterizedTest(name = "check CSV file  {0}")
    @ValueSource(strings = {
            "csv4test.csv"
    })
    void checkCSVContentInZip(String fileName) throws IOException, CsvException {
        try (InputStream is = parseZipFile(fileName)) {
            if (is != null) {
                CSVReader csv = new CSVReader(new InputStreamReader(is));
                List<String[]> content = csv.readAll();
                assertTrue(content.size() > 1);
                String actualValue = content.get(2)[1];
                Assertions.assertEquals("3 Zombie", actualValue);

            } else {
                throw new IOException("No such CSV file in archive (" + fileName + ")");
            }
        }
    }


    @ParameterizedTest(name = "check XLSX file  {0}")
    @ValueSource(strings = {
            "xlsx4test.xlsx"
    })
    void checkXLSXContentInZip(String fileName) throws IOException {
        try (InputStream is = parseZipFile(fileName)) {
            if (is != null) {
                XLS xlsx = new XLS(is);
                String actualCellValue = xlsx.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
                Assertions.assertEquals("Tokens", actualCellValue);

            } else throw new IOException("No such XLSX file in archive (" + fileName + ")");
        }
    }


}


