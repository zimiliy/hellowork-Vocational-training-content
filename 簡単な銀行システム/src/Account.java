public class Account {
    private String CardId;//?号
    private String UserWord;//客?名称
    private String PassWord;//密?
    private double Money;//余?
    private double QuotaMoney;//当次取?限?

    //无参函数
    public Account() {
    }

    //    ?造好了有参函数，那?就会有无参函数
//    有参函数
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
