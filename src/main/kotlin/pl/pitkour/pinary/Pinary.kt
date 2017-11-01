package pl.pitkour.pinary

import pl.pitkour.pinary.terminal.Terminal
import pl.pitkour.pinary.window.Window
import javax.swing.SwingUtilities

class Pinary(private val arguments : Array<String>)
{
	init
	{
		val type = getType()
		handleType(type)
	}

	private fun getType() : RunType
	{
		return if(this.arguments.isEmpty())
		{
			RunType.WINDOW
		}
		else
		{
			RunType.get(this.arguments[0]) ?: throw IllegalArgumentException("invalid run type name")
		}
	}

	private fun handleType(type : RunType)
	{
		when(type)
		{
			RunType.WINDOW -> runAsWindow()
			RunType.TERMINAL -> runAsTerminal()
		}
	}

	private fun runAsWindow() = SwingUtilities.invokeLater { Window() }

	private fun runAsTerminal() = Terminal()
}