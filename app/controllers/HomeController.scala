package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Sam is a plague carrier"))
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

}
