package pl.pitkour.pinary

enum class RunType(private vararg val aliases : String)
{
	WINDOW("window", "frame", "gui"),
	TERMINAL("terminal", "console");

	companion object
	{
		fun get(type : String) : RunType?
		{
			for(value in values())
			{
				value.aliases.filter { it.equals(type, true) }.forEach { return value }
			}
			return null
		}
	}
}