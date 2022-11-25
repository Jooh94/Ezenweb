package com.Ezenweb.domain.test.연관객체;

public class 테스트 {
    public static void main(String[] args) {
        // 1. 학급 생성
        학급 학급1 = new 학급();
        학급1.학급명="초등학생";

        // 2. 학생 생성
        학생 학생1 = new 학생();
        학생1.학생명 = "유재석";

        // 2. 학생 생성
        학생 학생2 = new 학생();
        학생2.학생명 = "신동엽";
        // 2. 학생 생성
        학생 학생3 = new 학생();
        학생3.학생명 = "강호동";

        //3. 단방향
        학생1.학급= 학급1;
        //4. 양방향
        학급1.학생리스트.add(학생2);

        //5. JoIN기능
            //1. 학급에서 학생 조회가능
        System.out.println(학급1.학생리스트.get(0).학생명);
            //2. 학생이 학급조회가능
        System.out.println(학생1.학급.학급명);
    }
}
