package atm;

public class Account {
    private String name;
    private String cardId;
    private String passWord;
    private char sex;
    private double cash;
    private double limitCash;

    public Account() {
    }

    public Account(String name, String cardId, String passWord, char sex, double cash, double limitCash) {
        this.name = name;
        this.cardId = cardId;
        this.passWord = passWord;
        this.sex = sex;
        this.cash = cash;
        this.limitCash = limitCash;
    }

    public String getName() {
        return sex == '男'?"Mr."+name:"Mrs."+name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getLimitCash() {
        return limitCash;
    }

    public void setLimitCash(double limitCash) {
        this.limitCash = limitCash;
    }
}
