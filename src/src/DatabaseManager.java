import org.postgresql.ds.PGSimpleDataSource;

public class DatabaseManager {

    public static PGSimpleDataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("BJLeaderboard");
        dataSource.setUser("postgres");
        dataSource.setPassword("techelevator1");

        return dataSource;
    }
}
