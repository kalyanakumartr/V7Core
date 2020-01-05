package org.hbs.core.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public interface IConstProperty extends Serializable
{
	public String	HASH			= "#";
	public String	HYPHEN			= "-";
	public String	SLASH			= "/";
	public String	FILE			= "file:";
	public String	SLASH_STARS		= SLASH + "**";
	public String	COMMA_SPACE		= ", ";
	public String	FROM			= " From ";
	public String	SELECT_DISTINCT	= " Select Distinct ";
	public String	SPACE			= " ";
	public String	EQUALTO			= " = ";
	public String	DOT				= ".";
	public String	COLON			= ":";
	public String	WHERE_1_1		= " Where 1=1 ";
	public String	SELECT			= " Select ";
	public String	UPDATE			= " Update ";
	public String	DELETE			= " Delete ";
	public String	SET				= " Set ";

	public enum EPage implements EnumInterface
	{
		Failure, Success, Invalid, Error404, Error500
	}

	public enum EDate implements EnumInterface
	{
		DD_MMMM_YYYY_HH_MM_AM_PM("dd MMMM yyyy-hh:mm a"), //
		DD_MMM_YYYY("dd-MMM-yyyy"), //
		DD_MMM_YYYY_HH_MM_AM_PM("dd-MMM-yyyy HH:mm a"), //
		DD_MMM_YYYY_HH_MM_SS_AM_PM("dd-MMM-yyyy hh:mm:ssa"), //
		DD_MMM_YYYY_SPACE("dd MMM yyyy"), //
		DD_MM_YYYY("dd/MM/yyyy"), //
		DD_MM_YYYY_("dd-MM-YYYY"), //
		DD_MM_YYYY_HH_MM("dd-MM-yyyyHH:mm"), //
		MM_DD_YYYY("MM-dd-yyyy"), //
		MM_DD_YYYY_HH_MM("MM-dd-yyyy HH:mm"), //
		MM_DD_YYYY_HH_MM_SS_AM_PM("MM-dd-yyyy hh:mm:ssa"), //
		YYYYMMDD("yyyy MM dd"), //
		YYYY_MMM_DD("yyyy-MMM-dd"), //
		YYYY_MM_DD("yyyy-MM-dd"), //
		YYYY_MM_DD_HH_MM("yyyy-MM-dd hh:mm"), //
		YYYY_MM_DD_HH_MM_24("yyyy-MM-dd HH:mm"), //
		YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd hh:mm:ss"), //
		YYYY_MM_DD_HH_MM_SS_24("yyyy-MM-dd HH:mm:ss"), //
		YYYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd hh:mm:ss.SSS"), //
		YYYY_MM_DD_HH_MM_SS_SSS_24("yyyy-MM-dd HH:mm:ss.SSS");

		String format;

		EDate(String format)
		{
			this.format = format;
		}

		public String get()
		{
			return format;
		}

		public Timestamp byTimeZone(Date date, String timeZone)
		{
			DateFormat _DF = new SimpleDateFormat(format);
			_DF.setTimeZone(TimeZone.getTimeZone(timeZone.toUpperCase()));
			return Timestamp.valueOf(_DF != null ? _DF.format(date) : "");
		}

		public String byTimeZone(String sourceTZ, String destinationTZ, Timestamp timeStamp, EDate eDate)
		{
			return byTimeZone(sourceTZ, destinationTZ, timeStamp.toString(), eDate);
		}

		public String byTimeZone(String sourceTZ, String destinationTZ, String date, EDate eDate)
		{
			if (date.indexOf(DOT) > 0)
				date = date.substring(0, date.indexOf(DOT));

			LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(EDate.YYYY_MM_DD_HH_MM_SS_24.get()));

			ZonedDateTime serverZonedDateTime = ldt.atZone(ZoneId.of(sourceTZ));

			ZonedDateTime dateTime = serverZonedDateTime.withZoneSameInstant(ZoneId.of(destinationTZ));

			return DateTimeFormatter.ofPattern(format).format(dateTime);
		}

		public String formatted(Date date)
		{
			return new SimpleDateFormat(format).format(date);
		}

		public LocalDate getDateFromMilliSeconds(long milliSeconds)
		{
			return getDateFromMilliSeconds(milliSeconds, ZoneId.systemDefault());
		}

		public LocalDate getDateFromMilliSeconds(long milliSeconds, ZoneId zone)
		{
			return Instant.ofEpochMilli(milliSeconds).atZone(zone).toLocalDate();
		}

		public Date formatted(String date)
		{
			try
			{
				return new SimpleDateFormat(format).parse(date);
			}
			catch (ParseException e)
			{
				return null;
			}
		}
	}

	public enum EWrap implements EnumInterface
	{
		Brace("()"), Percent("%"), Quote("'"), QuotePercent(""), Hyphen("-");

		private String eWrap;

		private EWrap(String eWrap)
		{
			this.eWrap = eWrap;
		}

		public String enclose(Object data)
		{
			if (data != null)
			{
				String encData = String.valueOf(data);
				if (eWrap.equals(""))
				{
					return Quote.eWrap + Percent.eWrap + encData.trim() + Percent.eWrap + Quote.eWrap;
				}
				else if (eWrap.equals("()"))
				{
					return "(" + encData.trim() + ")";
				}
				else if (eWrap.equals("-"))
				{
					return encData.trim() + Hyphen.eWrap;
				}
				else
				{
					return eWrap + encData.trim() + eWrap;
				}
			}
			else if (eWrap.equals(""))
			{
				return Quote.eWrap + Percent.eWrap + Percent.eWrap + Quote.eWrap;
			}

			return "";
		}

		public String enclose(Object[] dataArr)
		{
			String dataQt = "";
			if (CommonValidator.isNotNullNotEmpty(dataArr))
			{
				if (dataArr[0] instanceof String || dataArr[0] instanceof Integer || dataArr[0] instanceof Long || dataArr[0] instanceof Float || dataArr[0] instanceof Double
						|| dataArr[0] instanceof Boolean)
				{
					for (Object datum : dataArr)
					{
						dataQt += enclose(datum) + COMMA_SPACE;
					}
					if (dataQt.indexOf(COMMA_SPACE) > 0)
					{
						dataQt = dataQt.substring(0, dataQt.lastIndexOf(COMMA_SPACE));
					}
				}
			}
			return dataQt;
		}

		public String append(Object[] dataArr)
		{
			String dataQt = "";
			if (CommonValidator.isNotNullNotEmpty(dataArr))
			{
				if (dataArr[0] instanceof String || dataArr[0] instanceof Integer || dataArr[0] instanceof Long || dataArr[0] instanceof Float || dataArr[0] instanceof Double
						|| dataArr[0] instanceof Boolean)
				{
					for (Object datum : dataArr)
					{
						dataQt += enclose(datum);
					}
					dataQt = dataQt.substring(0, dataQt.trim().length() - 1);
				}
			}
			return dataQt;
		}
	}
}
