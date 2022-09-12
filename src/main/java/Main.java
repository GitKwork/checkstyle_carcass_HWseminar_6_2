/**
 * Реализовать алгоритм перевода из инфиксной записи в постфиксную для арифметического выражения.
 * Вычислить запись если это возможно
 */

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        boolean isFormatted = false;
        String request = "пожалуйста форматируйте код";
        // исправили теперь build succcessful
//        if (isFormatted) {
        String infix = "A+(B*C)-(D*E)+F/D";
        String postfix = infixToPostfix(infix);
        System.out.println(postfix);
//        }
    }

    public static int prec(char c) {
        if (c == '*' || c == '/') {
            return 3;
        }
        if (c == '+' || c == '-') {
            return 4;
        }
        if (c == '&') {
            return 8;
        }
        if (c == '^') {
            return 9;
        }
        if (c == '|') {
            return 10;
        }
        return Integer.MAX_VALUE;
    }

    public static boolean isOperand(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    public static String infixToPostfix(String infix) {
        if (infix == null || infix.length() == 0) {
            return infix;
        }
        Stack<Character> s = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        for (char c : infix.toCharArray()) {
            if (c == '(') {
                s.add(c);
            } else if (c == ')') {
                while (s.peek() != '(') {
                    postfix.append(s.pop());
                }
                s.pop();
            } else if (isOperand(c)) {
                postfix.append(c);
            } else {
                while (!s.isEmpty() && prec(c) >= prec(s.peek())) {
                    postfix.append(s.pop());
                }
                s.add(c);
            }
        }
        while (!s.isEmpty()) {
            postfix.append(s.pop());
        }
        return postfix.toString();
    }
}
