import java.util.Stack;

public class Sort_a_Stack {

    // https://www.youtube.com/watch?v=vFOY8rd_Bcw
    public static Stack<Integer> sortStack(Stack<Integer> input) {
        Stack<Integer> result = new Stack<>();
        while(!input.empty()) {
            int temp = input.pop();
            while (!result.empty() && result.peek() > temp) {
                input.push(result.pop());
            }
            result.push(temp);
        }
        return result;
    }

    // https://www.youtube.com/watch?v=lDThYwMDNTU
    public static void sortStack1(Stack<Integer> input) {
        if (input.empty()) return;
        int temp = input.pop();
        sortStack1(input);
        insertAtCorrectPos(input, temp);
    }

    public static void insertAtCorrectPos(Stack<Integer> input, int temp) {
        if (input.empty() || input.peek() <= temp) {
            input.push(temp);
            return;
        }
        int lastElement = input.pop();
        insertAtCorrectPos(input, temp);
        input.push(lastElement);
    }

    public static void main(String[] args) {
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < 20; i++)
            // stk.push(20 - i - 1);
            stk.push((int)(Math.random() * 100));

        System.out.println(stk);
        // stk = sortStack(stk);
        sortStack1(stk);
        System.out.println(stk);
    }
}
