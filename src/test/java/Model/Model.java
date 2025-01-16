package Model;

public class Model {
    public String fName;
    public   String lName;
    public String email;
    public String password;
    public String phnNumber;
    public String add;
    public String gen;
    public boolean termsAccepted;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhnNumber() {
        return phnNumber;
    }

    public void setPhnNumber(String phnNumber) {
        this.phnNumber = phnNumber;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public boolean isTermsAccepted() {
        return termsAccepted;
    }

    public void setTermsAccepted(boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

    public Model(String email, String password){
        this.email=email;
        this.password=password;
    }

    public Model(String fName,String lName, String email, String password, String phnNumber, String add, String gen, boolean termsAccepted){
        this.fName=fName;
        this.lName=lName;
        this.email=email;
        this.password=password;
        this.phnNumber=phnNumber;
        this.add=add;
        this.gen=gen;
        this.termsAccepted=termsAccepted;
    }


    public Model(){

    }
}
