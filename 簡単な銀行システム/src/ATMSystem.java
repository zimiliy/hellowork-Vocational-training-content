import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args)
    {
//        1.�y?�n?���v�I�e��?�ہC�p?��????��
        ArrayList<Account> accounts = new ArrayList();

//        2.�y?�n?�I��?�C�o���C??
        showMain(accounts);
    }
    public static void showMain(ArrayList<Account> accounts)
    //showMain ??��?�I�ӎv
//            ArrayList<Account> accounts   �g�p���@��?���\?���e�풆  accounts��?�Q
    {
        System.out.println("=============�悤�����I�ˋ�̋�s===========");
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("�����������ł����H�F");
            System.out.println("1.���O�C��");
            System.out.println("2.�o�^");
            System.out.println("1or2�����ɓ��͂��Ă��������F");
            int command = sc.nextInt();
            switch(command)
            {
                case 1:
                    //�o?
                    login(accounts,sc);
                    break;
                case 2:
                    //??
                    register(accounts,sc);
                    break;
                default:
                    System.out.println("���̓G���[�ł��B");
            }
        }
    }
    /**
     * �����p?�o?
     * @param accounts
     */
    private static void login(ArrayList<Account> accounts,Scanner sc)
    {
        //�K?�n?������??�ˉȓo?
        if (accounts.size() == 0)
        {
            //�v�L�C��??
            System.out.println("�V�X�e���ɂ͓o�^���ꂽ����������܂���B");
            return;//����?�����@�I?�s�I
        }
        //2.?�p????��?���C����?��?????��
        while (true)
        {
            System.out.println("�J�[�h�ԍ�����͂��Ă��������F");
            String cardId = sc.next();
            //����?��?????��
            Account acc = getAccountBycardId(cardId,accounts);
//        3.���f???�ې��ۑ��݁C����?��?���v??
            if (acc != null)
            {
                while (true)
                {
//                    4.?�p????����?
                    System.out.println("�p�X���[�h����͂��Ă��������F");
                    String password = sc.next();
//              5.���f��?���ې�?
                    if (acc.getPassWord().equals(password))
                    {
                        //��?��?�C�o������
                        //�W���n?�o?�@�I����E��
                        System.out.println("���߂łƂ��������܂��B" + acc.getUserWord() +",�V�X�e���Ƀ��O�C�����܂����B�J�[�h�ԍ���" + acc.getCardId());
                        //�W������?��
                        showUserCommand(sc,acc,accounts);
                        return;//???���o?���@
                    }
                    else
                    {
                        System.out.println("�p�X���[�h���Ԉ���Ă��܂��B");

                    }
                }
            }
            else
            {
                System.out.println("���݂܂���A���̃J�[�h�ԍ��͂���܂���I");
            }
        }
    }

    private static void showUserCommand(Scanner sc,Account acc,ArrayList<Account> accounts)
    {
        while (true)
        {
            System.out.println("=========����y�[�W========");
            System.out.println("1.�c���Ɖ�");
            System.out.println("2.����");
            System.out.println("3.�o��");
            System.out.println("4.����");
            System.out.println("5.�p�X���[�h�ύX");
            System.out.println("6.���O�A�E�g");
            System.out.println("7.�މ�");
            System.out.println("�ԍ��͓��͂��Ă��������F");
            int command = sc.nextInt();
            switch (command)
            {
                case 1:
                    //????
                    showAccount(acc);
                    break;
                case 2:
                    //����
                    depositMoney(acc,sc);
                    break;
                case 3:
                    //�押
                    drawMoney(acc,sc);
                    break;
                case 4:
                    //??
                    transferMoney(accounts,acc ,sc);
                    break;
                case 5:
                    //�C����?
                    updataPassWord(acc,sc);
                    return;//?�����O�c�c�c�c
                case 6:
                    //�ޏo
                    System.out.println("���肪�Ƃ��������܂����B�܂����z�����������܂��I�I");
                    return; //?�����OshowUserCommand(Scanner sc,Account acc)�I���@
                case 7:
                    //��???
                    //�����O�W�������{���O???�ۑ���
                    accounts.remove(acc);
                    System.out.println("�މ��܂����B�I�I");
                    return;
                default:
                    System.out.println("?���͊ԈႢ�܂����B�I");
            }
        }
    }

    /**
     * �C����?
     * @param acc
     */
    private static void updataPassWord(Account acc,Scanner sc)
    {
        System.out.println("===========�p�X���[�h�ύX=========");
        while (true)
        {
            System.out.println("�������p�X���[�h����͂��Ă��������F");
            String okPassWord = sc.next();
            //���f��?���ې�?
            if (acc.getPassWord().equals(okPassWord))
            {
                //��?���V��?
                System.out.println("�V�����p�X���[�h����͂��Ă��������F");
                String newPassWord = sc.next();

                System.out.println("������x�V�����p�X���[�h����͂��Ă��������F");
                String okNewPassWord = sc.next();

                if (newPassWord.equals(okNewPassWord))
                {
                    //�C��???�ۓI��??�V��?
                    acc.setPassWord(newPassWord);
                    return;//����?�����@�I
                }
                else
                {
                    System.out.println("�p�X���[�h�͈�v�ł͂Ȃ��ł��B~~");
                }
            }
            else
            {
                System.out.println("�p�X���[�h�͊ԈႢ�܂����B~~~");
            }
        }

    }

    /**
     * ??���\
     * @param accounts
     * @param acc
     * @param sc
     */
    private static void transferMoney(ArrayList<Account> accounts, Account acc, Scanner sc)
    {
        //1.���f�n?�����ۗL2��??�y�ȏ�
        if (accounts.size() < 2)
        {
            System.out.println("���݂܂���I���̌������Ȃ��̂ŁA�������o���܂���I�I");
            return;
        }

        //2.���f���ȓI???�ے����ۗL?
        if (acc.getMoney() == 0)
        {
            System.out.println("���݂܂���A�c���s���ł��I�I");
            return;
        }

        //3.?�n????
        while (true)
        {
            System.out.println("�����J�[�h�ԍ�����͂��Ă��������F");
            String cardId = sc.next();
            Account account = getAccountBycardId(cardId,accounts);
            //���f����???�ې��ۑ��݁C����?��?��?��?����?
            if (account != null)
            {
                //���f?��???�ې��ې����O���ȓo?�I??
                if (account.getCardId().equals(acc.getCardId()))
                {
                    //��A��?����?�z?����??
                    System.out.println("�����ɑ����o���܂���I");
                }
                else
                {
                    //???���I����
                    String name = "*" + account.getUserWord().substring(1);
                    System.out.println("�y"+name + "�z���O���m�F���Ă��������F");
                    String preName = sc.next();
                    //���f
                    if (account.getUserWord().startsWith(preName))
                    {
                        //�^���I??��???�n
                        System.out.println("���z����͂��Ă��������F");
                        double money = sc.nextDouble();
                        //���f?����?���ے�?�����ȓI��?
                        if (money > acc.getMoney())
                        {
                            System.out.println("���݂܂���A�����̋��z�́F" + acc.getMoney()+"�~�ȉ��ɂ��Ă��������I�I");
                        }
                        else
                        {
                            //?�n��
                            acc.setMoney(acc.getMoney() - money);
                            account.setMoney(account.getMoney() + money);
                            System.out.println("�����������B" + account.getUserWord() + "�ɁF" + money+"�~�𑗋����܂����I�I");
                            showAccount(acc);
                            return;

                        }
                    }
                    else
                    {
                        System.out.println("���݂܂���A��񂪊ԈႢ�܂����B~~~");
                    }
                }

            }
            else
            {
                System.out.println("���݂܂���A�J�[�h�ԍ����ԈႢ�܂����B�I�I");

            }
        }
    }

    /**
     * �押����
     * @param acc
     * @param sc
     */
    private static void drawMoney(Account acc, Scanner sc)
    {
        System.out.println("==========�o��=========");
        //1.���f���I??���ۑ�?100��
        if (acc.getMoney() >= 1)
        {
            while (true) {
                System.out.println("�o���̋��z����͂��Ă��������F");
                double money = sc.nextDouble();
                //2.���f������?�L�v�L��?������?
                if (money > acc.getQuotaMoney())
                {
                    System.out.println("���݂܂���A�F" + acc.getQuotaMoney()+"�~�ȉ��ɂ��Ă��������I�I");
                }
                else
                {
                    //3.���f���O�]?���ۑ�??��?
                    if (acc.getMoney() >= money)
                    {
                        //?���C�Ȏ�?��
                        acc.setMoney(acc.getMoney() - money);
                        System.out.println( money + "���o�����āA�c���́F" + acc.getMoney()+"�~�ł��I�I");
                        return;//��?�@���{����?���@
                    }
                    else
                    {
                        System.out.println("�c���s���I�I");
                    }
                }
            }
        }
        else
        {
            System.out.println("�c����1�~�ł��Ȃ��A�d�����撣���Ă�������~~~");
        }

    }

    /**
     * ??��?�I
     * @param acc
     */
    private static void depositMoney(Account acc,Scanner sc)
    {
        System.out.println("===========������=========");
        System.out.println("�������z����͂��Ă��������F");
        double money = sc.nextDouble();

        //���ڔc��?�C����???�ۓImoney��������
        acc.setMoney(acc.getMoney() + money);
        System.out.println("���������I");
        showAccount(acc);

    }

    private static void showAccount(Account acc)
    {
        System.out.println("===========�������=========");
        System.out.println("�J�[�h�ԍ�" + acc.getCardId());
        System.out.println("����" + acc.getUserWord());
        System.out.println("�c��" + acc.getMoney());
        System.out.println("����������z�F" + acc.getQuotaMoney());

    }
    /**
     * �p???���\
     * @param accounts ??�I�W��?��
     */
    private static void register(ArrayList<Account> accounts, Scanner sc)
    {
        System.out.println("=========�o�^�y�[�W==========");
        //???�� ���� ��? ??��?
        System.out.println("���O����͂��Ă��������F");
        String name = sc.next();
        String password = "";
        while (true)
        {
            System.out.println("�p�X���[�h����͂��Ă��������F");
            password = sc.next();
            System.out.println("������x�p�X���[�h����͂��Ă��������F");
            String okPassword = sc.next();

//        ���f?��?���I��?���ۈ�v
            if (okPassword.equals(password))
            //��������?�pequals
            {
                break;
            }
            else
            {
                System.out.println("�p�X���[�h�͈�v�ł͂���܂���~~~");
            }
        }
        System.out.println("���̌��z�F");
        double quotaMoney = sc.nextDouble();

//        3.����??�I?���C?����8�ʁC�����s�\�^����???���d?�B
        String cardId = creatCardId(accounts);
//        4.?���꘢???�ە���??�I�M��
//        public Account(String cardId, String userWord, String passWord, double money, double quotaMoney)
        Account account = new Account(cardId,name,password,quotaMoney);

//        5.�c???�ۓY�����W������
        accounts.add(account);
        System.out.println("�o�^�������܂����A�J�[�h�ԍ��F" + account.getCardId() + ",��������ۊǂ��Ă�������");
    }

    public static String creatCardId(ArrayList<Account> accouts)
    {
        while (true) {
//        ����8�ʐ����I������\?��
            String cardId = "";
            Random r = new Random();
            for (int i = 0; i < 8; i++)
            {
                cardId += r.nextInt(10);
            }

//        ���f?�����ۏd?��
            Account acc = getAccountBycardId(cardId,accouts);
            if (acc == null)
            {
                //            ?�����O?���v�L�d?
                return cardId;
            }
        }
    }

    public static Account getAccountBycardId(String cardId, ArrayList<Account> accounts)
    {
//        ����?��???��
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getCardId().equals(cardId))
            {
                return acc;
            }
        }
        return null;
        //?�ٍ�??�C?��?���v�L�d?���I
    }
}