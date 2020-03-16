package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Sam is a plague carrier"))
  }

  def name(name: String) = Action {
    Ok(s"Hello $name")
  }

  def getGreeting() = Action { implicit request: Request[AnyContent] =>
    Ok("Hello World!")
  }

  def redirect() = Action {
    Redirect("http://www.google.com")
  }

  def infinite() = Action {
    Redirect(routes.HomeController.index())
  }

  def toDo() = TODO

  def htmlText() = Action {
    Ok(<h1>Hello World!</h1>).as("text/html")

  }

  def html() = Action {
    Ok(<h1>Hello World!</h1>).as(HTML)
    Ok("Hello World!").withCookies(
      Cookie("colour", "blue")
    )
    implicit request: Request[AnyContent] =>
      Ok("Welcome!").withSession(request.session +  ("connected" -> "user@gmail.com"))
  }

  def hello() = Action {
    implicit request: Request[AnyContent] =>
      implicit val myCustomCharset: Codec = Codec.javaSupported("iso-8859-1")
      Ok("Hello World!")
  }

  def getCookie() = Action {
    implicit request: Request[AnyContent] =>
      request.cookies.get("colour") match {
        case Some(cookie) => Ok(s"Your cookie value is: ${cookie.value}")
        case _ => Ok("Cookie not found")
      }
  }

  def getSession() = Action {
    implicit request: Request[AnyContent] =>
      Ok(request.session.get("connected").getOrElse("User is not logged in"))
  }

  def read() = Action {
    implicit request: Request[AnyContent] =>
      Ok(request.flash.get("success").getOrElse("Something went wrong while redirecting"))
  }

  def write() = Action {
    implicit request: Request[AnyContent] =>
      Redirect("/read").flashing("success" -> "You have been successfully redirected")
  }

}
