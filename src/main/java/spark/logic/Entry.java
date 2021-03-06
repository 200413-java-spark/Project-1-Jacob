package spark.logic;

import java.io.Serializable;

public class Entry implements Serializable {
    // course_id,course_title,url,is_paid,price,num_subscribers,num_reviews,num_lectures,level,content_duration,published_timestamp,subject
    // 1,3,4,5,7,8,9,11
    // 1070968,Ultimate Investment Banking Course,https://www.udemy.com/ultimate-investment-banking-course/,True,200,2147,23,51,All Levels,1.5 hours,2017-01-18T20:58:58Z,Business Finance
    String course_title;
    boolean is_paid;
    int price;
    int num_subscribers;
    int num_lectures;
    String level;
    String content_duration;
    String subject;

    Entry(String[] s) {
        this.course_title = s[0];
        this.is_paid = Boolean.parseBoolean(s[1]);
        try {
            this.price = Integer.parseInt(s[2]);
        } catch(NumberFormatException e){
            this.price = 0;
        }
        this.num_subscribers = Integer.parseInt(s[3]);
        this.num_lectures = Integer.parseInt(s[4]);
        this.level = s[5];
        this.content_duration = s[6];
        this.subject = s[7];
    }
    
    public String getTitle() { return this.course_title; }
    public Boolean getPaid() { return this.is_paid; }
    public Integer getPrice() { return this.price; }
    public Integer getSubscribers() { return this.num_subscribers; }
    public Integer getLectures() { return this.num_lectures; }
    public String getLevel() { return this.level; }
    public String getDuration() { return this.content_duration; }
    public String getSubject() { return this.subject; }

    public String toString() {
        String output = this.course_title + this.is_paid + this.price + this.num_subscribers + this.num_lectures 
            + this.level + this.content_duration + this.subject;
        return output;
    }
}