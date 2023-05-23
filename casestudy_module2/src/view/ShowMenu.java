package view;

public class ShowMenu {
    public static void users(){
        System.out.println("║      1. Đăng kí        ║");
        System.out.println("║      2. Đăng nhập      ║");
        System.out.print("Nhập lựa chọn của bạn: ");
    }
    public static void showMainMenu() {
        System.out.println("\n╔══════════════MAIN MENU══════════════╗");
        System.out.println("║        1. Quản lý nhân viên         ║");
        System.out.println("║        2. Quản lý lương             ║");
        System.out.println("║        0. Thoát                     ║");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    public static void showEmployeeManagementMenu() {
        System.out.println("\n╔══════════QUẢN LÝ NHÂN VIÊN══════════╗");
        System.out.println("║    1. Danh sách nhân viên           ║");
        System.out.println("║    2. Thêm nhân viên mới            ║");
        System.out.println("║    3. Xóa nhân viên                 ║");
        System.out.println("║    4. Chỉnh sửa thông tin nhân viên ║");
        System.out.println("║    5. Tìm nhân viên                 ║");
        System.out.println("║    0. Quay lại Main Menu            ║");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    public static void showSalaryManagementMenu() {
        System.out.println("\n╔════════════QUẢN LÝ LƯƠNG════════════╗");
        System.out.println("║       1. Check in                   ║");
        System.out.println("║       2. lương nhân viên            ║");
        System.out.println("║       3. Lịch sử điểm danh          ║");
        System.out.println("║       0. Quay lại Main Menu         ║");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

}
