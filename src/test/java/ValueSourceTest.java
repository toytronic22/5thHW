import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ValueSourceTest {
    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {
            "джинсы",
            "кардиган"
    })
    @ParameterizedTest(name = "Поиск осуществлен по значению {0}")
    void wbSearchTest(String productName) {
        open("https://www.wildberries.ru/");
        $("#searchInput").setValue(productName).pressEnter();
        $$(".goods-name").first().shouldHave(text(productName));
    }
}
