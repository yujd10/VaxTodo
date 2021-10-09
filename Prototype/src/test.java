public class test {
    public static void main(String[] args) {
        Patient p1 = new Patient("gg", "Male", 19);
        p1.setFirstDose(new Vaccine("Pfizer"));
        System.out.println(p1.getFirstDose().name);
    }
}
