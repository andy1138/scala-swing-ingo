/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2010, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.swing

import swing.CardPanel.Card


object  CardPanel {
  class Card protected[CardPanel](val comp: scala.swing.Component, val name: String)  extends Proxy {
    def self = comp

  }
}

/**
 * A panel that displays only one component at a time. 
 * based on: http://download.oracle.com/javase/tutorial/uiswing/layout/card.html
 * 
 * @author andy hicks
 * @see java.awt.CardLayout
 */
class CardPanel(hgap: Int, vgap: Int) extends Panel with Publisher  {
  def this() = this(0, 0)

  peer.setLayout(new java.awt.CardLayout(vgap, hgap))
  private def cardLayout = peer.getLayout.asInstanceOf[java.awt.CardLayout]




  object card extends BufferWrapper[Card] {
    /** add card to panel. */
    def +=(card:Card):this.type = { peer.add(card.comp.peer, card.name);this }

    protected def insertAt(n: Int, c: Card) {
      peer.add(c.comp.peer, n.toString)
    }

    override def remove(n: Int): Card = { val t = peer.getComponent(n);peer.remove(n);t.asInstanceOf[Card]}
    def length = peer.getComponentCount
    def apply(n: Int) = new Card(UIElement.cachedWrapper[Component](peer.getComponent(n).asInstanceOf[javax.swing.JComponent]), "")

  }


  /** Show the first item. */
  def first { cardLayout.first(peer) }

  /** Show the last item. */
  def last { cardLayout.last(peer) }

  /** Show the previous item. */
  def next { cardLayout.next(peer) }
  
  /** Show the previous item. */
  def previous { cardLayout.previous(peer) }

  /** Show the Nth item. */
  def show(s: String) { cardLayout.show( peer, s) }
  
  //  def vGap: Int = layoutManager.getVgap
  //  def vGap_=(n: Int) { layoutManager.setVgap(n) }
  //  def hGap: Int = layoutManager.getHgap
  //  def hGap_=(n: Int) { layoutManager.setHgap(n) }
}
