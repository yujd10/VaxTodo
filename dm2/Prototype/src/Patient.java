public class Patient {
    private String name;
    private String sex;
    private int age;
    private Vaccine firstDose;
    private Vaccine secondDose;

    public Patient(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.firstDose=null;
        this.secondDose=null;
    }

    //Setters
    public void setFirstDose(Vaccine firstDose) {
        if(this.getAge()<18){
            System.out.println("Le vaccin peut seulement être fait pour les adultes");
        }else if(this.getFirstDose()!=null){
            System.out.println("Le premier dose est déjà fait pour cette personne.");}
        else if(this.getAge()>=18 && this.getFirstDose()==null){
            this.firstDose = firstDose;}
    }

    public void setSecondDose() {
        if(this.getFirstDose()!=null){
            this.secondDose = this.firstDose;
        }
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Vaccine getFirstDose() {
        return firstDose;
    }

    public Vaccine getSecondDose() {
        return secondDose;
    }
}
