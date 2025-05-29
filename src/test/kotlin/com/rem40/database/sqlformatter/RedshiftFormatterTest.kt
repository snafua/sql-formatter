package com.rem40.database.sqlformatter

import com.rem40.database.sqlformatter.features.supportsAlterTable
import com.rem40.database.sqlformatter.features.supportsAlterTableModify
import com.rem40.database.sqlformatter.features.supportsCreateTable
import com.rem40.database.sqlformatter.features.supportsJoin
import com.rem40.database.sqlformatter.features.supportsOperators
import com.rem40.database.sqlformatter.features.supportsSchema
import com.rem40.database.sqlformatter.features.supportsStrings
import com.rem40.database.sqlformatter.languages.Dialect
import com.rem40.database.sqlformatter.languages.StringLiteral
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Skip
import org.spekframework.spek2.style.specification.describe

object RedshiftFormatterTest :
  Spek({
    val formatter = SqlFormatter.of(Dialect.Redshift)

    describe("RedshiftFormatter") {
      with(formatter) {
        behavesLikeSqlFormatter(formatter)
        supportsCreateTable(formatter)
        supportsAlterTable(formatter)
        supportsAlterTableModify(formatter)
        supportsStrings(
          formatter,
          listOf(StringLiteral.DOUBLE_QUOTE, StringLiteral.SINGLE_QUOTE, StringLiteral.BACK_QUOTE)
        )
        supportsSchema(formatter)
        supportsOperators(
          formatter,
          listOf("%", "^", "|/", "||/", "<<", ">>", "&", "|", "~", "!", "!=", "||")
        )
        supportsJoin(formatter)

        it("formats LIMIT") {
          expect(format("SELECT col1 FROM tbl ORDER BY col2 DESC LIMIT 10;"))
            .toBe(
              """
        SELECT
          col1
        FROM
          tbl
        ORDER BY
          col2 DESC
        LIMIT
          10;
                    """.trimIndent()
            )
        }

        it("formats only -- as a line comment") {
          val result =
            format(
              """
        SELECT col FROM
        -- This is a comment
        MyTable;
        """
            )
          expect(result)
            .toBe(
              """
        SELECT
          col
        FROM
          -- This is a comment
          MyTable;
                    """.trimIndent()
            )
        }

        it("recognizes @ as part of identifiers") {
          val result = format("SELECT @col1 FROM tbl")
          expect(result)
            .toBe(
              """
        SELECT
          @col1
        FROM
          tbl
                    """.trimIndent()
            )
        }

        it("formats DISTKEY and SORTKEY after CREATE TABLE", skip = Skip.Yes()) {
          expect(
              format(
                "CREATE TABLE items (a INT PRIMARY KEY, b TEXT, c INT NOT NULL, d INT NOT NULL) DISTKEY(created_at) SORTKEY(created_at);"
              )
            )
            .toBe(
              """
        CREATE TABLE items (
          a INT PRIMARY KEY,
          b TEXT,
          c INT NOT NULL,
          d INT NOT NULL
        )
        DISTKEY(created_at)
        SORTKEY(created_at);
                    """.trimIndent()
            )
        }

        it("formats COPY") {
          expect(
              format(
                """
          COPY schema.table
          FROM 's3://bucket/file.csv'
          IAM_ROLE 'arn:aws:iam::123456789:role/rolename'
          FORMAT AS CSV DELIMITER ',' QUOTE '"'
          REGION AS 'us-east-1'
          """
              )
            )
            .toBe(
              """
        COPY
          schema.table
        FROM
          's3://bucket/file.csv'
        IAM_ROLE
          'arn:aws:iam::123456789:role/rolename'
        FORMAT
          AS CSV
        DELIMITER
          ',' QUOTE '"'
        REGION
          AS 'us-east-1'
                    """.trimIndent()
            )
        }
      }
    }
  })
