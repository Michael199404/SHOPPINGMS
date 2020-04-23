package dao;

import db.DbClose;
import db.DbConn;
import entity.Goods;
import tools.QueryPrint;
import tools.ScannerChoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 数据库goods表操作
 * @author zhangchaochao
 **/

public class GoodsDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 1. 添加商品到goods表
     * @param goods 商品对象
     * @return boolean
      */
    public boolean addGoods(Goods goods){
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO GOODS(GNAME, GPRICE, GNUM) VALUES(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods.getGname());
            pstmt.setDouble(2, goods.getGprice());
            pstmt.setInt(3, goods.getGnum());

            int rs = pstmt.executeUpdate();
            if(rs > 0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return bool;
    }

    /**
     * 2.更改商品信息到数据库goods表
     * @param key 要修改的商品
     * @return boolean
     */
    public boolean updateGoods(int key, Goods goods){
        boolean bool = false;
        conn = DbConn.getconn();
        switch (key){
            //key为1，更改商品名称
            case 1:
                String sqlName = "UPDATE GOODS SET GNAME=? WHERE GID=?";
                try {
                    pstmt = conn.prepareStatement(sqlName);
                    pstmt.setString(1, goods.getGname());
                    pstmt.setInt(2, goods.getGid());

                    int rs = pstmt.executeUpdate();
                    if(rs > 0){
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.addClose(pstmt, conn);
                }
                break;
            //key为2，更改商品价格
            case 2:
                String sqlPRICE = "UPDATE GOODS SET GPRICE=? WHERE GID=?";
                try {
                    pstmt = conn.prepareStatement(sqlPRICE);
                    pstmt.setDouble(1, goods.getGprice());
                    pstmt.setInt(2, goods.getGid());
                    int rs = pstmt.executeUpdate();
                    if(rs > 0){
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.addClose(pstmt, conn);
                }
                break;
            //key为3，更改商品数量
            case 3:
                String sqlNum = "UPDATE GOODS SET GNUM=? WHERE GID=?";
                try {
                    pstmt = conn.prepareStatement(sqlNum);
                    pstmt.setInt(1, goods.getGnum());
                    pstmt.setInt(2, goods.getGid());

                    int rs = pstmt.executeUpdate();
                    if(rs > 0){
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.addClose(pstmt, conn);
                }
                break;
            default:
                break;
        }
        return bool;
    }

    /**
     * 3.删除goods表中的商品信息
     * @param gid
     * @return boolean
     */
    public boolean deleteGoods(int gid){
        boolean bool = false;
        conn = DbConn.getconn();
        String sqlDeleteGood = "Delete FROM GOODS WHERE GID=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlDeleteGood);
            pstmt.setInt(1, gid);
            int rs = pstmt.executeUpdate();

            if(rs > 0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return bool;
    }

    /**
     * 4.查询goods表中的商品信息
     * @param key 查询方法
     * @return ArrayList<Goods>
     */
    public ArrayList<Goods> queryGoods(int key){
        ArrayList<Goods> goodsList = new ArrayList<>();
        conn = DbConn.getconn();

        switch(key){
            //key=1，按商品数量升序查询
            case 1:
                String sqlGnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
                try {
                    pstmt = conn.prepareStatement(sqlGnum);
                    rs = pstmt.executeQuery();
                    while(rs.next()){
                        int gid = rs.getInt("gid");
                        String gname = rs.getString(2);
                        double gprice = rs.getDouble(3);
                        int gnum = rs.getInt(4);

                        Goods goods = new Goods(gid, gname, gprice, gnum);
                        goodsList.add(goods);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.queryClose(pstmt, rs, conn);
                }
                break;
            //key=2，按照商品价格升序查询
            case 2:
                String sqlPrice = "SELECT * FROM GOODS ORDER BY GPRICE ASC";
                try {
                    pstmt = conn.prepareStatement(sqlPrice);
                    rs = pstmt.executeQuery();
                    while(rs.next()){
                        int gid = rs.getInt("gid");
                        String gname = rs.getString(2);
                        double gprice = rs.getDouble(3);
                        int gnum = rs.getInt(4);

                        Goods goods = new Goods(gid, gname, gprice, gnum);
                        goodsList.add(goods);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.queryClose(pstmt, rs, conn);
                }
                break;
            //key=3，按照关键字查询
            case 3:
                String nameGet = ScannerChoice.ScannerInfoString();
                String sqlGname = "SELECT * FROM GOODS WHERE GNAME LIKE ?";
                try {
                    pstmt = conn.prepareStatement(sqlGname);
                    pstmt.setString(1, "%"+nameGet+"%");
                    rs = pstmt.executeQuery();
                    while (rs.next()){
                        int gid = rs.getInt("gid");
                        String gname = rs.getString(2);
                        double gprice = rs.getDouble(3);
                        int gnum = rs.getInt(4);

                        Goods goods = new Goods(gid, gname, gprice, gnum);
                        goodsList.add(goods);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.queryClose(pstmt, rs, conn);
                }
                break;
            default:
                break;
        }
        return goodsList;
    }

    /**
     * 5.显示所有商品信息
     * @return ArrayList<Goods>
     */
    public ArrayList<Goods> displayGoods(){
        ArrayList<Goods> goodsList = new ArrayList<>();
        conn = DbConn.getconn();
        String sql = "SELECT * FROM GOODS";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                int gid = rs.getInt(1);
                String gname = rs.getString(2);
                double gprice = rs.getDouble(3);
                int gnum = rs.getInt(4);

                Goods goods = new Goods(gid, gname, gprice, gnum);
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return goodsList;
    }
}
