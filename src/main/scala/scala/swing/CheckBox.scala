/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2010, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import javax.swing._

/**
 * Two state button that can either be checked or unchecked.
 * 
 * @see javax.swing.JCheckBox
 */
class CheckBox(text: String, icon0:Icon, selected:Boolean) extends ToggleButton {
  override lazy val peer: JCheckBox = new JCheckBox(text, toNullIcon(icon0), selected) with SuperMixin
  def this() = this("", EmptyIcon, false)
  def this(text:String) = this(text, EmptyIcon, false)
  def this(text:String, icon:Icon) = this(text, icon, false)
  def this(icon:Icon) = this("", icon, false)

  def borderPaintedFlat: Boolean = peer.isBorderPaintedFlat
  def borderPaintedFlat_=(flat: Boolean) { peer.setBorderPaintedFlat(flat) }
}
