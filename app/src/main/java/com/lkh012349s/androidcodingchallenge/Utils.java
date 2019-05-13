package com.lkh012349s.androidcodingchallenge;

import android.content.Context;

import java.util.Calendar;

class Utils {
	
	static String getTimeSpan( final Context context, final long timeCreated ) {
		
		final Calendar calendarNow = Calendar.getInstance();
		final Calendar calendarCreated = Calendar.getInstance();
		calendarCreated.setTimeInMillis( timeCreated * 1000 );
		
		final int yearNow = calendarNow.get( Calendar.YEAR );
		final int yearCreated = calendarCreated.get( Calendar.YEAR );
		final int monthNow = calendarNow.get( Calendar.MONTH );
		final int monthCreated = calendarCreated.get( Calendar.MONTH );
		final int dayNow = calendarNow.get( Calendar.DATE );
		final int dayCreated = calendarCreated.get( Calendar.DATE );
		
		int yearDiff = yearNow - yearCreated;
		int monthDiff = monthNow - monthCreated;
		int dayDiff = dayNow - dayCreated;
		
		if ( dayDiff < 0 ) {
			monthDiff--;
			dayDiff += 30;
		}
		
		if ( monthDiff < 0 ) {
			yearDiff--;
			monthDiff += 12;
		}
		
		final int quantity;
		final int resId;
		
		if ( yearDiff > 0 ) {
			quantity = yearDiff;
			resId = R.plurals.year;
		} else if ( monthDiff > 0 ) {
			quantity = monthDiff;
			resId = R.plurals.month;
		} else if ( dayDiff >= 7 ) {
			quantity = dayDiff / 7;
			resId = R.plurals.week;
		} else {
			quantity = dayDiff;
			resId = R.plurals.day;
		}
		
		return context.getResources().getQuantityString( resId, quantity, quantity );
		
	}
	
}