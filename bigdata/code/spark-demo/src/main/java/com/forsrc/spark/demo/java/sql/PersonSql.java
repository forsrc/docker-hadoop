package com.forsrc.spark.demo.java.sql;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;


public class PersonSql {
    public static void main(String[] args) {

        System.setProperty("user.name", "root");

        SparkSession sparkSession = SparkSession.builder()
                .appName("forsrc-spark-sql-person")
                .config("spark.some.config.option", "some-value")
                .master("local[2]")
                .getOrCreate();
        String json = PersonSql.class.getClassLoader().getResource("person.json").getFile();
        System.out.println(json);
        Dataset<Row> dataset = sparkSession.read().json(json);
        dataset.createOrReplaceTempView("person");
        Dataset<Row> sqlDataset = sparkSession.sql("SELECT name FROM person WHERE age BETWEEN 22 AND 24");
        sqlDataset.show();

        String csv = PersonSql.class.getClassLoader().getResource("person.csv").getFile();
        JavaRDD<Person> personRDD = sparkSession.read().textFile(csv).javaRDD().map(new Function<String, Person>() {
            //            @Override
            public Person call(String line) throws Exception {
                String[] parts = line.split(",");
                Person person = new Person();
                person.setName(parts[0].trim());
                person.setAge(Integer.parseInt(parts[1].trim()));
                person.setSex(parts[2].trim());
                return person;
            }
        });

        Dataset<Row> peopleDF = sparkSession.createDataFrame(personRDD, Person.class);
        peopleDF.createOrReplaceTempView("people");

        Dataset<Row> teenagersDS = sparkSession.sql("SELECT name, count(*) FROM people WHERE age BETWEEN 13 AND 19 group by name");

        Encoder<String> stringEncoder = Encoders.STRING();
        Dataset<String> teenagerNamesByIndexDS = teenagersDS.map(new MapFunction<Row, String>() {
            //            @Override
            public String call(Row row) throws Exception {
                return "Name: " + row.getString(0);
            }
        }, stringEncoder);
        teenagerNamesByIndexDS.show();

        sparkSession.close();

    }
}
