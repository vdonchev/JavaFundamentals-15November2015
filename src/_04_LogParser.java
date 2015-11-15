import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class _04_LogParser {
    private static TreeMap<String, Integer> projectErrors;
    private static TreeMap<String, ArrayList<String>> projectWarnMsg;
    private static TreeMap<String, ArrayList<String>> projectCriticalMsg;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        projectErrors = new TreeMap<>();
        projectCriticalMsg = new TreeMap<>();
        projectWarnMsg = new TreeMap<>();

        String tokens;
        while (!(tokens = scanner.nextLine()).equals("END")) {
            Pattern pattern = Pattern.compile("\\{\"Project\": \\[\"(.*?)\"\\], \"Type\": \\[\"(.*?)\"\\], \"Message\": \\[\"(.*?)\"\\]\\}");
            Matcher matcher = pattern.matcher(tokens);
            while (matcher.find()) {
                String projectName = matcher.group(1);
                String errorType = matcher.group(2);
                String errorMsg = matcher.group(3);

                if (!projectErrors.containsKey(projectName)) {
                    projectErrors.put(projectName, 0);
                    projectCriticalMsg.put(projectName, new ArrayList<>());
                    projectWarnMsg.put(projectName, new ArrayList<>());
                }

                if (errorType.equals("Critical")) {
                    if (!projectCriticalMsg.containsKey(projectName)) {
                        projectCriticalMsg.put(projectName, new ArrayList<>());
                    }
                    projectCriticalMsg.get(projectName).add(errorMsg);
                } else {
                    if (!projectWarnMsg.containsKey(projectName)) {
                        projectWarnMsg.put(projectName, new ArrayList<>());
                    }
                    projectWarnMsg.get(projectName).add(errorMsg);
                }

                int totalErrors = projectErrors.get(projectName);
                projectErrors.put(projectName, totalErrors + 1);
            }
        }

        List<Object> sortedProjects = projectErrors.keySet().stream()
                .sorted((p1, p2) -> projectErrors.get(p2).compareTo(projectErrors.get(p1)))
                .collect(Collectors.toList());

        boolean first = true;
        for (Object sortedProject : sortedProjects) {
            if (first) {
                first = false;
            } else {
                System.out.println();
            }

            System.out.printf("%s:\n", sortedProject);
            System.out.printf("Total Errors: %d\n", projectErrors.get(sortedProject));
            System.out.printf("Critical: %d\n", projectCriticalMsg.get(sortedProject).size());
            System.out.printf("Warnings: %d\n", projectWarnMsg.get(sortedProject).size());

            System.out.printf("Critical Messages:\n");
            if (projectCriticalMsg.get(sortedProject).size() == 0) {
                System.out.printf("--->None\n");
            } else {
                projectCriticalMsg.get(sortedProject).stream()
                        .sorted((o11, o21) -> o11.compareTo(o21))
                        .sorted((o1, o2) -> o1.length() - o2.length())
                        .forEach(i -> System.out.printf("--->%s\n", i));

            }

            System.out.printf("Warning Messages:\n");
            if (projectWarnMsg.get(sortedProject).size() == 0) {
                System.out.printf("--->None\n");
            } else {
                projectWarnMsg.get(sortedProject).stream()
                        .sorted((o11, o21) -> o11.compareTo(o21))
                        .sorted((o1, o2) -> o1.length() - o2.length())
                        .forEach(i -> System.out.printf("--->%s\n", i));

            }
        }
    }
}