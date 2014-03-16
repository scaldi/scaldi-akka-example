package scaldi.akka.example

import scaldi.Injector
import akka.actor.{ActorRef, Actor}
import scaldi.akka.AkkaInjectable

class Receptionist (implicit inj: Injector) extends Actor with AkkaInjectable {
  import Messages._

  val userService = inject [UserService]

  val orderProcessorProps = injectActorProps [OrderProcessor]
  val priceCalculator = injectActorRef [PriceCalculator]

  def receive = {
    case PlaceOrder(userName, itemId, netAmount) =>
      userService.getUserByUserName(userName)
        .map { user =>
          val processor = context.actorOf(orderProcessorProps)
          processor forward ProcessOrder(user, itemId, netAmount)
          sender ! OrderProcessingStarted(processor)
        }
        .getOrElse {
          sender ! OrderProcessingFailed("Unknown user")
        }
    case msg: CalculatePrice =>
      priceCalculator forward msg
  }
}

object Messages {
  case class PlaceOrder(userName: String, itemId: Long, netAmount: Int)
  case class ProcessOrder(user: User, itemId: Long, netAmount: Int)
  case class CalculatePrice(netAmount: Int)
  case object CancelProcessing

  case class OrderProcessingFailed(reason: String)
  case class OrderProcessed(orderNumber: String, grossAmount: Int)
  case class OrderProcessingStarted(processor: ActorRef)
  case class GrossPriceCalculated(netPrice: Int, grossPrice: Int)

}
