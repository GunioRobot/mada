

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


import sbt._


class Mada(info: ProjectInfo) extends DefaultProject(info) {
    val junit = "junit" % "junit" % "3.8.2" % "test"
    val scalatest = "org.scalatest" % "scalatest" % "1.2" % "test"
    override def compileOptions = Seq(Deprecation, Unchecked/*, ExplainTypes*/) ++ compileOptions("-Yrecursion", "50") ++ super.compileOptions

    override def managedStyle = ManagedStyle.Maven
    override def pomExtra =
        <distributionManagement>
            <repository>
                <id>repo</id>
                <url>http://github.com/okomok/maven-repo/raw/master/releases</url>
            </repository>
            <repository>
                <id>snapshot-repo</id>
                <url>http://github.com/okomok/maven-repo/raw/master/snapshots</url>
            </repository>
        </distributionManagement>
    lazy val publishTo = Resolver.file("Publish", new java.io.File("../maven-repo/snapshots/"))
}