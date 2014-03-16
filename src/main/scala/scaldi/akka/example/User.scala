package scaldi.akka.example

import scaldi.Injectable

trait UserService {
  def getUserByUserName(userName: String): Option[User]
}

class SimpleUserService extends UserService {
  private def Users = Map("test" -> User("X54532", "Test", "User"))

  def getUserByUserName(userName: String) = Users get userName
}

case class User(customerNumber: String, firstName: String, lastName: String)
