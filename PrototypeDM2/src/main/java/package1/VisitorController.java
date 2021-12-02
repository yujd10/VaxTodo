package package1;

public class VisitorController {
    public Visitor creatVisitorAccount(){return new Visitor();}
    public void modifyVisitorAccount(){}
    public Visitor deleteVisitorAccount(Visitor visitor){return visitor;}
}
