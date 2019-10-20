package grant.guo.leetcode.string

/**
 *
 * https://leetcode.com/problems/simplify-path/
 *
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 *
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix
 *
 * Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.
 *
 */
object leetcode71_simplify_path extends App {


  def simplifyPath(path: String): String = {
    val seq = path.split('/').filter( exp => {
      !(exp.trim.equals("") || exp.equals("."))
    })

    println(seq.mkString("/", "/", ""))
    val list = List.empty[String]

    seq.foldLeft(list)((acc, item) => {
      item match {
        case ".." => if(acc.isEmpty) acc else acc.dropRight(1)
        case _ => (acc :+ item)
      }
    }).mkString("/", "/", "")

  }

  val input = "/a//b////c/d//././/.."
  println(simplifyPath(input))
}
