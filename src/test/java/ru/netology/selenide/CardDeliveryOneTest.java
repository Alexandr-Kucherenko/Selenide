package ru.netology.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Condition;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryOneTest {
    private String generateDate(int addDays,String pattern){
    return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void shouldBESuccessFullyComplited(){
        Configuration.timeout=15;
        open("http://localhost:9999");
        $("[data-test-in='city'] input").setValue("Москва");
        String currentDate = generateDate(4,"dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.SHIFT,Keys.HOME,Keys.DELETE);
        $("[data-test-id='date'] input").setValue(currentDate);
        $("[data-test-id='name'] input").setValue("Пушкин Александр Сергеевич");
        $("[data-test-id='phone'] input").setValue("+79155001162");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
         $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на" + currentDate));


    }
}
