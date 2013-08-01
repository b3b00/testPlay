import sbt._
import Keys._
import play.Project._


object ApplicationBuild extends Build {

  val appName         = "testPlay"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean)
	//"commons-io" % "commons-io" % "2.3")
	//"mysql" % "mysql-connector-java" % "5.1.18"
  //)

val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings()
  // ... Your own settings here
 // Play2WarKeys.servletVersion := "3.0"
//).settings(Play2WarPlugin.play2WarSettings: _*)

}
