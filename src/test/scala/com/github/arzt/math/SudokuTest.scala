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
  "matches cell" should "respect cell constraint" in {
    val s = new Sudoku(2, 2)
    s.matchesCell("1", "____1") mustBe true
    s.matchesCell("12", "+r2") mustBe true
    s.matchesCell("123", "123") mustBe true
    s.matchesCell("123", "13") mustBe true
    s.matchesCell("123", "1 3") mustBe true
    s.matchesCell("123", "122") mustBe false
    s.matchesCell("122", "122455") mustBe true
    s.matchesCell("1224", "122") mustBe true
  }
  "matches row" should "respect row constrains" in {
    val s = new Sudoku(2, 2)
    s.matchesRow("12341", "______2_____") mustBe true
    s.matchesRow("12341", "______2") mustBe true
    s.matchesRow("12341", "_______1") mustBe false
    s.matchesRow("21", "__3_") mustBe true
    s.matchesRow("1", "21") mustBe false
    s.matchesRow("1", "2341") mustBe false
    s.matchesRow("1", "__1_") mustBe false
  }
  "matches col" should "respect col constrains" in {
    val s = new Sudoku(2, 2)
    s.matchesCol("1", "____2") mustBe true
    s.matchesCol("12", "_________2") mustBe false
    s.matchesCol("1", "____1") mustBe false
  }
  "get box offset 2x2" should "convert absolute index to box offset" in {
    val s = new Sudoku(2,2)
    s.boxOffset(0) mustBe 0
    s.boxOffset(4) mustBe 0
    s.boxOffset(2) mustBe 2
    s.boxOffset(13) mustBe 8
    s.boxOffset(11) mustBe 10
    s.boxOffset(15) mustBe 10
  }
  "get box offset 3x3" should "convert absolute index to box offset" in {
    val s = new Sudoku(3,3)
    s.boxOffset(0) mustBe 0
    s.boxOffset(3) mustBe 3
    s.boxOffset(6) mustBe 6
    s.boxOffset(9) mustBe 0
    s.boxOffset(12) mustBe 3
    s.boxOffset(15) mustBe 6
    s.boxOffset(18) mustBe 0
    s.boxOffset(80) mustBe 6*9+2*3
  }
  "get internal box offset" should "compute internal box offset" in {
    val s = new Sudoku(2, 2)
    s.inverseBoxIndex(0) mustBe 0
    s.inverseBoxIndex(1) mustBe 1
    s.inverseBoxIndex(2) mustBe 4
    s.inverseBoxIndex(3) mustBe 5
  }
  "get internal box offset 3x3" should "compute internal box offset" in {
    val s = new Sudoku(3, 3)
    s.inverseBoxIndex(0) mustBe 0
    s.inverseBoxIndex(1) mustBe 1
    s.inverseBoxIndex(2) mustBe 2
    s.inverseBoxIndex(3) mustBe 3 + 6
    s.inverseBoxIndex(4) mustBe 4 + 6
    s.inverseBoxIndex(5) mustBe 5 + 6
    s.inverseBoxIndex(6) mustBe 6 + 6 + 6
    s.inverseBoxIndex(7) mustBe 7 + 6 + 6
    s.inverseBoxIndex(8) mustBe 8 + 6 + 6
  }
  "within box offset" should "work" in {
    val s = new Sudoku(2, 2)
    s.boxIndex(0) mustBe 0
    s.boxIndex(1) mustBe 1
    s.boxIndex(2) mustBe 0
    s.boxIndex(3) mustBe 1
    s.boxIndex(4) mustBe 2
    s.boxIndex(5) mustBe 3
    s.boxIndex(6) mustBe 2
    s.boxIndex(7) mustBe 3
  }
  "exhaustive box conversion test" should "work" in {
    val s = new Sudoku(3,3)
    for (x <- 0 to s.cellCount) {
      val result = s.inverseBoxIndex(s.boxIndex(x)) + s.boxOffset(x)
      result mustBe x
    }
  }
  "matches box" should "respect box constrains" in {
    val s = new Sudoku(2, 2)
    s.matchesBox("21", "____1___________") mustBe false
    s.matchesBox("1", "_____1") mustBe false
    s.matchesBox("12343", "____________3______") mustBe true
    s.matchesBox("12343", "_____4") mustBe true
    s.matchesBox("12343", "_____3") mustBe false
    s.matchesBox("21", "_____1__________") mustBe false
    s.matchesBox("1", "_____2") mustBe true
    s.matchesBox("21", "_____2") mustBe true
    s.matchesBox("21", "_____1") mustBe false
  }
  it should "yield matching sudoku" in {
    val s = new Sudoku(3, 3)
    val te =
      "_3_______" +
      "___195___" +
      "__8____6_" +
      "8___6____" +
      "4__8____1" +
      "____2____" +
      "_6____28_" +
      "___419__5" +
      "_______7_"
    val p = (x: String) => s.isValid(x) && s.matchesTemplate(x, te)
    var su = "1"
    while (true) {
      while (su.length < 81) {
        su = s.nextCandidate(p, su)
      }
      print(su)
    }

  }
}
