package Model;

import java.util.List;

public class testOutput {
    public static void main(String[] args) {
        Person person = new Person("123456789012","GG","QQ","1234567890","jjjjjjjj@jj.com","1999-09-09",false);

        Form form = new Form(person,"2000-09-09",new Vaccine("Pfizer","123","123"),true,true,true,true,true,true);
        Form form2 = null;
//        form.addNewForm(form);

        List<Form> forms = form.read();
        for(Form form1:forms){
            if(form1.getVisitor().getFirstName().equals("GG")){
                form2=form1;
            }
        }
//        System.out.println(form2.getVisitor().getFirstName());
        System.out.println(form2.toString());
    }
}
