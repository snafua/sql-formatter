package com.rem40.database.sqlformatter

import com.rem40.database.sqlformatter.features.supportsAlterTable
import com.rem40.database.sqlformatter.features.supportsBetween
import com.rem40.database.sqlformatter.features.supportsCase
import com.rem40.database.sqlformatter.features.supportsCreateTable
import com.rem40.database.sqlformatter.features.supportsJoin
import com.rem40.database.sqlformatter.features.supportsOperators
import com.rem40.database.sqlformatter.features.supportsStrings
import com.rem40.database.sqlformatter.languages.StringLiteral
import org.spekframework.spek2.style.specification.Suite

fun Suite.behavesLikeMariaDbFormatter(formatter: SqlFormatter.Formatter) {
  with(formatter) {
    behavesLikeSqlFormatter(formatter)
    supportsCase(formatter)
    supportsCreateTable(formatter)
    supportsAlterTable(formatter)
    supportsStrings(
      formatter,
      listOf(StringLiteral.DOUBLE_QUOTE, StringLiteral.SINGLE_QUOTE, StringLiteral.BACK_QUOTE)
    )
    supportsBetween(formatter)
    supportsOperators(
      formatter,
      listOf(
        "%",
        "&",
        "|",
        "^",
        "~",
        "!=",
        "!",
        "<=>",
        "<<",
        ">>",
        "&&",
        "||",
        ":=",
      )
    )
    supportsJoin(
      formatter,
      without = listOf("FULL"),
      additionally =
        listOf(
          "STRAIGHT_JOIN",
          "NATURAL LEFT JOIN",
          "NATURAL LEFT OUTER JOIN",
          "NATURAL RIGHT JOIN",
          "NATURAL RIGHT OUTER JOIN",
        )
    )

    it("supports # comments") {
      expect(format("SELECT a # comment\nFROM b # comment"))
        .toBe(
          """
      SELECT
        a # comment
      FROM
        b # comment
                """.trimIndent()
        )
    }

    it("supports @variables") {
      expect(format("SELECT @foo, @bar"))
        .toBe("""
      SELECT
        @foo,
        @bar
                """.trimIndent())
    }

    it("supports setting variables: @var :=") {
      expect(format("SET @foo := (SELECT * FROM tbl);"))
        .toBe(
          """
      SET
        @foo := (
          SELECT
            *
          FROM
            tbl
        );
                """.trimIndent()
        )
    }
  }
}
