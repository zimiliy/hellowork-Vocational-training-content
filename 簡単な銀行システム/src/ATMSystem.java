import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args)
    {
//        1.准?系?需要的容器?象，用?存????象
        ArrayList<Account> accounts = new ArrayList();

//        2.准?系?的首?，登入，??
        showMain(accounts);
    }
    public static void showMain(ArrayList<Account> accounts)
    //showMain ??首?的意思
//            ArrayList<Account> accounts   使用方法定?功能?入容器中  accounts是?参
    {
        System.out.println("=============ようこそ！架空の銀行===========");
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("何がしたいですか？：");
            System.out.println("1.ログイン");
            System.out.println("2.登録");
            System.out.println("1or2こちに入力してください：");
            int command = sc.nextInt();
            switch(command)
            {
                case 1:
                    //登?
                    login(accounts,sc);
                    break;
                case 2:
                    //??
                    register(accounts,sc);
                    break;
                default:
                    System.out.println("入力エラーです。");
            }
        }
    }
    /**
     * 完成用?登?
     * @param accounts
     */
    private static void login(ArrayList<Account> accounts,Scanner sc)
    {
        //必?系?中存在??才可以登?
        if (accounts.size() == 0)
        {
            //没有任何??
            System.out.println("システムには登録された口座がありません。");
            return;//直接?束方法的?行！
        }
        //2.?用????入?号，根据?号?????象
        while (true)
        {
            System.out.println("カード番号を入力してください：");
            String cardId = sc.next();
            //根据?号?????象
            Account acc = getAccountBycardId(cardId,accounts);
//        3.判断???象是否存在，存在?明?号没??
            if (acc != null)
            {
                while (true)
                {
//                    4.?用????入密?
                    System.out.println("パスワードを入力してください：");
                    String password = sc.next();
//              5.判断密?是否正?
                    if (acc.getPassWord().equals(password))
                    {
                        //密?正?，登入成功
                        //展示系?登?后的操作界面
                        System.out.println("おめでとうございます。" + acc.getUserWord() +",システムにログインしました。カード番号は" + acc.getCardId());
                        //展示操作?面
                        showUserCommand(sc,acc,accounts);
                        return;//???束登?方法
                    }
                    else
                    {
                        System.out.println("パスワードが間違っています。");

                    }
                }
            }
            else
            {
                System.out.println("すみません、このカード番号はありません！");
            }
        }
    }

    private static void showUserCommand(Scanner sc,Account acc,ArrayList<Account> accounts)
    {
        while (true)
        {
            System.out.println("=========操作ページ========");
            System.out.println("1.残高照会");
            System.out.println("2.入金");
            System.out.println("3.出金");
            System.out.println("4.送金");
            System.out.println("5.パスワード変更");
            System.out.println("6.ログアウト");
            System.out.println("7.退会");
            System.out.println("番号は入力してください：");
            int command = sc.nextInt();
            switch (command)
            {
                case 1:
                    //????
                    showAccount(acc);
                    break;
                case 2:
                    //存款
                    depositMoney(acc,sc);
                    break;
                case 3:
                    //取款
                    drawMoney(acc,sc);
                    break;
                case 4:
                    //??
                    transferMoney(accounts,acc ,sc);
                    break;
                case 5:
                    //修改密?
                    updataPassWord(acc,sc);
                    return;//?束当前…………
                case 6:
                    //退出
                    System.out.println("ありがとうございました。またお越しくださいませ！！");
                    return; //?束当前showUserCommand(Scanner sc,Account acc)的方法
                case 7:
                    //注???
                    //从当前集合中抹掉当前???象即可
                    accounts.remove(acc);
                    System.out.println("退会されました。！！");
                    return;
                default:
                    System.out.println("?入力間違いました。！");
            }
        }
    }

    /**
     * 修改密?
     * @param acc
     */
    private static void updataPassWord(Account acc,Scanner sc)
    {
        System.out.println("===========パスワード変更=========");
        while (true)
        {
            System.out.println("正しいパスワードを入力してください：");
            String okPassWord = sc.next();
            //判断密?是否正?
            if (acc.getPassWord().equals(okPassWord))
            {
                //可以?入新密?
                System.out.println("新しいパスワードを入力してください：");
                String newPassWord = sc.next();

                System.out.println("もう一度新しいパスワードを入力してください：");
                String okNewPassWord = sc.next();

                if (newPassWord.equals(okNewPassWord))
                {
                    //修改???象的密??新密?
                    acc.setPassWord(newPassWord);
                    return;//直接?束方法！
                }
                else
                {
                    System.out.println("パスワードは一致ではないです。~~");
                }
            }
            else
            {
                System.out.println("パスワードは間違いました。~~~");
            }
        }

    }

    /**
     * ??功能
     * @param accounts
     * @param acc
     * @param sc
     */
    private static void transferMoney(ArrayList<Account> accounts, Account acc, Scanner sc)
    {
        //1.判断系?中是否有2个??及以上
        if (accounts.size() < 2)
        {
            System.out.println("すみません！他の口座がないので、ご送金出来ません！！");
            return;
        }

        //2.判断自己的???象中是否有?
        if (acc.getMoney() == 0)
        {
            System.out.println("すみません、残高不足です！！");
            return;
        }

        //3.?始????
        while (true)
        {
            System.out.println("送金カード番号を入力してください：");
            String cardId = sc.next();
            Account account = getAccountBycardId(cardId,accounts);
            //判断整个???象是否存在，存在?明?方?号?入正?
            if (account != null)
            {
                //判断?个???象是否是当前自己登?的??
                if (account.getCardId().equals(acc.getCardId()))
                {
                    //也就是?里企?想?自己??
                    System.out.println("自分に送金出来ません！");
                }
                else
                {
                    //???方的姓氏
                    String name = "*" + account.getUserWord().substring(1);
                    System.out.println("【"+name + "】名前を確認してください：");
                    String preName = sc.next();
                    //判断
                    if (account.getUserWord().startsWith(preName))
                    {
                        //真正的??才???始
                        System.out.println("金額を入力してください：");
                        double money = sc.nextDouble();
                        //判断?个金?是否超?了自己的金?
                        if (money > acc.getMoney())
                        {
                            System.out.println("すみません、送金の金額は：" + acc.getMoney()+"円以下にしてください！！");
                        }
                        else
                        {
                            //?始了
                            acc.setMoney(acc.getMoney() - money);
                            account.setMoney(account.getMoney() + money);
                            System.out.println("ご送金完了。" + account.getUserWord() + "に：" + money+"円を送金しました！！");
                            showAccount(acc);
                            return;

                        }
                    }
                    else
                    {
                        System.out.println("すみません、情報が間違いました。~~~");
                    }
                }

            }
            else
            {
                System.out.println("すみません、カード番号が間違いました。！！");

            }
        }
    }

    /**
     * 取款操作
     * @param acc
     * @param sc
     */
    private static void drawMoney(Account acc, Scanner sc)
    {
        System.out.println("==========出金=========");
        //1.判断它的??是否足?100元
        if (acc.getMoney() >= 1)
        {
            while (true) {
                System.out.println("出金の金額を入力してください：");
                double money = sc.nextDouble();
                //2.判断整个金?有没有超?当次限?
                if (money > acc.getQuotaMoney())
                {
                    System.out.println("すみません、：" + acc.getQuotaMoney()+"円以下にしてください！！");
                }
                else
                {
                    //3.判断当前余?是否足??取?
                    if (acc.getMoney() >= money)
                    {
                        //?了，可以取?了
                        acc.setMoney(acc.getMoney() - money);
                        System.out.println( money + "を出金して、残高は：" + acc.getMoney()+"円です！！");
                        return;//取?后干掉了取?方法
                    }
                    else
                    {
                        System.out.println("残高不足！！");
                    }
                }
            }
        }
        else
        {
            System.out.println("残高が1円でもない、仕事を頑張ってください~~~");
        }

    }

    /**
     * ??存?的
     * @param acc
     */
    private static void depositMoney(Account acc,Scanner sc)
    {
        System.out.println("===========ご入金=========");
        System.out.println("入金金額を入力してください：");
        double money = sc.nextDouble();

        //直接把金?修改到???象的money属性中去
        acc.setMoney(acc.getMoney() + money);
        System.out.println("入金完了！");
        showAccount(acc);

    }

    private static void showAccount(Account acc)
    {
        System.out.println("===========口座情報=========");
        System.out.println("カード番号" + acc.getCardId());
        System.out.println("姓名" + acc.getUserWord());
        System.out.println("残高" + acc.getMoney());
        System.out.println("一日落ち限額：" + acc.getQuotaMoney());

    }
    /**
     * 用???功能
     * @param accounts ??的集合?象
     */
    private static void register(ArrayList<Account> accounts, Scanner sc)
    {
        System.out.println("=========登録ページ==========");
        //???入 姓名 密? ??密?
        System.out.println("名前を入力してください：");
        String name = sc.next();
        String password = "";
        while (true)
        {
            System.out.println("パスワードを入力してください：");
            password = sc.next();
            System.out.println("もう一度パスワードを入力してください：");
            String okPassword = sc.next();

//        判断?次?入的密?是否一致
            if (okPassword.equals(password))
            //字符串比?用equals
            {
                break;
            }
            else
            {
                System.out.println("パスワードは一致ではありません~~~");
            }
        }
        System.out.println("一回の限額：");
        double quotaMoney = sc.nextDouble();

//        3.生成??的?号，?号是8位，而且不能与其他???号重?。
        String cardId = creatCardId(accounts);
//        4.?建一个???象封装??的信息
//        public Account(String cardId, String userWord, String passWord, double money, double quotaMoney)
        Account account = new Account(cardId,name,password,quotaMoney);

//        5.把???象添加到集合中去
        accounts.add(account);
        System.out.println("登録成功しました、カード番号：" + account.getCardId() + ",しっかり保管してください");
    }

    public static String creatCardId(ArrayList<Account> accouts)
    {
        while (true) {
//        生成8位随机的数字代表?号
            String cardId = "";
            Random r = new Random();
            for (int i = 0; i < 8; i++)
            {
                cardId += r.nextInt(10);
            }

//        判断?号是否重?了
            Account acc = getAccountBycardId(cardId,accouts);
            if (acc == null)
            {
                //            ?明当前?号没有重?
                return cardId;
            }
        }
    }

    public static Account getAccountBycardId(String cardId, ArrayList<Account> accounts)
    {
//        根据?号???象
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getCardId().equals(cardId))
            {
                return acc;
            }
        }
        return null;
        //?无此??，?明?号没有重?了！
    }
}