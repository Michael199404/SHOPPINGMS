package tools;

import dao.GoodsDao;
import db.DbClose;
import db.DbConn;
import entity.Goods;
import page.GoodsPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 查询&打印
 * @author zhangchaochao
 */
public final class QueryPrint {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 模糊查询并陈列查询信息函数小工具
     * @return 查询到信息的gid，如果返回-1，则代表查询异常
     */
    public static int query(String oper){
        int gid = -1;
        //键盘获取商品的名字
        String shopping = ScannerChoice.ScannerInfoString();
        //根据键盘获取的数据调用精确查询函数，确定用户所要操作的数据
        ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(-1, shopping);
        if(goodsList == null || goodsList.size() <= 0){
            System.out.println("\t !!查无此商品!!");
            //调用下一步函数
            ScannerChoice.changedInfoNext(oper);
        }else{
            Goods goods = goodsList.get(0);

            System.out.println("\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            System.out.println();
            if(goods.getGnum() == 0){
                System.out.println("\t\t 该商品已售空");
            }else if(goods.getGnum() < 10){
                System.out.println("\t\t 该商品已不足10件");
            }else{
                System.out.println("\t\t -");
            }
            gid = goods.getGid();
        }
        return gid;
    }

    /**
     * 根据商品的gid或gName查询商品
     * @param gId、gName
     * @return 商品信息
     */
    public ArrayList<Goods> queryGoodsKey(int gId, String gName){
        ArrayList<Goods> goodsArrayList = new ArrayList<Goods>();

        String sql = "SELECT * FROM GOODS WHERE GID=? OR GNAME=?";
        Connection conn = DbConn.getconn();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gId);
            pstmt.setString(2, gName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int gid = rs.getInt("gid");
                String gname = rs.getString(2);
                double gprice = rs.getDouble(3);
                int gnum = rs.getInt(4);

                Goods goods = new Goods(gid, gname, gprice, gnum);
                goodsArrayList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return goodsArrayList;
    }

//    /**
//     * 模糊查询函数小工具
//     * @return int 当商品数有且只有一件时，返回商品gid号，商品售空时返回-1， 大于1件时，返回-2，查无此商品时返回-3
//     */
//    public static int querySettlement(){
//        int gid = -1;
//        ArrayList<Goods> goodsSettlement = new GoodsDao().queryGoods(3);
//        if(goodsSettlement == null || goodsSettlement.size() <= 0){
//            System.out.println("\t !!查无此商品!! \n");
//            gid = -3;
//        }else{
//            System.out.println("\t\t\t\t 商品列表 \n\n");
//            System.out.println("\t 商品编号\t\t 商品名称\t\t 商品价格\t\t 商品数量\t\t 备注\n");
//            for (int i = 0; i < goodsSettlement.size(); i++) {
//                Goods goods = goodsSettlement.get(i);
//                if(goods.getGnum() > 0){
//                    System.out.println("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() +"\t" + goods.getGnum());
//                }
//            }
//        }
//        return gid;
//    }
}
