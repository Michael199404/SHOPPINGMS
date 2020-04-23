package dao;

import db.DbClose;
import db.DbConn;
import entity.SalesMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 数据库对salesman表操作
 * @author zhangchaochao
 */
public class SalesManDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    /**
     * 1.前台收银登录
     * @param sName 用户名
     * @return ArrayList<SalesMan> sPassword, sId
     */
    public ArrayList<SalesMan> checkstandLog(String sName){
        ArrayList<SalesMan> salesManInfo = new ArrayList<>();
        conn = DbConn.getconn();
        String sql = "SELECT SID, SPASSWORD FROM SALESMAN WHERE SNAME=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String sPassword = rs.getString("spassword");
                int sId = rs.getInt("sid");
                SalesMan salesMan = new SalesMan(sId, sPassword);
                salesManInfo.add(salesMan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return salesManInfo;
    }

    /**
     * 2.售货员添加
     */
    public boolean addSalesMan(String sName, String sPasswd){
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO SALESMAN(SNAME, SPASSWORD) VALUES(?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            pstmt.setString(2, sPasswd);

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

}
