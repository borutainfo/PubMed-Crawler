package SQLite;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DB {

    private Connection conn = null;

    public DB() {
        /* connect to DB */
        if (!isConnected()) {
            connect();
        }
        if (!exists()) {
            createDB();
        }
    }

    /* check if object is connected to DB */
    private boolean isConnected() {
        try {
            return (conn != null) && conn.isValid(0);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /* check if DB exists */
    private boolean exists() {
        try {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "settings", null);
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
    }

    /* connect to DB */
    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:settings.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /* create new DB */
    private void createDB() {
        try {
            Statement stat = conn.createStatement();
            stat.execute("CREATE TABLE settings (name TEXT, state);");
            stat.execute("CREATE TABLE expressions (id INTEGER PRIMARY KEY ASC AUTOINCREMENT, expression TEXT);");
            stat.execute("CREATE TABLE summaries (id INTEGER PRIMARY KEY ASC, expression_id INTEGER, title TEXT, abstract TEXT);");
            stat.execute("CREATE TABLE history (id INTEGER PRIMARY KEY ASC AUTOINCREMENT, expression TEXT);");
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public Vector<String> getExpressions() {
        return getExpressions(false);
    }

    /* get all expressions from DB*/
    public Vector<String> getExpressions(boolean history) {
        Vector<String> expressions = new Vector<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs;
            if(history)
                rs = stat.executeQuery("SELECT * FROM history ORDER BY id DESC;");
            else
                rs = stat.executeQuery("SELECT * FROM expressions ORDER BY id DESC;");
            while (rs.next()) {
                expressions.add(rs.getString("expression"));
            }
            return expressions;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    public int getExpressionId(String expression) {
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT id FROM expressions WHERE expression = '" + expression + "';");
            if (rs.next()) {
                return Integer.parseInt(rs.getString("id"));
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return -1;
        }
    }
    
    public String getAbstract(String title) {
        try {
            PreparedStatement pstat;
            pstat = conn.prepareStatement("SELECT * FROM summaries WHERE title = ?");
            pstat.setString(1, title);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                return rs.getString("abstract");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
    }
    
    public boolean addExpression(String expression) {
        return addExpression(expression, false);
    }

    /* add new expression to the DB */
    public boolean addExpression(String expression, boolean history) {
        if (!checkExpression(expression, history)) {
            try {
                Statement stat = conn.createStatement();
                if(history)
                    stat.executeUpdate("INSERT INTO history " + "(expression) VALUES ('" + expression + "');");
                else
                    stat.executeUpdate("INSERT INTO expressions " + "(expression) VALUES ('" + expression + "');");
                return true;
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean deleteExpression(String expression) {
        return deleteExpression(expression, false);
    }

    /* delete expression from the DB */
    public boolean deleteExpression(String expression, boolean history) {
        if (checkExpression(expression, history)) {
            try {
                Statement stat = conn.createStatement();
                if(history)
                    stat.executeUpdate("DELETE FROM history WHERE expression = '" + expression + "';");
                else
                    stat.executeUpdate("DELETE FROM expressions WHERE expression = '" + expression + "';");
                return true;
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
                return false;
            }
        } else {
            return false;
        }
    }
    
    private boolean checkExpression(String expression) {
        return checkExpression(expression, false);
    }

    /* check if expression exists in DB */
    private boolean checkExpression(String expression, boolean history) {
        try {
            Statement stat = conn.createStatement();
            ResultSet rs;
            if(history)
                rs = stat.executeQuery("SELECT * FROM history WHERE expression = '" + expression + "';");
            else
                rs = stat.executeQuery("SELECT * FROM expressions WHERE expression = '" + expression + "';");
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
    }

    public boolean addSummary(int id, int expressionId, String title) {
        if (!isConnected()) {
            connect();
        }
        try {
            PreparedStatement pstat;
            if (title == null) {
                pstat = conn.prepareStatement("INSERT OR IGNORE INTO summaries (id, expression_id) VALUES (?, ?);");
            } else {
                pstat = conn.prepareStatement("INSERT OR IGNORE INTO summaries (id, expression_id, title) VALUES (?, ?, ?);");
                pstat.setString(3, title);
            }
            pstat.setInt(1, id);
            pstat.setInt(2, expressionId);
            pstat.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
    }

    public boolean addFetch(int id, int expressionId, String title, String abs) {
        if (!isConnected()) {
            connect();
        }
        try {
            PreparedStatement pstat;
            pstat = conn.prepareStatement("INSERT OR IGNORE INTO summaries (id, expression_id, title, abstract) VALUES (?, ?, ?, ?);");
            pstat.setInt(1, id);
            pstat.setInt(2, expressionId);
            pstat.setString(3, title);
            pstat.setString(4, abs);
            pstat.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
    }

    public Vector<String> getResults(int expressionId) {
        Vector<String> results = new Vector<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM summaries WHERE expression_id = '" + expressionId + "';");
            while (rs.next()) {
                results.add(rs.getString("title"));
            }
            return results;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
    }
}
