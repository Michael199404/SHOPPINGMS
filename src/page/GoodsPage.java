package page;

import dao.GoodsDao;
import entity.Goods;
import tools.QueryPrint;
import tools.ScannerChoice;
import java.util.ArrayList;


/**
 * 操作商品界面
 * @author zhangchaochao
 */
public class GoodsPage extends ScannerChoice {

    /**
     * 1.添加商品
     */
    public static void addGoodsPage(){
        System.out.println("\t 正在执行添加商品操作\n");

        System.out.println("\n 请输入添加商品-名称");
        String goodName = ScannerInfoString();

        System.out.println("\n 请输入添加商品-价格");
        double goodPrice = ScannerInfo();

        System.out.println("\n 请输入添加商品-数量");
        int goodNum = ScannerNum();

        //将数据保存到Goods类中
        Goods goods = new Goods(goodName, goodPrice, goodNum);
        //将数据写入数据库
        boolean bool = new GoodsDao().addGoods(goods);

        if(bool){
            System.out.println("\n\t 您已成功添加商品到数据库中！");
        }else{
            System.out.println("添加商品失败！");
        }

        //选择下一步
        changedInfoNext("addGoodsPage");
    }

    /**
     * 2.修改商品界面
     */
    public static void updateGoodsPage(){
        System.out.println("\t 正在执行修改商品操作\n");
        System.out.println("请输入要更改商品的名字：");

        //调用查找函数，显示将要更改商品的信息
        int gid = QueryPrint.query("updateGoodsPage");

        System.out.println("\n----------请选择您要更改的内容\n");
        System.out.println("\t 1.更改商品-名称");
        System.out.println("\t 1.更改商品-价格");
        System.out.println("\t 1.更改商品-数量");
        System.out.println("\n 请输入选项，或者按0返回上一级菜单");

        do{
            String choice = ScannerInfoString();
            String regex = "[0-3]";
            if(choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        MainPage.MaintenancePage();
                        break;
                    case 1:
                        System.out.println("请输入商品-新名称：");
                        String gname = ScannerChoice.ScannerInfoString();
                        Goods goodsName = new Goods(gid, gname);
                        boolean boolName = new GoodsDao().updateGoods(1, goodsName);
                        if(boolName){
                            System.out.println("\n\t !!成功更新商品名到数据库!!\n");
                        }else{
                            System.err.println("\n\t !!更新商品名失败!!");
                        }
                        changedInfoNext("updateGoodsPage");
                        break;
                    case 2:
                        System.out.println("请输入商品-最新价格：");
                        double gprice = ScannerChoice.ScannerInfo();
                        Goods goodsPrice = new Goods(gid, gprice);
                        boolean boolPrice = new GoodsDao().updateGoods(2, goodsPrice);
                        if(boolPrice){
                            System.out.println("\n\t !!成功更新商品价格到数据库!!\n");
                        }else{
                            System.err.println("\n\t !!更新商品价格失败!!");
                        }
                        changedInfoNext("updateGoodsPage");
                        break;
                    case 3:
                        System.out.println("请输入商品-最新数量：");
                        int gnum = ScannerChoice.ScannerNum();
                        Goods goodsNum = new Goods(gid, gnum);
                        boolean boolNum = new GoodsDao().updateGoods(3, goodsNum);
                        if(boolNum){
                            System.out.println("\n\t !!成功更新商品数量到数据库!!\n");
                        }else{
                            System.err.println("\n\t !!更新商品数量失败!!");
                        }
                        changedInfoNext("updateGoodsPage");
                        break;
                    default:
                        System.out.println("请输入正确选择！");
                        break;
                }
            }
            System.out.println("!输入有误!");
            System.out.println("请重新选择，或按0返回上一级菜单");
        }while (true);
    }

    /**
     * 3.删除商品界面
     */
    public static void deleteGoodsPage(){
        System.out.println("\t 正在执行删除商品操作\n");
        System.out.println("请输入想要删除商品的名字");

        //调用查找函数，显示将要删除的商品（输入商品名字，返回商品id）
        int gid = QueryPrint.query("deleteGoodsPage");

        do{
            System.out.println("\n 确认删除该商品：Y/N");
            String choice = ScannerChoice.ScannerInfoString();
            if(choice.equals("Y") || choice.equals("y")){
                boolean boolDeleteGoods = new GoodsDao().deleteGoods(gid);
                if(boolDeleteGoods){
                    System.out.println("\t ！！已成功删除该商品！！\n");
                }else{
                    System.out.println("\t ！！删除商品失败！！\n");
                }
                changedInfoNext("deleteGoodsPage");
            }else if(choice.equals("N") || choice.equals("n")){
                MainPage.MaintenancePage();
            }
            System.out.println("\t !!输入有误，请重新输入!!\n");
        }while(true);
    }

    /**
     * 4.查询商品界面
     */
    public static void queryGoodsPage(){
        System.out.println("\t\t 正在执行商品 关键字 查询操作\n");
        System.out.println("\t\t 1.按照商品数量升序查询");
        System.out.println("\t\t 2.按照商品价格升序查询");
        System.out.println("\t\t 3.按照商品关键字查询");
        System.out.println("\n 输入选项，或者按0返回上一级菜单。");

        do{
            String info = ScannerInfoString();
            String regex = "[0-3]";
            if(info.matches(regex)){
                int choice = Integer.parseInt(info);
                switch (choice){
                    case 0:
                        MainPage.MaintenancePage();
                        break;
                    case 1:
                    case 2:
                    case 3:
                        if(choice == 3){
                            System.out.println("\t\t 正在执行商品 关键字 查询操作\n");
                            System.out.println("请输入商品关键字");
                        }
                        //调用查询功能
                        ArrayList<Goods> goodsList = new GoodsDao().queryGoods(choice);
                        if(goodsList == null || goodsList.size() <= 0){
                            System.err.println("\n\t !!您查询的商品不存在!!");
                            queryGoodsPage();
                        }else{
                            if(choice == 1){
                                System.out.println("\t\t\t\t\t 商品按照 数量升序 列表 \n\n");
                            }else if(choice == 2){
                                System.out.println("\t\t\t\t\t 商品按照 价格升序 列表 \n\n");
                            }else{
                                System.out.println("\t\t\t\t\t 您所查找的商品如下 \n\n");
                            }

                            //遍历数组
                            for (int i = 0; i < goodsList.size(); i++) {
                                Goods goods = goodsList.get(i);
                                System.out.println("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());
                                int gnum = goods.getGnum();
                                if(gnum == 0){
                                    System.out.println("\t\t 该商品已售空！");
                                }else if(gnum < 10){
                                    System.out.println("\t\t 该商品已不足10件！");
                                }else{
                                    System.out.println("\t\t -");
                                }
                                System.out.println("\t");
                            }
                            System.out.println("-------------------------------");

                            do{
                                System.out.println("输入0返回上一级菜单");
                                String choiceNext = ScannerInfoString();
                                if("0".equals(choiceNext)){
                                    MainPage.MaintenancePage();
                                }
                                System.err.println("输入有误！");
                            }while (true);
                        }
                        break;
                    default:
                        break;
                }
                break;
            }
            System.err.println("输入有误！");
            System.out.println("请重新选择，按0返回上一级菜单。");
        }while(true);
    }

    /**
     * 5.展示所有商品界面
     */
    public static void displayGoodsPage(){
        System.out.println("\t\t\t\t\t 所有商品列表 \n\n");
        ArrayList<Goods> goodsList = new GoodsDao().displayGoods();

        if(goodsList.size() <= 0){
            System.err.println("!库存为空!");
            MainPage.MaintenancePage();
        }else{
            //展示商品
            System.out.println("\t 商品编号 \t\t 商品名称 \t\t 商品价格\t\t 商品数量 \t\t备注 \n");
            //避免重复计算变量，浪费资源
            for (int i = 0, length = goodsList.size(); i < length; i++) {
                Goods goods = goodsList.get(i);
                System.out.println("\t" + goods.getGid() + "\t" + goods.getGname() + "\t" + goods.getGprice() + "\t" + goods.getGnum());

                //对每个商品进行数量上的判断
                int gNum = goods.getGnum();
                if(gNum == 0){
                    System.out.println("\t\t 该商品已售空");
                }else if(gNum < 10){
                    System.out.println("\t\t 该商品已不足10件");
                }else{
                    System.out.println("\t\t -");
                }
            }

            //下一步
            System.out.println("----------------------------");
            do{
                System.out.println("输入 0 返回上一级菜单");
                String choice = ScannerInfoString();
                if("0".equals(choice)){
                    MainPage.MaintenancePage();
                }else{
                    System.out.println("输入错误");
                }
            }while(true);

        }
    }
}













