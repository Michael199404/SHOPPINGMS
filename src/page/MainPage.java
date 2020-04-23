package page;

import dao.GoodsDao;
import dao.SalesManDao;
import entity.Goods;
import entity.SalesMan;
import tools.ScannerChoice;
import java.util.ArrayList;

/**
* 商超购物管理系统主界面
* @author 张超超
* @version 1.0
* */
public final class MainPage extends ScannerChoice{
    /*
    * 入口函数
    * */
    public static void main(String[] args) {
        MainPage.mainPage();
    }

    /*
    * 主界面
    * */
    public static void mainPage(){
        System.out.println("************************\n");
        System.out.println("\t1. 商品维护\n");
        System.out.println("\t2. 前台收银\n");
        System.out.println("\t3. 商品管理\n");
        System.out.println("************************");

        System.out.println("\n请输入选项，或者按0退出：");

        do {
            String choice = ScannerInfoString();
            String regex = "[0-3]";//正则表达式
            if(choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        System.out.println("----------------------");
                        System.out.println("您已退出系统");
                        System.exit(1);//退出程序
                        break;
                    case 1:
                        //商品维护界面
                        MaintenancePage();
                        break;
                    case 2:
                        //前台收银界面
                        checkstandLogPage();
                        break;
                    case 3:
                        //商品管理界面
                        commodityManagementPage();
                        break;
                }
            }
            System.out.println("!输入有误!");
            System.out.println("重新选择或者按0退出。");
        }while(true);
    }

    /*
    * 商品维护界面
    * */
    public static void MaintenancePage(){
        System.out.println("******************************\n");
        System.out.println("\t 1.添加商品\n");
        System.out.println("\t 2.更改商品\n");
        System.out.println("\t 3.删除商品\n");
        System.out.println("\t 4.查询商品\n");
        System.out.println("\t 5.显示所有商品\n");
        System.out.println("*****************************");

        System.out.println("\n请输入选项，或者按0返回上一级菜单。");
        do {
            String choice = ScannerInfoString();
            String regex = "[0-5]";
            if(choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        //添加商品
                        GoodsPage.addGoodsPage();
                        break;
                    case 2:
                        //修改商品
                        GoodsPage.updateGoodsPage();
                        break;
                    case 3:
                        //删除商品
                        GoodsPage.deleteGoodsPage();
                        break;
                    case 4:
                        //查询商品
                        GoodsPage.queryGoodsPage();
                        break;
                    case 5:
                        //显示所有商品
                        GoodsPage.displayGoodsPage();
                        break;
                }
            }
            System.out.println("!输入有误!");
            System.out.println("重新输入或按0返回上级菜单。");
        }while(true);
    }

    /**
    * 2.前台收银登录界面
    * */
    public static void checkstandLogPage(){
        System.out.println("\n**************欢迎使用商超购物管理系统***************\n");
        System.out.println("\t 1.登录系统\n");
        System.out.println("\t 2.退出\n");
        System.out.println("----------------------------------------------------");
        System.out.println("请输入选项，或者按0返回上一级菜单。");

        do {
            String choice = ScannerInfoString();
            String regex = "[0-2]";
            if (choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        int loginTimes = 3;//3次登陆机会
                        while(loginTimes != 0){
                            loginTimes--;
                            System.out.println("---用户名---");
                            String sName = ScannerInfoString();
                            System.out.println("---密码---");
                            String sPassword = ScannerInfoString();

                            ArrayList<SalesMan> salesManInfo = new SalesManDao().checkstandLog(sName);//以用户名从数据库中获取用户和密码

                            //没有此用户
                            if(salesManInfo == null || salesManInfo.size() == 0){
                                System.err.println("\t !!用户输入有误!! \n");
                                System.out.println("\n 剩余登陆次数：" + loginTimes);
                            }else{
                                SalesMan salesMan = salesManInfo.get(0);//此时，只返回了一条数据，只遍历一次即可
                                if(sPassword.equals(salesMan.getsPassword())){
                                    System.out.println("\t ---账户登陆成功---");
                                    //登陆成功，进入购物结算界面
                                    shoppingSettlementPage();
                                }else{
                                    System.out.println("\t !!密码错误!! \n");
                                    System.out.println("\n 剩余登陆次数：" + loginTimes);
                                }
                            }
                        }
                        //当loginTimes为0
                        System.out.println("----------------------");
                        System.out.println("\t !!您已被强制退出系统!!");
                        System.exit(1);
                        break;
                    case 2:
                        System.out.println("----------------------");
                        System.out.println("\t !!您已被强制退出系统!!");
                        System.exit(1);
                        break;
                }
            }
            System.out.println("!输入有误！");
            System.out.println("重新输入或按 0 返回上一级菜单");
        }while(true);
    }

    /*
    * 商品管理界面
    * */
    public static void commodityManagementPage(){
        System.out.println("*****************************\n");
        System.out.println("\t 1.售货员管理\n");
        System.out.println("\t 2.列出当日卖出列表\n");
        System.out.println("*****************************");

        System.out.println("请输入选项，或者按0返回上一级菜单。");
        do {
            String choice = ScannerInfoString();
            String regex = "[0-2]";
            if(choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        //售货员管理界面

                        break;
                    case 2:
                        //当日卖出列表
                        break;
                    default:
                        break;
                }
            }
            System.out.println("!输入有误!");
            System.out.println("请输入选项，或者按0返回上一级菜单。");
        }while(true);
    }

    /*
    * 购物结算界面
    * */
    public static void shoppingSettlementPage(){
        System.out.println("\n\t **********购物结算********** \n");

        do{
            System.out.println("按 S 开始购物结算，按 0 返回账户登陆界面");
            String choNext = ScannerInfoString();
            if("0".equals(choNext)){
                checkstandLogPage();
            }else if("s".equals(choNext) || "S".equals(choNext)){
                System.out.println("\n 请输入商品关键字：");
                ArrayList<Goods> goodsList = new GoodsDao().queryGoods(3);
                if(goodsList == null || goodsList.size() <= 0){
                    System.out.println("\t !!查无此商品!! \n");
                }else{
                    System.out.println("\t\t\t\t 商品列表 \n\n");
                    System.out.println("\t 商品编号\t\t 商品名称\t\t 商品价格\t\t 商品数量\t\t 备注\n");
                    for (int i = 0; i < goodsList.size(); i++) {
                        Goods goods = goodsList.get(i);
                        if(goods.getGnum() > 0){
                            System.out.println("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() +"\t" + goods.getGnum());
                        }
                    }
                }
            }
        }while(true);
    }

    /**
     * 售货员管理界面
     */
    public static void salesManManagementPage(){
        System.out.println("******************************\n");
        System.out.println("\t 1.添加售货员 \n");
        System.out.println("\t 2.更改售货员 \n");
        System.out.println("\t 3.删除售货员 \n");
        System.out.println("\t 4.显示所有售货员 \n");
        System.out.println("\t 5.查询售货员 \n");

        System.out.println("\n 请输入选项，或者按 0 返回上一级菜单。");

        String choice = ScannerInfoString();
        String regex = "[0-5]";
        if(choice.matches(regex)){
            int info = Integer.parseInt(choice);
            switch (info){
                case 0:
                    commodityManagementPage();
                    break;
                case 1:
                    //添加售货员
                    SalesManPage.addSalesManPage();
                    break;
                case 2:
                    //更改售货员
                    break;
                case 3:
                    //删除售货员
                    break;
                case 4:
                    //显示所有售货员
                    break;
                case 5:
                    //查询售货员
                    break;
            }
        }
    }
}













