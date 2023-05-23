package managementEmploee;

import model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeList {
    static ArrayList<Employee> employeeList = new ArrayList<>();
    static String csvFilePath = "data\\employees.csv";


    public static void  showEmployeeList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                         DANH SÁCH NHÂN VIÊN                                         ║");
            System.out.println("║══════════╦══════════════════════╦════════════════╦════════════════════╦═════════════════════════════║                              ║");
            System.out.println("║    ID    ║      HỌ VÀ TÊN       ║   Ngày Sinh    ║   Số điện thoại    ║             email           ║");

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String birthDay = fields[2];
                String phone = fields[3];
                String email = fields[4];
                Employee employee = new Employee(id,name,birthDay,phone,email);

                employeeList.add(employee);
                System.out.println("║══════════║══════════════════════║════════════════║════════════════════║═════════════════════════════║");
                System.out.println(employee);
            }
            reader.close();
            System.out.println("╚══════════╩══════════════════════╩════════════════╩════════════════════╩═════════════════════════════╝");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
