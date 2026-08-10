public class FommaterExample {
    public static void main(String[] args) {
        String name = "스칼라";
        int age = 30;
        String formatted = String.format("이름: %s, 나이: %d", name, age);
        System.out.println(formatted);

        double pi = 3.141592;
        System.out.println(String.format("원주율: %.2f", pi));  // 원주율: 3.14
        System.out.printf("|%10s|\n", "Java");  // 오른쪽 정렬
        System.out.printf("|%-10s|\n", "Java"); // 왼쪽 정렬
    }
}
