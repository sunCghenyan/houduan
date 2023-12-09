package colector;


import colector.utils.JDBCutil;

import java.sql.*;

public class test {

        //定义初始值
        public static final String DRIVER_NAME ="com.mysql.cj.jdbc.Driver";

        public static void main(String[] args)throws ClassNotFoundException, SQLException
        {
            //定义变量
            Connection connection=null;
            Statement statement=null;
            ResultSet resultSet=null;
            //加载驱动
            //Class.forName(DRIVER_NAME);
            //新建连接
           // connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);

            //改为获取链接
           Connection conn=JDBCutil.getConnection();


            //新建statement
            statement=connection.createStatement();
            //执行SQL

            String sql="select * from user";


            resultSet= statement.executeQuery(sql);


            while(resultSet.next())
            {
                String username= resultSet.getString("username");
                System.out.println(username);
            }
        }

}
