import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {
    public static String setLocalDate(int day) {
        String date = LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestForDay3() {
        $("[data-test-id=city] .input__control").setValue("Чебоксары");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(setLocalDate(3));
        $("[data-test-id=name] .input__control").setValue("Иванов Иван");
        $("[data-test-id=phone] .input__control").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=notification] .notification__title").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + setLocalDate(3)));
    }
}
