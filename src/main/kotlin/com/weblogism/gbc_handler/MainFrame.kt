package com.weblogism.gbc_handler

import java.awt.*
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class MainFrame: JFrame() {
    val displayPanel = JPanel(GridBagLayout())
    val valueDisplayPanel = JPanel(GridBagLayout())
    val gridBagConstraints = GridBagConstraints()

    var weightX:DoubleArray = DoubleArray(3)
    var weightY:DoubleArray = DoubleArray(3)

    init {
        val controlPanel = JPanel(GridBagLayout())
        val gbc = GridBagConstraints()
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = Insets(4,4,4,4)

        controlPanel.add(JLabel("ipadX"), gbc)
        controlPanel.border = BorderFactory.createTitledBorder("Controls")

        gbc.gridx = 1
        gbc.weightx = 1.0
        val ipadXValues = JComboBox(arrayOf(0, 5, 10, 15, 20, 40, 50))
        controlPanel.add(ipadXValues, gbc)
        ipadXValues.addActionListener {
            gridBagConstraints.ipadx = ipadXValues.getItemAt(ipadXValues.selectedIndex)
            rebuildDisplayPanel()
        }

        gbc.gridx = 0
        gbc.gridy = 1
        gbc.weightx = 0.0
        controlPanel.add(JLabel("ipadY"), gbc)

        gbc.gridx = 1
        gbc.weightx = 1.0
        val ipadYValues = JComboBox(arrayOf(0, 5, 10, 15, 20, 40, 50))
        controlPanel.add(ipadYValues, gbc)
        ipadYValues.addActionListener {
            gridBagConstraints.ipady = ipadYValues.getItemAt(ipadYValues.selectedIndex)
            rebuildDisplayPanel()
        }

        gbc.gridx = 0
        gbc.gridy = 2
        gbc.weightx = 0.0
        controlPanel.add(JLabel("fill"), gbc)

        gbc.gridx = 1
        gbc.weightx = 1.0
        val fillValues = JComboBox(arrayOf("NONE", "VERTICAL", "HORIZONTAL", "BOTH"))
        controlPanel.add(fillValues, gbc)
        fillValues.addActionListener {
            gridBagConstraints.fill = when (fillValues.getItemAt(fillValues.selectedIndex)) {
                "VERTICAL" -> GridBagConstraints.VERTICAL
                "HORIZONTAL" -> GridBagConstraints.HORIZONTAL
                "BOTH" -> GridBagConstraints.BOTH
                else -> GridBagConstraints.NONE
            }
            rebuildDisplayPanel()
        }

        gbc.gridx = 0
        gbc.gridy = 3
        gbc.weightx = 0.0
        controlPanel.add(JLabel("insets"), gbc)

        gbc.gridx = 1
        gbc.weightx = 1.0
        val insetsValues = JComboBox(arrayOf(0, 5, 10, 15, 20, 40, 50))
        controlPanel.add(insetsValues, gbc)
        insetsValues.addActionListener {
            val insets = insetsValues.getItemAt(insetsValues.selectedIndex)
            gridBagConstraints.insets = Insets (insets, insets, insets, insets)
            rebuildDisplayPanel()
        }

        gbc.gridx = 0
        gbc.gridy = 4
        gbc.weightx = 0.0
        controlPanel.add(JLabel("weightX"), gbc)

        gbc.gridx = 1
        gbc.weightx = 1.0
        val weightXValues = JTextField()
        controlPanel.add(weightXValues, gbc)
        weightXValues.addActionListener {
            weightX = parseWeights(weightXValues.text)
            rebuildDisplayPanel()
        }

        gbc.gridx = 0
        gbc.gridy = 5
        gbc.weightx = 0.0
        controlPanel.add(JLabel("weightY"), gbc)

        gbc.gridx = 1
        gbc.weightx = 1.0
        val weightYValues = JTextField()
        controlPanel.add(weightYValues, gbc)
        weightYValues.addActionListener {
            weightY = parseWeights(weightYValues.text)
            rebuildDisplayPanel()
        }

        gbc.gridx = 0
        gbc.gridy = 6
        gbc.weightx = 0.0
        controlPanel.add(JLabel("anchor"), gbc)

        gbc.gridx = 1
        gbc.weightx = 1.0
        val anchorValues = JComboBox(arrayOf("FIRST_LINE_START", "PAGE_START", "FIRST_LINE_END", "LINE_START", "CENTER", "LINE_END", "LAST_LINE_START", "PAGE_END", "LAST_LINE_END"))
        controlPanel.add(anchorValues, gbc)
        anchorValues.addActionListener {
            gridBagConstraints.anchor = when(anchorValues.getItemAt(anchorValues.selectedIndex)) {
                "FIRST_LINE_START" -> GridBagConstraints.FIRST_LINE_START
                "PAGE_START" -> GridBagConstraints.PAGE_START
                "FIRST_LINE_END" -> GridBagConstraints.FIRST_LINE_END
                "LINE_START" -> GridBagConstraints.LINE_START
                "LINE_END" -> GridBagConstraints.LINE_END
                "LAST_LINE_START" -> GridBagConstraints.LAST_LINE_START
                "PAGE_END" -> GridBagConstraints.PAGE_END
                "LAST_LINE_END" -> GridBagConstraints.LAST_LINE_END
                else -> GridBagConstraints.CENTER
            }
            rebuildDisplayPanel()
        }

        size = Dimension(800, 600)
        contentPane.layout = BorderLayout()
        contentPane.add(displayPanel, BorderLayout.WEST)
        contentPane.add(controlPanel, BorderLayout.CENTER)

        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true

    }

    private fun parseWeights(value: String):DoubleArray {
        val v = value.split(",")
        val weights = v.map { s -> s.toDouble() }.toDoubleArray()
        return weights + DoubleArray(3 - weights.size) {
            weights.last()
        }
    }

    fun rebuildDisplayPanel() {
        displayPanel.removeAll()

        val button1 = JButton("Button 1")
        val button2 = JButton("Button 2")
        val button3 = JButton("Button 3")

        val button4 = JButton("Button 1")
        val button5 = JButton("Button 2")
        val button6 = JButton("Button 3")

        val weightx = weightX
        val weighty = weightY

        gridBagConstraints.gridx = 0
        gridBagConstraints.gridy = 0
        gridBagConstraints.weightx = weightX[0]
        gridBagConstraints.weighty = weightY[0]
        displayPanel.add(button1, gridBagConstraints)

        gridBagConstraints.gridx = 1
        gridBagConstraints.weightx = weightX[1]
        gridBagConstraints.weighty = weightY[1]
        displayPanel.add(button2, gridBagConstraints)

        gridBagConstraints.gridx = 2
        gridBagConstraints.weightx = weightX[2]
        gridBagConstraints.weighty = weightY[2]
        displayPanel.add(button3, gridBagConstraints)

        gridBagConstraints.gridx = 0
        gridBagConstraints.gridy = 1
        gridBagConstraints.weightx = weightX[0]
        gridBagConstraints.weighty = weightY[0]
        displayPanel.add(button4, gridBagConstraints)

        gridBagConstraints.gridx = 1
        gridBagConstraints.weightx = weightX[1]
        gridBagConstraints.weighty = weightY[1]
        displayPanel.add(button5, gridBagConstraints)

        gridBagConstraints.gridx = 2
        gridBagConstraints.weightx = weightX[2]
        gridBagConstraints.weighty = weightY[2]
        displayPanel.add(button6, gridBagConstraints)

        displayPanel.border = BorderFactory.createTitledBorder("Display")

        contentPane.remove(displayPanel)
        contentPane.add(displayPanel, BorderLayout.WEST)

        val valueGbc = GridBagConstraints()
        valueGbc.fill = GridBagConstraints.HORIZONTAL
        valueGbc.weightx = 0.5
        valueGbc.insets = Insets(4,4,4,4)
        valueDisplayPanel.removeAll()

        valueDisplayPanel.add(JLabel("ipadx"), valueGbc)
        valueGbc.gridx = 1
        valueGbc.weightx = 2.0
        valueDisplayPanel.add(JLabel(gridBagConstraints.ipadx.toString()), valueGbc)

        valueGbc.gridx = 0
        valueGbc.gridy = 1
        valueGbc.weightx = 0.5
        valueDisplayPanel.add(JLabel("ipady"), valueGbc)
        valueGbc.gridx = 1
        valueDisplayPanel.add(JLabel(gridBagConstraints.ipady.toString()), valueGbc)

        valueGbc.gridx = 0
        valueGbc.gridy = 2
        valueDisplayPanel.add(JLabel("fill"), valueGbc)
        valueGbc.gridx = 1
        valueDisplayPanel.add(JLabel(gridBagConstraints.fill.toString()), valueGbc)

        valueGbc.gridx = 0
        valueGbc.gridy = 3
        valueDisplayPanel.add(JLabel("insets"), valueGbc)
        valueGbc.gridx = 1
        valueDisplayPanel.add(JLabel(gridBagConstraints.insets.top.toString()), valueGbc)

        valueGbc.gridx = 0
        valueGbc.gridy = 4
        valueDisplayPanel.add(JLabel("weightx"), valueGbc)
        valueGbc.gridx = 1
        valueDisplayPanel.add(JLabel(weightx.asList().toString()), valueGbc)

        valueGbc.gridx = 0
        valueGbc.gridy = 5
        valueDisplayPanel.add(JLabel("weighty"), valueGbc)
        valueGbc.gridx = 1
        valueDisplayPanel.add(JLabel(weighty.asList().toString()), valueGbc)

        valueGbc.gridx = 0
        valueGbc.gridy = 6
        valueDisplayPanel.add(JLabel("anchor"), valueGbc)
        valueGbc.gridx = 1
        valueDisplayPanel.add(JLabel(gridBagConstraints.anchor.toString()), valueGbc)
        valueDisplayPanel.border = BorderFactory.createTitledBorder("Values")

        contentPane.add(valueDisplayPanel, BorderLayout.SOUTH)

        revalidate()
        repaint()

    }
}

fun main() {
    MainFrame().rebuildDisplayPanel()
}
