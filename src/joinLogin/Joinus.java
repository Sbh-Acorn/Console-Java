package joinLogin;

import java.util.ArrayList;
import java.util.Scanner;

import main.MovieReservationSystem;

public class Joinus {

    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User loggedInUser = null;  // 로그인한 사용자 정보 저장

    public static void main(String[] args) {
        while (true) {
            System.out.println("에이콘시어터🎬에 오신걸 환영합니다");
            System.out.println("   메뉴를 선택해주세요   ");
            System.out.println();
            
            System.out.println("=======메뉴 선택=======");
            System.out.println("    1. 로그인         ");
            System.out.println("    2. 회원 가입       ");
            System.out.println("    3. 정보 조회       ");
            System.out.println("=====================");
            int menu = scanner.nextInt();
            scanner.nextLine();  

            switch (menu) {
                case 1:
                    if (loginUser()) {
                        loggedInUser = getLoggedInUser();
                        showMainMenu();  
                    }
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    if (loggedInUser == null) {
                        System.out.println("로그인 후 이용해주세요.");
                    } else {
                        viewLoggedInUser(); 
                    }
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 메뉴를 다시 선택해주세요.");
            }
        }
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=======메뉴 선택=======");
            System.out.println("    1. 영화 예약       ");
            System.out.println("    2. 로그아웃       ");
            System.out.println("    3. 회원 정보 조회 ");
            System.out.println("=====================");
            int menu = scanner.nextInt();
            scanner.nextLine();  

            switch (menu) {
                case 1:
                    MovieReservationSystem.startMovieReservation(loggedInUser);
                    break;
                case 2:
                    loggedInUser = null;  
                    System.out.println("로그아웃 되었습니다.");
                    return;  
                case 3:
                    viewLoggedInUser(); 
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 메뉴를 다시 선택해주세요.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("아이디를 입력하세요: ");
        String id = scanner.nextLine();
        
        String password;
        while (true) {
            System.out.print("비밀번호를 입력하세요: ");
            password = scanner.nextLine();
            System.out.print("비밀번호를 다시 입력하세요: ");
            String confirmPassword = scanner.nextLine();
            
            if (password.equals(confirmPassword)) {
                break; 
            } else {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 시도해 주세요.");
            }
        }

        System.out.println("연령대를 선택하세요:");
        System.out.println("1. 성인");
        System.out.println("2. 청소년");
        System.out.println("3. 아이/노인");
        int ageGroupChoice = scanner.nextInt();
        scanner.nextLine();  

        User.AgeGroup ageGroup;
        switch (ageGroupChoice) {
            case 1:
                ageGroup = User.AgeGroup.ADULT;
                break;
            case 2:
                ageGroup = User.AgeGroup.TEENAGER;
                break;
            case 3:
                ageGroup = User.AgeGroup.CHILD_SENIOR;
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                ageGroup = User.AgeGroup.UNKNOWN;
        }

        // 기본 등급을 Bronze
        String grade = "Bronze";

        User newUser = new User(id, password, name, ageGroup, grade);
        users.add(newUser); 
        System.out.println("회원 가입이 완료되었습니다.");
    }

    private static boolean loginUser() {
        System.out.print("아이디를 입력하세요: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getId().equals(id) && user.checkPassword(password)) {
                System.out.println("로그인 완료");
                System.out.println("환영합니다, " + user.getName() + "님.");
                loggedInUser = user;  
                return true;  
            }
        }
        System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
        return false;  
    }

    private static User getLoggedInUser() {
        return loggedInUser;
    }

    private static void viewLoggedInUser() {
        if (loggedInUser != null) {
            System.out.println("\n[회원 정보]");
            System.out.println("아이디: " + loggedInUser.getId());
            System.out.println("이름: " + loggedInUser.getName());
            System.out.println("등급: " + loggedInUser.getGrade());
            System.out.println("연령대: " + loggedInUser.getAgeGroup());
            System.out.println("예매 내역: " + loggedInUser.getReservations());
        } else {
            System.out.println("로그인된 사용자가 없습니다.");
        }
    }
}
