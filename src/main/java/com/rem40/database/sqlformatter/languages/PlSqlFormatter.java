package com.rem40.database.sqlformatter.languages;

import com.rem40.database.sqlformatter.core.AbstractFormatter;
import com.rem40.database.sqlformatter.core.DialectConfig;
import com.rem40.database.sqlformatter.core.FormatConfig;
import com.rem40.database.sqlformatter.core.Token;
import com.rem40.database.sqlformatter.core.TokenTypes;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlSqlFormatter extends AbstractFormatter {

  private static final List<String> reservedWords =
      Arrays.asList(
          "A",
          "ACCESSIBLE",
          "AGENT",
          "AGGREGATE",
          "ALL",
          "ALTER",
          "ANY",
          "ARRAY",
          "AS",
          "ASC",
          "AT",
          "ATTRIBUTE",
          "AUTHID",
          "AVG",
          "BETWEEN",
          "BFILE_BASE",
          "BINARY_INTEGER",
          "BINARY",
          "BLOB_BASE",
          "BLOCK",
          "BODY",
          "BOOLEAN",
          "BOTH",
          "BOUND",
          "BREADTH",
          "BULK",
          "BY",
          "BYTE",
          "C",
          "CALL",
          "CALLING",
          "CASCADE",
          "CASE",
          "CHAR_BASE",
          "CHAR",
          "CHARACTER",
          "CHARSET",
          "CHARSETFORM",
          "CHARSETID",
          "CHECK",
          "CLOB_BASE",
          "CLONE",
          "CLOSE",
          "CLUSTER",
          "CLUSTERS",
          "COALESCE",
          "COLAUTH",
          "COLLECT",
          "COLUMNS",
          "COMMENT",
          "COMMIT",
          "COMMITTED",
          "COMPILED",
          "COMPRESS",
          "CONNECT",
          "CONSTANT",
          "CONSTRUCTOR",
          "CONTEXT",
          "CONTINUE",
          "CONVERT",
          "COUNT",
          "CRASH",
          "CREATE",
          "CREDENTIAL",
          "CURRENT",
          "CURRVAL",
          "CURSOR",
          "CUSTOMDATUM",
          "DANGLING",
          "DATA",
          "DATE_BASE",
          "DATE",
          "DAY",
          "DECIMAL",
          "DEFAULT",
          "DEFINE",
          "DELETE",
          "DEPTH",
          "DESC",
          "DETERMINISTIC",
          "DIRECTORY",
          "DISTINCT",
          "DO",
          "DOUBLE",
          "DROP",
          "DURATION",
          "ELEMENT",
          "ELSIF",
          "EMPTY",
          "END",
          "ESCAPE",
          "EXCEPTIONS",
          "EXCLUSIVE",
          "EXECUTE",
          "EXISTS",
          "EXIT",
          "EXTENDS",
          "EXTERNAL",
          "EXTRACT",
          "FALSE",
          "FETCH",
          "FINAL",
          "FIRST",
          "FIXED",
          "FLOAT",
          "FOR",
          "FORALL",
          "FORCE",
          "FROM",
          "FUNCTION",
          "GENERAL",
          "GOTO",
          "GRANT",
          "GROUP",
          "HASH",
          "HEAP",
          "HIDDEN",
          "HOUR",
          "IDENTIFIED",
          "IF",
          "IMMEDIATE",
          "IN",
          "INCLUDING",
          "INDEX",
          "INDEXES",
          "INDICATOR",
          "INDICES",
          "INFINITE",
          "INSTANTIABLE",
          "INT",
          "INTEGER",
          "INTERFACE",
          "INTERVAL",
          "INTO",
          "INVALIDATE",
          "IS",
          "ISOLATION",
          "JAVA",
          "LANGUAGE",
          "LARGE",
          "LEADING",
          "LENGTH",
          "LEVEL",
          "LIBRARY",
          "LIKE",
          "LIKE2",
          "LIKE4",
          "LIKEC",
          "LIMITED",
          "LOCAL",
          "LOCK",
          "LONG",
          "MAP",
          "MAX",
          "MAXLEN",
          "MEMBER",
          "MERGE",
          "MIN",
          "MINUTE",
          "MLSLABEL",
          "MOD",
          "MODE",
          "MONTH",
          "MULTISET",
          "NAME",
          "NAN",
          "NATIONAL",
          "NATIVE",
          "NATURAL",
          "NATURALN",
          "NCHAR",
          "NEW",
          "NEXTVAL",
          "NOCOMPRESS",
          "NOCOPY",
          "NOT",
          "NOWAIT",
          "NULL",
          "NULLIF",
          "NUMBER_BASE",
          "NUMBER",
          "OBJECT",
          "OCICOLL",
          "OCIDATE",
          "OCIDATETIME",
          "OCIDURATION",
          "OCIINTERVAL",
          "OCILOBLOCATOR",
          "OCINUMBER",
          "OCIRAW",
          "OCIREF",
          "OCIREFCURSOR",
          "OCIROWID",
          "OCISTRING",
          "OCITYPE",
          "OF",
          "OLD",
          "ON",
          "ONLY",
          "OPAQUE",
          "OPEN",
          "OPERATOR",
          "OPTION",
          "ORACLE",
          "ORADATA",
          "ORDER",
          "ORGANIZATION",
          "ORLANY",
          "ORLVARY",
          "OTHERS",
          "OUT",
          "OVERLAPS",
          "OVERRIDING",
          "PACKAGE",
          "PARALLEL_ENABLE",
          "PARAMETER",
          "PARAMETERS",
          "PARENT",
          "PARTITION",
          "PASCAL",
          "PCTFREE",
          "PIPE",
          "PIPELINED",
          "PLS_INTEGER",
          "PLUGGABLE",
          "POSITIVE",
          "POSITIVEN",
          "PRAGMA",
          "PRECISION",
          "PRIOR",
          "PRIVATE",
          "PROCEDURE",
          "PUBLIC",
          "RAISE",
          "RANGE",
          "RAW",
          "READ",
          "REAL",
          "RECORD",
          "REF",
          "REFERENCE",
          "RELEASE",
          "RELIES_ON",
          "REM",
          "REMAINDER",
          "RENAME",
          "RESOURCE",
          "RESULT_CACHE",
          "RESULT",
          "RETURN",
          "RETURNING",
          "REVERSE",
          "REVOKE",
          "ROLLBACK",
          "ROW",
          "ROWID",
          "ROWNUM",
          "ROWTYPE",
          "SAMPLE",
          "SAVE",
          "SAVEPOINT",
          "SB1",
          "SB2",
          "SB4",
          "SEARCH",
          "SECOND",
          "SEGMENT",
          "SELF",
          "SEPARATE",
          "SEQUENCE",
          "SERIALIZABLE",
          "SHARE",
          "SHORT",
          "SIZE_T",
          "SIZE",
          "SMALLINT",
          "SOME",
          "SPACE",
          "SPARSE",
          "SQL",
          "SQLCODE",
          "SQLDATA",
          "SQLERRM",
          "SQLNAME",
          "SQLSTATE",
          "STANDARD",
          "START",
          "STATIC",
          "STDDEV",
          "STORED",
          "STRING",
          "STRUCT",
          "STYLE",
          "SUBMULTISET",
          "SUBPARTITION",
          "SUBSTITUTABLE",
          "SUBTYPE",
          "SUCCESSFUL",
          "SUM",
          "SYNONYM",
          "SYSDATE",
          "TABAUTH",
          "TABLE",
          "TDO",
          "THE",
          "THEN",
          "TIME",
          "TIMESTAMP",
          "TIMEZONE_ABBR",
          "TIMEZONE_HOUR",
          "TIMEZONE_MINUTE",
          "TIMEZONE_REGION",
          "TO",
          "TRAILING",
          "TRANSACTION",
          "TRANSACTIONAL",
          "TRIGGER",
          "TRUE",
          "TRUSTED",
          "TYPE",
          "UB1",
          "UB2",
          "UB4",
          "UID",
          "UNDER",
          "UNIQUE",
          "UNPLUG",
          "UNSIGNED",
          "UNTRUSTED",
          "USE",
          "USER",
          "USING",
          "VALIDATE",
          "VALIST",
          "VALUE",
          "VARCHAR",
          "VARCHAR2",
          "VARIABLE",
          "VARIANCE",
          "VARRAY",
          "VARYING",
          "VIEW",
          "VIEWS",
          "VOID",
          "WHENEVER",
          "WHILE",
          "WITH",
          "WORK",
          "WRAPPED",
          "WRITE",
          "YEAR",
          "ZONE");

  private static final List<String> reservedTopLevelWords =
      Arrays.asList(
          "ADD",
          "ALTER COLUMN",
          "ALTER TABLE",
          "BEGIN",
          "CONNECT BY",
          "DECLARE",
          "DELETE FROM",
          "DELETE",
          "END",
          "EXCEPT",
          "EXCEPTION",
          "FETCH FIRST",
          "FROM",
          "GROUP BY",
          "HAVING",
          "INSERT INTO",
          "INSERT",
          "LIMIT",
          "LOOP",
          "MODIFY",
          "ORDER BY",
          "SELECT",
          "SET CURRENT SCHEMA",
          "SET SCHEMA",
          "SET",
          "START WITH",
          "UPDATE",
          "VALUES",
          "WHERE");

  private static final List<String> reservedTopLevelWordsNoIndent =
      Arrays.asList("INTERSECT", "INTERSECT ALL", "MINUS", "UNION", "UNION ALL");

  private static final List<String> reservedNewlineWords =
      Arrays.asList(
          "AND",
          "CROSS APPLY",
          "ELSE",
          "END",
          "OR",
          "OUTER APPLY",
          "WHEN",
          "XOR",
          // joins
          "JOIN",
          "INNER JOIN",
          "LEFT JOIN",
          "LEFT OUTER JOIN",
          "RIGHT JOIN",
          "RIGHT OUTER JOIN",
          "FULL JOIN",
          "FULL OUTER JOIN",
          "CROSS JOIN",
          "NATURAL JOIN");

  @Override
  public DialectConfig dialectConfig() {
    return DialectConfig.builder()
        .reservedWords(reservedWords)
        .reservedTopLevelWords(reservedTopLevelWords)
        .reservedTopLevelWordsNoIndent(reservedTopLevelWordsNoIndent)
        .reservedNewlineWords(reservedNewlineWords)
        .stringTypes(
            Arrays.asList(
                StringLiteral.DOUBLE_QUOTE,
                StringLiteral.N_SINGLE_QUOTE,
                StringLiteral.Q_SINGLE_QUOTE,
                StringLiteral.SINGLE_QUOTE,
                StringLiteral.BACK_QUOTE))
        .openParens(Arrays.asList("(", "CASE"))
        .closeParens(Arrays.asList(")", "END"))
        .indexedPlaceholderTypes(Collections.singletonList("?"))
        .namedPlaceholderTypes(Collections.singletonList(":"))
        .lineCommentTypes(Collections.singletonList("--"))
        .specialWordChars(Arrays.asList("_", "$", "#", ".", "@"))
        .operators(Arrays.asList("||", "**", "!=", ":="))
        .build();
  }

  @Override
  public Token tokenOverride(Token token) {
    if (Token.isSet(token) && Token.isBy(super.previousReservedToken)) {
      return new Token(TokenTypes.RESERVED, token.value);
    }
    return token;
  }

  public PlSqlFormatter(FormatConfig cfg) {
    super(cfg);
  }
}
