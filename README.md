A [Scalatra](http://www.scalatra.org/) sample project using [Neo4j](http://neo4j.org/) targeted to be run at [Heroku](http://www.heroku.com/).

# Quick Start

1. [Sign up for Heroku](https://api.heroku.com/signup)
1. Clone the [Gensen template](http://gensen.herokuapp.com/install/21) to your Heroku account
1. Install the [Neo4j addon](https://addons.heroku.com/neo4j) to the application
1. Check the Neo4j addon's URL, login and password and put them as `neo4j.url`, `neo4j.login` and `neo4j.password` properties to `src/main/resources/props/production.default.props`
1. Commit and push to Heroku, it should be working

