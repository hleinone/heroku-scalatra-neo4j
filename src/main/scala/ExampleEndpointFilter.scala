import java.net.URL

import scala.collection.JavaConversions.iterableAsScalaIterable

import org.neo4j.graphdb.DynamicRelationshipType
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.rest.graphdb.RestGraphDatabase
import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraFilter

import grizzled.slf4j.Logging

class ExampleEndpointFilter extends ScalatraFilter with ScalateSupport with Logging {
  debug("DEVELOPMENT: " + isDevelopmentMode + " environment: " + System.getenv("org.scalatra.environment"))
  val gds: GraphDatabaseService =
    if (isDevelopmentMode)
      new RestGraphDatabase("http://localhost:7474/db/data")
    else
      new RestGraphDatabase("http://749dda205:e272997de@d4b0f9790.hosted.neo4j.org:7027/db/data/", "749dda205", "e272997de")

  val me = gds.getReferenceNode()
  if (!me.hasProperty("name")) {
    me.setProperty("name", "I")
    val you = gds.createNode()
    you.setProperty("name", "you")
    me.createRelationshipTo(you, DynamicRelationshipType.withName("love"))
  }

  get("/") {
    val me = gds.getNodeById(0)
    val rels = me.getRelationships(DynamicRelationshipType.withName("love"))
    val rel = rels.head
    val you = rel.getEndNode()
    contentType = "text/html"
    templateEngine.layout("/WEB-INF/scalate/templates/index.scaml",
      Map("me" -> me.getProperty("name"),
        "rel" -> rel.getType().name(),
        "you" -> you.getProperty("name")));
  }

  notFound {
    // If no route matches, then try to render a Scaml template
    val templateBase = requestPath match {
      case s if s.endsWith("/") => s + "index"
      case s => s
    }
    val templatePath = "/WEB-INF/scalate/templates/" + templateBase + ".scaml"
    servletContext.getResource(templatePath) match {
      case url: URL =>
        contentType = "text/html"
        templateEngine.layout(templatePath)
      case _ =>
        filterChain.doFilter(request, response)
    }
  }
}