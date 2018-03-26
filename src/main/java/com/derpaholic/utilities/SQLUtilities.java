package com.derpaholic.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SQLUtilities {

    private static final String insert = "INSERT INTO %s (%s) VALUES (%s);";
    private static final String select = "SELECT * from %s where %s;";
    private static final String column = "%s='%s'";

    public static Connection getConnection(String url) {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Statement getStatement(Connection c) {
        try {
            return c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isEmpty(ResultSet rs) {
        try {
            return !rs.next();
        } catch (Exception e) {
            return true;
        }
    }

    public static String getInsert(String table, String[] tbl_var, String[] values) {
        return String.format(insert, table, formatArray(tbl_var), formatArray(values));
    }

    public static String getSelect(String table, String col, String val) {
        return String.format(select, table, String.format(column, col, val));
    }

    private static String formatArray(String[] ary) {
        return Stream.of(ary).collect(Collectors.joining(",", "", ""));
    }

}