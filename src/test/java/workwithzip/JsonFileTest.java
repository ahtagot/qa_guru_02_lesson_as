package workwithzip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import workwithzip.model.LibraryBookPojo;
import workwithzip.model.Storage;

import java.io.InputStream;

public class JsonFileTest {

    private static final ClassLoader cl = JsonFileTest.class.getClassLoader();
    private static final String JSON_FILE = "json4test.json";

    @Test
    @DisplayName("Check json content")
    void testJsonParsingWithFileInputStream() throws Exception {

        String[] expectedVolumeNames = {"One", "Two", "Three"};
        Storage expectedStorage = new Storage(1, 3);


        LibraryBookPojo expectedRecord = new LibraryBookPojo(
                "Lord of the Rings",
                expectedVolumeNames,
                2000,
                expectedStorage);

        try (InputStream stream = cl.getResourceAsStream(JSON_FILE)) {
            ObjectMapper objectMapper = new ObjectMapper();
            LibraryBookPojo jsonHome = objectMapper.readValue(stream, LibraryBookPojo.class);

            assertEqualObjects(expectedRecord, jsonHome);
        }
    }

    private void assertEqualObjects(LibraryBookPojo expected, LibraryBookPojo actual) {
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getTotalPages(), actual.getTotalPages());
        Assertions.assertArrayEquals(expected.getVolume(), actual.getVolume());
        Storage expectedStorage = new Storage(expected.getStorage().getAisle(), expected.getStorage().getBin());
        Storage actualStorage = actual.getStorage();
        Assertions.assertEquals(expectedStorage.getAisle(), actualStorage.getAisle());
        Assertions.assertEquals(expectedStorage.getBin(), actualStorage.getBin());
    }
}


