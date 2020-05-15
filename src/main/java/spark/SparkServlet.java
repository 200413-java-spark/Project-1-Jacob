package spark;

import spark.io.SqlDataSource;
import spark.logic.*;
import spark.rddmethods.*;
import spark.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;
import scala.annotation.meta.param;

@WebServlet("/spark")
public class SparkServlet extends HttpServlet {
	JavaSparkContext sparkContext;
	ArrayList<String> names;
	JavaRDD<Entry> entries;
	// List<String> names;

	@Override
	public void init() throws ServletException {
		SparkConf conf = new SparkConf().setAppName("Project1").setMaster("local");
		sparkContext = new JavaSparkContext(conf);
		JavaRDD<String> data = sparkContext.textFile("C:/Users/bigma/Documents/RevatureWork/Project-1-JacobMacklin/src/main/resources/resource.csv");
		entries = data.map((f) -> EntryParser.parse(f));


		/*
		names = new ArrayList<>();
		names.add("Mehrab");
		names.add("Sutter");
		names.add("Sutter");
		names.add("Sutter");
		names.add("Sutter");
		names.add("Sutter");
		names.add("Sutter");
		names.add("Sutter");
		names.add("Jeremy");
		names.add("Jeremy");
		names.add("Jeremy");
		names.add("Daniel");
		names.add("Daniel");
		names.add("Daniel");
		names.add("Daniel");
		names.add("Daniel");
		*/
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String param = req.getParameter("request");
		if(param != null) {
			if(param.equals("is_paid")) {
				JavaPairRDD<Boolean, Integer> count = Actions.is_paid(entries);
				resp.getWriter().print(count.collect());
			}
			else if(param.equals("prices")) {
				JavaPairRDD<Integer, Integer> count = Actions.prices(entries);
				resp.getWriter().print(count.collect());
			}
			else if(param.equals("subscribers")) {
				JavaPairRDD<Integer, Integer> count = Actions.subscribers(entries);
				resp.getWriter().print(count.collect());
			}
			else if(param.equals("lectures")) {
				JavaPairRDD<Integer, Integer> count = Actions.lectures(entries);
				resp.getWriter().print(count.collect());
			}
			else if(param.equals("levels")) {
				JavaPairRDD<String, Integer> count = Actions.levels(entries);
				resp.getWriter().print(count.collect());
			}
			else if(param.equals("durations")) {
				JavaPairRDD<String, Integer> count = Actions.durations(entries);
				resp.getWriter().print(count.collect());
			}
			else if(param.equals("subjects")) {
				JavaPairRDD<String, Integer> count = Actions.subjects(entries);
				resp.getWriter().print(count.collect());
			}
			else if(param.equals("write")) {
				SqlDataset.write();
			}
			
		}

		/*
		String name = req.getParameter("name");
		String lang = req.getParameter("lang");
		if (name != null && lang != null) {
			names.add(name);
			if (lang.equals("en")) {
				resp.getWriter().println(new HelloGreeter().greet(name));
			} else if (lang.equals("es")) {
				Greetable holaGreeter = new Greetable() {

					@Override
					public String greet(String name) {
						return "Hola, " + name;
					}
				};
				resp.getWriter().println(holaGreeter.greet(name));
			} else if (lang.equals("de")) {
				Greetable gutentagGreetable = (n) -> {
					return "Guten tag, " + n;
				};
				resp.getWriter().println(gutentagGreetable.greet(name));
				Runnable runner = () -> {
					System.out.println("Running thread");
				};
				new Thread(runner).start();
			}
		}

		for (String n : names) {
			resp.getWriter().println(n);
		}
		names.stream().map((n) -> {
			return n + "!";
		}).forEach((n) -> {
			System.out.println(n);
		});
		names.forEach((x) -> {
			System.out.println(x);
		});
		*/
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		JavaPairRDD<String, Integer> test = entries.mapToPair((f) -> new Tuple2<>(f.getSubject(), 1));
		JavaPairRDD<String, Integer> count = test.reduceByKey((x, y) -> (int) x + (int) y);
		resp.getWriter().print(count.collect());
		
		//JavaRDD<String> namesRDD = sparkContext.parallelize(names);
		//JavaPairRDD<String, Integer> namesMapper = namesRDD.mapToPair((f) -> new Tuple2<>(f, 1));
		//resp.getWriter().println(namesMapper.collect());
		//JavaPairRDD<String, Integer> countNames = namesMapper.reduceByKey((x, y) -> ((int) x + (int) y));
		//resp.getWriter().println(countNames.collect());
		
	}
}
