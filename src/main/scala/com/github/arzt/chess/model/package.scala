package com.github.arzt.chess

import com.github.arzt.tensor.Tensor
import com.github.arzt.tensor.TensorImplicits._
package object model {

  val startBoard: Tensor[Int] = Array[Int](
    white.Rook1, white.Knight1, white.Bishop, white.King, white.Queen, white.Bishop2, white.Knight2, white.Rook2,
    white.Pawn1, white.Pawn2, white.Pawn3, white.Pawn4, white.Pawn5, white.Pawn6, white.Pawn7, white.Pawn8,
    0,0,0,0,0,0,0,0,
    0,0,0,0,0,0,0,0,
    0,0,0,0,0,0,0,0,
    0,0,0,0,0,0,0,0,
    black.Pawn1, black.Pawn2, black.Pawn3, black.Pawn4, black.Pawn5, black.Pawn6, black.Pawn7, black.Pawn8,
    black.Rook1, black.Knight1, black.Bishop, black.King, black.Queen, black.Bishop2, black.Knight2, black.Rook2
  ).asRows(8)

  case class Pos(col: Int, row: Int)

  trait Piece {
    val pos: Option[Pos]
  }

  case class King(pos: Option[Pos]) extends Piece

  case class Queen(pos: Option[Pos]) extends Piece

  case class Player(
      king: King
  )

  case class ChessBoard(white: Player, black: Player)
}
