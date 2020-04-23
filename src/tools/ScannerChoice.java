package tools;

import page.GoodsPage;
import page.MainPage;

import java.util.Scanner;

/*
* 1.
* 2.界面选择操作
* */
public class ScannerChoice {

    /**
     * @return double 键盘获取商品价格，小数点后两位
     */
    public static double ScannerInfo(){
        double num = 0.00;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("保留小数点后两位，请输入：");
            String info = sc.next();

            String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";//必须为保留小数点后两位的小数
            if(info.matches(regex)){
                num = Double.parseDouble(info);
            }else{
                System.out.println("!输入有误!");
                continue;
            }
            break;
        }while (true);
        return num;
    }

    /**
     * @return int 键盘获取商品数量
     */
    public static int ScannerNum(){
        int num = 0;
        String regex = "([1-9])|([1-9][0-9]+)";//商品数量
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入：");
            String nums = sc.next();

            if(nums.matches(regex)){
                num = Integer.parseInt(nums);
            }else{
                System.out.println("!输入有误!");
                continue;
            }
            break;
        }while (true);
        return num;
    }

    /**
    * @return String 获取键盘输入
    * */
    public static String ScannerInfoString(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        return scanner.next();
    }

    /**
     * 添加完商品-下一步
     * 更改完商品-下一步
     * 删除完商品-下一步
     */
    public static void changedInfoNext(String oper){
        System.out.println("是否继续进行-当前操作：（Y/N）");
        String choice = ScannerChoice.ScannerInfoString();

        do{
            //在Java中，equals比较的是值，==比较的是地址
            if("y".equals(choice) || "Y".equals(choice)){
                if("addGoodsPage".equals(oper)){
                    GoodsPage.addGoodsPage();
                }else if("updateGoodsPage".equals(oper)){
                    GoodsPage.updateGoodsPage();
                }else if("deleteGoodsPage".equals(oper)){
                    GoodsPage.deleteGoodsPage();
                }
            }else if("N".equals(choice) || "n".equals(choice)){
                MainPage.MaintenancePage();
            }
            System.out.println("\n 输入有误！请重新输入。");
        }while (true);
    }


}
