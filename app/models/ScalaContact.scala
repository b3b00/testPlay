package models
 
import play.api.db._
import play.api.Play.current
 
import anorm._
import anorm.SqlParser._
 
case class ScalaContact(id: Pk[Long], firstName: String, lastName: String, phoneNumber: String)
 
object ScalaContact {
 
  val simple = {
   get[Pk[Long]]("contact.id") ~
    get[String]("contact.first_name") ~
    get[String]("contact.last_name") ~
    get[String]("contact.phone_number") map {
      case id~firstname~lastname~phonenumber => ScalaContact(id, firstname, lastname, phonenumber)
    }
  }
 
  def findAll(): Seq[ScalaContact] = {
    DB.withConnection { implicit connection =>
      SQL("select * from contact").as(ScalaContact.simple *)
    }
  }

  def delete(id: Integer): Unit = {
    DB.withConnection { implicit connection =>
      SQL("delete from contact where id={id}").on(
        'id -> id
      ).executeUpdate()
    }
  }
 
  def create(contact: ScalaContact): Unit = {
    DB.withConnection { implicit connection =>
      SQL("insert into contact(first_name,last_name,phone_number) values ({firstName},{lastName},{phoneNumber})").on(
        'firstname -> contact.firstName,        
        'lastname -> contact.lastName,
        'name -> contact.phoneNumber
      ).executeUpdate()
    }
  }
 
}