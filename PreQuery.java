package query;

import java.sql.*;
import java.util.Properties;


public class PreQuery {

    String databaseName = "";
    String SQL;
    String[] columnName;
    String[][] record;

    public PreQuery() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void setDatabaseName(String s) {
        databaseName = s.trim();
    }

    public void setSQL(String SQL) {
        this.SQL = SQL.trim();
    }

    public String[] getColumnName() {

        return columnName;
    }

    public String[][] getRecord() {

        return record;
    }

    public void startQuery() {
        Connection con;
        PreparedStatement sql;
        ResultSet rs;
        Properties  properties=new Properties();
        
        try {
            String uri = "jdbc:derby:"+databaseName+";create=true";
            con = DriverManager.getConnection(uri);
            sql = con.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sql.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();//返回的结果值的列数
            columnName = new String[columnCount];//返回的姓名的数组
            for (int i = 1; i <= columnCount; i++) {
                columnName[i - 1] = metaData.getColumnName(i);
            }
            rs.last();
            int recordAmount = rs.getRow();//返回的当前行的编号
            record = new String[recordAmount][columnCount];
            int i = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int j = 1; j <= columnCount; j++) {
                    record[i][j - 1] = rs.getString(j);
                }
                i++;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("请输入正确的表名" + e);
        }
    }
}
