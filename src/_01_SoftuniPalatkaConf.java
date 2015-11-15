import java.util.Scanner;

public class _01_SoftuniPalatkaConf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int people = Integer.parseInt(scanner.nextLine());
        int lineCount = Integer.parseInt(scanner.nextLine());

        int beds = 0;
        int food = 0;

        for (int i = 0; i < lineCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            String item = tokens[0];
            int count = Integer.parseInt(tokens[1]);
            String type = tokens[2];

            if (item.equals("tents")) {
                if (type.equals("firstClass")) {
                    beds += (3 * count);
                } else {
                    beds += (2 * count);
                }
            } else if (item.equals("rooms")) {
                if (type.equals("single")) {
                    beds += count;
                } else if (type.equals("double")) {
                    beds += (2 * count);
                } else {
                    beds += (3 * count);
                }
            } else {
                if (type.equals("musaka")) {
                    food += (count * 2);
                }
            }
        }

        if (beds >= people) {
            System.out.printf("Everyone is happy and sleeping well. Beds left: %d\n", beds - people);
        } else {
            System.out.printf("Some people are freezing cold. Beds needed: %d\n", people - beds);
        }

        if (food >= people) {
            System.out.printf("Nobody left hungry. Meals left: %d\n", food - people);
        } else {
            System.out.printf("People are starving. Meals needed: %d\n", people - food);
        }
    }
}
