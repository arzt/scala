package com.github.arzt.chess

import com.github.arzt.chess.model.{black, white}
import com.github.arzt.rl.Environment
import com.github.arzt.tensor.Tensor
import com.github.arzt.tensor.TensorImplicits._

object Chess extends Environment[Tensor[Int], Int]{
  override val init: Tensor[Int] = Array[Int](
    white.Rook1, white.Knight1, white.Bishop, white.King, white.Queen, white.Bishop2, white.Knight2, white.Rook2,
    white.Pawn1, white.Pawn2, white.Pawn3, white.Pawn4, white.Pawn5, white.Pawn6, white.Pawn7, white.Pawn8,
    0,0,0,0,0,0,0,0,
    0,0,0,0,0,0,0,0,
    0,0,0,0,0,0,0,0,
    0,0,0,0,0,0,0,0,
    black.Pawn1, black.Pawn2, black.Pawn3, black.Pawn4, black.Pawn5, black.Pawn6, black.Pawn7, black.Pawn8,
    black.Rook1, black.Knight1, black.Bishop, black.King, black.Queen, black.Bishop2, black.Knight2, black.Rook2
  ).asRows(8)

  override def isFinal(s: Tensor[Int]): Boolean = ???

  override def apply(s: Tensor[Int], a: Int): Tensor[Int] = ???

  override def applyPolicy(p: Tensor[Int] => Int): Unit = ???
}
