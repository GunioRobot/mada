

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


import sbt._


class Mada(info: ProjectInfo) extends DefaultProject(info) with AutoCompilerPlugins {
    val continuations = compilerPlugin("org.scala-lang.plugins" % "continuations" % buildScalaVersion)
    val junit = "junit" % "junit" % "3.8.2" % "test"
    val scalatest = "org.scalatest" % "scalatest" % "1.2" % "test"

    override def compileOptions = super.compileOptions ++
        Seq(Deprecation, Unchecked/*, ExplainTypes*/) ++
        compileOptions("-Yrecursion", "50") ++ compileOptions("-P:continuations:enable")

    override def managedStyle = ManagedStyle.Maven
    override def pomExtra =
        <distributionManagement>
            <repository>
                <id>repo</id>
                <url>http://okomok.github.com/maven-repo/releases</url>
            </repository>
            <repository>
                <id>snapshot-repo</id>
                <url>http://okomok.github.com/maven-repo/snapshots</url>
            </repository>
        </distributionManagement>
    lazy val publishTo = Resolver.file("Publish", new java.io.File("../maven-repo/snapshots/"))
}