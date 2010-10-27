/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2010, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.swing

import java.awt.CardLayout
import javax.swing.JPanel

/**
 * A panel that displays only one component at a time. 
 * based on: http://download.oracle.com/javase/tutorial/uiswing/layout/card.html
 * 
 * @author andy hicks
 * @see java.awt.CardLayout
 */
class CardPanel(hgap: Int, vgap: Int) extends Panel with SequentialContainer.Wrapper {
  def this() = this(0, 0)

  peer.setLayout(new java.awt.CardLayout(vgap, hgap))
  private def cardLayout = peer.getLayout.asInstanceOf[java.awt.CardLayout]

  /** add card to panel. */
  def +=(c: scala.swing.Component, name: String) { peer.add(c.peer, name) }

  /** Show the first item. */
  def first { cardLayout.first(peer) }

  /** Show the last item. */
  def last { cardLayout.last(peer) }

  /** Show the previous item. */
  def next { cardLayout.next(peer) }
  
  /** Show the previous item. */
  def previous { cardLayout.previous(peer) }

  /** Show the Nth item. */
  def show(s: String) { cardLayout.show(peer, s) }
  
  //  def vGap: Int = layoutManager.getVgap
  //  def vGap_=(n: Int) { layoutManager.setVgap(n) }
  //  def hGap: Int = layoutManager.getHgap
  //  def hGap_=(n: Int) { layoutManager.setHgap(n) }
}
