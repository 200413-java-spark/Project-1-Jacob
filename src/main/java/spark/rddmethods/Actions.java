package spark.rddmethods;

import spark.logic.*;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

import scala.Tuple2;

public class Actions {

    //num_subscribers,num_reviews,num_lectures,level,content_duration
    public static JavaPairRDD<Boolean, Integer> is_paid(JavaRDD<Entry> entries) {
        JavaPairRDD<Boolean, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getPaid(), 1));
		JavaPairRDD<Boolean, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
        return count;
    }

    public static JavaPairRDD<Integer, Integer> prices(JavaRDD<Entry> entries) {
        JavaPairRDD<Integer, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getPrice(), 1));
		JavaPairRDD<Integer, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
        return count;
    }

    public static JavaPairRDD<Integer, Integer> subscribe(JavaRDD<Entry> entries) {
        JavaPairRDD<Integer, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getSubscribers(), 1));
		JavaPairRDD<Integer, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
        return count;
    }

    public static JavaPairRDD<Integer, Integer> lectures(JavaRDD<Entry> entries) {
        JavaPairRDD<Integer, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getLectures(), 1));
		JavaPairRDD<Integer, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
        return count;
    }

    public static JavaPairRDD<String, Integer> levels(JavaRDD<Entry> entries) {
        JavaPairRDD<String, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getLevel(), 1));
		JavaPairRDD<String, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
        return count;
    }

    public static JavaPairRDD<String, Integer> durations(JavaRDD<Entry> entries) {
        JavaPairRDD<String, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getDuration(), 1));
		JavaPairRDD<String, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
        return count;
    }

    public static JavaPairRDD<String, Integer> subjects(JavaRDD<Entry> entries) {
        JavaPairRDD<String, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getSubject(), 1));
		JavaPairRDD<String, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
        return count;
    }

    public static JavaPairRDD<String, Integer> priceForSubject(JavaRDD<Entry> entries) {
        //JavaPairRDD<String, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getSubject(), f.getPrice()));
        //JavaPairRDD<String, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
        JavaPairRDD<String, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getSubject(), f.getPrice()));
        JavaPairRDD<String, Tuple2<Integer, Integer>> count = test.mapValues((f) -> new Tuple2<Integer, Integer>(f, 1));
        JavaPairRDD<String, Tuple2<Integer, Integer>> reducedCount = count.reduceByKey((tuple1,tuple2) ->  new Tuple2<Integer, Integer>(tuple1._1 + tuple2._1, tuple1._2 + tuple2._2));
        JavaPairRDD<String, Integer> averagePair = reducedCount.mapValues(f -> f._1 / f._2);
        

        return averagePair;
    }

    public static JavaPairRDD<String, Integer> subscribers(JavaRDD<Entry> entries) {
        JavaPairRDD<String, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getSubject(), f.getSubscribers()));
        JavaPairRDD<String, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
        return count;
    }

}