package org.jetbrains.plugins.notebooks.ui.jupyterToolbar

import com.intellij.ide.ui.customization.CustomActionsSchema
import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.editor.impl.EditorImpl
import com.intellij.util.concurrency.annotations.RequiresEdt
import org.jetbrains.plugins.notebooks.ui.visualization.DefaultNotebookEditorAppearanceSizes
import java.awt.MouseInfo
import java.awt.Point
import java.awt.Rectangle
import java.awt.event.*
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer

class JupyterToolbarManager(
  private val editor: EditorImpl,
  private val panel: JPanel,
  private val actionGroupId: String
): Disposable {  // See PY-66455
  private var toolbar: JupyterToolbar? = null
  private var hideToolbarTimer = Timer(TOOLBAR_HIDE_DELAY) { conditionallyHideToolBar() }

  private var panelMouseListener: MouseAdapter? = null
  private var panelComponentListener: ComponentAdapter? = null
  private var editorKeyListener: KeyAdapter? = null
  private var editorComponentListener: ComponentAdapter? = null

  init {
    initPanelMouseListeners()
    addPanelComponentListener()
    addEditorKeyListener()
    addEditorComponentListener()
  }

  private fun initPanelMouseListeners() {
    panelMouseListener = object : MouseAdapter() {
      override fun mouseEntered(e: MouseEvent) {
        hideToolbarTimer.stop()
        showToolbar()
      }

      override fun mouseExited(e: MouseEvent) = hideToolbarTimer.restart()
    }

    panel.addMouseListener(panelMouseListener)
  }

  private fun addPanelComponentListener() {
    panelComponentListener = object : ComponentAdapter() {
      override fun componentResized(e: ComponentEvent?) {
        super.componentResized(e)
        toolbar?.let {
          it.bounds  = calculateToolbarBounds(editor, panel, it)
          panel.revalidate()
          panel.repaint()
        }
      }

      override fun componentMoved(e: ComponentEvent?) {
        super.componentMoved(e)
        toolbar?.let {
          it.bounds  = calculateToolbarBounds(editor, panel, it)
          panel.revalidate()
          panel.repaint()
        }
      }
    }
    panel.addComponentListener(panelComponentListener)
  }

  private fun addEditorKeyListener() {
    editorKeyListener = object : KeyAdapter() {
      override fun keyTyped(e: KeyEvent) = hideToolBar()
      override fun keyPressed(e: KeyEvent) = hideToolBar()
    }

    editor.contentComponent.addKeyListener(editorKeyListener)
  }

  private fun addEditorComponentListener() {
    editorComponentListener = object : ComponentAdapter() {
      override fun componentResized(e: ComponentEvent?) = hideToolBar()
    }
    editor.contentComponent.addComponentListener(editorComponentListener)
  }

  /**
   * Checks if the mouse is over the toolbar before hiding it.
   * This is necessary because mouseExited events on the panel can trigger even when the mouse
   * is over the toolbar due to its position in the Z-stack.
   * If the mouse is indeed over the toolbar, hiding is deferred to prevent an unintended disappearance,
   * acting as an additional safeguard for better user experience.
   */
  private fun conditionallyHideToolBar() {
    toolbar?.let { tb ->
      val mousePos = MouseInfo.getPointerInfo().location
      SwingUtilities.convertPointFromScreen(mousePos, editor.contentComponent)

      when (tb.bounds.contains(mousePos)) {
        true -> hideToolbarTimer.restart()
        else -> hideToolBar()
      }
    }
  }

  fun hideToolBar() {
    toolbar?.let {
      editor.contentComponent.remove(it)
      editor.contentComponent.revalidate()
      editor.contentComponent.repaint()
      toolbar = null
      JupyterToolbarVisibilityManager.notifyToolbarHidden(this)
    }
  }

  private fun showToolbar() {
    if (toolbar == null) {
      val actionGroup = createActionGroup() ?: return
      toolbar = JupyterToolbar(actionGroup, editor.contentComponent)
    }
    JupyterToolbarVisibilityManager.requestToolbarDisplay(this)
    editor.contentComponent.add(toolbar, 0)
    positionToolbar()
  }

  private fun positionToolbar() {
    toolbar?.let { tb ->
      tb.bounds = calculateToolbarBounds(editor, panel, tb)
    }
  }

  private fun createActionGroup(): ActionGroup? = CustomActionsSchema.getInstance().getCorrectedAction(actionGroupId) as? ActionGroup

  companion object {
    private const val TOOLBAR_HIDE_DELAY = 800
    private val DELIMITER_SIZE = DefaultNotebookEditorAppearanceSizes.cellBorderHeight / 2

    fun calculateToolbarBounds(
      editor: EditorImpl,
      panel: JPanel,
      toolbar: JupyterToolbar,
    ) : Rectangle {
      val panelHeight = panel.height
      val panelWidth = panel.width

      val toolbarHeight = toolbar.preferredSize.height
      val toolbarWidth = toolbar.preferredSize.width

      val xOffset = (panelWidth - toolbarWidth) / 2
      val yOffset = (panelHeight - (1.5 * DELIMITER_SIZE) - (toolbarHeight / 2)).toInt()

      val editorComponent = editor.contentComponent
      val panelLocationInEditor = SwingUtilities.convertPoint(panel, Point(0, 0), editorComponent)

      val xCoordinate = panelLocationInEditor.x + xOffset
      val yCoordinate = panelLocationInEditor.y + yOffset
      return Rectangle(xCoordinate, yCoordinate, toolbarWidth, toolbarHeight)
    }
  }

  @RequiresEdt
  override fun dispose() {
    hideToolbarTimer.stop()
    panel.removeMouseListener(panelMouseListener)
    panel.removeComponentListener(panelComponentListener)
    editor.contentComponent.removeKeyListener(editorKeyListener)
    editor.contentComponent.removeComponentListener(editorComponentListener)

    toolbar?.let { tb ->
      editor.contentComponent.remove(tb)
      toolbar = null
    }
  }
}
