package data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;

import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Condition.exactText;

@Data
public class DataHelper {
    private Faker faker;

    @Value
    public static class NumberCardClass {
        private String numberCard;
    }

    public static NumberCardClass getNumberCardApproved() {
        return new NumberCardClass("4444 4444 4444 4441");
    }
    public static NumberCardClass getNumberCardDeclined() {
        return new NumberCardClass("4444 4444 4444 4442");
    }
    public static NumberCardClass getNumberCardSymbol() {
        return new NumberCardClass("As!@");
    }
    public static NumberCardClass getNumberCardLessQuantity() {
        return new NumberCardClass("4");
    }
    public static NumberCardClass getNumberCardMoreQuantity() {
        return new NumberCardClass("4444 4444 4444 4442 4");
    }

    @Value
    public static class CardownerClass {
        private String cardowner;
    }

    @BeforeEach
    void setFaker() {
        faker = new Faker(new Locale("en"));
    }

    private String cardownerFaker = faker.name().firstName();

//    public static CardownerClass getCardownerValid() {
//        return new CardownerClass(cardownerFaker);
//    }
    public static CardownerClass getCardownerLessQuantity() {
        return new CardownerClass("z");
    }
    public static CardownerClass getCardownerMoreQuantity() {
        return new CardownerClass("CardownerCardownerCard");
    }
    public static CardownerClass getCardownerSymbol() {
        return new CardownerClass("1,ю");
    }

    @Value
    public static class MonthClass {
        private String month;
    }

    public static MonthClass getMonthVal() {
        Random random = new Random();
        int rand = random.nextInt(12);
        String monthVal[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return new MonthClass(monthVal[rand]);
    }

    public static MonthClass getMonthZero() {
        return new MonthClass("00");
    }
    public static MonthClass getMonthLessQuantity() {
        return new MonthClass("1");
    }
    public static MonthClass getMonthMoreQuantity() {
        return new MonthClass("011");
    }
    public static MonthClass getMonthSymbol() {
        return new MonthClass("s,");
    }

    @Value
    public static class YearClass {
        private String year;
    }

    public static YearClass getYearVal() {
        Random random = new Random();
        int rand = random.nextInt(4);
        String yearVal[] = {"21", "22", "23", "24"};
        return new YearClass(yearVal[rand]);
    }
    public static YearClass getYearZero() {
        return new YearClass("00");
    }
    public static YearClass getYearLessQuantity() {
        return new YearClass("2");
    }
    public static YearClass getYearMoreQuantity() {
        return new YearClass("022");
    }
    public static YearClass getYearSymbol() {
        return new YearClass("w!");
    }

    @Value
    public static class CvcClass {
        private String cvc;
    }

    public static CvcClass getCvcVal() {
        Random random = new Random();
        int rand = random.nextInt(4);
        String cvcVal[] = {"121", "115", "243", "604"};
        return new CvcClass(cvcVal[rand]);
    }

    public static CvcClass getCvcLessQuantity() {
        return new CvcClass("5");
    }
    public static CvcClass getCvcMoreQuantity() {
        return new CvcClass("0001");
    }
    public static CvcClass getCvcSymbol() {
        return new CvcClass("r-");
    }
}
