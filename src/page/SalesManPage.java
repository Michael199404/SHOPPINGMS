package page;

import dao.SalesManDao;
import entity.SalesMan;
import tools.ScannerChoice;

/**
 * 操作售货员界面
 * @author zhangchaochao
 */
public class SalesManPage extends ScannerChoice {
    /**
     * 1.添加售货员界面
     */
    public static void addSalesManPage(){
        System.out.println("\t 正在执行添加售货员操作 \n");

        do{
            System.out.println("\n 添加售货员名字");
            String sName = ScannerInfoString();

            System.out.println("\n 添加售货员密码");
            String sPasswd = ScannerInfoString();

            boolean bool = new SalesManDao().addSalesMan(sName, sPasswd);
            if(bool){
                System.out.println("\n\t 您已成功添加售货员到数据库！");
            }else{
                System.out.println("添加售货员失败！");
            }

            System.out.println("是否继续（y/n）");
            String choice = ScannerInfoString();
            if(choice.matches("y") || choice.matches("Y")){

            }
        }while (true);

    }
}
