/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2010, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.swing
package test

import event._

import BorderPanel._

/**
 * CardPanel Demo
 * Based on http://download.oracle.com/javase/tutorial/uiswing/layout/card.html
 *
 * @author andy hicks
 */
object CardPanelDemo extends SimpleSwingApplication {
  def top = new MainFrame {

    val BUTTONPANEL = "Card with JButtons"
    val TEXTPANEL = "Card with JTextField"

    val cb = new ComboBox(BUTTONPANEL :: TEXTPANEL :: Nil)
    val first = new Button("First") {
        reactions += { case ButtonClicked(_) => println("first"); card.first }
    }
    val last = new Button("Last") {
        reactions += { case ButtonClicked(_) => println("last"); card.last }
    }
    val next = new Button("Next") {
        reactions += { case ButtonClicked(_) => println("next"); card.next }
    }
    
    val prev = new Button("Prev") {
        reactions += { case ButtonClicked(_) => println("previous"); card.previous }
    }

    val cmd = new FlowPanel() {
      contents += first
      contents += last
      contents += cb
      contents += next
      contents += prev
    }

    reactions += {
      case SelectionChanged(`cb`) => card.show(cb.selection.item)
    }
    listenTo(cb.selection)

    //Create the "cards".
    val buttons = new FlowPanel() {
      contents += new Button("Button 1")
      contents += new Button("Button 2")
      contents += new Button("Button 3")
    }

    val text = new FlowPanel() {
      contents += new TextField("TextField", 20)
    }

//    val card = new CardPanel() {
//      contents += (buttons, BUTTONPANEL)
//      contents += (text, TEXTPANEL)
//    }
    
    val card = new CardPanel()
    card += (buttons, BUTTONPANEL)
    card += (text, TEXTPANEL)

    title = "CardPanel Demo"
    contents = new BorderPanel {
      layout(cmd) = Position.North
      layout(card) = Position.Center
    }

    size = new Dimension(600, 100)
  }

}

