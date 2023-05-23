package managementSalary;

import model.Employee;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SalaryManagement {
    private static List<Employee> employeeList;
    private static Map<String, List<LocalDateTime>> attendanceMap;
    private static String employeeFilePath = "data\\employees.csv";
    private static String salaryFilePath = "data\\salary.csv";
    private static String attendanceFilePath = "data\\attendance.csv";

    public SalaryManagement() {
        employeeList = loadEmployeeData(employeeFilePath);
        attendanceMap = new HashMap<>();
    }

    public void markAttendance() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ID nhân viên: ");
        String id = scanner.nextLine().trim();

        boolean found = false;
        for (Employee employee : employeeList) {
            if (employee.getId().equals(id)) {
                LocalDateTime now = LocalDateTime.now();

                List<LocalDateTime> attendanceList = attendanceMap.getOrDefault(id, new ArrayList<>());
                attendanceList.add(now);
                attendanceMap.put(id, attendanceList);

                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Điểm danh thành công!");
            updateAttendanceFile();
        } else {
            System.out.println("Không tìm thấy nhân viên với ID đã nhập!");
        }
    }

    public void showUpdatedSalaryList() {
        for (Employee employee : employeeList) {
            String id = employee.getId();
            String name = employee.getName();
            double salary = calculateSalary(id);
            System.out.printf("ID: %s - Tên: %s - Lương: %.2f%n", id, name, salary);
        }
        updateSalaryFile();
    }

    public void showAttendanceModificationList() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ID nhân viên: ");
        String id = scanner.nextLine().trim();

        List<LocalDateTime> attendanceList = attendanceMap.get(id);
        if (attendanceList != null && !attendanceList.isEmpty()) {
            for (LocalDateTime dateTime : attendanceList) {
                System.out.println(dateTime);
            }
        } else {
            System.out.println("Không tìm thấy thông tin điểm danh cho nhân viên có ID đã nhập!");
        }
    }

    private List<Employee> loadEmployeeData(String filePath) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    Employee employee = new Employee(id, name);
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private double calculateSalary(String id) {
        List<LocalDateTime> attendanceList = attendanceMap.getOrDefault(id, new ArrayList<>());
        int attendanceCount = attendanceList.size();
        return attendanceCount * 100000.0;
    }
    private void updateSalaryFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(salaryFilePath))) {
            for (Employee employee : employeeList) {
                String id = employee.getId();
                String name = employee.getName();
                double salary = calculateSalary(id);
                String line = id + "," + name + "," + salary;
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateAttendanceFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(attendanceFilePath))) {
            for (Map.Entry<String, List<LocalDateTime>> entry : attendanceMap.entrySet()) {
                String id = entry.getKey();
                List<LocalDateTime> attendanceList = entry.getValue();
                for (LocalDateTime dateTime : attendanceList) {
                    String line = id + "," + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
