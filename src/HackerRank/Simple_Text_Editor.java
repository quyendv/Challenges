// https://www.hackerrank.com/challenges/simple-text-editor/problem
/*
    Nhập T test case: Cho S = ""
    Mỗi test: nhập số nguyên: nếu là
    1 -> nhập string w và insert w vào S
    2 -> nhập k, xóa k kí tự khói S
    3 -> nhập k, in S[k]
    4 -> undo S lại thao tác (thay đổi) trước (chỉ áp dụng cho 1 hoặc 2, 3 là print nên k undo về nó)
        ** Cách thông thường: ta tạo 1 stack, mỗi thao tác ta push S hiện tại, sau đó thay đổi S theo yêu cầu
                              khi undo chỉ cần gán S = stack.pop()
                              ==> Tuy nhiên : Runtime Error
        ** Tối ưu bộ nhớ: + ta dùng stack<Object> để push NHỮNG THAY ĐỔI vào stack (insert thì push k kí tự để xóa đi,
                                                                                delete thì push string cần delete đi)
                              ==> Tuy nhiên chỉ cải thiện được bộ nhớ
                          + Biển diễn S dưới dạng Stack<Character> hoặc StringBuilder
        ** Tối ưu: thay vì in ra ở thao tác 3, ta tạo 1 StringBuilder output để push kí tự cần in ở thao tác 3 và '\n'
        rồi sau vòng lặp ta sout(output)
            ==> k hiểu kiểu gì =))
*/
package HackerRank;

import java.util.Scanner;
import java.util.Stack;

public class Simple_Text_Editor {

    public static void main(String[] args) {
        MyTextEditor mte = new MyTextEditor();
        StringBuilder output = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            switch (sc.nextInt()) {
                case 1:
                    mte.insert(sc.next());
                    break;
                case 2:
                    mte.delete(sc.nextInt());
                    break;
                case 3:
                    output.append(mte.getAtIndex(sc.nextInt()));
                    output.append('\n');
                    break;
                case 4:
                    mte.undo();
                    break;
            }
        }
        System.out.println(output);
    }

    static class MyTextEditor {
        StringBuilder S = new StringBuilder();
        Stack<Object> historyStk = new Stack<Object>();

        public void insert(String str) {
            historyStk.push(str.length());
            S.append(str);
        }

        public void delete(int k) {
            historyStk.push(S.substring(S.length() - k));
            S.delete(S.length() - k, S.length());
        }

        public char getAtIndex(int idx) {
            return S.charAt(idx - 1);  
        }

        public void undo() {
            Object tmp = historyStk.pop();
            if (tmp instanceof Integer) {
                S.delete(S.length() - (Integer) tmp, S.length());
            } else {
                S.append((String)tmp);
            }
        }
    }

    static class MyTextEditor1 {
        Stack<Character> S = new Stack<>();
        Stack<Object> historyStk = new Stack<>();

        public void insert(String str) {
            historyStk.push(str.length());
            for (char c : str.toCharArray()) S.push(c);
        }

        public void delete(int k) {
            Stack<Character> reverseDeletedStr = new Stack<>();
            for (int i = 0; i < k; i++)
                reverseDeletedStr.push(S.pop());
            historyStk.push(reverseDeletedStr);
        }

        public char getAtIndex(int idx) {
            return S.get(idx - 1);
        }

        public void undo() {
            Object tmp = historyStk.pop();
            if (tmp instanceof Integer) {
                for (int i = 0; i < (Integer) tmp; i++)
                    S.pop();
            } else {
                Stack<Character> reverseDeletedStr = (Stack<Character>) tmp;
                while (!reverseDeletedStr.empty())
                    S.push(reverseDeletedStr.pop());
            }
        }
    }
}