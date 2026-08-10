public class NumberBox {
    public static void main(String[] args) {
        int a = 100;
        int b = 100;

        Integer A = 200;
        Integer B = 200;

        // 기본형 비교 (값 비교)
        System.out.println("a == b: " + (a == b));
        
        // 래퍼 클래스 비교 (주소 비교)
        System.out.println("A == B: " + (A == B));
        // Java의 Integer는 -128 ~ 127 범위의 값을 캐싱 
        // => 100 이하의 동일한 값을 저장하는 래퍼는 동일하다는 결과가 나올 수 있음 !

        // 래퍼 클래스 equals()로 비교 (값 비교)
        System.out.println("A.equals(B): " + A.equals(B));
    }
}
