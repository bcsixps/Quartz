package com.panshuai.redis;

	public enum RedisCacheTime {

		FIVE_SECOND_STORE("fiveSecondStore", 5),
		THIRTY_SECOND_STORE("thirtySecondStore", 30),
		ONE_MINUTE_STORE("oneMinuteStore", 60),
		FIVE_MINUTE_STORE("fiveMinuteStore", 300),
		TEN_MINUTE_STORE("tenMinuteStore", 600),
		THIRTY_MINUTE_STORE("thirtyMinuteStore",1800),
		ONE_HOUR_STORE("oneHourStore", 3600),
		TWO_HOUR_STORE("twoHourStore", 7200),
		THREE_HOUR_STORE("threeHourStore",10800),
		SIX_HOUR_STORE("sixHourStore", 21600),
		TWELVE_HOUR_STORE("twelveHourStore",43200),
		ONE_DAY_STORE("oneDayStore", 86400),
		TWO_DAY_STORE("twoDayStore", 172800),
		THREE_DAY_STORE("threeDayStore", 259200),
		ONE_MONTH_STORE("oneMonthStore", 2592000),
		TWO_MONTH_STORE("twoMonthStore",5184000),
		THREE_MONTH_STORE("threeMonthStore",7776000),
		SIX_MONTH_STORE("sixMonthStore", 15552000),
		PERMANENT_STORE("permanentStore", 0);
		
		private String name;
		private int seconds;
		
		private RedisCacheTime(String name , int seconds) {
			this.name = name;
			this.seconds = seconds;
		}

		public int getSeconds() {
			return seconds;
		}

		public String getName() {
			return name;
		}
}
