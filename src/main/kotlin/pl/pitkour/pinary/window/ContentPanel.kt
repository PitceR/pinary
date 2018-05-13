package pl.pitkour.pinary.window

import pl.pitkour.pinary.ConversionMode
import pl.pitkour.pinary.Converter
import java.awt.Font
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JPanel
import javax.swing.JSpinner
import javax.swing.JTextField
import javax.swing.SpinnerNumberModel

class ContentPanel : JPanel()
{
	private val modeBox : ModeBox
	private val radixSpinner : RadixSpinner
	private val inputField : InputField
	private val outputField : OutputField
	private val calculateButton : CalculateButton

	init
	{
		this.modeBox = ModeBox()
		this.radixSpinner = RadixSpinner()
		this.inputField = InputField()
		this.outputField = OutputField()
		this.calculateButton = CalculateButton()
		add(this.modeBox)
		add(this.radixSpinner)
		add(this.inputField)
		add(this.outputField)
		add(this.calculateButton)
	}

	private inner class ModeBox : JComboBox<ConversionMode>()
	{
		init
		{
			ConversionMode.values().forEach { addItem(it) }
			font = Font(Font.DIALOG, Font.PLAIN, 12)
		}
	}

	private inner class RadixSpinner : JSpinner()
	{
		init
		{
			model = SpinnerNumberModel().apply {
				minimum = Converter.MINIMUM_RADIX
				maximum = Converter.MAXIMUM_RADIX
			}
			value = Converter.MINIMUM_RADIX
			font = Font(Font.DIALOG, Font.PLAIN, 12)
		}
	}

	private inner class InputField : JTextField()
	{
		init
		{
			columns = 17
			font = Font(Font.MONOSPACED, Font.PLAIN, 12)
		}
	}

	private inner class OutputField : JTextField()
	{
		init
		{
			columns = 33
			isEditable = false
			font = Font(Font.MONOSPACED, Font.PLAIN, 12)
		}
	}

	private inner class CalculateButton : JButton()
	{
		init
		{
			text = "Convert"
			addActionListener { convert() }
			font = Font(Font.DIALOG, Font.PLAIN, 12)
		}

		private fun convert()
		{
			when(this@ContentPanel.modeBox.selectedItem)
			{
				ConversionMode.FROM_DECIMAL -> fromDecimal()
				ConversionMode.TO_DECIMAL -> toDecimal()
			}
		}

		private fun fromDecimal()
		{
			val number = this@ContentPanel.inputField.text.toLong()
			val radix = this@ContentPanel.radixSpinner.value as Int
			val result = Converter.convertFromDecimal(number, radix)
			this@ContentPanel.outputField.text = result.joinToString("")
		}

		private fun toDecimal()
		{
			val number = this@ContentPanel.inputField.text.toCharArray()
			val radix = this@ContentPanel.radixSpinner.value as Int
			val result = Converter.convertToDecimal(number, radix)
			this@ContentPanel.outputField.text = result.toString()
		}
	}
}
