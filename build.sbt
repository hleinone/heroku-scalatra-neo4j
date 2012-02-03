import com.typesafe.startscript.StartScriptPlugin
import com.github.siasia.WebPlugin.webSettings

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

seq(webSettings :_*)

name := "scalatra-neo4j"

version := "1.0"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.0.3",
  "org.scalatra" %% "scalatra-scalate" % "2.0.1",
  "org.clapper" %% "grizzled-slf4j" % "0.6.6",
  "org.neo4j" % "neo4j-rest-graphdb" % "1.6",
  "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725" % "container",
  "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725",
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default"
)

resolvers ++= Seq(
  "Sonatype OSS" at "http://oss.sonatype.org/content/repositories/releases",
  "Web plugin repo" at "http://siasia.github.com/maven2",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "Neo4j Release Repository" at "http://m2.neo4j.org/content/repositories/releases"
)
