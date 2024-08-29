package mainFunc;


import java.io.FileReader;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Login implements ConsolePrint{
	
	public static void main(String[] args) {
		Login log = new Login();
		log.print();
	}
	
	
	Scanner scanner = new Scanner(System.in);
	@Override
	public void print() {
		System.out.println("╭─────── · · 🎬 · · ───────╮");
    	System.out.println("   에이콘시어터에 오신걸 환영합니다 ");
    	System.out.println("╰─────── · · 🎬 · · ───────╯\n");
    	System.out.println("      -메뉴를 선택해주세요-   ");
    	System.out.println();
    	
        System.out.println("=========Menu=========");
        System.out.println("    1. 회원 가입       ");
        System.out.println("    2. 로그인         ");
        System.out.println("======================");
        int menu = scanner.nextInt();
        if(menu==1 || menu==2) {
        	scanner.nextLine(); 
            JoinorLogin(menu);
        	
        }
        else {
        	System.out.println("올바른 메뉴를 선택해주세요");
        	print();
        }
        
	}
	
	public void JoinorLogin(int a) {
		if(a==1) {
			userJoin();
		}
		else if(a==2) {
			userLogin();
		}
	}
	
	public void userJoin() {
	    Login log = new Login();
	    System.out.println("회원가입 시작!");

	    String name = null;
	    String id = null;
	    String password;

	    String filePath = "resource/userinfo.json";  // JSON 파일 경로
	    Gson gson = new Gson();
	    Type type = new TypeToken<Map<String, User>>() {}.getType();
	    Map<String, User> users = null;

	    try {
	        // JSON 파일 읽기
	        FileReader reader = new FileReader(filePath);
	        users = gson.fromJson(reader, type);
	        reader.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("회원 정보 파일을 읽는 중 문제가 발생했습니다.");
	        return;
	    }

	    // 사용자 이름과 ID 중복 검사
	    while (true) {
	        System.out.print("이름을 입력하세요: ");
	        name = scanner.nextLine();
	        System.out.print("아이디를 입력하세요: ");
	        id = scanner.nextLine();

	        boolean idExists = users != null && users.containsKey(id);
	        boolean nameExists = false;
	        
	        if (users != null) {
	            for (User user : users.values()) {
	                if (user.getName().equals(name)) {
	                    nameExists = true;
	                    break;
	                }
	            }
	        }

	        if (idExists) {
	            System.out.println("이미 사용 중인 아이디입니다. 다른 아이디를 입력해주세요.");
	        } else if (nameExists) {
	            System.out.println("이미 사용 중인 이름입니다. 다른 이름을 입력해주세요.");
	        } else {
	            break;
	        }
	    }

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

	    String mileage = "bronze";
	    User newUser = new User();
	    newUser.setId(id);
	    newUser.setName(name);
	    newUser.setPassword(password);
	    newUser.setMileage(mileage);

	    // 새로운 사용자 추가
	    users.put(newUser.getId(), newUser);  // User 객체의 id를 키로 사용

	    try {
	        // JSON 파일로 다시 저장
	        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
	        FileWriter writer = new FileWriter(filePath);
	        prettyGson.toJson(users, writer);
	        writer.close();

	        System.out.println("회원가입이 완료되었습니다!");
	        log.print();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("회원 정보를 저장하는 중 문제가 발생했습니다.");
	    }
	}

	
	public void userLogin() {
		Login log = new Login();
        System.out.print("아이디를 입력하세요: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();
        String filePath = "resource/userinfo.json";  // JSON 파일 경로
        
        try {
            // JSON 파일 읽기
            FileReader reader = new FileReader(filePath);
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, User>>() {}.getType();
            Map<String, User> users = gson.fromJson(reader, type);
            
            reader.close();
            // Map을 순회하며 id와 password 비교
            if (users.containsKey(id)) {  // 입력된 id가 Map에 있는지 확인
                User user = users.get(id);  // 해당 id의 User 객체 가져오기
             
                System.out.println(user);
                
                if (user.getPassword().equals(password)) {  // 비밀번호 비교
                    System.out.println("    로그인 성공!    \n" +
                                       "환영합니다, " + user.getName() + "님.\n\n");
                    SelectMovie sm= new SelectMovie(user);
                    sm.print();
                } else {
                    System.out.println("비밀번호가 일치하지 않습니다. 다시 시도해 주세요.");
                    log.print();
                }
            } else {
                System.out.println("입력한 아이디가 존재하지 않습니다.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
	}
}
