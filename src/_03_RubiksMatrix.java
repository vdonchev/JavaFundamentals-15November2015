import java.util.Scanner;

public class _03_RubiksMatrix {
    public static int matrixHeight;
    public static int matrixWidth;
    public static int[][] matrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] size = scanner.nextLine().split("\\s+");
        matrixHeight = Integer.parseInt(size[0]);
        matrixWidth = Integer.parseInt(size[1]);

        int index = 1;
        matrix = new int[matrixHeight][];
        for (int row = 0; row < matrixHeight; row++) {
            matrix[row] = new int[matrixWidth];
            for (int col = 0; col < matrixWidth; col++) {
                matrix[row][col] = index++;
            }
        }

        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            int dirNum = Integer.parseInt(tokens[0]);
            String type = tokens[1];
            int rotations = Integer.parseInt(tokens[2]);

            switch (type) {
                case "up":
                    slideUp(dirNum, rotations);
                    break;
                case "down":
                    slideDown(dirNum, rotations);
                    break;
                case "left":
                    slideLeft(dirNum, rotations);
                    break;
                case "right":
                    slideRight(dirNum, rotations);
                    break;
            }
        }

        index = 1;
        for (int row = 0; row < matrixHeight; row++) {
            for (int col = 0; col < matrixWidth; col++) {
                if (matrix[row][col] == index) {
                    System.out.printf("No swap required\n");
                } else {
                    for (int subRow = 0; subRow < matrixHeight; subRow++) {
                        for (int subCol = 0; subCol < matrixWidth; subCol++) {
                            if (matrix[subRow][subCol] == index) {
                                int temp = matrix[row][col];
                                matrix[row][col] = matrix[subRow][subCol];
                                matrix[subRow][subCol] = temp;
                                System.out.printf("Swap (%d, %d) with (%d, %d)\n", row, col, subRow, subCol);
                            }
                        }
                    }
                }

                index++;
            }
        }
    }

    private static void slideRight(int dirNum, int rotations) {
        rotations %= matrixWidth;

        for (int i = 0; i < rotations; i++) {
            int temp = matrix[dirNum][matrixWidth - 1];
            for (int col = matrixWidth - 1; col > 0; col--) {
                matrix[dirNum][col] = matrix[dirNum][col - 1];
            }

            matrix[dirNum][0] = temp;
        }
    }

    private static void slideLeft(int dirNum, int rotations) {
        rotations %= matrixWidth;

        for (int i = 0; i < rotations; i++) {
            int temp = matrix[dirNum][0];
            for (int col = 0; col < matrixWidth - 1; col++) {
                matrix[dirNum][col] = matrix[dirNum][col + 1];
            }

            matrix[dirNum][matrixWidth - 1] = temp;
        }
    }

    private static void slideDown(int dirNum, int rotations) {
        rotations %= matrixWidth;

        for (int i = 0; i < rotations; i++) {
            int temp = matrix[matrixHeight - 1][dirNum];
            for (int row = matrixHeight - 1; row > 0; row--) {
                matrix[row][dirNum] = matrix[row - 1][dirNum];
            }

            matrix[0][dirNum] = temp;
        }
    }


    private static void slideUp(int dirNum, int rotations) {
        rotations %= matrixWidth;

        for (int i = 0; i < rotations; i++) {
            int temp = matrix[0][dirNum];
            for (int row = 0; row < matrixHeight - 1; row++) {
                matrix[row][dirNum] = matrix[row + 1][dirNum];
            }

            matrix[matrixHeight - 1][dirNum] = temp;
        }
    }
}