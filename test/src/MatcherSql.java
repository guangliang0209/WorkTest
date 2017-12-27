import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fengguangliang on 2017/5/23.
 */
public class MatcherSql {

    private static Pattern SELECT_SQL_PATTERN = Pattern.compile("select\\s.+from\\s(.+)where\\s(.*)");
    private static Pattern INSERT_SQL_PATTERN = Pattern.compile("insert\\sinto\\s(.+)\\(.*\\)\\s.*");
    private static Pattern UPDATE_SQL_PATTERN = Pattern.compile("update\\s(.+)set\\s.*");
    private static Pattern DELETE_SQL_PATTERN = Pattern.compile("delete\\sfrom\\s(.+)where\\s(.*)");
    /**
     * @param sql lowcase
     * @return
     */
    public static String matchSql(String sql) {
        Matcher matcher = null;
        if (sql.startsWith("select")) {
            matcher = SELECT_SQL_PATTERN.matcher(sql);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        if (sql.startsWith("insert")) {
            matcher = INSERT_SQL_PATTERN.matcher(sql);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        if (sql.startsWith("update")) {
            matcher = UPDATE_SQL_PATTERN.matcher(sql);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        if (sql.startsWith("delete")) {
            matcher = DELETE_SQL_PATTERN.matcher(sql);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String sql = "select * from aaa where 1=1";
        String sql1 = "select id,name,password from bbb where id = 1 ";
        String sql2 = "select id,name,password from bbb as b left join ccc as c on b.name = c.name where id = 1 ";
        String table1 = matchSql(sql);
        String table2 = matchSql(sql1);
        String table3 = matchSql(sql2);
        System.out.println(table1);
        System.out.println(table2);
        System.out.println(table3);
    }
}
