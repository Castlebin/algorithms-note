package test;

class User {
    private String name = "user";

    public User() {
        System.out.println("init user..");
    }

    public void print() {
        System.out.println(name);
    }
}

class Student extends User {
    private String name = "student";

    public Student() {
        System.out.println("init student..");
    }

    @Override
    public void print() {
        System.out.println(name + ", s");
        super.print();
    }

}

class TestStudent {
    public static void main(String[] args) {
        new Student().print();
    }
}