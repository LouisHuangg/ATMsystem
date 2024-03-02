package atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    private ArrayList<Account> accountArrayList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private Account loginAccount = new Account();

    public void start(){
        System.out.println("1.Register");
        System.out.println("2.Login");
        System.out.println("0.exit");

        while (true)
        {
            int command = sc.nextInt();
            switch (command)
            {
                case 1:
                    RegisterSystem();
                case 2:
                    LoginSystem();
                case 0:
                    return;
                default:
                    System.out.println("Wrong input!");
                    System.out.println("Please input again!");
                    break;
            }
        }
    }

    public void RegisterSystem(){
        System.out.println("1.create account");
        System.out.println("0.exit this page");


        while (true)
        {
            int RegisterCommand = sc.nextInt();
            switch (RegisterCommand)
            {
                case 1:
                    createAccount();
                case 0:
                    return;
                default:
                    System.out.println("Wrong input!");
                    System.out.println("Please input again!");
                    break;
            }
        }
    }

    private void createAccount(){
        //创建用户对象用于封装数据
        Account newAccount = new Account();
        //获取姓名
        System.out.println("input your name");
        String name = sc.next();
        newAccount.setName(name);

        //获取性别
        while (true)
        {
            System.out.println("input your sex");
            char sex = sc.next().charAt(0);
            if (sex == '男'||sex =='女')
            {
                newAccount.setSex(sex);
                break;
            }else {
                System.out.println("Wrong input!");
                System.out.println("Please input again!");
            }
        }

        //创建卡号
        String CardId = createCardId();
        newAccount.setCardId(CardId);

        System.out.println("Your cardID:"+newAccount.getCardId());

        //设置密码
        System.out.println("input your passWord");
        String passWord = sc.next();
        while (true)
        {
            System.out.println("please input your passWord again");
            String passWord2 = sc.next();
            if (passWord.equals(passWord2))
            {
                newAccount.setPassWord(passWord);
                break;
            }else {
                System.out.println("your second password is different from your first password");
            }
        }

        //设置转账限额
        System.out.println("input your transfer limitation");
        int limitCash = sc.nextInt();
        newAccount.setLimitCash(limitCash);

        accountArrayList.add(newAccount);
        System.out.println("Register success!");
    }

    private String createCardId(){
        while (true)
        {
            String cardId = "312300";
            Random r = new Random();
            for (int i = 0; i < 4; i++) {
                int index =r.nextInt(10);
                cardId += index;
            }
            //检查新创建的卡号是否重复
            Account account = checkCardId(cardId);

            if (account == null)
            {
                return cardId;
            }
        }
    }

    private Account checkCardId(String cardId){
        for (int i = 0; i < accountArrayList.size(); i++) {
            Account account = accountArrayList.get(i);
            if (account.getCardId().equals(cardId))
            {
                return account;
            }
        }
        return null;
    }

    public void LoginSystem(){
        login();

        System.out.println("1.show your account");
        System.out.println("2.save money");
        System.out.println("3.draw money");
        System.out.println("4.transfer");
        System.out.println("5.modify your password");
        System.out.println("6.delete your account");
        System.out.println("0.exit");

        int loginCommand = sc.nextInt();
        while (true)
        {
            switch (loginCommand)
            {
                case 1:
                    showAccount();
                    break;
                case 2:
                    saveMoney();
                    break;
                case 3:
                    drawMoney();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    modifyPassWord();
                    break;
                case 6:
                    deleteAccount();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input!");
                    System.out.println("Please input again!");
                    break;
            }
        }
    }

    private void login(){
        if (accountArrayList.size() == 0)
        {
            System.out.println("the atm don`t have an account,please register");

            return;
        }

        while(true)
        {
            System.out.println("inout your cardId");
            String cardId = sc.next();
            Account account = checkCardId(cardId);

            if (account == null)
            {
                System.out.println("Not found this cardId,please try again");
                return;
            }else {
                while (true)
                {
                    int time = 0;
                    if (cardId == account.getCardId())
                    {
                        System.out.println("input your password");
                        String password = sc.next();

                        if (password == account.getPassWord())
                        {
                            System.out.println("welcome," + account.getName());
                            loginAccount = account;
                            break;
                        }else {
                            System.out.println("Your password is wrong,please try again");
                            time += 1;
                        }
                    }
                    if (time >= 3)
                    {
                        System.out.println("Check your account");
                        return;
                    }
                }
            }

        }

    }

    private void showAccount(){
        System.out.println("account name:" + loginAccount.getName());
        System.out.println("your cardId:" + loginAccount.getCardId());
        System.out.println("cash:" + loginAccount.getCash());
        System.out.println("transfer limitation:" + loginAccount.getLimitCash());
    }

    private void saveMoney(){
        double originalCash = loginAccount.getCash();
        System.out.println("input your cash");
        double newCash = sc.nextDouble();

        loginAccount.setCash(originalCash + newCash);
    }

    private void drawMoney(){
        System.out.println("input your drawCash");
        double drawCash = sc.nextDouble();

        if (drawCash > loginAccount.getCash())
        {
            System.out.println("Your money isn`t enough");
        }else if (drawCash > loginAccount.getLimitCash()){
            System.out.println("the money is more than your money limitation");
        }else {
            loginAccount.setCash(loginAccount.getCash() - drawCash);
            System.out.println("drawMoney success!");
        }
    }

    private void transfer(){

    }

    private void modifyPassWord(){
        System.out.println("input your original password");
        String oldPassWord = sc.next();

        if (oldPassWord == loginAccount.getPassWord())
        {
            System.out.println("input your new password");
            String newPassWord = sc.next();

            while (true)
            {
                int modifyTime = 0;
                System.out.println("input your new password again");
                String newPassWord2 = sc.next();

                if (newPassWord.equals(newPassWord2)){
                    loginAccount.setPassWord(newPassWord);
                }else {
                    System.out.println("your second password is different from your first password");
                    modifyTime +=1;
                }
                if (modifyTime >= 3 )
                {
                    System.out.println("please try again");
                }
            }
        }
    }

    private void deleteAccount(){
        for (int i = 0; i < accountArrayList.size(); i++) {
            Account account = accountArrayList.get(i);

            if (loginAccount.getCardId() == account.getCardId())
            {
                int index = i;
                break;
            }
        }
        //accountArrayList.remove();
    }
}
