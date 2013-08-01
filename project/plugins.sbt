// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects

addSbtPlugin("play" % "sbt-plugin" % "2.1.3-RC1")

//addSbtPlugin("com.github.play2war" % "play2-war-plugin" % "0.9-RC2")

//addSbtPlugin("mysql" % "mysql-connector-java" % "5.1.23")