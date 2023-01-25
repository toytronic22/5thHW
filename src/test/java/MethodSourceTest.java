import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


import data.Languages;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MethodSourceTest {
    static Stream<Arguments> miroLocaleDataProvider() {
        return Stream.of(
                Arguments.of(Languages.English, List.of(
                        "Product" + " " + "Solutions" + " " + "Resources" + " " + "Enterprise" + " " +
                                "Pricing")),
                Arguments.of(Languages.Italiano, List.of(
                        "Prodotto" + " " + "Soluzioni" + " " + "Risorse" + " " + "Enterprise" + " " + "Prezzi"))
        );
    }

    @MethodSource("miroLocaleDataProvider")
    @ParameterizedTest(name = "Для языка {0} отображаются кнопки {1}")
    @Tag("BLOCKER")
    void miroSiteShouldContainAllOfButtonsForGivenLocale(
            Languages language,
            List<String> buttons
    ) {
        open("https://miro.com/");
        $(".LanguageSwitcher__SwitcherContainer-sc-1n6vypn-0").click();
        $(byText(language.name())).click();
        $$(".fIGZUK").filter(visible).shouldHave(texts(buttons));

    }
}
