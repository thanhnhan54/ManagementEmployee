package user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private static final String USER_FILE_PATH = "data\\users.csv";
    private static final String CSV_DELIMITER = ",";
    private static final int USERNAME_INDEX = 0;
    private static final int PASSWORD_INDEX = 1;

    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        do {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (checkCredentials(username, password)) {
                System.out.println("Đăng nhập thành công!");
                loggedIn = true;
            } else {
                System.out.println("Đăng nhập thất bại! Vui lòng thử lại.");
                System.out.print("Bạn có muốn nhập lại (Y/N)? ");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("Y")) {
                    break;
                }
            }
        } while (!loggedIn);

        return loggedIn;
    }

    public static void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (registerUser(username, password)) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.out.println("Đăng ký thất bại! Vui lòng thử lại.");
        }
    }

    private static boolean checkCredentials(String username, String password) {
        List<String[]> userList = loadUserList(USER_FILE_PATH);

        for (String[] user : userList) {
            if (user[USERNAME_INDEX].equals(username) && user[PASSWORD_INDEX].equals(password)) {
                return true;
            }
        }

        return false;
    }

    private static boolean registerUser(String username, String password) {
        List<String[]> userList = loadUserList(USER_FILE_PATH);

        for (String[] user : userList) {
            if (user[USERNAME_INDEX].equals(username)) {
                return false; // Tên người dùng đã tồn tại
            }
        }

        String[] newUser = {username, password};
        userList.add(newUser);
        saveUserList(userList, USER_FILE_PATH);

        return true;
    }

    private static List<String[]> loadUserList(String filePath) {
        List<String[]> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(CSV_DELIMITER);
                userList.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    private static void saveUserList(List<String[]> userList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] user : userList) {
                String line = String.join(CSV_DELIMITER, user);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}