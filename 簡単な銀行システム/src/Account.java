public class Account {
    private String CardId;//?��
    private String UserWord;//�q?����
    private String PassWord;//��?
    private double Money;//�]?
    private double QuotaMoney;//������?��?

    //�َQ����
    public Account() {
    }

    //    ?���D���L�Q�����C��?�A��L�َQ����
//    �L�Q����
    public Account(String cardId, String userWord, String passWord, double quotaMoney) {
        CardId = cardId;
        UserWord = userWord;
        PassWord = passWord;
        QuotaMoney = quotaMoney;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getUserWord() {
        return UserWord;
    }

    public void setUserWord(String userWord) {
        UserWord = userWord;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double money) {
        Money = money;
    }

    public double getQuotaMoney() {
        return QuotaMoney;
    }

    public void setQuotaMoney(double quotaMoney) {
        QuotaMoney = quotaMoney;
    }
}
