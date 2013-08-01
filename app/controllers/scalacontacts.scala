package controllers


import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import anorm._

import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.libs.Json.toJson
import models.Contact
import models.ScalaContact
import play.api._
import play.api.mvc._
import play.api.libs.json._

import play.api.libs.concurrent.Execution.Implicits._

import play.api.libs.concurrent.Akka
import play.api.Play.current 
import scala.concurrent.Future
import play.libs.F
import play.libs.F.Promise
//import ExecutionContext.Implicits.global 

import views._

object ScalaContacts extends Controller {

	/***
	*
	*/
	case class ContactBinding(first: String, last: String, phone: String)

	/**
   * Contact Form definition.
   */
  val contactForm: Form[ContactBinding] = Form(    
  // Defines a mapping that will handle Contact values
  mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "phoneNumber" -> nonEmptyText
    )(ContactBinding.apply)(ContactBinding.unapply)
  )
  
  /**
   * Contact Form definition.
   */
  val scalaContactForm = Form(
    mapping(
      "id" -> ignored(NotAssigned:Pk[Long]),
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "phoneNumber" -> nonEmptyText
    )(ScalaContact.apply)(ScalaContact.unapply)
  )
  

	/***
	* UTILITAIRE : convertie binding form en Contact (DB)
	*/
  def toDBContact(frm : ContactBinding ) : Contact = {
  	var contact : Contact = new Contact();
  	contact.firstName = frm.first;
  	contact.lastName = frm.last;
  	contact.phoneNumber = frm.phone;
  	return contact;
  }
  
  	/***
	* UTILITAIRE : convertie binding form en Contact (DB)
	
  def toScalaDBContact(frm : ContactBinding ) : ScalaContact = {
  	var contact : ScalaContact = new ScalaContact(anorm.Pk(0l), frm.first, frm.last, frm.phone);  	
  	return contact;
  }
  */

	/***
	* UTILITAIRE : creation en DB
	*/
  def saveContactForm(frm : ContactBinding) : JsValue = {
  	var contactDB : Contact = toDBContact(frm);
	  contactDB.save();
    getAllAsJsValue()
  }

  /***
	* UTILITAIRE : retourne la liste complete comme JSON
	*/
  def getAllAsJsValue() : JsValue = {
  	Json.toJson(ScalaContact.findAll().map { 
		    c=> (
		    	Map("id" -> c.id.toString,"firstName" -> c.firstName, "lastName" -> c.lastName,"phoneNumber" -> c.phoneNumber)
		  	)
	  	}
	  	)
  }


  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
  
	/***
	* ACTION : index
	*/
  def index = Action {
  	Ok(html.listScalaContacts(ScalaContact.findAll()));    
  }


	/***
	* ACTION : retourne liste complete comme json
	*/
  def getAll() = Action {
	 
		val promise = Akka.future {
			getAllAsJsValue() 
		}
	  
	  Async {
	    promise.map(l => Ok(l))
	  }

	}

	

  
	/***
	* ACTION : suppression asynchrone
	*/
	def delete(id: Integer) = Action {
		
		val promise = Akka.future { 
			ScalaContact.delete(id)				
			getAllAsJsValue()
		}	
		
		Async{
			promise.map(l => Ok(l))														
		}
		
	}

	/***
	* ACTION : ajout asynchrone
	*/
	def add() = Action { implicit request =>
    contactForm.bindFromRequest.fold(
      errors => { 
      						sys.error("non non non")						 
      					},
      contact => { 
      	
      						val promise = Akka.future {
      							saveContactForm(contact) 
      						}
      						
									Async {
										promise.map(l => Ok(l))														
									}      						
									
      					}
      )
	}

	/***
	* ACTION : test debile
	*/
  def hello(name: String) = Action {
  	Ok("Hello " + name)  
	}


}
  