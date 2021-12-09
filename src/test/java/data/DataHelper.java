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

//    @Value
//    public static class PaymentByCard {
//        private String numberCard;
//    }

//    public static PaymentByCard getNumberCardApproved() {
//        return new PaymentByCard ("4444 4444 4444 4441");
//    }
//    public static NumberCard getNumberCardDeclined() {
//        return new NumberCard ("4444 4444 4444 4442");
//    }

    public static String getNumberCardApproved() {
        return "4444 4444 4444 4441";
    }

    @BeforeEach
    void setFaker() {
        faker = new Faker(new Locale("en"));
    }

    private String cardowner = faker.name().firstName();

    public String cardownerLessQuantity = "z";
    public String cardownerMoreQuantity = "CardownerCardownerCard";
    public String cardownerSymbol = "1,ю";

    public static String monthVal() {
        Random random = new Random();
        int rand = random.nextInt(12);
        String monthVal[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return monthVal[rand];
    }

    public static String monthZero = "00";
    public String monthLessQuantity = "1";
    public String monthMoreQuantity = "011";
    public String monthSymbol = "s,";

    public static String yearVal() {
        Random random = new Random();
        int rand = random.nextInt(4);
        String monthVal[] = {"21", "22", "23", "24"};
        return monthVal[rand];
    }

    public String yearZero = "00";
    public String yearLessQuantity = "2";
    public String yearMoreQuantity = "022";
    public String yearSymbol = "w!";

    public static String cvcVal() {
        Random random = new Random();
        int rand = random.nextInt(4);
        String monthVal[] = {"121", "115", "243", "604"};
        return monthVal[rand];
    }

    public String cvcLessQuantity = "2";
    public String cvcMoreQuantity = "0001";
    public String cvcSymbol = "r-";
}
