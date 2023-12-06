package programmers.lv2.no.PCCP3;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
    	Time startTime = new Time(h1, m1, s1);
        Time endTime = new Time(h2, m2, s2);
        Alarm alarm = new Alarm(startTime, endTime);
        return alarm.callCount();
    }
}

interface Clock {
	Clock addSecond(final int second);
}

class Second implements Clock {
    static final int MAX = 60;
    static final double DEGREE_PER_SECOND = 180 / MAX;
    final int second;
    final double degree;
    
    Second(final int second) {
    	this.second = second;
        this.degree = DEGREE_PER_SECOND * second;
    }
    
    Second(final double degree) {
    	this.degree = degree;
    }

	@Override
	public Clock addSecond(int second) {
		final double newDegree = this.degree + DEGREE_PER_SECOND * second;
		return new Second(newDegree);
	}
}


class Minute implements Clock {
    static final int MAX = 60;
    static final int DEGREE_PER_MINUTE = 180 / MAX;
    static final double DEGREE_PER_SECOND = DEGREE_PER_MINUTE / 60;
    final int minute;
    final double degree;
    
    Minute(final int minute) {
    	this.minute = minute;
        this.degree = DEGREE_PER_MINUTE * minute;
    }
    
    private Minute(final double degree) {
    	this.degree = degree;
    }
    
	@Override
	public Clock addSecond(int second) {
		final double newDegree = this.degree + DEGREE_PER_SECOND * second;
		return new Minute(newDegree);
	}
}

class Hour implements Clock {
    static final int MAX = 12;
    static final int DEGREE_PER_HOUR = 180 / MAX;
    static final double DEGREE_PER_SECOND = DEGREE_PER_HOUR / 3600;
    final int hour;
    final double degree;
    
    Hour(final int hour) {
    	this.hour = hour;
        this.degree = DEGREE_PER_HOUR * hour;
    }
    
    
    Hour(final double degree) {
    	this.degree = degree;
    }
    
    getSecondDiff(Hour otherHour) {
    	return this
    }
    
	@Override
	public Clock addSecond(int second) {
		final double newDegree = this.degree + DEGREE_PER_SECOND * second;
		return new Hour(newDegree);
	}
}


class Time {
    final Hour hour;
    final Minute minute;
    final Second second;
    
    Time(final int hour, final int minute, final int second) {
    	this(new Hour(hour), new Minute(minute), new Second(second));
    }
    
    Time(final Hour hour, final Minute minute, final Second second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
}

class Alarm {
    final Time startTime, endTime;
    
    Alarm(final Time startTime, final Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    int callCount() {
        final int meetCountByHourAndSecond = getMeetCountByHourAndSecond();
        final int meetCountByMinuteAndSecond = getMeetCountByMinuteAndSecond();
        final int meetCountByHourAndMinuteAndSecond = getMeetCountByHourAndMinuteAndSecond();
        
        final int callCount = meetCountByHourAndSecond + meetCountByMinuteAndSecond - meetCountByHourAndMinuteAndSecond;
        return callCount;
    }
    
    private int getMeetCountByHourAndSecond() {
    	
        return 0;
    }
    
    private int getMeetCountByMinuteAndSecond() {
        return 0;
    }
    
    private int getMeetCountByHourAndMinuteAndSecond() {
        return 0;
    }
}

