package com.github.arzt.math

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class SudokuTest extends AnyFlatSpec with Matchers {
  "prefix version" should "have valid row" in {
    new Sudoku(2, 2).hasValidRow("1234121") mustBe false
    new Sudoku(2, 2).hasValidRow("1232") mustBe false
    new Sudoku(2, 2).hasValidRow("1234") mustBe true
    new Sudoku(2, 2).hasValidRow("12") mustBe true
    new Sudoku(2, 2).hasValidRow("11") mustBe false
    new Sudoku(2, 2).hasValidRow("123412") mustBe true
  }
  it should "have valid col" in {
    new Sudoku(2, 2).hasValidCol("12341") mustBe false
    new Sudoku(2, 2).hasValidCol("1234") mustBe true
    new Sudoku(2, 2).hasValidCol("12342") mustBe true
    new Sudoku(2, 2).hasValidCol("12342341") mustBe true
    new Sudoku(2, 2).hasValidCol("12342344") mustBe false
    new Sudoku(2, 2).hasValidCol("123423412") mustBe false
    new Sudoku(2, 2).hasValidCol("123423414") mustBe true
  }
  it should "have valid box" in {
    new Sudoku(2, 2).hasValidBox("12341") mustBe false
    new Sudoku(2, 2).hasValidBox("12343") mustBe true
    new Sudoku(2, 2).hasValidBox("123434") mustBe true
    new Sudoku(2, 2).hasValidBox("123432") mustBe false
    new Sudoku(2, 2).hasValidBox("12343212") mustBe true
    new Sudoku(2, 2).hasValidBox("123432122") mustBe true
    new Sudoku(2, 2).hasValidBox("1234321222") mustBe false
    new Sudoku(2, 2).hasValidBox("1234321221") mustBe true
  }
  "next candidate string" should "yield next candidate" in {
    val s = new Sudoku(2, 2)
    val nextCandidate = (x: String) => s.nextCandidate(s.isValid, x)
    nextCandidate("121") mustBe "122"
    nextCandidate("1") mustBe "11"
    nextCandidate("11") mustBe "12"
    nextCandidate("12") mustBe "121"
    nextCandidate("122") mustBe "123"
    nextCandidate("123") mustBe "1231"
  }
}
