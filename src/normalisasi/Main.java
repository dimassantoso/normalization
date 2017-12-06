package normalisasi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dimassantoso
 */
public class Main {

    static HashMap<Integer, List<Double>> dataMap = new HashMap<>();

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        user();
        System.out.println("\nSuccessfully");

    }

    private static void readFile(String pathDataTraining) throws IOException {

        String line;
        BufferedReader br = new BufferedReader(new FileReader(pathDataTraining));

        int i = 0;

        while ((line = br.readLine()) != null) {

            List<Double> data = new ArrayList<>();
            String[] dataString = line.split(",");

            for (String temp : dataString) {
                double featureValue = Double.parseDouble(temp); //Membaca per atribut
                data.add(featureValue);
            }

            dataMap.put(i, data);
            i++;

        }
    }

    private static void writeFile(String text, String fileName) {
        try {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
                out.println(text);
            }

        } catch (IOException e) {
            System.out.println("Failed : " + fileName);
        }
    }

    private static void user() throws IOException {
        Function function = new Function();
        Scanner input = new Scanner(System.in);
        System.out.println("Choose Data : \n1. Data Iris\n2. Data New Thyroid");
        System.out.print("Your Choose : ");
        int data = input.nextInt();

        System.out.println("\n\nNormalization Data : \n1. Min-Max\n2. Z-Score\n3. Decimal Scalling\n4. Sigmoidal\n5. Softmax");
        System.out.print("Choose Method : ");
        int method = input.nextInt();

        switch (data) {
            case 1:
                readFile("src\\resources\\DatasetIris.csv");
                switch (method) {
                    case 1:
                        function.MinMax(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataIrisNormalizedByMinMax.csv");
                        });
                        break;
                    case 2:
                        function.zScore(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataIrisNormalizedByZScore.csv");
                        });
                        break;
                    case 3:
                        function.decimalScalling(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataIrisNormalizedByDecimalScalling.csv");
                        });
                        break;
                    case 4:
                        function.sigmoidal(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataIrisNormalizedBySigmoidal.csv");
                        });
                        break;
                    case 5:
                        function.softmax(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataIrisNormalizedBySoftmax.csv");
                        });
                        break;
                    default:
                        System.out.println("Invalid");
                }
                break;
            case 2:
                readFile("src\\resources\\DatasetThyroid.csv");
                switch (method) {
                    case 1:
                        function.MinMax(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataThyroidNormalizedByMinMax.csv");
                        });
                        break;
                    case 2:
                        function.zScore(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataThyroidNormalizedByZScore.csv");
                        });
                        break;
                    case 3:
                        function.decimalScalling(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataThyroidNormalizedByDecimalScalling.csv");
                        });
                        break;
                    case 4:
                        function.sigmoidal(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataThyroidNormalizedBySigmoidal.csv");
                        });
                        break;
                    case 5:
                        function.softmax(dataMap).entrySet().forEach((entry) -> {
                            writeFile(entry.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", ","), "DataThyroidNormalizedBySoftmax.csv");
                        });
                        break;
                    default:
                        System.out.println("Invalid");
                }
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

}
