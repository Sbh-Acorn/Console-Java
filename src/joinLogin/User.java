package joinLogin;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String password;
    private String name;
    private AgeGroup ageGroup;
    private String grade;
    private List<String> reservations;  // 예매 내역 저장

    public enum AgeGroup {
        ADULT("성인"),
        TEENAGER("청소년"),
        CHILD_SENIOR("아이/노인"),
        UNKNOWN("미정");

        private String description;

        AgeGroup(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public User(String id, String password, String name, AgeGroup ageGroup, String grade) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.ageGroup = ageGroup;
        this.grade = grade;
        this.reservations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public List<String> getReservations() {
        return reservations;
    }

    public void addReservation(String reservation) {
        this.reservations.add(reservation);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
