package pl.pitkour.pinary

object Converter
{
	const val MINIMUM_RADIX = 2
	const val MAXIMUM_RADIX = 36

	fun convertFromDecimal(decimalNumber : Int, radix : Int) : CharArray
	{
		val resultLength = getNumberLength(decimalNumber, radix)
		val result = CharArray(resultLength)
		var number = decimalNumber
		var index = resultLength - 1
		while(number != 0)
		{
			result[index] = Character.forDigit(number % radix, radix)
			number /= radix
			index--
		}
		return result
	}

	private fun getNumberLength(number : Int, radix : Int) : Int
	{
		if(number == 0)
		{
			return 1
		}
		if(radix < MINIMUM_RADIX)
		{
			throw IllegalArgumentException("minimum radix is " + MINIMUM_RADIX)
		}
		return (Math.log10(Math.abs(number).toDouble()) / Math.log10(radix.toDouble()) + 1).toInt()
	}

	fun convertToDecimal(number : CharArray, radix : Int) : Int
	{
		var decimalNumber = 0
		val startIndex = number.size - 1
		for(index in startIndex downTo 0)
		{
			val digit = Character.digit(number[index], radix)
			decimalNumber += digit * Math.pow(radix.toDouble(), (startIndex - index).toDouble()).toInt()
		}
		return decimalNumber
	}
}