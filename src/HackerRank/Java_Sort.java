// https://www.hackerrank.com/challenges/java-sort/problem

package HackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Java_Sort {
    static class Student {
        private int id;
        private String fname;
        private double cgpa;

        public Student(int id, String fname, double cgpa) {
            super();
            this.id = id;
            this.fname = fname;
            this.cgpa = cgpa;
        }

        public int getId() {
            return id;
        }

        public String getFname() {
            return fname;
        }

        public double getCgpa() {
            return cgpa;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());

        List<Student> studentList = new ArrayList<Student>();
        while (testCases > 0) {
            int id = in.nextInt();
            String fname = in.next();
            double cgpa = in.nextDouble();

            Student st = new Student(id, fname, cgpa);
            studentList.add(st);

            testCases--;
        }
        // sort: Cgpa(Double) cao xuong thap, Fname(String) thu tu tu dien, Id(int) thap den cao
        // return -1 là đứng trước, 1 là đứng sau, 0 là bằng

//        Collections.sort(studentList, new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                if (o1.getCgpa() > o2.getCgpa()) return -1;
//                if (o1.getCgpa() < o2.getCgpa()) return 1;
//                if (o1.getFname().compareTo(o2.getFname()) > 0) return 1;
//                if (o1.getFname().compareTo(o2.getFname()) < 0) return -1;
//                return Integer.compare(o1.getId(), o2.getId());
//            }
//        });

//        studentList.sort(new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                if (o1.getCgpa() > o2.getCgpa()) return -1;
//                if (o1.getCgpa() < o2.getCgpa()) return 1;
//                if (o1.getFname().compareTo(o2.getFname()) > 0) return 1;
//                if (o1.getFname().compareTo(o2.getFname()) < 0) return -1;
//                return Integer.compare(o1.getId(), o2.getId());
//            }
//        });
        studentList.sort((o1, o2) -> {
            if (o1.getCgpa() > o2.getCgpa()) return -1;
            if (o1.getCgpa() < o2.getCgpa()) return 1;
            if (o1.getFname().compareTo(o2.getFname()) > 0) return 1;
            if (o1.getFname().compareTo(o2.getFname()) < 0) return -1;
            return Integer.compare(o1.getId(), o2.getId());
        });

        for (Student st : studentList) {
            System.out.println(st.getFname());
        }
    }
}
