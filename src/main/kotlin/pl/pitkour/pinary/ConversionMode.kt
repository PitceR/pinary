package pl.pitkour.pinary

enum class ConversionMode(private val displayName : String)
{
	FROM_DECIMAL("From decimal"),
	TO_DECIMAL("To decimal");

	override fun toString() : String
	{
		return this.displayName
	}
}