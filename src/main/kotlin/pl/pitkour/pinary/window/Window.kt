package pl.pitkour.pinary.window

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.WindowConstants

class Window : JFrame()
{
	private val panel : ContentPanel

	init
	{
		setSystemLookAndFeel()
		isResizable = false
		isLocationByPlatform = true
		iconImage = loadIcon("icon.png")
		title = "Pinary"
		defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
		this.panel = ContentPanel()
		add(this.panel)
		pack()
		isVisible = true
	}

	private fun setSystemLookAndFeel()
	{
		val systemLookAndFeelName = UIManager.getSystemLookAndFeelClassName()
		UIManager.setLookAndFeel(systemLookAndFeelName)
	}

	private fun loadIcon(iconName : String) : BufferedImage
	{
		val classLoader = javaClass.classLoader
		val url = classLoader.getResource(iconName)
		return ImageIO.read(url)
	}
}