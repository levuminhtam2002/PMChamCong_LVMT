package hust.project.base.utils.sql_hikari;

public class DatabaseManager {
    HikariClient defaultClient;
    SQLJavaBridge defaultBridge;
    private static DatabaseManager ins = null;

    private DatabaseManager() {

    }

    public static DatabaseManager instance() {
        if (ins == null) {
            ins = new DatabaseManager();
        }
        return ins;
    }
    public void init() {
//        String jdbcURL = "jdbc:mariadb://127.0.0.1:3306/timekeeper";
//        String user = "root";
//        String password = "1234";
//        String database = "timekeeper";
//        public static final String URL = "jdbc:mysql://localhost:3306/pmcc";
//        public static final String USERNAME = "root";
//        public static final String PASSWORD = "123456";
        String jdbcURL = "jdbc:mysql://localhost:3306/pmcc";
        String user = "root";
        String password = "123456";
        String database = "pmcc";
        HikariClient sqlClient = new HikariClient(jdbcURL, user, password, database);
        SQLJavaBridge bridge= new SQLJavaBridge(sqlClient);
        this.defaultClient = sqlClient;
        this.defaultBridge = bridge;
    }
    public SQLJavaBridge defaulSQLJavaBridge() {
        return defaultBridge;
    }

}
