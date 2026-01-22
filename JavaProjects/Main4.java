import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read array size
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        // Step 2: Create arrays
        int[] arr = new int[n];

        // Step 3: Input elements
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Step 4: Create another array to mark visited elements
        int[] freq = new int[n];
        int visited = -1;

        // Step 5: Count frequencies
        for (int i = 0; i < n; i++) {
            if (freq[i] == visited) {
                continue;
            }

            int count = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                    freq[j] = visited; // Mark as counted
                }
            }
            freq[i] = count;
        }

        // Step 6: Print result
        System.out.println("Frequencies of elements:");
        for (int i = 0; i < n; i++) {
            if (freq[i] != visited) {
                System.out.println(arr[i] + " => " + freq[i]);
            }
        }

        scanner.close();
    }
}
