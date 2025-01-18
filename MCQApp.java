import java.util.*;

class User {
    private String username;
    private String password;
    private String profile;

    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void updateProfile(String profile) {
        this.profile = profile;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}

class MCQTest {
    private Map<Integer, String> questions;
    private Map<Integer, String> answers;
    private Map<Integer, String> userAnswers;
    private long startTime;
    private long duration;

    public MCQTest(long duration) {
        questions = new HashMap<>();
        answers = new HashMap<>();
        userAnswers = new HashMap<>();
        this.duration = duration; // in milliseconds
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions.put(1, "What is the capital of France?\nA) Paris\nB) London\nC) Berlin\nD) Rome");
        answers.put(1, "A");
        questions.put(2, "What is 2 + 2?\nA) 3\nB) 4\nC) 5\nD) 6");
        answers.put(2, "B");
    }

    public void startTest() {
        startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);

        for (Map.Entry<Integer, String> question : questions.entrySet()) {
            if (isTimeUp()) {
                System.out.println("Time is up! Submitting test automatically.");
                break;
            }

            System.out.println(question.getValue());
            System.out.print("Your answer: ");
            String answer = scanner.nextLine().toUpperCase();
            userAnswers.put(question.getKey(), answer);
        }

        submitTest();
    }

    private boolean isTimeUp() {
        return System.currentTimeMillis() - startTime >= duration;
    }

    private void submitTest() {
        System.out.println("\nTest Submitted!");
        int score = 0;

        for (Map.Entry<Integer, String> entry : userAnswers.entrySet()) {
            if (answers.get(entry.getKey()).equals(entry.getValue())) {
                score++;
            }
        }

        System.out.println("Your score: " + score + "/" + questions.size());
    }
}

public class MCQApp {
    private static User currentUser;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Map<String, User> users = new HashMap<>();
        users.put("admin", new User("admin", "password", "Default Profile"));

        while (true) {
            System.out.println("\n1. Login\n2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                login(users);
            } else if (choice == 2) {
                System.out.println("Exiting application.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void login(Map<String, User> users) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.validatePassword(password)) {
            currentUser = user;
            System.out.println("Login successful!\n");
            showMenu();
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\n1. Update Profile\n2. Update Password\n3. Take MCQ Test\n4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                updateProfile();
            } else if (choice == 2) {
                updatePassword();
            } else if (choice == 3) {
                takeTest();
            } else if (choice == 4) {
                System.out.println("Logging out.");
                currentUser = null;
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void updateProfile() {
        System.out.print("Enter new profile: ");
        String profile = scanner.nextLine();
        currentUser.updateProfile(profile);
        System.out.println("Profile updated successfully.");
    }

    private static void updatePassword() {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        currentUser.updatePassword(newPassword);
        System.out.println("Password updated successfully.");
    }

    private static void takeTest() {
        System.out.println("Starting the MCQ Test...");
        MCQTest test = new MCQTest(60000); // Test duration is 1 minute
        test.startTest();
    }
}
