import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class CsvSourceTest {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @CsvSource({
            "холодильник, Холодильники",
            "телевизор, Телевизоры"
    })
    @ParameterizedTest(name = "В результате поиска товара{0} должна появится его категория {1}")
    void dnsSearchTest(
            String productName,
            String productCategory
    ) {
        open("https://www.dns-shop.ru/");
        $(".presearch__input").setValue(productName).pressEnter();
        $(".products-page__title").$("h1").shouldHave(text(productCategory));
    }


}
