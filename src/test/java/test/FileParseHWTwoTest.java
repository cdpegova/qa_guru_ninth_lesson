package test;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class FileParseHWTwoTest {

    ClassLoader classLoader = FileParseHWOneTest.class.getClassLoader();
    @Test
    void jsonTestNG() {
        InputStream is = classLoader.getResourceAsStream("123.json");
        Gson gson = new Gson();
        DataUser dataUser = gson.fromJson(new InputStreamReader(is), DataUser.class);
        assertThat(dataUser.getName()).isEqualTo("Zula");
        assertThat(dataUser.getAge()).isEqualTo(1);

    }

}
