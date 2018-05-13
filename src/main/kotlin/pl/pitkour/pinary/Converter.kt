package pl.pitkour.pinary

object Converter
{
	const val MINIMUM_RADIX = 2
	const val MAXIMUM_RADIX = 36

	fun convertFromDecimal(decimalNumber : Long, radix : Int) : CharArray
	{
		val resultLength = getNumberLength(decimalNumber, radix)
		val result = CharArray(resultLength)
		var number = decimalNumber
		var index = resultLength - 1
		while(number != 0.toLong())
		{
			result[index] = forDigit(number % radix, radix)
			number /= radix
			index--
		}
		return result
	}

	private fun getNumberLength(number : Long, radix : Int) : Int
	{
		if(number == 0.toLong())
		{
			return 1
		}
		if(radix < MINIMUM_RADIX)
		{
			throw IllegalArgumentException("minimum radix is " + MINIMUM_RADIX)
		}
		return (Math.log10(Math.abs(number).toDouble()) / Math.log10(radix.toDouble()) + 1).toInt()
	}

	fun convertToDecimal(number : CharArray, radix : Int) : Long
	{
		var decimalNumber : Long = 0
		val startIndex = number.size - 1
		for(index in startIndex downTo 0)
		{
			val digit = Character.digit(number[index], radix)
			decimalNumber += digit * Math.pow(radix.toDouble(), (startIndex - index).toDouble()).toLong()
		}
		return decimalNumber
	}

	fun forDigit(digit : Long, radix : Int) : Char
	{
		if(digit >= radix || digit < 0)
		{
			return '\u0000'
		}
		if(radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
		{
			return '\u0000'
		}
		return if(digit < 10)
		{
			('0'.toInt() + digit).toChar()
		}
		else ('a'.toInt() - 10 + digit).toChar()
	}
}
