package view;

import managementEmploee.EmployeeList;
import managementEmploee.EmployeeManagementSystem;
import managementSalary.SalaryManagement;
import user.User;

import java.util.Scanner;

public class RunMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void runMenu() {
        int choice;
        do {
            ShowMenu.showMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    employeeManagement();
                    break;
                case 2:
                    salaryManagement();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }

    public static void employeeManagement() {
        int choice;
        do {
            ShowMenu.showEmployeeManagementMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    EmployeeList.showEmployeeList();
                    break;
                case 2:
                    EmployeeManagementSystem.addEmployee();
                    break;
                case 3:
                    EmployeeManagementSystem.removeEmployee();
                    break;
                case 4:
                    EmployeeManagementSystem.editEmployee();
                    break;
                case 5:
                    EmployeeManagementSystem.searchEmployee();
                    break;
                case 0:
                    System.out.println("Quay vê main menu...");
                    break;
                default:
                    System.out.println("Lựa chọn sai!!! Vui lòng nhập lại.");
                    break;
            }
        } while (choice != 0);
    }

    public static void salaryManagement() {
        SalaryManagement salaryManagement = new SalaryManagement();
        int choice;
        do {
            ShowMenu.showSalaryManagementMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    salaryManagement.markAttendance();
                    break;
                case 2:
                    salaryManagement.showUpdatedSalaryList();
                    break;
                case 3:
                    salaryManagement.showAttendanceModificationList();
                    break;
                case 0:
                    System.out.println("Quay vê main menu...");
                    break;
                default:
                    System.out.println("Lựa chọn sai!!! Vui lòng nhập lại.");
                    break;
            }
        } while (choice != 0);
    }

    public static void runUsers(){
        int choice;

        do {
            ShowMenu.users();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    User.register();
                    break;
                case 2:
                    if (User.login()) {
                        RunMenu.runMenu();
                    } else {
                        System.out.println("Đăng nhập không thành công. Vui lòng thử lại hoặc đăng kí nếu chưa có tài khoản.");
                    }
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 0);
    }
}

