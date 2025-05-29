package com.rem40.database.sqlformatter

import com.rem40.database.sqlformatter.features.supportsAlterTable
import com.rem40.database.sqlformatter.features.supportsBetween
import com.rem40.database.sqlformatter.features.supportsCase
import com.rem40.database.sqlformatter.features.supportsCreateTable
import com.rem40.database.sqlformatter.features.supportsJoin
import com.rem40.database.sqlformatter.features.supportsOperators
import com.rem40.database.sqlformatter.features.supportsSchema
import com.rem40.database.sqlformatter.features.supportsStrings
import com.rem40.database.sqlformatter.languages.Dialect
import com.rem40.database.sqlformatter.languages.StringLiteral
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object PostgreSqlFormatterTest :
  Spek({
    val formatter = SqlFormatter.of(Dialect.PostgreSql)

    describe("PostgreSqlFormatter") {
      with(formatter) {
        behavesLikeSqlFormatter(formatter)
        supportsCase(formatter)
        supportsCreateTable(formatter)
        supportsAlterTable(formatter)
        supportsStrings(
          formatter,
          listOf(
            StringLiteral.DOUBLE_QUOTE,
            StringLiteral.SINGLE_QUOTE,
            StringLiteral.U_DOUBLE_QUOTE,
            StringLiteral.U_SINGLE_QUOTE,
            StringLiteral.DOLLAR
          )
        )
        supportsBetween(formatter)
        supportsSchema(formatter)
        supportsOperators(
          formatter,
          listOf(
            "%",
            "^",
            "!",
            "!!",
            "@",
            "!=",
            "&",
            "|",
            "~",
            "#",
            "<<",
            ">>",
            "||/",
            "|/",
            "::",
            "->>",
            "->",
            "~~*",
            "~~",
            "!~~*",
            "!~~",
            "~*",
            "!~*",
            "!~",
            "@@",
            "@@@",
          )
        )
        supportsJoin(formatter)

        it("supports \$n placeholders") {
          val result = format("SELECT \$1, \$2 FROM tbl")
          expect(result)
            .toBe(
              """
        SELECT
          $1,
          $2
        FROM
          tbl
                    """.trimIndent()
            )
        }

        it("replaces \$n placeholders with param values") {
          val result =
            format(
              "SELECT \$1, \$2 FROM tbl",
              mapOf("1" to """"variable value"""", "2" to """"blah"""")
            )
          expect(result)
            .toBe(
              """
        SELECT
          "variable value",
          "blah"
        FROM
          tbl
                    """.trimIndent()
            )
        }

        it("supports :name placeholders") { expect(format("foo = :bar")).toBe("foo = :bar") }

        it("replaces :name placeholders with param values") {
          expect(
              format(
                """foo = :bar AND :"field" = 10 OR col = :'val'""",
                mapOf(
                  "bar" to "'Hello'",
                  "field" to "some_col",
                  "val" to 7,
                )
              )
            )
            .toBe(
              """
        foo = 'Hello'
        AND some_col = 10
        OR col = 7
                    """.trimIndent()
            )
        }
      }
    }
  })
