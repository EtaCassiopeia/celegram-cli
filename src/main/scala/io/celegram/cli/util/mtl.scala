package io.celegram.cli.util

import cats._
import cats.mtl.{ApplicativeAsk, DefaultApplicativeAsk}

trait AskFor[A] {
  type Ask[F[_]] = ApplicativeAsk[F, A]
  def ask[F[_]](implicit ask: Ask[F]): F[A] = ask.ask

  def askLiftF[F[_]: Applicative](fa: F[A]): Ask[F] = new DefaultApplicativeAsk[F, A] {
    val applicative: Applicative[F] = Applicative[F]
    val ask: F[A] = fa
  }
}
