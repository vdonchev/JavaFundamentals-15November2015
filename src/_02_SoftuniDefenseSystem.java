import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _02_SoftuniDefenseSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long totalAlcohol = 0;
        String token;
        while (!(token = scanner.nextLine()).equals("OK KoftiShans")) {
            Pattern pattern = Pattern.compile(".*?([A-Z][a-z]+).*?([A-Z][a-z]*[A-Z]).*?(\\d+)L");
            Matcher matcher = pattern.matcher(token);

            while (matcher.find()) {
                String person = matcher.group(1);
                int quantity = Integer.parseInt(matcher.group(3));
                String alcohol = matcher.group(2);

                System.out.printf("%s brought %d liters of %s!\n", person, quantity, alcohol.toLowerCase());
                totalAlcohol += quantity;
            }

        }

        System.out.printf("%.3f softuni liters\n", (double)totalAlcohol * 0.001);
    }
}