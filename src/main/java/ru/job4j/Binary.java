package ru.job4j;

public class Binary {
    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(Binary.binary((int) Math.pow(2, 32) + 1));
        System.out.println(Binary.binary(Integer.MAX_VALUE));
        System.out.println(Binary.binary(Integer.MIN_VALUE));
    }
}
