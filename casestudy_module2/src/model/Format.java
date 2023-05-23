package model;

import java.util.Scanner;

public class Format {
    private static final String PHONE_REGEX = "0\\d{9}";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static String validatePhone() {
        Scanner scanner = new Scanner(System.in);
        String phone;
        do {
            phone = scanner.nextLine();
            if (!phone.matches(PHONE_REGEX)) {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            }
        } while (!phone.matches(PHONE_REGEX));
        return phone;
    }

    public static String validateEmail() {
        Scanner scanner = new Scanner(System.in);
        String email;
        do {
            email = scanner.nextLine();
            if (!email.matches(EMAIL_REGEX)) {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            }
        } while (!email.matches(EMAIL_REGEX));
        return email;
    }
}
