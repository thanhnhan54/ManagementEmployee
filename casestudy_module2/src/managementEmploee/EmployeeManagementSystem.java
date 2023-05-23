package managementEmploee;

import model.Employee;
import model.Format;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static managementEmploee.EmployeeList.employeeList;

public class EmployeeManagementSystem {
    static Scanner scanner = new Scanner(System.in);
    static String csvFilePath = "data\\employees.csv";

    public static void addEmployee() {
        System.out.print("\nNhập ID: ");
        String id = validateNonEmptyInput("ID");

        // Check if ID already exists in the employee list
        boolean isDuplicateId = isDuplicateIdInCsv(id);
        while (isDuplicateId) {
            System.out.println("ID đã tồn tại. Vui lòng nhập lại ID khác.");
            id = validateNonEmptyInput("ID");
            isDuplicateId = isDuplicateIdInCsv(id);
        }

        // Check if ID already exists in the CSV file
        while (isDuplicateIdInCsv(id)) {
            System.out.println("ID đã tồn tại trong file CSV. Vui lòng nhập lại ID khác.");
            id = validateNonEmptyInput("ID");
        }

        System.out.print("Nhập tên: ");
        String name = validateNonEmptyInput("Tên");

        System.out.print("Nhập ngày sinh: ");
        String birthDate = validateNonEmptyInput("Ngày sinh");

        System.out.print("Nhập số điện thoại: ");
        String phone = Format.validatePhone ();
        System.out.print("Nhập email: ");
        String email = Format.validateEmail();
//        if (!Format.validateEmail(email)){
//            System.out.println("email không hợp lệ!!!");
//        }

        Employee newEmployee = new Employee(id, name, birthDate, phone, email);
        try {
            FileWriter csvWriter = new FileWriter(csvFilePath, true);
            csvWriter.append(id);
            csvWriter.append(",");
            csvWriter.append(name);
            csvWriter.append(",");
            csvWriter.append(birthDate);
            csvWriter.append(",");
            csvWriter.append(phone);
            csvWriter.append(",");
            csvWriter.append(email);
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Đã thêm nhân viên thành công!");
    }

    private static String validateNonEmptyInput(String fieldName) {
        String input = scanner.nextLine();
        while (input.isEmpty()) {
            System.out.println(fieldName + " không được để trống. Vui lòng nhập lại " + fieldName.toLowerCase() + ".");
            System.out.print("Nhập " + fieldName.toLowerCase() + ": ");
            input = scanner.nextLine();
        }
        return input;
    }

    private static int validateIntegerInput(String fieldName) {
        String input = scanner.nextLine();
        while (input.isEmpty() || !input.matches("\\d+")) {
            System.out.println(fieldName + " không hợp lệ. Vui lòng nhập lại " + fieldName.toLowerCase() + ".");
            System.out.print("Nhập " + fieldName.toLowerCase() + ": ");
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }

    private static boolean isDuplicateIdInCsv(String id) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0 && fields[0].equals(id)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void removeEmployee() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            List<String> lines = new ArrayList<>();
            System.out.print("\nNhập id nhân viên cần xóa: ");
            String idToRemove = scanner.next();
            boolean removed = false;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0];
                if (id.equals(idToRemove)) {
                    removed = true;
                    continue;
                }
                lines.add(line);
            }
            reader.close();

            if (removed) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
                for (String newLine : lines) {
                    writer.write(newLine + "\n");
                }
                writer.close();
                System.out.println("Xóa nhân viên thành công.");
            } else {
                System.out.println("Không tìm thấy nhân viên có id " + idToRemove);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editEmployee() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            List<String> lines = new ArrayList<>();
            boolean found = false;

            System.out.print("\nNhập id nhân viên cần chỉnh sửa: ");
            String idToUpdate = validateNonEmptyInput("ID");

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String birthDate = fields[2];
                String phone = fields[3];
                String email = fields[4];

                if (id.equals(idToUpdate)) {
                    found = true;
                    System.out.println("Thông tin hiện tại của nhân viên:");
                    System.out.println("ID: " + id);
                    System.out.println("Tên: " + name);
                    System.out.println("Ngày sinh: " + birthDate);
                    System.out.println("Số điện thoại: " + phone);
                    System.out.println("Email: " + email);

                    System.out.print("\nNhập tên mới: ");
                    String newName = validateNonEmptyInput("Tên");

                    System.out.print("Nhập ngày sinh mới: ");
                    String newBirthDate = validateNonEmptyInput("Ngày sinh");

                    System.out.print("Nhập số điện thoại mới: ");
                    String newPhone = Format.validatePhone();

                    System.out.print("Nhập email mới: ");
                    String newEmail = Format.validateEmail();

                    line = id + "," + newName + "," + newBirthDate + "," + newPhone + "," + newEmail;
                    System.out.println("Chỉnh sửa thông tin thành công.");
                }
                lines.add(line);
            }
            reader.close();

            if (!found) {
                System.out.println("Không tìm thấy nhân viên có id " + idToUpdate);
                return;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
            for (String updatedLine : lines) {
                writer.write(updatedLine + "\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void searchEmployee() {
        System.out.print("\nNhập ID nhân viên cần tìm kiếm: ");
        String id = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0 && fields[0].equals(id)) {
                    found = true;
                    String name = fields[1];
                    String birthDate = fields[2];
                    String phone = fields[3];
                    String email = fields[4];

                    System.out.println("Thông tin nhân viên:");
                    System.out.println("ID: " + id);
                    System.out.println("Tên: " + name);
                    System.out.println("Ngày sinh: " + birthDate);
                    System.out.println("Số điện thoại: " + phone);
                    System.out.println("Email: " + email);
                    break;
                }
            }

            reader.close();

            if (!found) {
                System.out.println("Không tìm thấy nhân viên có ID: " + id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}