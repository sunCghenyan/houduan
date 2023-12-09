package colector.utils;

import java.sql.*;
public class JDBCutil {
    public static final String URL="jdbc:mysql://localhost:3306/demo";
    public static final String USERNAME="root";
    public static final String PASSWORD="Scy2193189426";
    //静态块，先于构造器执行，仅执行一次
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //获取链接
    public static Connection getConnection()  {
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //返回目标对象
        return conn;
    }

    public static Statement getStatement(Connection conn){
        Statement stmt= null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stmt;
    }


    /**
     * 获取执行语句preparedstatement
     * @param conn 连接对象
     * @param sql 准备sql
     * @return
     */
    public static PreparedStatement getPreparedStatement(Connection conn,String sql){
        PreparedStatement pstmt=null;
        try {
            pstmt=conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回目标对象
        return pstmt;
    }

    /**
     *
     * @param rs 结果集
     * @param stmt 执行语句
     * @param conn 链接
     */
    public static  void close(ResultSet rs,Statement stmt,Connection conn){
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //增(****)
    public static void insertUser(String s1,String s2,String s3) throws SQLException {
        Connection conn= JDBCutil.getConnection();

        String sql = " insert into User(username,password,phone) values ("+s1+","+s2+","+s3+")";
        PreparedStatement pstmt=JDBCutil.getPreparedStatement(conn,sql);

        ResultSet resultSet=null;

        int count1;
        count1 = pstmt.executeUpdate(sql);

        System.out.println("更新行数"+count1);


        //释放资源
        // jdbcutils.close(null,pstmt,conn);
    }

    //删
    public static void deleteUser(String s)  {
        Connection conn= JDBCutil.getConnection();

        String sql = " delete from User where username= "+s+"";
        PreparedStatement pstmt=JDBCutil.getPreparedStatement(conn,sql);
        int count2 = 0;

        try {
            count2 = pstmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("更新行数"+count2);
        JDBCutil.close(null,pstmt,conn);
    }


    //改(****)
    public static void updateUser(String p,String u) throws SQLException {

        Connection conn= JDBCutil.getConnection();

        String sql = "update User set password="+p+" where username="+u+" ";
        PreparedStatement pstmt=JDBCutil.getPreparedStatement(conn,sql);

        int count3 = 0;

        count3 = pstmt.executeUpdate(sql);
        System.out.println("更新行数"+count3);

        //释放资源
        JDBCutil.close(null,pstmt,conn);
    }

    //查
    public static void selectUser() throws SQLException {
        Connection conn= JDBCutil.getConnection();

        String sql = "select username from user";
        PreparedStatement pstmt=JDBCutil.getPreparedStatement(conn,sql);

        ResultSet resultSet=null;


        resultSet=pstmt.executeQuery(sql);

        while(true)
        {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String username= null;
            try {
                username = resultSet.getString("username");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(username);
        }

        //释放资源
        JDBCutil.close(null,pstmt,conn);
    }

}