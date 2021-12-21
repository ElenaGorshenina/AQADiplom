package data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;

import java.util.Locale;
import java.util.Random;

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

     public static String getCardownerValid() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getCardownerLessQuantity() {
        return "z";
    }
    public static String getCardownerMoreQuantity() {
        return "CardownerCardownerCard";
    }
    public static String getCardownerSymbol() {
        return "1,ю";
    }

    public static String getMonthVal() {
        Random random = new Random();
        int rand = random.nextInt(12);
        String monthVal[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return monthVal[rand];
    }

    public static String getMonthZero() {
        return "00";
    }
    public static String getMonthLessQuantity() {
        return "1";
    }
    public static String getMonthMoreQuantity() {
        return "011";
    }
    public static String getMonthSymbol() {
        return "s,";
    }

    public static String getYearVal() {
        Random random = new Random();
        int rand = random.nextInt(4);
        String yearVal[] = {"21", "22", "23", "24"};
        return yearVal[rand];
    }
    public static String getYearZero() {
        return "00";
    }
    public static String getYearLessQuantity() {
        return "2";
    }
    public static String getYearMoreQuantity() {
        return "022";
    }
    public static String getYearSymbol() {
        return "w!";
    }

    public static String getCvcVal() {
        Random random = new Random();
        int rand = random.nextInt(4);
        String cvcVal[] = {"121", "115", "243", "604"};
        return cvcVal[rand];
    }

    public static String getCvcLessQuantity() {
        return "5";
    }
    public static String getCvcMoreQuantity() {
        return "0001";
    }
    public static String getCvcSymbol() {
        return "r-";
    }
}
