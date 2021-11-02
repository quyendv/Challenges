package HackerRank;

// https://www.hackerrank.com/challenges/java-priority-queue/problem

import java.util.*;

public class Java_Priority_Queue {
    static class Student {
        private final int ID;
        private final String name;
        private final double cgpa;

        public Student(int ID, String name, double cgpa) {
            this.ID = ID;
            this.name = name;
            this.cgpa = cgpa;
        }

        public int getID() {
            return ID;
        }

        public String getName() {
            return name;
        }

        public double getCgpa() {
            return cgpa;
        }
    }

    static class Priorities {
        public static List<Student> getStudents(List<String> events) {
            Queue<Student> student_queue = new PriorityQueue<>(
                    Comparator.comparing(Student::getCgpa).reversed().thenComparing(Student::getName).thenComparing(Student::getID)
            );
            for (String e : events) {
                Scanner sc = new Scanner(e);    // name cgpa id
                String event = sc.next();
                if (event.equals("ENTER")) {
                    String name = sc.next();
                    double cgpa = sc.nextDouble();
                    int id = sc.nextInt();
                    student_queue.add(new Student(id, name, cgpa));
                } else if (event.equals("SERVED")) {
                    student_queue.poll();
                }
                sc.close();
            }
            // return new ArrayList<>(student_queue); // sai vì pq không phải sắp xếp mà chỉ duy trì thứ tự lấy ra
            List<Student> ans = new ArrayList<>();
            while (!student_queue.isEmpty()) {
                ans.add(student_queue.poll());
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();

        List<String> events = new ArrayList<>();
        while (T-- > 0) {
            events.add(sc.nextLine());
        }
        List<Student> students = Priorities.getStudents(events);
        if (students.isEmpty()) System.out.println("EMPTY");
        else {
            for (Student i : students)
                System.out.println(i.getName());
        }

//        // Queue<Student> students_queue = new PriorityQueue<>(
//        //         Comparator.comparing(Student::getCgpa).reversed().thenComparing(Student::getName).thenComparing(Student::getID)
//        // );
//        Queue<Student> students_queue = new PriorityQueue<Student>((o1, o2) -> {
//            if (o1.getCgpa() > o2.getCgpa()) return -1;
//            if (o1.getCgpa() < o2.getCgpa()) return 1;
//            if (o1.getName().compareTo(o2.getName()) < 0) return -1;
//            if (o1.getName().compareTo(o2.getName()) > 0) return 1;
//            return Integer.compare(o1.getID(), o2.getID());
//        });
//
//        while (T-- > 0) {
//            String query = sc.next();
//            if (query.equals("ENTER")) {
//                String name = sc.next();
//                double cgpa = sc.nextDouble();
//                int id = sc.nextInt();
//                students_queue.offer(new Student(id, name, cgpa));
//            } else if (query.equals("SERVED")) {
//                students_queue.poll();
//            }
//        }
//        if (students_queue.isEmpty()) {
//            System.out.println("EMPTY");
//        } else {
//            while (!students_queue.isEmpty()) {
//                System.out.println(students_queue.poll().getName());
//            }
//        }
    }
}
