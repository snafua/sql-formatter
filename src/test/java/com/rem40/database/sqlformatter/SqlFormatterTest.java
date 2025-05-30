package com.rem40.database.sqlformatter;

import static org.junit.jupiter.api.Assertions.*;

import com.rem40.database.sqlformatter.core.*;
import com.rem40.database.sqlformatter.languages.Dialect;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class SqlFormatterTest {

  @Test
  public void simpleSP() {

    String sql = new ParserFileUtils().readFile("C:\\DFTest\\inputSQL\\SWA\\RepairDetails.sql");

    FormatConfig build = FormatConfig.builder()
            .linesBetweenQueries(2)
            .indent("    ") // Defaults to four spaces
            .uppercase(true) // Defaults to false (not safe to use when SQL dialect has case-sensitive identifiers)
            .linesBetweenQueries(2) // Defaults to 1
            .maxColumnLength(120) // Defaults to 50
//            .params(Arrays.asList("a", "b", "c")) // Map or List. See Placeholders replacement.
            .build();
    //String format = SqlFormatter.of(Dialect.MySql).format(sql,build);
    //String format = SqlFormatter.of(Dialect.PlSql).format(sql,build);
    //String format = SqlFormatter.of(Dialect.SparkSql).format(sql,build);
    //String format = SqlFormatter.of(Dialect.TSql).format(sql,build); //maybe
    //String format = SqlFormatter.of(Dialect.Redshift).format(sql,build);
    //String format = SqlFormatter.of(Dialect.MariaDb).format(sql);
    String format = SqlFormatter.of(Dialect.SybaseSql).format(sql,build);

    System.out.println("format = " + format);
    new ParserFileUtils().writeToFile("C:\\DFTest\\outputSQL\\SWA\\RepairDetailsTEST.sql", format);
/*
    assertEquals(
            format,
            "SELECT\n"
                    + "  foo,\n"
                    + "  bar,\n"
                    + "  CASE\n"
                    + "    baz\n"
                    + "    WHEN 'one' THEN 1\n"
                    + "    WHEN 'two' THEN 2\n"
                    + "    ELSE 3\n"
                    + "  END\n"
                    + "FROM\n"
                    + "  table");
*/
  }

  @Test
  public void simple() {
    String format =
        SqlFormatter.format(
            "SELECT foo, bar, CASE baz WHEN 'one' THEN 1 WHEN 'two' THEN 2 ELSE 3 END FROM table");
    assertEquals(
        format,
        "SELECT\n"
            + "  foo,\n"
            + "  bar,\n"
            + "  CASE\n"
            + "    baz\n"
            + "    WHEN 'one' THEN 1\n"
            + "    WHEN 'two' THEN 2\n"
            + "    ELSE 3\n"
            + "  END\n"
            + "FROM\n"
            + "  table");
  }

  @Test
  public void withIndent() {
    String format =
        SqlFormatter.format(
            "SELECT foo, bar, CASE baz WHEN 'one' THEN 1 WHEN 'two' THEN 2 ELSE 3 END FROM table",
            "    ");
    assertEquals(
        format,
        "SELECT\n"
            + "    foo,\n"
            + "    bar,\n"
            + "    CASE\n"
            + "        baz\n"
            + "        WHEN 'one' THEN 1\n"
            + "        WHEN 'two' THEN 2\n"
            + "        ELSE 3\n"
            + "    END\n"
            + "FROM\n"
            + "    table");
  }

  @Test
  public void withNamedParams() {
    Map<String, String> namedParams = new HashMap<>();
    namedParams.put("foo", "'bar'");

    String format =
        SqlFormatter.of(Dialect.TSql).format("SELECT * FROM tbl WHERE foo = @foo", namedParams);
    assertEquals(format, "SELECT\n" + "  *\n" + "FROM\n" + "  tbl\n" + "WHERE\n" + "  foo = 'bar'");
  }

  @Test
  public void withFatArrow() {
    String format =
        SqlFormatter.extend(config -> config.plusOperators("=>"))
            .format("SELECT * FROM tbl WHERE foo => '123'");
    assertEquals(
        format, "SELECT\n" + "  *\n" + "FROM\n" + "  tbl\n" + "WHERE\n" + "  foo => '123'");
  }

  @Test
  public void withIndexedParams() {
    String format = SqlFormatter.format("SELECT * FROM tbl WHERE foo = ?", Arrays.asList("'bar'"));
    assertEquals(format, "SELECT\n" + "  *\n" + "FROM\n" + "  tbl\n" + "WHERE\n" + "  foo = 'bar'");
  }

  @Test
  public void withLambdasParams() {
    String format =
        SqlFormatter.of(Dialect.SparkSql)
            .format("SELECT aggregate(array(1, 2, 3), 0, (acc, x) -> acc + x, acc -> acc * 10);");
    assertEquals(
        format,
        "SELECT\n" + "  aggregate(array(1, 2, 3), 0, (acc, x) -> acc + x, acc -> acc * 10);");
  }
}
