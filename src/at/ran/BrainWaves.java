package at.ran;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BrainWaves {

    private String getWaveType(int wave) {
        if (wave >= 8 && wave <= 13) {
            return "Alpha";
        }
        if (wave >= 13 && wave <= 30) {
            return "Beta";
        }
        if (wave >= 30 && wave <= 100) {
            return "Gamma";
        }
        if (wave >= 0.5 && wave <= 4) {
            return "Delta";
        }
        if (wave >= 4 && wave <= 7) {
            return "Theta";
        } else {
            return "Keine Resultate";
        }
    }
    private static Map<String, String> brainWaveTypes = new HashMap<>();

    static {
        brainWaveTypes.put("Alpha", "8-13");
        brainWaveTypes.put("Beta", "14-30");
        brainWaveTypes.put("Theta", "4-7");
        brainWaveTypes.put("Delta", "0.5-4");
        brainWaveTypes.put("Gamma", "30-100");
    }

    public static void main(String[] args) {
        System.out.println(brainWaveTypes.get("Alpha").split("-")[0]);
        System.out.println(brainWaveTypes.get("Beta").split("-")[0]);
        System.out.println(brainWaveTypes.get("Theta").split("-")[0]);
        System.out.println(brainWaveTypes.get("Delta").split("-")[0]);
        System.out.println(brainWaveTypes.get("Gamma").split("-")[0]);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte geben Sie die Frequenz in Hz ein: ");
        String inputFrequency = scanner.nextLine();
        int frequency = extractFrequency(inputFrequency);

        String brainWaveType = getBrainWaveType(frequency);
        if (brainWaveType != null) {
            System.out.println("Aktuelle Hirnwellen für Frequenz " + inputFrequency + ":");
            System.out.println(brainWaveType);
        } else {
            System.out.println("Keine entsprechenden Hirnwellen für die angegebene Frequenz gefunden.");
        }

        scanner.close();
    }

    private static int extractFrequency(String inputFrequency) {
        String frequencyString = inputFrequency.replaceAll("[^0-9.]", ""); // Entfernt alle nicht-numerischen Zeichen
        return Integer.parseInt(frequencyString);
    }

    private static String getBrainWaveType(int frequency) {
        for (Map.Entry<String, String> entry : brainWaveTypes.entrySet()) {
            String waveType = entry.getKey();
            String waveFrequencyRange = entry.getValue();
            String[] range = waveFrequencyRange.split("-");
            int lowerBound = Integer.parseInt(range[0]);
            int upperBound = Integer.parseInt(range[1]);

            if (frequency >= lowerBound && frequency <= upperBound) {
                return waveType;
            }
        }

        return null;
    }
}
